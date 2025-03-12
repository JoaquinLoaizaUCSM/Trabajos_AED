import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    /**
     * Método para mostrar la información de un rectángulo
     */
    public static void mostrarRectangulo(Rectangulo rect, String nombre) {
        System.out.println("Rectangulo " + nombre + " = " + rect.toString());
    }
    
    /**
     * Método para solicitar las coordenadas de un rectángulo al usuario
     */
    public static Rectangulo solicitarRectangulo(Scanner scanner, String nombre) {
        System.out.println("\nIngrese las coordenadas para el rectángulo " + nombre + ":");
        System.out.print("Coordenada X de la primera esquina: ");
        double x1 = scanner.nextDouble();
        System.out.print("Coordenada Y de la primera esquina: ");
        double y1 = scanner.nextDouble();
        System.out.print("Coordenada X de la segunda esquina: ");
        double x2 = scanner.nextDouble();
        System.out.print("Coordenada Y de la segunda esquina: ");
        double y2 = scanner.nextDouble();
        
        return new Rectangulo(new Coordenada(x1, y1), new Coordenada(x2, y2));
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        
        // Crear rectángulos
        Rectangulo rectanguloA = solicitarRectangulo(scanner, "A");
        Rectangulo rectanguloB = solicitarRectangulo(scanner, "B");
        
        scanner.close();
        System.out.println("\n--- Resultados ---");
        
        // Mostrar información de los rectángulos
        mostrarRectangulo(rectanguloA, "A");
        mostrarRectangulo(rectanguloB, "B");
        
        // Verificar relación entre los rectángulos
        Verificador verificador = new Verificador();
        int caso = verificador.verificarRectangulos(rectanguloA, rectanguloB);
        
        // Mostrar el resultado según el caso
        switch (caso) {
            case Verificador.SOBREPONEN:
                System.out.println("Rectangulos A y B se sobreponen.");
                // Calcular y mostrar el área de sobreposición
                Rectangulo sobreposicion = verificador.calcularSobreposicion(rectanguloA, rectanguloB);
                double areaSobreposicion = sobreposicion.calculoArea();
                System.out.println("Area de sobreposicion = " + df.format(areaSobreposicion));
                break;
            case Verificador.JUNTAN:
                System.out.println("Rectangulos A y B se juntan.");
                break;
            case Verificador.DISJUNTOS:
                System.out.println("Rectangulos A y B son disjuntos.");
                break;
        }
    }
}
