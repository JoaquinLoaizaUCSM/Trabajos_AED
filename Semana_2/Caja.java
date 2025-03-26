
public class Caja<T>
{
    private T elemento;
    private String color;

    public Caja(T _elemento,String _color)
    {
        this.color = _color;
        this.elemento = _elemento;
    }

    public String getColor() 
    {
        return color;
    }

    public void setColor(String _color) 
    {
        this.color = _color;
    }

    public T getElemento() 
    {
        return elemento;
    }

    public void setElemento(T _elemento) 
    {
        this.elemento = _elemento;
    }

    @Override
    public String toString() 
    {
        return color;
    }
    
    public String getContenidoToString() 
    {
        return elemento.toString();
    }
    
}

///Prueba