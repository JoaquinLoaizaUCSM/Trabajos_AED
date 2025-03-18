//import java.text.DecimalFormat;
//import java.util.Scanner;
//
//public class VisualizadorRectangulos {
//    private Rectangulo rectA;
//    private Rectangulo rectB;
//    private Rectangulo sobreposicion;
//    private int relacion;
//    private DecimalFormat df = new DecimalFormat("0.00");
//    private static final int ANCHO_LIENZO = 60;
//    private static final int ALTO_LIENZO = 20;
//
//    // Símbolos para los rectángulos
//    private static final char SIMBOLO_RECT_A = '■'; // Carácter para el rectángulo A
//    private static final char SIMBOLO_RECT_B = '□'; // Carácter para el rectángulo B
//    private static final char SIMBOLO_SOBREPOSICION = '▣'; // Carácter para la sobreposición
//    private static final char SIMBOLO_EJE = '·'; // Carácter para los ejes
//
//    public VisualizadorRectangulos(Rectangulo rectA, Rectangulo rectB) {
//        this.rectA = rectA;
//        this.rectB = rectB;
//
//        // Determinar la relación entre los rectángulos
//        this.relacion = Verificador.verificarRectangulos(rectA, rectB);
//
//        if (this.relacion == Verificador.SOBREPONEN) {
//            this.sobreposicion = verificador.calcularSobreposicion(rectA, rectB);
//        }
//
//        mostrarVisualizacionASCII();
//    }
//
//    private void mostrarVisualizacionASCII() {
//        // Calcular el rango de coordenadas para escalar adecuadamente
//        double minX = Math.min(rectA.getMinX(), rectB.getMinX());
//        double maxX = Math.max(rectA.getMaxX(), rectB.getMaxX());
//        double minY = Math.min(rectA.getMinY(), rectB.getMinY());
//        double maxY = Math.max(rectA.getMaxY(), rectB.getMaxY());
//
//        // Agregar un margen
//        double rangoX = maxX - minX;
//        double rangoY = maxY - minY;
//        minX -= rangoX * 0.1;
//        maxX += rangoX * 0.1;
//        minY -= rangoY * 0.1;
//        maxY += rangoY * 0.1;
//
//        // Asegurar que hay un rango mínimo para evitar divisiones por cero
//        if (rangoX < 0.001) rangoX = 1.0;
//        if (rangoY < 0.001) rangoY = 1.0;
//
//        // Variables para la escala
//        final double escalaX = ANCHO_LIENZO / (maxX - minX);
//        final double escalaY = ALTO_LIENZO / (maxY - minY);
//        final double minX_final = minX;
//        final double minY_final = minY;
//
//        // Crear el lienzo ASCII
//        char[][] lienzo = new char[ALTO_LIENZO][ANCHO_LIENZO];
//        for (int y = 0; y < ALTO_LIENZO; y++) {
//            for (int x = 0; x < ANCHO_LIENZO; x++) {
//                lienzo[y][x] = ' ';
//            }
//        }
//
//        // Calcular la posición de los ejes X e Y
//        int ejeXPos = (int)((0 - minX_final) * escalaX);
//        int ejeYPos = (int)((0 - minY_final) * escalaY);
//
//        // Invertir coordenada Y para el sistema cartesiano
//        ejeYPos = ALTO_LIENZO - 1 - ejeYPos;
//
//        // Dibujar los ejes X e Y
//        for (int x = 0; x < ANCHO_LIENZO; x++) {
//            if (ejeYPos >= 0 && ejeYPos < ALTO_LIENZO) {
//                lienzo[ejeYPos][x] = SIMBOLO_EJE;
//            }
//        }
//
//        for (int y = 0; y < ALTO_LIENZO; y++) {
//            if (ejeXPos >= 0 && ejeXPos < ANCHO_LIENZO) {
//                lienzo[y][ejeXPos] = SIMBOLO_EJE;
//            }
//        }
//
//        // Si hay sobreposición, marcarla primero para que los rectángulos no la sobrescriban
//        if (relacion == Verificador.SOBREPONEN) {
//            dibujarRectanguloASCII(lienzo, sobreposicion, escalaX, escalaY, minX_final, minY_final, SIMBOLO_SOBREPOSICION);
//        }
//
//        // Dibujar el segundo rectángulo (B)
//        dibujarRectanguloASCII(lienzo, rectB, escalaX, escalaY, minX_final, minY_final, SIMBOLO_RECT_B);
//
//        // Dibujar el primer rectángulo (A)
//        dibujarRectanguloASCII(lienzo, rectA, escalaX, escalaY, minX_final, minY_final, SIMBOLO_RECT_A);
//
//        // Mostrar el lienzo ASCII
//        System.out.println("\n=== Plano Cartesiano ===");
//        System.out.println(SIMBOLO_RECT_A + ": Rectángulo A | " + SIMBOLO_RECT_B + ": Rectángulo B | " +
//                          SIMBOLO_SOBREPOSICION + ": Área de sobreposición | " + SIMBOLO_EJE + ": Ejes");
//
//        // Dibujar el marco superior
//        System.out.print("┌");
//        for (int x = 0; x < ANCHO_LIENZO; x++) {
//            System.out.print("─");
//        }
//        System.out.println("┐");
//
//        // Dibujar el lienzo con marco lateral
//        for (int y = 0; y < ALTO_LIENZO; y++) {
//            System.out.print("│");
//            for (int x = 0; x < ANCHO_LIENZO; x++) {
//                System.out.print(lienzo[y][x]);
//            }
//            System.out.println("│");
//        }
//
//        // Dibujar el marco inferior
//        System.out.print("└");
//        for (int x = 0; x < ANCHO_LIENZO; x++) {
//            System.out.print("─");
//        }
//        System.out.println("┘");
//
//        // Añadir leyendas de escala
//        System.out.printf("min X: %.2f, max X: %.2f, min Y: %.2f, max Y: %.2f\n",
//                         minX_final, maxX, minY_final, maxY);
//
//        // Mostrar información adicional
//        System.out.println("\n=== Información ===");
//        System.out.println("Rectángulo A: " + rectA.toString());
//        System.out.println("Rectángulo B: " + rectB.toString());
//
//        String relacionStr;
//        switch (relacion) {
//            case Verificador.SOBREPONEN:
//                relacionStr = "Los rectángulos se sobreponen";
//                System.out.println("Área de sobreposición: " + df.format(sobreposicion.calculoArea()));
//                break;
//            case Verificador.JUNTAN:
//                relacionStr = "Los rectángulos se juntan";
//                break;
//            case Verificador.DISJUNTOS:
//                relacionStr = "Los rectángulos son disjuntos";
//                break;
//            default:
//                relacionStr = "Relación desconocida";
//        }
//        System.out.println(relacionStr);
//    }
//
//    private void dibujarRectanguloASCII(char[][] lienzo, Rectangulo rect, double escalaX, double escalaY,
//                                    double minX, double minY, char simbolo) {
//        int x1 = (int)((rect.getMinX() - minX) * escalaX);
//        int y1 = (int)((rect.getMinY() - minY) * escalaY);
//        int x2 = (int)((rect.getMaxX() - minX) * escalaX);
//        int y2 = (int)((rect.getMaxY() - minY) * escalaY);
//
//        // Asegurar que los puntos estén dentro de los límites del lienzo
//        x1 = Math.max(0, Math.min(x1, ANCHO_LIENZO - 1));
//        y1 = Math.max(0, Math.min(y1, ALTO_LIENZO - 1));
//        x2 = Math.max(0, Math.min(x2, ANCHO_LIENZO - 1));
//        y2 = Math.max(0, Math.min(y2, ALTO_LIENZO - 1));
//
//        // Invertir coordenadas Y para que sean consistentes con el sistema de coordenadas cartesiano
//        y1 = ALTO_LIENZO - 1 - y1;
//        y2 = ALTO_LIENZO - 1 - y2;
//
//        // Asegurar que y1 sea siempre menor que y2
//        if (y1 > y2) {
//            int temp = y1;
//            y1 = y2;
//            y2 = temp;
//        }
//
//        // Dibujar el rectángulo en el lienzo (solo el borde para ser más minimalista)
//        for (int y = y1; y <= y2; y++) {
//            for (int x = x1; x <= x2; x++) {
//                // Dibujar solo los bordes o relleno según preferencia
//                boolean esBorde = (y == y1 || y == y2 || x == x1 || x == x2);
//
//                if (x >= 0 && x < ANCHO_LIENZO && y >= 0 && y < ALTO_LIENZO) {
//                    // Si es área de sobreposición, siempre usar el símbolo de sobreposición
//                    if (simbolo == SIMBOLO_SOBREPOSICION) {
//                        lienzo[y][x] = simbolo;
//                    }
//                    // Si no es sobreposición, solo dibujar si es un borde o si el espacio está vacío
//                    else if (esBorde || lienzo[y][x] == ' ' || lienzo[y][x] == SIMBOLO_EJE) {
//                        lienzo[y][x] = simbolo;
//                    }
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//        Rectangulo rectanguloA = solicitarRectangulo(scanner, "A");
//        Rectangulo rectanguloB = solicitarRectangulo(scanner, "B");
//
//        scanner.close();
//
//        // Mostrar información en consola
//        System.out.println("\n--- Resultados ---");
//        mostrarRectangulo(rectanguloA, "A");
//        mostrarRectangulo(rectanguloB, "B");
//
//        Verificador verificador = new Verificador();
//        int caso = verificador.verificarRectangulos(rectanguloA, rectanguloB);
//
//        switch (caso) {
//            case Verificador.SOBREPONEN:
//                System.out.println("Rectángulos A y B se sobreponen.");
//                Rectangulo sobreposicion = verificador.calcularSobreposicion(rectanguloA, rectanguloB);
//                System.out.println("Área de sobreposición = " +
//                                  new DecimalFormat("0.00").format(sobreposicion.calculoArea()));
//                break;
//            case Verificador.JUNTAN:
//                System.out.println("Rectángulos A y B se juntan.");
//                break;
//            case Verificador.DISJUNTOS:
//                System.out.println("Rectángulos A y B son disjuntos.");
//                break;
//        }
//
//        // Iniciar la visualización ASCII
//        new VisualizadorRectangulos(rectanguloA, rectanguloB);
//    }
//
//    public static void mostrarRectangulo(Rectangulo rect, String nombre) {
//        System.out.println("Rectángulo " + nombre + " = " + rect.toString());
//    }
//
//    public static Rectangulo solicitarRectangulo(Scanner scanner, String nombre) {
//        System.out.println("\nIngrese las coordenadas para el rectángulo " + nombre + ":");
//        System.out.print("Coordenada X de la primera esquina: ");
//        double x1 = scanner.nextDouble();
//        System.out.print("Coordenada Y de la primera esquina: ");
//        double y1 = scanner.nextDouble();
//        System.out.print("Coordenada X de la segunda esquina: ");
//        double x2 = scanner.nextDouble();
//        System.out.print("Coordenada Y de la segunda esquina: ");
//        double y2 = scanner.nextDouble();
//
//        return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
//    }
//}
