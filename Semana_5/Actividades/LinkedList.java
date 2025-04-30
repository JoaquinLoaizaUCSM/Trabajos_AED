package Actividades;

public class LinkedList<T extends Comparable<T>> implements TDAList<T> {
    public Node<T> first;
    protected int size;

    public LinkedList() {
        this.first = null;
        size = 0;
    }

    @Override
    public void insertFirst(T x) {
        Node<T> nuevo = new Node<T>(x);
        nuevo.setNext(first);
        first = nuevo;
        size++;
    }

    @Override
    public boolean isEmptyList() {
        return (first == null);
    }

    @Override
    public void insertLast(T x) {
        if (isEmptyList()) {
            insertFirst(x);
        } else {
            Node<T> nuevo = new Node<T>(x);
            Node<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(nuevo);
            size++;
        }
    }

    @Override
    public int length() {
        return size;
    }

    @Override
    public void destroyList() {
        first = null;
        size = 0;
    }

    @Override
    public int search(T x) {
        Node<T> current = first;
        while (current != null) {
            if (current.getData().equals(x)) {
                return 1; // Elemento encontrado
            }
            current = current.getNext();
        }
        return -1; // Elemento no encontrado
    }

    @Override
    public void removeNode(T x) {
        if (isEmptyList()) {
            System.out.println("La lista esta vacia");
            return;
        }

        if (first.getData().equals(x)) {
            first = first.getNext();
            size--;
            return;
        }

        Node<T> current = first.getNext();
        Node<T> prev = first;
        while (current != null) {
            if (current.getData().equals(x)) {
                prev.setNext(current.getNext());
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }

        System.out.println("El elemento no existe en la lista.");
    }
}
