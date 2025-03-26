
public class TestGen2 
{
    public static void main(String[] args) 
    {
        System.out.println("\n*-----------------[GOLOSINAS]-----------------*:");
       Golosina golosina1 = new Golosina("Caramelo", 0.1);
       Golosina golosina2 = new Golosina("Chupetin", 0.3);
       Golosina golosina3 = new Golosina("Galleta", 1);
       Golosina golosina4 = new Golosina("Chicle", 0.1);
       Golosina golosina5 = new Golosina("Chocolate", 0.5);

       Cajoneria<Golosina> cajoneriaGolosina = new Cajoneria<Golosina>();

       cajoneriaGolosina.add(golosina1, "Verde");
       cajoneriaGolosina.add(golosina2, "Rojo");
       cajoneriaGolosina.add(golosina3, "Azul");
       cajoneriaGolosina.add(golosina4, "Morado");
       cajoneriaGolosina.add(golosina5, "Amarillo");

        System.out.println(cajoneriaGolosina);

        System.out.println("\nProbando metodo Busqueda:");
        System.out.println(cajoneriaGolosina.search(golosina3));

        System.out.println("\nProbando metodo Eliminar:");
        System.out.println("Se elimino " + cajoneriaGolosina.delete(golosina3));
        
        System.out.println(cajoneriaGolosina);

        System.out.println("\n*-----------------[CHOCOLATINAS]-----------------*");
        Chocolatina choco1 = new Chocolatina("Milky");
        Chocolatina choco2 = new Chocolatina("Ferreyro");
        Chocolatina choco3 = new Chocolatina("Feastable");
        Chocolatina choco4 = new Chocolatina("Sublime");
        Chocolatina choco5 = new Chocolatina("Triangulo");
 
        Cajoneria<Chocolatina> cajoneriaChocolatina = new Cajoneria<Chocolatina>();
 
        cajoneriaChocolatina.add(choco1, "Dorado");
        cajoneriaChocolatina.add(choco2, "Marron");
        cajoneriaChocolatina.add(choco3, "Negro");
        cajoneriaChocolatina.add(choco4, "Blanco");
        cajoneriaChocolatina.add(choco5, "Naranja");

        System.out.println(cajoneriaChocolatina);
        System.out.println("\nProbando metodo Busqueda:");
        System.out.println(cajoneriaChocolatina.search(choco5));

        System.out.println("\nProbando metodo Eliminar:");
        System.out.println("Se elimino " + cajoneriaChocolatina.delete(choco4));
        
        System.out.println(cajoneriaChocolatina);

    }
}
