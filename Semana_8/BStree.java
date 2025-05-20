public abstract class BStree<E extends Comparable<E>> 
{
    protected Node<E> root;

    public BStree() {
        root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public abstract void insert(E x);
    public abstract void delete(E x);
    
    // Other BST methods like search, etc., can be added if needed
}
