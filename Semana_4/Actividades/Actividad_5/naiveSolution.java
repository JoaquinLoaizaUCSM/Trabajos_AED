package Actividades.Actividad_5;

public class naiveSolution
 {

    static int getValue(int[] values, int length)  // Metodo recursivo que calcula el valor maximo
    {
        if (length <= 0)  // Caso base si el tamaño del arreglo es 0 o menor, el valor maximo es 0
        {
            return 0; 
        }
        int tmpMax = -1; // Variable temporal para almacenar el maximo valor encontrado hasta el momento
        for (int i = 0; i < length; i++) 
        {
            // En cada iteracion, tomamos el valor en el indice actual y lo sumamos con el valor maximo
            // obtenido de la subcadena que queda después de este valor
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        // Finalmente, retornamos el valor maximo encontrado
        return tmpMax;
    }

    public static void main(String[] args) 
    {

        int[] values = new int[]{8, 2, 6, 3, 12, 4};
        int rotLength = values.length;
        System.out.println("El valor maximo: " + getValue(values, rotLength));
    }
}
