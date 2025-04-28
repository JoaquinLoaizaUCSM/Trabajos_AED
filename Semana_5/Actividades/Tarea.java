package Actividades;

public class Tarea implements Comparable<Tarea> {
    private String titulo;
    private int prioridad; // Mayor número = Mayor prioridad

    // Constructor
    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    // Gettters
    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // to string
    @Override
    public String toString() {
        return "Tarea{" +
               "titulo='" + titulo + '\'' +
               ", prioridad=" + prioridad +
               '}';
    }

    @Override
    public int compareTo(Tarea o) {
        // Primero compara las prioridades de las tareas
        int cmp = Integer.compare(this.prioridad, o.prioridad); // cmp puede ser -1,1,0
        
        // Si las prioridades son diferentes, devuelve el resultado de la comparación
        // Si las prioridades son iguales (cmp == 0), compara los títulos ignorando mayúsculas/minúsculas
        return (cmp == 0) ? this.titulo.compareToIgnoreCase(o.titulo) : cmp;
    }
    
    @Override
    public boolean equals(Object o) {
        // Verificación rápida: si son el mismo objeto en memoria, son iguales
        if (this == o) return true;
        
        // si el Object  no es una instancia de Tarea devuelve false
        if (!(o instanceof Tarea)) return false;
        
        // Convierte el Object del parametro a tipo Tarea para acceder a sus propiedades
        Tarea other = (Tarea) o;
        
        // Caso 1: this.titulo no es nulo comparo su contenido 
        if (titulo != null) {
            //  // se compararan los títulos
            if (!titulo.equalsIgnoreCase(other.titulo)) return false;
            // Caso 2: this.titulo es nulo, pero other.titulo no es nulo
        } else if (other.titulo != null) {
            // las tareas no son iguales
            return false;
        }
        
        // Finalmente, compara las prioridades
        // Las tareas son iguales solo si tienen mismo título y misma prioridad
        return prioridad == other.prioridad;
    }
}