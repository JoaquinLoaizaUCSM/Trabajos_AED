package Actividades;

public class GestorDeTareas2<T extends Comparable<T>> {
    private LinkedList<T> listaTareas;

    public GestorDeTareas2() {
        this.listaTareas = new LinkedList<>();
    }

    public GestorDeTareas2(LinkedList<T> list) {
        this.listaTareas = list;
    }

    public Node<T> ObtenerPrimero() {
        return listaTareas.first;
    }

    void agregarTarea(T tarea) {
        listaTareas.insertLast(tarea);
    }

    boolean eliminarTarea(T tarea) {
        int index = listaTareas.search(tarea);
        if (index != -1) {
            listaTareas.removeNode(tarea);
            return true;
        }
        return false;
    }

    boolean contieneTarea(T tarea) {
        return listaTareas.search(tarea) != -1;
    }

    void imprimirTareas() {
        Node<T> current = listaTareas.first;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    int contarTareas() {
        return listaTareas.length();
    }

    public T obtenerTareaMasPrioritaria() {
        if (listaTareas.isEmptyList()) {
            return null;
        }
        Node<T> current = listaTareas.first;
        T tareaMaxima = current.getData();
        while (current != null) {
            if (current.getData().compareTo(tareaMaxima) > 0) {
                tareaMaxima = current.getData();
            }
            current = current.getNext();
        }
        return tareaMaxima;
    }

    void invertirTareas() {
        Node<T> anterior = null;
        Node<T> actual = listaTareas.first;
        Node<T> siguiente;

        while (actual != null) {
            siguiente = actual.next;   // Guardamos el siguiente
            actual.next = anterior;    // Invertimos el enlace
            anterior = actual;         // Movemos anterior hacia adelante
            actual = siguiente;        // Movemos actual hacia adelante
        }

        listaTareas.first = anterior; // El nuevo primero es el último que habíamos leídothis.listaTareas = listaInvertida; // Reemplazamos la lista original por la invertida
    }
}
