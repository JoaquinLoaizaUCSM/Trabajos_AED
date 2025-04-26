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


}
