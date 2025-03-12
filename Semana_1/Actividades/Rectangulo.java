public class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    public void setEsquina1(Coordenada coo) {
        this.esquina1 = coo;
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = coo;
    }

    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    public double getMinX() {
        return Math.min(esquina1.getX(), esquina2.getX());
    }

    public double getMinY() {
        return Math.min(esquina1.getY(), esquina2.getY());
    }

    public double getMaxX() {
        return Math.max(esquina1.getX(), esquina2.getX());
    }

    public double getMaxY() {
        return Math.max(esquina1.getY(), esquina2.getY());
    }

    // Método para calcular el área del rectángulo
    public double calculoArea() {
        double ancho = Math.abs(esquina1.getX() - esquina2.getX());
        double alto = Math.abs(esquina1.getY() - esquina2.getY());
        return ancho * alto;
    }

    @Override
    public String toString() {
        return "([" + getMinX() + ", " + getMinY() + "], [" + 
               getMaxX() + ", " + getMaxY() + "])";
    }
}