public static int moda1(int array[])
{
    int first = 0;
    int end = array.length-1;
    if (first == end)
    {
        return array[first]; // Si el arreglo solo tiene 1 elemento, esa es la moda
    }
    int moda = array[first]; // Inicializa la moda como el primer elemento
    int maxfrec = frecuencia(array, first, end, moda); // Calcula la frecuencia del primer elemento
    for (int i = first+1; i<end; i++)  // Recorre el arreglo desde el segundo elemento
    {
        int frec = frecuencia(array, i, end, array[i]); // Calcula la frecuencia de cada elemento
        if(frec > maxfrec)// Si la frecuencia es mayor a la maxima frecuencia, actualiza la moda
        {
            maxfrec = frec;
            moda = array[i];
        }
    }
    return moda;
}

private static int frecuencia(int []array, int first, int end, int ele)
{
    if(first > end) {return 0;}  // Si el rango no es valido, devuelve 0
    int suma = 0; 
    for (int i = first; i <= end; i++)
    {
        if (array[i] == ele) {suma ++;}  // Cuenta cuantas veces aparece el elemento
    }
    return suma;// Devuelve la frecuencia de ese elemento (cuantas veces lo conto)
}