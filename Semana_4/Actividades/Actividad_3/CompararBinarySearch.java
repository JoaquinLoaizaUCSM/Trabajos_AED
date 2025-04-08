package Actividades.Actividad_3;

public class CompararBinarySearch {
    public static void main(String[] args) {
        int[] tamanios = {10, 100, 1000, 10000, 100000};

        // Crear instancias de las clases
        BinarySearch busquedaRecursiva = new BinarySearch();
        BinarySearchIterativo busquedaIterativa = new BinarySearchIterativo();

        System.out.printf("%-12s%-20s%-20s\n", "Tamaño", "Iterativo (ns)", "Recursivo (ns)");

        for (int n : tamanios) {
            int[] arr = generarArreglo(n);
            int buscar = n - 1; // caso peor: último elemento

            /*
            // System.nanoTime() devuelve el tiempo actual del sistema en nanosegundos.
            // Al restar el tiempo antes y despues de la ejecución del algoritmo,
            // obtenemos la duración exacta que demoró en ejecutarse ese metodo.
             */
            // Medir tiempo del metodo iterativo
            long startIter = System.nanoTime();
            busquedaIterativa.binarySearch(arr, buscar);
            long endIter = System.nanoTime();

            // Medir tiempo del metodo recursivo
            long startRec = System.nanoTime();
            busquedaRecursiva.binarySearch(arr, 0, arr.length - 1, buscar);
            long endRec = System.nanoTime();

            // Mostrar resultados
            System.out.printf("%-12d%-20d%-20d\n", n, (endIter - startIter), (endRec - startRec));
        }
    }

    // Metodo para generar un arreglo ordenado de tamaño n
    public static int[] generarArreglo(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = i;
        return arr;
    }

}