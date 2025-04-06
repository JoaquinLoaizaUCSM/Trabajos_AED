
public class dpSolutions
{
    static int getValue(int[] values, int rodLength)
    {
        int[] subSolutions = new int[rodLength + 1]; // Creamos un arreglo donde almacenaremos las sub-soluciones
        for(int i = 1; i <= rodLength; i ++)
        {
            int tmpMax = -1; // Variable para almacenar el valor maximo para la longitud de barra i
            
            for (int j = 0; j < i; j++)  // Calculamos el valor maximo para cada sublongitud
            {
                 // Comparamos el valor maximo entre la opción actual y el valor obtenido
                // de cortar la barra en el punto j y sumarlo con las subsoluciones obtenidas
                tmpMax = Math.max(tmpMax, values[j] + subSolutions[i-j-1]); 
            }
            // Guardamos el valor maximo para la longitud de barra i en el arreglo subSolutions
            subSolutions[i] = tmpMax;
        }
        return subSolutions[rodLength];
    }



    public static void main(String[] args)
    {
        int[]values = new int[] {3,7,1,3,9};
        int rodLength = values.length; // La longitud total de la barra es igual a la cantidad de elementos en el arreglo 'values'
        System.out.println("El valor maximo: "+ getValue(values, rodLength));
    }
}
