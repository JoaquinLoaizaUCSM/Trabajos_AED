public class Verificador 
{
    public static boolean esSobrePos (Rectangulo rectA, Rectangulo rectB) {
        // Recta A
        double rectA_x1 = rectA.getMinX();
        double rectA_x2 = rectA.getMaxX();
        double rectA_y1 = rectA.getMinY();
        double rectA_y2 = rectA.getMaxY();

        // Recta B
        double rectB_x1 = rectB.getMinX();
        double rectB_x2 = rectB.getMaxX();
        double rectB_y1 = rectB.getMinY();
        double rectB_y2 = rectB.getMaxY();

        return rectA_x1 < rectB_x2 && rectA_x2 > rectB_x1 // si se sobreponen en el eje x
                && // Y
                rectA_y1 < rectB_y2 && rectA_y2 > rectB_y1; // se sobreponen en el eje y;
    }

    public static boolean esJunto (Rectangulo rectA, Rectangulo rectB) {
        // Recta A
        double rectA_x1 = rectA.getMinX();
        double rectA_x2 = rectA.getMaxX();
        double rectA_y1 = rectA.getMinY();
        double rectA_y2 = rectA.getMaxY();

        // Recta B
        double rectB_x1 = rectB.getMinX();
        double rectB_x2 = rectB.getMaxX();
        double rectB_y1 = rectB.getMinY();
        double rectB_y2 = rectB.getMaxY();

        // si no sobreponen
        if (!esSobrePos(rectA, rectB)) {
            return false;
        }

        return (rectA_x2 == rectB_x1 && !(rectA_y2 < rectB_y1 || rectA_y1 > rectB_y2)) ||
                (rectA_x1 == rectB_x2 && !(rectA_y2 < rectB_y1 || rectA_y1 > rectB_y2)) ||
                (rectA_y2 == rectB_y1 && !(rectA_x2 < rectB_x1 || rectA_x1 > rectB_x2)) ||
                (rectA_y1 == rectB_y2 && !(rectA_x2 < rectB_x1 || rectA_x1 > rectB_x2));
    }

    public static boolean esDisjunto (Rectangulo rectA, Rectangulo rectB) {
        return !esSobrePos(rectA, rectB) && !esJunto(rectA, rectB);
    }

    public static Rectangulo calcularSobreposicion(Rectangulo rectA, Rectangulo rectB) {

        double x1S = Math.max(rectA.getMinX(), rectB.getMinX());
        double y1S = Math.max(rectA.getMinY(), rectB.getMinY());
        double x2S = Math.min(rectA.getMaxX(), rectB.getMaxX());
        double y2S = Math.min(rectA.getMaxY(), rectB.getMaxY());
        
        return new Rectangulo(new Coordenada(x1S, y1S), new Coordenada(x2S, y2S));
    }
}
