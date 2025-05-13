public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {

    private class Node {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb);
        return sb.toString().trim(); // Eliminamos el espacio final
    }

    private void toStringRec(Node node, StringBuilder sb) {
        if (node != null) {
            toStringRec(node.left, sb);
            sb.append(node.data).append(" ");
            toStringRec(node.right, sb);
        }
    }

    // ejercicio 7,8 y 9
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println("Visitar la raíz: " + node.data);
            inOrder(node.right);
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
}