package Ejercicios.EJ_1;

class Ejercicio_1 {
    public static void main(String[] args) {
        int[] arreglo1 = {4, 2, 7, 1, 4};
        int numArreglo1 = 5;

        // Probamos sumObj = 7, por ejemplo.
        System.out.println(compObjetive(numArreglo1, arreglo1, 7));
    }

    public static boolean compObjetive(int N, int[] arreglo, int sumObj) {
        // Caso base: si hemos alcanzado el objetivo exactamente
        if (sumObj == 0) {
            return true;
        }

        // Caso base: sin elementos restantes y no hemos logrado sumObj = 0
        if (N == 0) {
            return false;
        }

        int elem = arreglo[N-1]; // elemento actual

        // Si el elemento es múltiplo de 7, debemos incluirlo
        if (elem % 7 == 0) {

            int nuevoSum = sumObj - elem;

            // Verificar si el siguiente elemento en el arreglo es 1 y está prohibido
            // El siguiente elemento es arreglo[N], siempre y cuando N < arreglo.length
            if (N < arreglo.length && arreglo[N] == 1) {
                // Debemos saltar este 1 prohibido, por lo que avanzamos N-2
                // Hemos tomado el 7 (arr[N-1]), y el arr[N] = 1 no se puede tomar, se salta.
                // Por tanto reducimos en 2 el N.
                if (N >= 2) {
                    return compObjetive(N-2, arreglo, sumObj-1);
                } else {
                    // Si no hay suficientes elementos para saltar dos, significa que no podemos proceder
                    // Esto es raro, solo pasaría si el 7 es penúltimo elemento. En ese caso no hay que tomar el 1.
                    // Si N=1 y encontramos un 1 prohibido a N= arr.length, no deberíamos tener arr[N].
                    // Por seguridad:
                    return false;
                }
            }else{
                // Si incluir este elemento supera el objetivo, no hay solución
                if (elem > sumObj) {
                    return false;
                }
            }


            // Si no hay 1 prohibido después del 7
            return compObjetive(N-1, arreglo, nuevoSum);
        }

        // Si no es múltiplo de 7

        // Opción 1: no tomar este elemento
        if (compObjetive(N-1, arreglo, sumObj)) {
            return true;
        }

        // Opción 2: tomar este elemento, si no excede
        if (elem <= sumObj) {
            if (compObjetive(N-1, arreglo, sumObj - elem)) {
                return true;
            }
        }

        // Si ni tomándolo ni dejándolo conseguimos el objetivo
        return false;
    }
}
