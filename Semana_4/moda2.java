
public static int moda2 (int array[])
{
    int first = 1;
    int p = 0;
    int end = array.length-1;
    int moda = array[0];
    int frec = 1;
    int maxfrec = 0;
    while (first <= end) 
    {
        if(array[p] == array [first])
        {
            frec++;
            first++;
        }
        else{
            if(frec > maxfrec)
            {
                maxfrec = frec;
                moda = array[p];
            }
            p = first;
            first = p+1;
            frec = 1;
        }
        
    }
    return moda;
}
