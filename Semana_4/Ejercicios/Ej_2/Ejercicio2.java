package Ejercicios.Ej_2;

public class Ejercicio2 {
    public static void main(String[] args) {
//        int[] arr = {4, 2, 7, 10, 4, 17};
//        int k = 3;
//
//        int result = quickSelect(arr, 0, arr.length - 1, k);
//        System.out.println("El " + k + "° elemento más pequeño es: " + result);
//

    }

    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left]; // solo hay un elemento
        }

        int pivotIndex = partition(arr, left, right);

        int count = pivotIndex - left + 1; // cuántos elementos hay en la izquierda (incluyendo el pivote)

        if (k == count) {
            return arr[pivotIndex]; // el pivote es el k-ésimo
        } else if (k < count) {
            return quickSelect(arr, left, pivotIndex - 1, k); // está en la izquierda
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k - count); // está en la derecha
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // usamos el último como pivote
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                // intercambiamos arr[i] y arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        // colocamos el pivote en su posición final
        int temp = arr[i];
        arr[i] = arr[right];
        arr[right] = temp;

        return i; // nueva posición del pivote
    }
}

