import java.util.ArrayList;
import java.util.Iterator;

public class Cajoneria<T extends Comparable<T>> implements Iterable<T> 
{
    private ArrayList<Caja<T>> cajas;  // Array listo de tipo caja para almacenar cajas

    public Cajoneria() 
    {
        cajas = new ArrayList<>();
    }

    public void add(T elemento, String colorCaja) 
    {
        cajas.add(new Caja<T>(elemento, colorCaja));
    }

    @Override
    public Iterator<T> iterator() 
    {
        ArrayList<T> elementos = new ArrayList<>();
        for (Caja<T> caja : cajas) 
        {
            elementos.add(caja.getElemento());
        }
        return elementos.iterator();
    }
    
    public String search(T elemento) 
    {
        if(elemento == null){
            return "El elemento no existe";
        }
        for (int i = 0; i < cajas.size(); i++) 
        {
            if (cajas.get(i).getElemento().equals(elemento)) 
            {
                return "El elemento '" + elemento.toString() + 
                       "' se encuentra en la posicion [" + (i+1) + 
                       "] (Caja color " + cajas.get(i).getColor() + ")";
            }
        }
        return "El elemento '" + elemento.toString() + "' no se encontro";
    }
    

    public T delete(T elemento)
    {
        Iterator<Caja<T>> iterator = cajas.iterator();
        while (iterator.hasNext()) 
        {
            Caja<T> caja = iterator.next();
            if(caja.getElemento().equals(elemento)) 
            {
                iterator.remove();  
                return caja.getElemento();
            }
        }   
        return null;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Posicion\tColor Caja\tObjeto\n");
        
        for (int i = 0; i < cajas.size(); i++) 
        {
            Caja<T> caja = cajas.get(i);
            sb.append(String.format("%-8d\t%-10s\t%-20s\n", i+1, 
            caja.toString(), 
            caja.getContenidoToString()));
        }
        
        return sb.toString();
    }
    
}