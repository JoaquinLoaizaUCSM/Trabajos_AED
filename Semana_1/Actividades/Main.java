import java.text.DecimalFormat;
import java.util.Scanner;


public class Main 
{
    public static void mostrarRectangulo(Rectangulo rect, String nombre) 
    {
        System.out.println("Rectangulo " + nombre + " = " + rect.toString());
    }
    
    
    public static Rectangulo solicitarRectangulo(Scanner scanner, String nombre) 
    {
        System.out.println("\nIngrese las coordenadas para el rectangulo " + nombre + ":");
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
    
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("0.00");
        
        ContainerRect container = new ContainerRect(5);
        
        Rectangulo rectanguloA = solicitarRectangulo(scanner, "A");
        container.addRectangulo(rectanguloA);
        Rectangulo rectanguloB = solicitarRectangulo(scanner, "B");
        container.addRectangulo(rectanguloB);
        
        scanner.close();
        
        System.out.println("\n--- Resultados ---");
        
        mostrarRectangulo(rectanguloA, "A");
        mostrarRectangulo(rectanguloB, "B");

        // Verificamos el caso del rectangulo con los metodos de clase
        if (Verificador.esSobrePos(rectanguloA, rectanguloB))
        {
            System.out.println("Rectangulos A y B se sobreponen.");
            Rectangulo sobreposicion = Verificador.calcularSobreposicion(rectanguloA, rectanguloB);
            double areaSobreposicion = sobreposicion.calculoArea();
            System.out.println("Area de sobreposicion = " + df.format(areaSobreposicion));
        }
        else if (Verificador.esJunto(rectanguloA, rectanguloB))
        {
            System.out.println("Rectangulos A y B se juntan.");
        }
        else if (Verificador.esDisjunto(rectanguloA, rectanguloB))
        {
            System.out.println("Rectangulos A y B son disjuntos.");
        }

        System.out.println(container.toString());

    }
}
