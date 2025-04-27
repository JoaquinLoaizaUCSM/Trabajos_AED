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
        int cmp = Integer.compare(this.prioridad, o.prioridad);
        return (cmp != 0) ? cmp : this.titulo.compareToIgnoreCase(o.titulo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tarea)) return false;
        Tarea other = (Tarea) o;
        if (titulo != null) {
            if (!titulo.equals(other.titulo)) return false;
        } else if (other.titulo != null) {
            return false;
        }
        return prioridad == other.prioridad;
    }
}