public class Verificador 
{
    
   
    public static final int SOBREPONEN = 1;
    public static final int JUNTAN = 2;
    public static final int DISJUNTOS = 3;
    
    public int verificarRectangulos(Rectangulo rectA, Rectangulo rectB) {
        
        if (rectA.getMinX() < rectB.getMaxX() && rectA.getMaxX() > rectB.getMinX() && 
            rectA.getMinY() < rectB.getMaxY() && rectA.getMaxY() > rectB.getMinY()) {
            return SOBREPONEN;
        }
       
        else if (rectA.getMinX() == rectB.getMaxX() || rectA.getMaxX() == rectB.getMinX() || 
                 rectA.getMinY() == rectB.getMaxY() || rectA.getMaxY() == rectB.getMinY()) {
            return JUNTAN;
        }
        
        else 
        {
            return DISJUNTOS;
        }
    }
    

    public Rectangulo calcularSobreposicion(Rectangulo rectA, Rectangulo rectB) {

       
        double x1S = Math.max(rectA.getMinX(), rectB.getMinX());
        double y1S = Math.max(rectA.getMinY(), rectB.getMinY());
        double x2S = Math.min(rectA.getMaxX(), rectB.getMaxX());
        double y2S = Math.min(rectA.getMaxY(), rectB.getMaxY());
        
        return new Rectangulo(new Coordenada(x1S, y1S), new Coordenada(x2S, y2S));
    }
}
