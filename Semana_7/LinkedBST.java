import Ejercicio1.*;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    public class Node {
        E data;
        Node left, right;

        Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    // Insert, delete, search y toString se irán agregando progresivamente

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null) return new Node(data);

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + data);
        }

        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        return searchRec(root, data);
    }

    private E searchRec(Node node, E data) throws ItemNotFound {
        if (node == null) throw new ItemNotFound("Elemento no encontrado: " + data);

        int cmp = data.compareTo(node.data);

        if (cmp < 0) return searchRec(node.left, data);
        else if (cmp > 0) return searchRec(node.right, data);
        else return node.data;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty("El BST está vacío");
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);

        if (cmp < 0) {
            node.left = deleteRec(node.left, data);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, data);
        } else {
            // Caso 1: Sin hijos
            if (node.left == null && node.right == null) return null;
            // Caso 2: Un hijo
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Caso 3: Dos hijos
            Node minRight = findMin(node.right);
            node.data = minRight.data;
            node.right = deleteRec(node.right, minRight.data);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        toStringRec(root, sb);
//        return sb.toString().trim(); // Eliminamos el espacio final
//    }

    private void toStringRec(Node node, StringBuilder sb) {
        if (node != null) {
            toStringRec(node.left, sb);
            sb.append(node.data).append(" ");
            toStringRec(node.right, sb);
        }
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("Visitar la raíz: " + node.data);
            inOrder(node.right);
        } else {
            System.out.println("Vacío");
        }
    }


    public void preOrder() {
    if (root != null) {
        System.out.println("Visitar la raíz: " + root.data);
        preOrder(root.left);
        preOrder(root.right);
    } else {
        System.out.println("Vacío");
        }
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.println("Visitar la raíz: " + node.data);
            preOrder(node.left);
            preOrder(node.right);
        } else {
            System.out.println("Vacío");
        }
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println("Visitar la raíz: " + node.data);
        } else {
            System.out.println("Vacío");
        }
    }


     // EJERCICIO 1 

    // a. Eliminar todos los nodos del arbol
    public void destroyNodes() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("El arbol ya esta vacio");
        }
        root = null;
    }

    // b. Contar nodos internos es decir nodos NO hoja
    public int countAllNodes() 
    {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node node) 
    {
        // Si el nodo es nulo o es hoja, no contamos
        if (node == null || (node.left == null && node.right == null)) 
        {
            return 0;
        }
        // Si no es hoja, contamos 1 y seguimos con los hijos
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }


    // d. Altura de un subarbol con raiz x (iterativo)
    public int height(E x) throws ExceptionIsEmpty 
    {
        Node start = findNodeIteratively(root, x);  // Busca el nodo con valor x en el arbol de forma iterativa
        if (start == null) return -1; // Si no encuentra el nodo, retorna -1
        return calculateHeightIteratively(start); // Calcula la altura del subarbol a partir del nodo encontrado
    }

    private Node findNodeIteratively(Node node, E x) 
    {
        while (node != null) 
        {
            int cmp = x.compareTo(node.data);
            if (cmp == 0) { return node; }
            else if (cmp < 0) { node = node.left; }
            else {node = node.right;}
        }
        return null;
    }

    private int calculateHeightIteratively(Node node) throws ExceptionIsEmpty 
    {
        if (node == null) return -1; // Si el nodo es nulo, la altura es -1

        QueueLink<Node> queue = new QueueLink<>(); // Crea una cola para recorrer el arbol por niveles
        queue.enqueue(node); // Encola el nodo raiz, el primero
        int height = -1;  // Empieza con una altura de -1

        while (!queue.isEmpty()) 
        {
            int size = queue.size();  // Obtiene el tamaño de la cola (numero de nodos en el nivel ACTUAL)
            height++;
            for (int i = 0; i < size; i++) 
            {
                Node current = queue.dequeue();  //Desencola el nodo actual
                if (current.left != null) queue.enqueue(current.left); // Si tiene hijo izquierdo, lo agrega a la cola
                if (current.right != null) queue.enqueue(current.right); // Si tiene hijo derecho, lo agrega a la cola
            }
        }

        return height;
    }

    // e. Amplitud: cantidad de nodos en un nivel dado
    public int amplitude(int nivel) 
    {
        return countNodesAtLevel(root, nivel);
    }

    private int countNodesAtLevel(Node node, int targetLevel) 
    {
        if (node == null || targetLevel < 0) return 0; // Si el nodo es nulo o el nivel es negativo, es pq no hay nodos

        QueueLink<Node> queue = new QueueLink<>(); //Cola para recorrer los niveles
        QueueLink<Integer> levels = new QueueLink<>(); // Cola para llevar el nivel de cada nodo
        queue.enqueue(node); // Encola el nodo raiz, el primero
        levels.enqueue(0);  // Encola el nivel del nodo raíz (0)
        int count = 0;

        while (!queue.isEmpty()) 
        {
            Node current = queue.dequeue(); //Desencola el nodo actual
            int currentLevel = levels.dequeue(); // Desencola el nivel del nodo actual

            // Imprimir para depuracion
            System.out.println("Nodo: " + current.data + ", Nivel: " + currentLevel);

            if (currentLevel == targetLevel) // Si el nivel actual es el que buscamos, incrementa el contador
            {
                count++;
            }

            if (current.left != null) { // Si tiene hijo izquierdo, lo agrega a la cola
                queue.enqueue(current.left); 
                levels.enqueue(currentLevel + 1); // El hijo izquierdo esta UN NIVEL MAS PROFUNDO
            }

            if (current.right != null) {// Si tiene hijo derecho, lo agrega a la cola
                queue.enqueue(current.right); 
                levels.enqueue(currentLevel + 1); // El hijo derecho tambien esta UN NIVEL MAS PROFUNDO
            }
        }

        return count;
    }


    // EJERCICIO 2

    public int areaBST() throws ExceptionIsEmpty{
        return calculateHeightIteratively(root) * countAllNodes(); 
    }

    
    /** Imprime el árbol con la raíz arriba y las hojas al fondo,
     *  usando una representación ASCII parecida a los diagramas clásicos. */
    public void drawBST() {
        if (root == null) {
            System.out.println("<Árbol vacío>");
            return;
    }

    // 1. altura del árbol
    int h = 0;
    try { 
        h = calculateHeightIteratively(root); 
    }          
    catch (ExceptionIsEmpty e) { /* nunca llega aquí */ }

    // 2. ancho total: 2^(h+1) − 1 “posiciones” (nodos + huecos)
    int maxWidth = (1 << (h + 1)) - 1;

    // 3. recorrido nivel‑por‑nivel
    QueueLink<Node> q  = new QueueLink<>();
    QueueLink<Integer> lvl = new QueueLink<>();
    q.enqueue(root);
    lvl.enqueue(0);

    int currLevel = 0;
    /* ancho actual entre nodos = maxWidth; se reducirá a la mitad en cada piso */
    int between = maxWidth;                              

    // Buffers para imprimir las dos líneas de cada nivel
    StringBuilder nodesLine = new StringBuilder();
    StringBuilder armsLine  = new StringBuilder();

    while (!q.isEmpty()) {
        Node n = q.dequeue();
        int  l = lvl.dequeue();

        if (l != currLevel) {              // ─ cambio de nivel ─
            System.out.println(nodesLine); // 1ª línea con nodos
            System.out.println(armsLine);  // 2ª línea con aristas
            nodesLine.setLength(0);
            armsLine.setLength(0);
            currLevel = l;
            between  >>= 1;                // la separación se parte a la mitad
        }

        // nodos: imprimimos “between/2” espacios antes, el valor, y “between/2” espacios después
        int pad = between >> 1;
        appendSpaces(nodesLine, pad);
        nodesLine.append(n == null ? " " : n.data.toString());
        appendSpaces(nodesLine, pad);

        // aristas para el siguiente nivel
        if (between > 1) {                 // sólo si todavía hay espacio para dibujarlas
            appendSpaces(armsLine, pad);
            if (n != null && n.left != null)  armsLine.append('/');
            else                              armsLine.append(' ');
            appendSpaces(armsLine, between - pad - 1);
            if (n != null && n.right != null) armsLine.append('\\');
            else                              armsLine.append(' ');
            appendSpaces(armsLine, pad);
        }

        /* Encolar hijos para el siguiente nivel (o nulos para mantener el ancho) */
        if (n == null) {
            q.enqueue(null); q.enqueue(null);
        } else {
            q.enqueue(n.left);  q.enqueue(n.right);
        }
        lvl.enqueue(l + 1);  lvl.enqueue(l + 1);

        /* Cuando llegamos al último posible nivel ya no hace falta seguir */
        if (l + 1 > h) break;
    }
        // Imprime el último nivel acumulado
        System.out.println(nodesLine);
        }

    /* ---------- Pequeña ayuda ---------- */
    private void appendSpaces(StringBuilder sb, int n) {
        for (int i = 0; i < n; i++) sb.append(' ');
    }



    // Ejercicio 3

    /**
     * Busca un nodo por valor en la estructura jerárquica usando recorrido DFS (child-sibling).
     */
    public Node searchNode(E x) throws ItemNotFound {
        Node result = searchNodeDFS(root, x);
        if (result == null) {
            throw new ItemNotFound("Dato no encontrado");
        }
        return result;
    }

    /**
     * Búsqueda recursiva en child-sibling: left = primero hijo, right = siguiente hermano.
     */
    private Node searchNodeDFS(Node node, E x) {
        if (node == null) return null;
        if (node.data.equals(x)) return node;
        Node found = searchNodeDFS(node.left, x);   // buscar en hijos
        if (found != null) return found;
        return searchNodeDFS(node.right, x);         // buscar en hermanos
    }

    public void parenthesize() {
    parenthesize(root, 0);          // raíz, nivel de sangría 0
    }

    private void parenthesize(Node node, int depth) {
        if (node == null) return;

        indent(depth);
        System.out.print(node.data);

        if (node.left != null) {        // ¿al menos un hijo?
            System.out.println(" (");
            /* Recorremos TODOS los hijos
            1º = node.left, los demás => cadena por right */
            Node child = node.left;
            while (child != null) {
                parenthesize(child, depth + 1);
                child = child.right;    // siguiente hermano
            }
            indent(depth);
            System.out.println(")");
        } else {
            System.out.println();       // hoja → salto de línea
        }
    }

    /* Imprime 2·depth espacios */
    private void indent(int depth) {
        for (int i = 0; i < depth; i++) System.out.print("  ");
    }


    public Node addChild(Node parent, E childData) {
        Node child = new Node(childData);
        if (parent.left == null) {
            parent.left = child;             // primer hijo
        } else {
            // recorremos hasta el último hermano
            Node sibling = parent.left;
            while (sibling.right != null) sibling = sibling.right;
            sibling.right = child;           // lo encadenamos al final
        }
        return child;
    }
}