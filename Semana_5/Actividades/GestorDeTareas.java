package Actividades;

public class GestorDeTareas<T extends Comparable<T>> {
    private Node<T> first;

    public GestorDeTareas()
    {
        this.first = null;
    }

    public Node<T> ObtenerPrimero() //Metodo para obtener el primer nodo de la lista ya que el atributo es privado
    {
        return first;
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


    //Ejercicio 3 Y 4
    //Metodo que inserta al final un elemento 
    //Recibe y rentorna un nodocabeza (es decir una lista completa) 
    public Node<T> InsertarAlFinal(Node<T> NodoCabeza, T dato)
    {
        Node<T> newnode = new Node<T>(dato);
        if (NodoCabeza == null) { return newnode;}
        else
        {   
            Node<T> nodoActual = NodoCabeza;
            while (nodoActual.next != null) // Verifica si el SIGUIENTE del ACTUAL es null
            {
                nodoActual = nodoActual.next;
            }
            nodoActual.next = newnode;
            return NodoCabeza;
        }
 
    }

    public static <T> int ContarNodos(Node<T> NodoCabeza)
    {
        int contador = 0;
        Node<T> nodoActual = NodoCabeza;
        while (nodoActual != null) //Verifica si el ACTUAL es null
        {
            contador++;
            nodoActual = nodoActual.next;
        }
        return contador;
    }


    // Ejercicio 5
    public static <T> boolean sonIguales(Node<T> head1, Node<T> head2) {
        Node<T> actual1 = head1;
        Node<T> actual2 = head2;

        while (actual1 != null && actual2 != null) {
            if (actual1.data == null) {
                if (actual2.data != null) {
                    return false;
                }
            } else if (!actual1.data.equals(actual2.data)) {
                return false;
            }
            actual1 = actual1.next;
            actual2 = actual2.next;
        }

        // Si ambas listas llegaron al final al mismo tiempo, son iguales.
        return actual1 == null && actual2 == null;
    }


    // Ejercicio 6
    public static <T> Node<T> concatenarListas(Node<T> head1, Node<T> head2) {
    if (head1 == null && head2 == null) {
        return null;
    }

    Node<T> nuevaCabeza = null;
    Node<T> colaNueva = null;
    Node<T> actual = head1;

    // Copiar la primera lista
    while (actual != null) {
        Node<T> nuevoNodo = new Node<>(actual.data);
        if (nuevaCabeza == null) {
            nuevaCabeza = nuevoNodo;
            colaNueva = nuevoNodo;
        } else {
            colaNueva.next = nuevoNodo;
            colaNueva = nuevoNodo;
        }
        actual = actual.next;
    }

    // Copiar la segunda lista
    actual = head2;
    while (actual != null) {
            Node<T> nuevoNodo = new Node<>(actual.data);
            if (nuevaCabeza == null) { // Si la primera lista era null
                nuevaCabeza = nuevoNodo;
                colaNueva = nuevoNodo;
            } else {
                colaNueva.next = nuevoNodo;
                colaNueva = nuevoNodo;
            }
            actual = actual.next;
    }

    return nuevaCabeza;

    }
}
