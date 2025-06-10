package Semana_9.graph;

import Semana_9.list.Node;

/**
 * Implementación propia de PriorityQueue usando enlaces ordenados
 */
public class PriorityQueueLink<T extends Comparable<T>> {
    private Node<T> head;
    private int size;

    public PriorityQueueLink() {
        this.head = null;
        this.size = 0;
    }

    public void offer(T data) {
        Node<T> newNode = new Node<>(data);
        
        // Si la cola está vacía o el nuevo elemento tiene mayor prioridad que el primero
        if (head == null || data.compareTo(head.getData()) < 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }

        // Buscar la posición correcta para insertar
        Node<T> current = head;
        while (current.getNext() != null && data.compareTo(current.getNext().getData()) >= 0) {
            current = current.getNext();
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        
        T data = head.getData();
        head = head.getNext();
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}
