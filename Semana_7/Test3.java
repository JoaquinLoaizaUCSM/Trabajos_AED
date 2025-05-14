
public class Test3 {
    public static void main(String[] args) throws ItemNotFound, ItemDuplicated, ExceptionIsEmpty {

        // Crear y poblar el árbol de enteros
        LinkedBST<Integer> arbol = new LinkedBST<>();
        arbol.insert(15);
        arbol.insert(10);
        arbol.insert(20);
        arbol.insert(8);
        arbol.insert(12);
        arbol.insert(17);
        arbol.insert(25);
        arbol.insert(6);
        arbol.insert(9);
        
        System.out.println("\nPruebas en el árbol drawBST:");

        arbol.drawBST();
        
        
        

        LinkedBST<Integer> arbol2 = new LinkedBST<>();
        arbol2.insert(50);
        arbol2.insert(30);
        arbol2.insert(70);
        arbol2.insert(20);
        arbol2.insert(40);
        
        System.out.println("\nComparación de árboles:");
        System.out.println("¿Tienen la misma área? " + sameArea(arbol, arbol2));
        System.out.println("¿Tienen la misma área? " + sameArea(arbol, arbol));


                // Crear y poblar el árbol de cadenas con estructura jerárquica específica
        LinkedBST<String> t = new LinkedBST<>();
        t.insert("Sales");

        // Agregar hijos jerárquicos usando addChild de BSTree
        LinkedBST<String>.Node salesNode = t.searchNode("Sales");
        t.addChild(salesNode, "Domestic");
        t.addChild(salesNode, "International");

        LinkedBST<String>.Node intlNode = t.searchNode("International");
        t.addChild(intlNode, "Canada");
        t.addChild(intlNode, "S. America");
        t.addChild(intlNode, "Overseas");

        LinkedBST<String>.Node ovsNode = t.searchNode("Overseas");
        t.addChild(ovsNode, "Africa");
        t.addChild(ovsNode, "Europe");
        t.addChild(ovsNode, "Asia");
        t.addChild(ovsNode, "Australia");

        // Imprimir representación del árbol t
        System.out.println("Árbol t:");
        t.parenthesize();

        t.drawBST();
    }

    public static boolean sameArea(LinkedBST<?> arb1, LinkedBST<?> arb2) throws ExceptionIsEmpty {
        if (arb1 == arb2) {
            return true;
        }
        return arb1.areaBST() == arb2.areaBST();
    }


    
    
}

