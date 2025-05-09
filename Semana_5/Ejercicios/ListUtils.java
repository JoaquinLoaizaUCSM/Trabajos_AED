package Ejercicios;

import Actividades.LinkedList;
import Actividades.Node;

public class ListUtils {
    // Ejercicio 1: Buscar un elemento genérico en una lista
    public static <T extends Comparable<T>> boolean buscarElemento(LinkedList<T> lista, T elemento) {
        return lista.search(elemento) != -1;
    }

    // Ejercicio 2: Invertir una lista generica
    public static <T extends Comparable<T>> LinkedList<T> invertirLista(LinkedList<T> listaOriginal) {
        Node<T> anterior = null;
        Node<T> actual = listaOriginal.first;
        Node<T> siguiente;

        while (actual != null) {
            siguiente = actual.getNext();      // Guardamos el siguiente nodo
            actual.setNext(anterior);         // Invertimos el enlace
            anterior = actual;                // Avanzamos 'anterior'
            actual = siguiente;               // Avanzamos 'actual'
        }

        listaOriginal.first = anterior;       // El nuevo inicio es el último que recorrimos

        return listaOriginal;
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

    public static <T extends Comparable<T>> boolean sonIguales(LinkedList<T> lista1, LinkedList<T> lista2) {
        Node<T> actual1 = lista1.first;
        Node<T> actual2 = lista2.first;

        while (actual1 != null && actual2 != null) {
            T d1 = actual1.getData();
            T d2 = actual2.getData();
            // usar el comparador para determinar igualdad
            if (!d2.equals(d1)) {
                return false;
            }
            actual1 = actual1.getNext();
            actual2 = actual2.getNext();
        }

        // verificar que ambas listas terminen simultáneamente
        return actual1 == null && actual2 == null;
    }

    // Ejercicio 6: concatenar listas
    public static <T extends Comparable<T>> LinkedList<T> concatenarListas(LinkedList<T> lista1, LinkedList<T> lista2) {
        LinkedList<T> nuevaLista = new LinkedList<>();
        Node<T> actual = lista1.first;
        while (actual != null) {
            nuevaLista.insertLast(actual.getData());
            actual = actual.getNext();
        }
        actual = lista2.first;
        while (actual != null) {
            nuevaLista.insertLast(actual.getData());
            actual = actual.getNext();
        }
        return nuevaLista;
    }

}
