public class Golosina implements Comparable<Golosina> {
    private String nombre;
    private double precio;

    public Golosina(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public int compareTo(Golosina otra) {
        // Comparamos por nombre
        return this.nombre.compareTo(otra.nombre);
    }

    @Override
    public String toString() {
        return "Golosina [nombre=" + nombre + ", precio=" + precio + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                 // Misma instancia
        if (obj == null || getClass() != obj.getClass()) return false; // Tipos diferentes
        Golosina golosina = (Golosina) obj;           // Cast seguro
        return Double.compare(golosina.precio, precio) == 0 && // Compara precio
               nombre.equals(golosina.nombre);        // y nombre
    }
}
