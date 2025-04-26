public class Tarea {
    private String titulo;
    private int prioridad; // Mayor número = Mayor prioridad

    public Tarea(String titulo, int prioridad) {
        this.titulo = titulo;
        this.prioridad = prioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
               "titulo='" + titulo + '\'' +
               ", prioridad=" + prioridad +
               '}';
    }
}