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

}
