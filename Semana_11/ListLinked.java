package Semana_11;

public class ListLinked<E> {
    private Node<E> head;
    private int size;
    
    public ListLinked() {
        this.head = null;
        this.size = 0;
    }
    
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }
    
    public boolean remove(E data) {
        if (head == null) return false;
        
        if (head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return true;
        }
        
        Node<E> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    
    public boolean contains(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void destroyList() {
        head = null;
        size = 0;
    }

    public int searchIndex(E x) {
        Node<E> current = head;
        int index = 0;
        while (current != null) {
            if (current.getData().equals(x)) {
                return index; // Element found at index
            }
            current = current.getNext();
            index++;
        }
        return -1; // Element not found
    }

    public E searchDate(E data) {
        Node<E> current = head;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current.getData(); // Element found
            }
            current = current.getNext();
        }
        return null; // Element not found
    }
    
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
    
    public Node<E> getHead() { return head; }

    public void setHead(Node<E> next) {
        this.head = (Node<E>) next;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }
}