public class Coordenada{

    // atributos
    private double x;
    private double y;

    // Constructor1
    public Coordenada( ){
        this.x = 0.0;
        this.y = 0.0;
    }

    // Constructor2
    public Coordenada(double x, double y ){
        this.x = x;
        this.y = y;
    }

    // Constructor3
    // sobrecarga de constructores
    public Coordenada(Coordenada c ){
        this.x = c.x;
        this.y = c.y;
    }

   // setters
    void setX(double x) {
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    // getters
    double getX(){
        return this.x;
    }

    double getY(){
        return this.y;
    }

    // metodo de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
        double dx = this.x - c.x;  // (x2 - x1)
        double dy = this.y - c.y;  // (y2 - y1)
        return Math.sqrt(dx*dx + dy*dy); // √(dx² + dy²)
    }

   // metodo de clase que calcula la distancia euclideana
    static double distancia(Coordenada c1, Coordenada c2){
        double dx = c1.x - c2.x;
        double dy = c1.y - c2.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    @Override
    public String toString(){
        return "[" + this.x + ", " + this.y + "]";
    }
}
