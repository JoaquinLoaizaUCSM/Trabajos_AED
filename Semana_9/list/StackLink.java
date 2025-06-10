package Semana_9.list;

/**
 * Implementación propia de Stack usando enlaces
 */
public class StackLink<T> {
    private Node<T> top;

    public StackLink() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.setNext(top);
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack vacío");
        }
        T data = top.getData();
        top = top.getNext();
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack vacío");
        }
        return top.getData();
    }

    public int size() {
        int count = 0;
        Node<T> current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
}
