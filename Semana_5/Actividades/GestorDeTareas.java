package Actividades;

public class GestorDeTareas<T extends Comparable<T>> {
    private Node<T> first;

    public GestorDeTareas(){
        this.first = null;
    }

    void agregarTarea(T tarea) { // se agrega una tarea al final de la lista
        // creo nodo
        Node<T> nuevoNodo = new Node<>(tarea);

        // 1. Si la lista está vacía
        if (first == null) {
            first = nuevoNodo;
        } else {
            // 2. Si no está vacía: recorrer hasta el final
            Node<T> current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = nuevoNodo;
        }
    }

    boolean eliminarTarea(T tarea) {
        if (first == null) {
            return false; // La lista está vacía
        }

        // Caso especial: si la tarea está en el primer nodo
        if (first.data.equals(tarea)) {
            first = first.next; // Eliminamos el primer nodo
            return true;
        }

        // Caso general: buscar el nodo antes del que queremos eliminar
        Node<T> current = first;
        while (current.next != null && !current.next.data.equals(tarea)) {
            current = current.next;
        }

        // Si encontramos la tarea
        if (current.next != null) {
            current.next = current.next.next; // Eliminamos el nodo
            return true;
        }

        // Si no encontramos la tarea
        return false;
    }

    boolean contieneTarea(T tarea) {
        Node<T> current = first;
        while (current != null) {
            if (current.data.equals(tarea)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    void imprimirTareas() {
        Node<T> current = first;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    int contarTareas() {
        int contador = 0;
        Node<T> current = first;
        while (current != null) {
            contador++;
            current = current.next;
        }
        return contador;
    }

    public T obtenerTareaMasPrioritaria() {
        if (first == null) {
            return null;
        }
        Node<T> current = first;
        T tareaMaxima = first.data;
        while (current != null) {
            if (current.data.compareTo(tareaMaxima) > 0) {
                tareaMaxima = current.data;
            }
            current = current.next;
        }
        return tareaMaxima;
    }

    // Invertir es solo cambiar el sentido de las flechas (next)
    // entre los nodos. No creamos nodos nuevos.
    void invertirTareas() {
        Node<T> anterior = null;
        Node<T> actual = first;
        Node<T> siguiente;

        while (actual != null) {
            siguiente = actual.next;   // Guardamos el siguiente
            actual.next = anterior;    // Invertimos el enlace
            anterior = actual;         // Movemos anterior hacia adelante
            actual = siguiente;        // Movemos actual hacia adelante
        }

        first = anterior; // El nuevo primero es el último que habíamos leído
    }

}
