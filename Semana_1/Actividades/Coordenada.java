public class Coordenada{
    
    private double x;
    private double y;

    public Coordenada( ){
        this.x = 0.0;
        this.y = 0.0;
    }

    public Coordenada(double x, double y ){
        this.x = x;
        this.y = y;
    }

    public Coordenada(Coordenada c ){
        this.x = c.x;
        this.y = c.y;
    }

    //métodos setter
    void setX(double x) {
        this.x = x;
    }

    void setY(double y){
        this.y = y;
    }

    //métodos getter
    double getX(){
        return this.x;
    }

    double getY(){
        return this.y;
    }

    //método de instancia que calcula la distancia euclideana
    double distancia(Coordenada c){
        double dx = this.x - c.x;
        double dy = this.y - c.y;
        return Math.sqrt(dx*dx + dy*dy);
    }

    //método de clase que calcula la distancia euclideana
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