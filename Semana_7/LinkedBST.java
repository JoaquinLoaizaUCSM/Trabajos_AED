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
}