
public static int moda2 (int array[])
{
    int first = 1;// El primer índice a comparar (empieza en 1 porque comparamos con el anterior)
    int p = 0;
    int end = array.length-1;
    int moda = array[0]; // Inicializa la moda con el primer elemento
    int frec = 1; // Frecuencia del primer elemento
    int maxfrec = 0;  // La maxima frecuencia hasta ahora
    while (first <= end)  // Si el elemento actual es igual al anterior, aumenta la frecuencia
    {
        if(array[p] == array [first])
        {
            frec++; // aumenta la frecuencia
            first++; // aumenta la moda
        }
        else{
            if(frec > maxfrec) // Si la frecuencia actual es mayor, actualiza la moda
            {
                maxfrec = frec;
                moda = array[p];
            }
            // Reajustamos los índices y reiniciamos la frecuencia para el nuevo valor
            p = first;
            first = p+1;// Comenzamos a contar para el siguiente grupo de valores
            frec = 1; // Reinicia la frecuencia para el nuevo valor
        }
        
    }
    return moda; // Devuelve el elemento con la mayor frecuencia
}
