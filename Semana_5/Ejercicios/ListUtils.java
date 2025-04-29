package Ejercicios;

public class ListUtils {
    public static <T extends Comparable<T>> boolean buscarElemento(LinkedList<T> lista, T elemento) {
        return lista.search(elemento) != -1;
    }

    public static <T extends Comparable<T>> LinkedList<T> invertirLista(LinkedList<T> listaOriginal) {
        LinkedList<T> listaInvertida = new LinkedList<>();

        Node<T> actual = listaOriginal.first;  // accedemos directo al head

        while (actual != null) {
            listaInvertida.insertFirst(actual.getData());  // insertamos al inicio
            actual = actual.getNext();
        }

        return listaInvertida;
    }

    // Ejercicio 3: insertar al final
    public static <T> Node<T> insertarAlFinal(Node<T> nodoCabeza, T dato) {
        Node<T> nuevoNodo = new Node<>(dato);

        if (nodoCabeza == null) {
            return nuevoNodo;
        } else {
            Node<T> nodoActual = nodoCabeza;
            while (nodoActual.getNext() != null) {  // Verifica si el SIGUIENTE del ACTUAL es null
                nodoActual = nodoActual.getNext();
            }
            nodoActual.setNext(nuevoNodo);
            return nodoCabeza;
        }
    }

    // Ejercicio 4: contar nodos
    public static <T> int contarNodos(Node<T> nodoCabeza) {
        int contador = 0;
        Node<T> nodoActual = nodoCabeza;

        while (nodoActual != null) { //Verifica si el ACTUAL es null
            contador++;
            nodoActual = nodoActual.getNext();
        }
        return contador;
    }

    // Ejercicio 5: comparar si dos listas son iguales
    public static <T> boolean sonIguales(Node<T> head1, Node<T> head2) {
        Node<T> actual1 = head1;
        Node<T> actual2 = head2;

        while (actual1 != null && actual2 != null) {
            if (actual1.getData() == null) {
                if (actual2.getData() != null) {
                    return false;
                }
            } else if (!actual1.getData().equals(actual2.getData())) {
                return false;
            }
            actual1 = actual1.getNext();
            actual2 = actual2.getNext();
        }

        // Si ambas listas llegaron al final al mismo tiempo, son iguales
        return actual1 == null && actual2 == null;
    }

    // Ejercicio 6: concatenar listas
    public static <T> Node<T> concatenarListas(Node<T> head1, Node<T> head2) {
        if (head1 == null && head2 == null) {
            return null;
        }

        Node<T> nuevaCabeza = null;
        Node<T> colaNueva = null;
        Node<T> actual = head1;

        // Copiar primera lista
        while (actual != null) {
            Node<T> nuevoNodo = new Node<>(actual.getData());
            if (nuevaCabeza == null) {
                nuevaCabeza = nuevoNodo;
                colaNueva = nuevoNodo;
            } else {
                colaNueva.setNext(nuevoNodo);
                colaNueva = nuevoNodo;
            }
            actual = actual.getNext();
        }

        // Copiar segunda lista
        actual = head2;
        while (actual != null) {
            Node<T> nuevoNodo = new Node<>(actual.getData());
            if (nuevaCabeza == null) { // Si la primera lista era null
                nuevaCabeza = nuevoNodo;
                colaNueva = nuevoNodo;
            } else {
                colaNueva.setNext(nuevoNodo);
                colaNueva = nuevoNodo;
            }
            actual = actual.getNext();
        }

        return nuevaCabeza;
    }

}
