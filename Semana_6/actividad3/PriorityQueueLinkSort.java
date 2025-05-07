package actividad3;

import actividad1.ExceptionIsEmpty;
import actividad2.Node;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {

    class EntryNode {
        E data;
        N priority;

        EntryNode(E data, N priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x, N pr) {
        EntryNode nuevo = new EntryNode(x, pr);
        Node<EntryNode> nuevoNodo = new Node<>(nuevo);

        if (isEmpty()) {
            first = last = nuevoNodo;
            return;
        }

        if (pr.compareTo(first.getData().priority) > 0) {
            nuevoNodo.setNext(first);
            first = nuevoNodo;
            return;
        }

        Node<EntryNode> current = first;
        while (current.getNext() != null && pr.compareTo(current.getNext().getData().priority) <= 0) {
            current = current.getNext();
        }

        nuevoNodo.setNext(current.getNext());
        current.setNext(nuevoNodo);

        if (nuevoNodo.getNext() == null) {
            last = nuevoNodo;
        }
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("No se puede eliminar: la cola está vacía.");
        }
        E aux = first.getData().data;
        first = first.getNext();
        if (first == null) {
            last = null;
        }
        return aux;
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return last.getData().data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return first.getData().data;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<EntryNode> current = first;
        while (current != null) {
            sb.append(current.getData().data)
                    .append(" (p:")
                    .append(current.getData().priority)
                    .append(")");
            if (current.getNext() != null) sb.append(" -> ");
            current = current.getNext();
        }
        return sb.toString();
    }
}