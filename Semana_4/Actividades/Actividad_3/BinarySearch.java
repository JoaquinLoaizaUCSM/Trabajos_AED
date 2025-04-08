package Actividades.Actividad_3;

public class BinarySearch {

    /**
     * Metodo de busqueda binaria recursiva.
     * @param arr Vector ordenado donde se realiza la busqueda
     * @param lo Limite inferior del vector
     * @param hi Limite superior del vector
     * @param x Elemento a buscar
     * @return Posicion donde se encuentra x, o -1 si no esta presente
     */
    int binarySearch(int[] arr, int lo, int hi, int x) {
        // Condicion base: mientras haya al menos un elemento para revisar
        if (lo <= hi) {
            int mid = lo + (hi - lo) / 2; // Evita overflow

            // Si el elemento del medio es el buscado, retornamos su posicion
            if (arr[mid] == x)
                return mid;

            // Si el valor buscado es menor, buscamos en la mitad izquierda
            if (x < arr[mid])
                return binarySearch(arr, lo, mid - 1, x);

            // Si el valor buscado es mayor, buscamos en la mitad derecha
            return binarySearch(arr, mid + 1, hi, x);
        }

        // Elemento no encontrado
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch ob = new BinarySearch();

        int[] arr = {1, 3, 5, 7, 9, 11};
        int n = arr.length;
        int x = 5;

        int position = ob.binarySearch(arr, 0, n - 1, x);

        if (position == -1)
            System.out.println("Elemento no encontrado");
        else
            System.out.println("Elemento encontrado en la posicion: " + position);
    }
}