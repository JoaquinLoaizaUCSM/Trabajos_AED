public class Verificador 
{
    // Clase auxiliar para almacenar las coordenadas extraídas de los rectángulos
    private static class RectCoords {
        double x1, x2, y1, y2;
        
        RectCoords(Rectangulo rect) {
            this.x1 = rect.getMinX();
            this.x2 = rect.getMaxX();
            this.y1 = rect.getMinY();
            this.y2 = rect.getMaxY();
        }
    }
    
    public static boolean esSobrePos(Rectangulo rectA, Rectangulo rectB) {
        RectCoords a = new RectCoords(rectA);
        RectCoords b = new RectCoords(rectB);

        return a.x1 < b.x2 && a.x2 > b.x1 // si se sobreponen en el eje x
                && // Y
                a.y1 < b.y2 && a.y2 > b.y1; // se sobreponen en el eje y;
    }

    public static boolean esJunto(Rectangulo rectA, Rectangulo rectB) {
        RectCoords a = new RectCoords(rectA);
        RectCoords b = new RectCoords(rectB);

        // si se sobreponen
        if (esSobrePos(rectA, rectB)) {
            return false;
        }

        return (a.x2 == b.x1 && !(a.y2 < b.y1 || a.y1 > b.y2)) ||
                (a.x1 == b.x2 && !(a.y2 < b.y1 || a.y1 > b.y2)) ||
                (a.y2 == b.y1 && !(a.x2 < b.x1 || a.x1 > b.x2)) ||
                (a.y1 == b.y2 && !(a.x2 < b.x1 || a.x1 > b.x2));
    }

    public static boolean esDisjunto(Rectangulo rectA, Rectangulo rectB) {
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
