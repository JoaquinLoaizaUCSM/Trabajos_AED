import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa<T> implements Iterable<T> 
{
    private ArrayList<T> elementos;

    public Bolsa() 
    {
        elementos = new ArrayList<>();
    }

    public void add(T elemento)
    {
        elementos.add(elemento);
    }

    @Override
    public Iterator<T> iterator()
    {
        return elementos.iterator();
    }
    
}
