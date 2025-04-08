package Actividades.Actividad_3;

public class BinarySearchIterativo {

    /**
     * Metodo de busqueda binaria iterativa.
     * @param arr Vector ordenado donde se realiza la busqueda
     * @param x Elemento a buscar
     * @return Posicion donde se encuentra x, o -1 si no esta presente
     */
    int binarySearch(int[] arr, int x) {
        int lo = 0;
        int hi = arr.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid] == x)
                return mid;

            // Si el elemento a buscar es mayor, ignoramos la mitad izquierda
            if (arr[mid] < x)
                lo = mid + 1;
            else
                hi = mid - 1;
        }

        // Elemento no encontrado
        return -1;
    }

    public static void main(String[] args) {
        BinarySearchIterativo ob = new BinarySearchIterativo();

        int[] arr = {1, 3, 5, 7, 9, 11};
        int x = 9;

        int position = ob.binarySearch(arr, x);

        if (position == -1)
            System.out.println("Elemento no presente");
        else
            System.out.println("Elemento encontrado en la posicion: " + position);
    }
}