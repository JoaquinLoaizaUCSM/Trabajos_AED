public class Chocolatina implements Comparable<Chocolatina> {
    private String marca;

    public Chocolatina(String marca) {
        this.marca = marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public int compareTo(Chocolatina otra) {
        // Comparamos por marca
        return this.marca.compareTo(otra.marca);
    }

    @Override
    public String toString() {
        return "Chocolatina [marca=" + marca + "]";
    }

    @Override
    public boolean equals(Object obj) //sirve para comparar si dos objetos son iguales segun sus atributos (marca) en lugar de su posición en memoria
    {
        if (this == obj) return true;                 // Son el mismo objeto
        if (obj == null || getClass() != obj.getClass()) return false; // Tipos diferentes
        Chocolatina that = (Chocolatina) obj;         // Cast seguro
        return marca.equals(that.marca);              // Comparación por marca
    }
}
