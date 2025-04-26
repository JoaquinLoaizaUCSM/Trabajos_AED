package Actividades;

public class GestorDeTareas<T> {
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




}
