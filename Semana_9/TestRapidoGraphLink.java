// package test
package Semana_9;

import Semana_9.graph.GraphLink;

public class TestRapidoGraphLink {

    public static void main(String[] args) {
        System.out.println("====== TEST RÁPIDO DE GRAPHLINK ======\n");

        // Crear un grafo no dirigido
        GraphLink<String> grafo = new GraphLink<>(false);

        System.out.println("1. Probando Grafo CICLO (C4):");
        System.out.println("   A -- B");
        System.out.println("   |    |");
        System.out.println("   D -- C");

        // Agregar vértices
        grafo.addVertex("A");
        grafo.addVertex("B");
        grafo.addVertex("C");
        grafo.addVertex("D");

        // Crear ciclo
        grafo.addEdge("A", "B", 1);
        grafo.addEdge("B", "C", 1);
        grafo.addEdge("C", "D", 1);
        grafo.addEdge("D", "A", 1);

        // Probar ejercicio 5
        System.out.println("\n--- EJERCICIO 5: Identificación ---");
        System.out.println("Grado de A: " + grafo.gradoNodo("A"));
        System.out.println("¿Es camino? " + grafo.esCamino());
        System.out.println("¿Es ciclo? " + grafo.esCiclo());
        System.out.println("¿Es rueda? " + grafo.esRueda());
        System.out.println("¿Es completo? " + grafo.esCompleto());

        // Probar ejercicio 6
        System.out.println("\n--- EJERCICIO 6: Representaciones ---");
        grafo.mostrarFormal();
        grafo.mostrarAdyacencias();
        grafo.mostrarMatrizAdyacencia();

        // Test 2: Convertir a RUEDA agregando centro
        System.out.println("\n\n2. Convirtiendo a RUEDA (W4):");
        System.out.println("   Agregando centro X");

        grafo.addVertex("X");
        grafo.addEdge("X", "A", 2);
        grafo.addEdge("X", "B", 2);
        grafo.addEdge("X", "C", 2);
        grafo.addEdge("X", "D", 2);

        System.out.println("\n--- Verificación después de agregar centro ---");
        System.out.println("Grado de X (centro): " + grafo.gradoNodo("X"));
        System.out.println("Grado de A (periferia): " + grafo.gradoNodo("A"));
        System.out.println("¿Es rueda ahora? " + grafo.esRueda());

        grafo.mostrarAdyacencias();

        // Test 3: Grafo COMPLETO pequeño
        System.out.println("\n\n3. Probando Grafo COMPLETO (K3):");
        GraphLink<Integer> k3 = new GraphLink<>(false);

        k3.addVertex(1);
        k3.addVertex(2);
        k3.addVertex(3);

        k3.addEdge(1, 2, 1);
        k3.addEdge(1, 3, 1);
        k3.addEdge(2, 3, 1);

        System.out.println("¿Es completo K3? " + k3.esCompleto());
        k3.mostrarFormal();

        System.out.println("\n====== FIN DEL TEST ======");
    }
}