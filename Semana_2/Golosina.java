public class Golosina 
{
    private String nombre;
    private double peso;

    public Golosina(String nombre, double peso) 
    {
        this.nombre = nombre;
        this.peso = peso;
    }

    public String getNombre() 
    {
        return this.nombre;
    }

    public void setNombre(String nombre)
     {
        this.nombre = nombre;
    }

    public double getPeso() 
    {
        return this.peso;
    }

    public void setPeso(double peso) 
    {
        this.peso = peso;
    }

    @Override
    public String toString() 
    {
        return nombre + " (" + peso + " kg)";
    }

    @Override
    public boolean equals(Object obj) //sirve para comparar si dos objetos son iguales segun sus atributos (nombre y peso) en lugar de su posición en memoria
    {
        if (this == obj) return true;                 // Misma instancia
        if (obj == null || getClass() != obj.getClass()) return false; // Tipos diferentes
        Golosina golosina = (Golosina) obj;           // Cast seguro
        return Double.compare(golosina.peso, peso) == 0 && // Compara peso
               nombre.equals(golosina.nombre);        // y nombre
    }
}
