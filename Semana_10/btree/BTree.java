package Semana_10.btree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Semana_10.RegistroEstudiante;
import Semana_10.list.ListLinked;
import Semana_10.list.Node;

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

    /* Ejercicio 3*/
    public static BTree<Integer> building_Btree(String path) throws ItemNoFound {

        /* ---------- 1. Lectura secuencial de las líneas ---------- */
        BufferedReader in;
        try { in = new BufferedReader(new FileReader(path)); 
        }
        catch (IOException ex) { throw new ItemNoFound("No se puede abrir el archivo"); 
        }

        String linea;
        int numeroLinea = 0;                // 0‑based
        int orden = 0;                      // se leerá en la línea 0

        ListLinked<BNode<Integer>> nodos = new ListLinked<>();   // nodos en orden de aparición

        try {
            while ((linea = in.readLine()) != null) {
                linea = linea.trim();
                if (linea.length() == 0) continue;               // ignorar líneas vacías

                /* -- Línea 0: sólo el orden del B‑Tree -- */
                if (numeroLinea == 0) {
                    orden = Integer.parseInt(linea);
                    if (orden < 3) throw new ItemNoFound("Orden inválido (<3)");
                    numeroLinea++;
                    continue;
                }

                /* -- Resto de líneas:  nivel,id,clave1,clave2,… -- */
                String[] partes = linea.split(",");
                if (partes.length < 3)                     // nivel, id, al menos 1 clave
                    throw new ItemNoFound("Formato incorrecto en línea " + (numeroLinea + 1));

                /* datos de cabecera */
                int idNodo    = Integer.parseInt(partes[1].trim());

                /* construir nodo “vacío” del tamaño adecuado */
                BNode<Integer> nodo = new BNode<>(orden);
                nodo.idNode = idNodo;

                /* cargar claves */
                int k = 0;
                for (int i = 2; i < partes.length; i++) {
                    int clave = Integer.parseInt(partes[i].trim());
                    nodo.keys.set(k, clave);   // la lista internamente ya trae ‘null’s
                    k++;
                }
                nodo.count = k;                // nº de claves realmente almacenadas

                nodos.add(nodo);               // queda registrado manteniendo el orden
                numeroLinea++;
            }
            in.close();
        } catch (IOException ex) { throw new ItemNoFound("Error leyendo el archivo"); }

        /* ---------- 2. Verificaciones básicas ---------- */
        if (orden == 0)               throw new ItemNoFound("El archivo está vacío");
        if (nodos.isEmpty())          return new BTree<>(orden);      // árbol vacío

        /* ---------- 3. Enlazado padre‑hijo SIN ArrayList/Queue ---------- *
         *    El archivo está en recorrido por niveles (raíz, nivel 1, …).
         *    Avanzamos con dos índices sobre la lista enlazada:
         *       parentIdx:  nodo que actúa como padre
         *       nextChild:  próximo nodo “libre” que tocará enlazar como hijo
         */
        int totalNodos = nodos.size();
        int parentIdx  = 0;
        int nextChild  = 1;                          // el 0 ya es la raíz

        BTree<Integer> arbol = new BTree<>(orden);
        arbol.root = elementoEn(nodos, 0);           // función auxiliar – acceso O(n)

        while (parentIdx < totalNodos && nextChild < totalNodos) {
            BNode<Integer> padre = elementoEn(nodos, parentIdx);

            int hijosNecesarios = padre.count + 1;   // regla del B‑Tree
            for (int h = 0; h < hijosNecesarios && nextChild < totalNodos; h++) {
                BNode<Integer> hijo = elementoEn(nodos, nextChild);
                padre.childs.set(h, hijo);           // posición ‘h’
                nextChild++;
            }
            parentIdx++;
        }

        if (nextChild != totalNodos)   // quedaron sueltos o faltaron
            throw new ItemNoFound("Estructura inconsistente padre‑hijo");

        /* ---------- 4. Validación exhaustiva ---------- */
        int[] nivelHoja = {-1};                      // se define una vez; se “pasa por referencia”
        validarRec(arbol.root, null, null, 0, nivelHoja, orden);

        return arbol;                                // ¡Árbol correcto!
    }

    /** Devuelve el elemento de una ListLinked en la posición ‘idx’*/
    private static <T> T elementoEn(ListLinked<T> lista, int idx) {
        Node<T> p = lista.getHead();
        int i = 0;
        while (p != null) {
            if (i == idx) return p.getData();
            p = p.getNext();
            i++;
        }
        return null;    // nunca debería ocurrir si los índices son válidos
    }

    /** Validación recursiva de todas las propiedades de un B‑Tree. */
    private static void validarRec(BNode<Integer> n,
                                   Integer min, Integer max,
                                   int nivel,
                                   int[] nivelHoja,
                                   int orden) throws ItemNoFound {

        /* 4.1 – Claves ordenadas y en rango */
        for (int i = 0; i < n.count - 1; i++)
            if (n.keys.get(i) >= n.keys.get(i + 1))
                throw new ItemNoFound("Claves desordenadas en nodo " + n.idNode);

        if (min != null && n.keys.get(0) <= min)
            throw new ItemNoFound("Clave fuera de rango en nodo " + n.idNode);
        if (max != null && n.keys.get(n.count - 1) >= max)
            throw new ItemNoFound("Clave fuera de rango en nodo " + n.idNode);

        /* 4.2 – Cantidad de claves */
        int minKeys = (orden % 2 == 0) ? (orden / 2 - 1)       // ⌈(m/2)⌉‑1
                                       : ((orden - 1) / 2);
        int maxKeys = orden - 1;

        if ((nivel == 0 && n.count == 0) ||   // raíz debe tener ≥1
            (nivel > 0  && (n.count < minKeys || n.count > maxKeys)))
            throw new ItemNoFound("Tamaño inválido en nodo " + n.idNode);

        /* 4.3 – ¿Hoja?  entonces todas las hojas al mismo nivel */
        boolean esHoja = (n.childs.get(0) == null);
        if (esHoja) {
            if (nivelHoja[0] == -1)         nivelHoja[0] = nivel;
            else if (nivelHoja[0] != nivel) throw new ItemNoFound("Hojas en niveles distintos");
            return;                         // nada más que revisar
        }

        /* 4.4 – Propiedades recursivas en los hijos */
        if (n.count + 1 > orden)
            throw new ItemNoFound("Demasiados hijos en nodo " + n.idNode);

        for (int i = 0; i <= n.count; i++) {
            BNode<Integer> hijo = n.childs.get(i);
            if (hijo == null)
                throw new ItemNoFound("Hijo faltante en nodo " + n.idNode);

            Integer nuevoMin = (i == 0)         ? min
                               : n.keys.get(i - 1);
            Integer nuevoMax = (i == n.count)   ? max
                               : n.keys.get(i);
            validarRec(hijo, nuevoMin, nuevoMax, nivel + 1, nivelHoja, orden);
        }
    }

    //Ejercicio 4
    public String buscarNombre(int codigo) {
        RegistroEstudiante claveBusqueda = new RegistroEstudiante(codigo, "");
        BNode<E> current = this.root;
        int[] pos = new int[1];

        while (current != null) {
            if (current.searchNode((E) claveBusqueda, pos)) {
                RegistroEstudiante encontrado = (RegistroEstudiante) current.keys.get(pos[0]);
                return encontrado.getNombre();
            }
            current = current.childs.get(pos[0]);
        }
        return "No encontrado";
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

        try {
            BTree<Integer> t = BTree.building_Btree("Semana_10/arbolB.txt");
            System.out.println(t);
        } catch (ItemNoFound ex) {
            System.err.println("Árbol inválido: " + ex.getMessage());
        }

          System.out.println("\n--- Ejercicio 4: BTree con RegistroEstudiante ---");
        BTree<RegistroEstudiante> arbolEstudiantes = new BTree<>(4);

        // Insertar estudiantes
        arbolEstudiantes.insert(new RegistroEstudiante(103, "Ana"));
        arbolEstudiantes.insert(new RegistroEstudiante(110, "Luis"));
        arbolEstudiantes.insert(new RegistroEstudiante(101, "Carlos"));
        arbolEstudiantes.insert(new RegistroEstudiante(120, "Lucía"));
        arbolEstudiantes.insert(new RegistroEstudiante(115, "David"));
        arbolEstudiantes.insert(new RegistroEstudiante(125, "Jorge"));
        arbolEstudiantes.insert(new RegistroEstudiante(140, "Camila"));
        arbolEstudiantes.insert(new RegistroEstudiante(108, "Rosa"));
        arbolEstudiantes.insert(new RegistroEstudiante(132, "Ernesto"));
        arbolEstudiantes.insert(new RegistroEstudiante(128, "Denis"));
        arbolEstudiantes.insert(new RegistroEstudiante(145, "Enrique"));
        arbolEstudiantes.insert(new RegistroEstudiante(122, "Karina"));
        arbolEstudiantes.insert(new RegistroEstudiante(108, "Juan"));  // Duplicado, no se insertará

        System.out.println("\nBuscar código 115: " + arbolEstudiantes.buscarNombre(115));  // David
        System.out.println("Buscar código 132: " + arbolEstudiantes.buscarNombre(132));  // Ernesto
        System.out.println("Buscar código 999: " + arbolEstudiantes.buscarNombre(999));  // No encontrado

        System.out.println("\nEliminar código 101 (Carlos)");
        arbolEstudiantes.remove(new RegistroEstudiante(101, ""));
        System.out.println(arbolEstudiantes);

        System.out.println("\nInsertar nuevo estudiante (106, Sara)");
        arbolEstudiantes.insert(new RegistroEstudiante(106, "Sara"));
        System.out.println("Buscar código 106: " + arbolEstudiantes.buscarNombre(106));  // Sara

        System.out.println("\nÁrbol final:");
        System.out.println(arbolEstudiantes);
        
    }
}