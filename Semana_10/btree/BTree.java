package Semana_10.btree;

import java.util.ArrayList;

public class BTree<E extends Comparable<E>> { 
    private BNode<E> root; 
    private int orden; 
 
    private boolean up; 
    private BNode<E> nDes; 
    
    public BTree(int orden){ 
        this.orden = orden; 
        this.root = null; 
    } 
     
    public boolean isEmpty(){ 
        return this.root == null; 
    } 
     
    public void insert(E cl){ 
        up = false; 
        E mediana; 
        BNode<E> pnew; 
        mediana = push(this.root,cl); 
 
        if(up){ 
            pnew = new BNode<E>(this.orden); 
            pnew.count = 1; 
            pnew.keys.set(0,mediana); 
            pnew.childs.set(0,this.root); 
            pnew.childs.set(1,nDes); 
            this.root = pnew; 
        } 
    } 
 
    private E push(BNode<E> current,E cl){  
        int pos[] = new int[1]; 
        E mediana;

        if(current == null){ 
            up = true; 
            nDes = null; 
            return cl; 
        } 
        else{ 
            boolean fl; 
            fl = current.searchNode(cl, pos); 
        
            if(fl){ 
                System.out.println("Item duplicado\n"); 
                up = false; 
                return null; 
            } 
        
            mediana = push(current.childs.get(pos[0]),cl); 
            
            if(up){ 
                if(current.nodeFull(this.orden-1)) 
                    mediana = dividedNode(current,mediana,pos[0]); 
                else{ 
                    up = false; 
                putNode(current,mediana,nDes,pos[0]); 
                } 
            } 
            return mediana; 
        }
    }

    private void putNode(BNode<E> current,E cl,BNode<E> rd,int k){
        int i; 
        for(i = current.count-1; i >= k; i--) {   
            current.keys.set(i+1,current.keys.get(i)); 
            current.childs.set(i+2,current.childs.get(i+1)); 
        } 
        current.keys.set(k,cl);   
        current.childs.set(k+1,rd); 
        current.count++; 
    }

    private E dividedNode(BNode<E> current,E cl,int k){ 
        BNode<E> rd = nDes;  
        int i, posMdna; 
        posMdna = (k <= this.orden/2) ? this.orden/2 : this.orden/2+1;  
 
        nDes = new BNode<E>(this.orden);

        for(i = posMdna; i < this.orden-1; i++) { 
            nDes.keys.set(i-posMdna,current.keys.get(i)); 
            nDes.childs.set(i-posMdna+1,current.childs.get(i+1)); 
        } 
 
        nDes.count = (this.orden - 1) - posMdna;  
        current.count = posMdna; 
 
        if(k <= this.orden/2)    
            putNode(current,cl,rd,k); 
        else   
            putNode(nDes,cl,rd,k-posMdna); 
 
        E median = current.keys.get(current.count-1);     
        nDes.childs.set(0,current.childs.get(current.count));  
        current.count--; 
        
        return median; 
    }

    // Ejercicio 1
    public boolean search(E cl) {
        BNode<E> current = this.root;
        int[] pos = new int[1];

        while (current != null) {
            if (current.searchNode(cl, pos)) {
                System.out.println(cl + " se encuentra en el nodo " + current.idNode + " en la posición " + pos[0]);
                return true;
            }
            current = current.childs.get(pos[0]);
        }
        return false;
    }

    // Ejercicio 2
    private boolean underflow;

    public void remove(E cl) {
        if (this.root == null) {
            System.out.println("BTree is empty");
            return;
        }
        underflow = false;
        removeRecursive(this.root, cl);
        if (underflow && this.root.count == 0) {
            this.root = this.root.childs.get(0);
        }
    }

    private void removeRecursive(BNode<E> current, E cl) {
        int[] pos = new int[1];
        if (current == null) {
            underflow = false;
            return;
        }
        boolean found = current.searchNode(cl, pos);
        if (found) {
            if (current.childs.get(pos[0]) == null) { // Es hoja
                removeKeyFromNode(current, pos[0]);
                underflow = current.count < (orden - 1) / 2;
            } else { // No es hoja
                findSuccessorAndRemove(current, pos[0]);
                if (underflow) {
                    restore(current, pos[0]);
                }
            }
        } else {
            removeRecursive(current.childs.get(pos[0]), cl);
            if (underflow) {
                restore(current, pos[0]);
            }
        }
    }

    private void removeKeyFromNode(BNode<E> node, int pos) {
        for (int i = pos; i < node.count - 1; i++) {
            node.keys.set(i, node.keys.get(i + 1));
        }
        node.keys.set(node.count - 1, null);
        node.count--;
    }

    private void findSuccessorAndRemove(BNode<E> current, int pos) {
        BNode<E> successorNode = current.childs.get(pos + 1);
        while (successorNode.childs.get(0) != null) {
            successorNode = successorNode.childs.get(0);
        }
        E successorKey = successorNode.keys.get(0);
        current.keys.set(pos, successorKey);
        removeRecursive(current.childs.get(pos + 1), successorKey);
    }

    private void restore(BNode<E> parent, int childPos) {
        if (childPos > 0 && parent.childs.get(childPos - 1).count > (orden - 1) / 2) {
            moveRight(parent, childPos);
        } else if (childPos < parent.count && parent.childs.get(childPos + 1).count > (orden - 1) / 2) {
            moveLeft(parent, childPos);
        } else {
            if (childPos > 0) {
                merge(parent, childPos - 1);
            } else {
                merge(parent, childPos);
            }
        }
    }

    private void moveRight(BNode<E> parent, int pos) {
        BNode<E> underflowNode = parent.childs.get(pos);
        BNode<E> leftSibling = parent.childs.get(pos - 1);
        underflow = false;

        for (int i = underflowNode.count; i > 0; i--) {
            underflowNode.keys.set(i, underflowNode.keys.get(i - 1));
            underflowNode.childs.set(i + 1, underflowNode.childs.get(i));
        }
        underflowNode.childs.set(1, underflowNode.childs.get(0));
        underflowNode.count++;
        underflowNode.keys.set(0, parent.keys.get(pos - 1));
        parent.keys.set(pos - 1, leftSibling.keys.get(leftSibling.count - 1));
        underflowNode.childs.set(0, leftSibling.childs.get(leftSibling.count));
        leftSibling.count--;
    }

    private void moveLeft(BNode<E> parent, int pos) {
        BNode<E> underflowNode = parent.childs.get(pos);
        BNode<E> rightSibling = parent.childs.get(pos + 1);
        underflow = false;

        underflowNode.keys.set(underflowNode.count, parent.keys.get(pos));
        underflowNode.count++;
        parent.keys.set(pos, rightSibling.keys.get(0));
        underflowNode.childs.set(underflowNode.count, rightSibling.childs.get(0));
        rightSibling.count--;
        for (int i = 0; i < rightSibling.count; i++) {
            rightSibling.keys.set(i, rightSibling.keys.get(i + 1));
            rightSibling.childs.set(i, rightSibling.childs.get(i + 1));
        }
        rightSibling.childs.set(rightSibling.count, rightSibling.childs.get(rightSibling.count + 1));
    }

    private void merge(BNode<E> parent, int pos) {
        BNode<E> leftNode = parent.childs.get(pos);
        BNode<E> rightNode = parent.childs.get(pos + 1);

        leftNode.keys.set(leftNode.count, parent.keys.get(pos));
        leftNode.count++;

        for (int i = 0; i < rightNode.count; i++) {
            leftNode.keys.set(leftNode.count, rightNode.keys.get(i));
            leftNode.childs.set(leftNode.count, rightNode.childs.get(i));
            leftNode.count++;
        }
        leftNode.childs.set(leftNode.count, rightNode.childs.get(rightNode.count));

        for (int i = pos; i < parent.count - 1; i++) {
            parent.keys.set(i, parent.keys.get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.count--;
        underflow = parent.count < (orden - 1) / 2;
    }

    public String toString(){
        String s = "";
        if (isEmpty())
            s += "BTree is empty...";
        else
            s = writeTree(this.root);
        return s;
    }

    private String writeTree(BNode<E> current){
        // StringBuilder para construir la tabla completa
        StringBuilder table = new StringBuilder();
        
        // Encabezados de la tabla
        table.append(String.format("%-10s %-15s %-12s %-15s\n", 
                                "Id.Nodo", "Claves Nodo", "Id.Padre", "Id.Hijos"));
        
        // Lista para almacenar información de todos los nodos
        ArrayList<NodeTableRow> rows = new ArrayList<>();
        
        // Recolectar información de todos los nodos
        collectNodeInfo(current, null, rows);
        
        // Ordenar por ID de nodo
        rows.sort((a, b) -> Integer.compare(a.nodeId, b.nodeId));
        
        // Construir las filas de la tabla
        for (NodeTableRow row : rows) {
            table.append(String.format("%-10d %-15s %-12s %-15s\n", 
                                    row.nodeId, row.keys, row.parentId, row.childIds));
        }
        
        return table.toString();
    }

    // Clase auxiliar para almacenar información de cada fila
    private class NodeTableRow {
        int nodeId;
        String keys;
        String parentId;
        String childIds;
        
        NodeTableRow(int nodeId, String keys, String parentId, String childIds) {
            this.nodeId = nodeId;
            this.keys = keys;
            this.parentId = parentId;
            this.childIds = childIds;
        }
    }

    // Método auxiliar para recolectar información de nodos recursivamente
    private void collectNodeInfo(BNode<E> current, BNode<E> parent, 
                                ArrayList<NodeTableRow> rows) {
        if (current == null) return;
        
        // Construir string de claves
        StringBuilder keysStr = new StringBuilder("(");
        for (int i = 0; i < current.count; i++) {
            keysStr.append(current.keys.get(i));
            if (i < current.count - 1) {
                keysStr.append(", ");
            }
        }
        keysStr.append(")");
        
        // ID del padre
        String parentIdStr = (parent == null) ? "--" : "[" + parent.idNode + "]";
        
        // IDs de los hijos
        StringBuilder childrenStr = new StringBuilder();
        boolean hasChildren = false;
        
        for (int i = 0; i <= current.count; i++) {
            if (current.childs.get(i) != null) {
                if (!hasChildren) {
                    childrenStr.append("[");
                    hasChildren = true;
                } else {
                    childrenStr.append(", ");
                }
                childrenStr.append(current.childs.get(i).idNode);
            }
        }
        
        if (hasChildren) {
            childrenStr.append("]");
        } else {
            childrenStr.append("--");
        }
        
        // Agregar fila a la lista
        rows.add(new NodeTableRow(current.idNode, keysStr.toString(), 
                                parentIdStr, childrenStr.toString()));
        
        // Recursión para los hijos
        for (int i = 0; i <= current.count; i++) {
            collectNodeInfo(current.childs.get(i), current, rows);
        }
    }




    public static void main(String[] args) {
        BTree<Integer> btree = new BTree<>(3);
        btree.insert(10);
        btree.insert(20);
        btree.insert(5);
        btree.insert(6);
        btree.insert(12);
        btree.insert(30);
        btree.insert(7);
        btree.insert(17);
        btree.insert(52);

        System.out.println(btree);

        System.out.println("\nBuscando el 7:");
        System.out.println("Resultado: " + btree.search(7));

        System.out.println("\nBuscando el 52:");
        System.out.println("Resultado: " + btree.search(52));

        System.out.println("\n--- Pruebas de Eliminación ---");

        System.out.println("\nÁrbol original:");
        System.out.println(btree);

        System.out.println("\nEliminando el 17 (caso hoja simple):");
        btree.remove(17);
        System.out.println(btree);

        System.out.println("\nEliminando el 7 (causa redistribución):");
        btree.remove(7);
        System.out.println(btree);
        
        System.out.println("\nEliminando el 12 (causa fusión):");
        btree.remove(12);
        System.out.println(btree);

        System.out.println("\nEliminando el 10 (clave interna, causa fusión y cambio de raíz):");
        btree.remove(10);
        System.out.println(btree);
    }
}