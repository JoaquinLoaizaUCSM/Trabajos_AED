public class Rectangulo{
    private Coordenada esquina1;
    private Coordenada esquina2;

    // Constructor
    public Rectangulo(Coordenada c1, Coordenada c2) {
        setEsquina1(c1);
        setEsquina2(c2);
    }

    // setters
    public void setEsquina1(Coordenada coo) {
        this.esquina1 = coo;
    }

    public void setEsquina2(Coordenada coo) {
        this.esquina2 = coo;
    }

    // getters
    public Coordenada getEsquina1() {
        return this.esquina1;
    }

    public Coordenada getEsquina2() {
        return this.esquina2;
    }

    // sirve para obtener el minimo y maximo de las coordenadas
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

   
    public double calculoArea() {
        double ancho = Math.abs(esquina1.getX() - esquina2.getX()); // restamos para obtener la base
        double alto = Math.abs(esquina1.getY() - esquina2.getY()); // abs para que no importe el orden si el resultado es negativo
        return ancho * alto; // base * altura
    }
    
    @Override
    public String toString() { // imprime detalles de el rectangulo
        return "([" + getMinX() + ", " + getMinY() + "], [" + 
               getMaxX() + ", " + getMaxY() + "])";
    }

}
