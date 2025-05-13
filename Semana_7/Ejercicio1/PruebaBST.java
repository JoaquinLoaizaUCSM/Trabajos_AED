package Semana_7.Ejercicio1;

import Semana_7.Actividad4.Exceptions.*;

public class PruebaBST {
    public static void main(String[] args) {
        LinkedBST<Integer> bst = new LinkedBST<>();

        try {
            // Insertando valores
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);

            System.out.println("Arbol inicial (in-order): " + bst.toString());

            // Probando destroyNodes
            System.out.println("\nEliminando todos los nodos del arbol...");
            bst.destroyNodes();
            System.out.println("Arbol destruido. ¿Esta vacio?: " + bst.isEmpty());

            // Reinsercion para siguientes pruebas
            bst.insert(50);
            bst.insert(30);
            bst.insert(70);
            bst.insert(20);
            bst.insert(40);
            bst.insert(60);
            bst.insert(80);

            System.out.println("\nArbol reinsertado (in-order): " + bst.toString());

            // Probando countAllNodes y countNodes
            System.out.println("\nNumero de nodos no-hojas: " + bst.countAllNodes());
            System.out.println("Numero de nodos no-hojas (usando countNodes): " + bst.countAllNodes());

            // Probando height
            System.out.println("\nAltura del subarbol con raiz 30: " + bst.height(30));
            System.out.println("Altura del subarbol con raiz 100 (no existe): " + bst.height(100));

            // Probando amplitude
            System.out.println("\nAmplitud en el nivel 2: " + bst.amplitude(2));

        } catch (ItemDuplicated e) {
            System.out.println("Error de duplicado: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
