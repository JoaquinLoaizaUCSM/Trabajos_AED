public class NodeAVL<E extends Comparable<E>> extends Node<E> 
{
    protected int bf; // Balance factor

    public NodeAVL(E data) {
        super(data);
        this.bf = 0; // Initially, the balance factor is 0
    }

    @Override
    public String toString() {
        return data + " (bf = " + bf + ")";
    }
}
