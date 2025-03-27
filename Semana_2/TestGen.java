public class TestGen 
{
    public static <T> boolean exist(T[] array, T element)
     {
       
        for (T item : array) 
        {
            if (item.equals(element)) 
            {
                return true; 
            }
        }
        return false; 
    }

    public static void main(String args[]) 
    {
        
        String[] x = {"Perez", "Sanchez", "Rodriguez"};
        Double[] y = {1.23,3.45,7.42,1.0};
        Integer[] z = {12, 34, 56};
        
        
        System.out.println(exist(x, "Sanchez"));  // true
        System.out.println(exist(y, 7.42));       // true  
        System.out.println(exist(z, 34));         // true
        System.out.println(exist(z, 86));          //false
      
    }
}
