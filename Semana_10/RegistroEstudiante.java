package Semana_10;

public class RegistroEstudiante implements Comparable<RegistroEstudiante> {
    private int codigo;
    private String nombre;

    public RegistroEstudiante(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    @Override
    public int compareTo(RegistroEstudiante other) {
        return Integer.compare(this.codigo, other.codigo);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegistroEstudiante)) return false;
        RegistroEstudiante other = (RegistroEstudiante) o;
        return this.codigo == other.codigo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codigo);
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }
}

