package Semana_9;

import Semana_9.graph.ArrayListLink;
import Semana_9.graph.GraphLink;

public class TestDijkstra {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>(false); // Grafo no dirigido

        // Agregar vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");

        // Agregar aristas con pesos
        graph.insertEdgeWeight("A", "B", 4);
        graph.insertEdgeWeight("A", "C", 2);
        graph.insertEdgeWeight("B", "C", 1);
        graph.insertEdgeWeight("B", "D", 5);
        graph.insertEdgeWeight("C", "D", 8);
        graph.insertEdgeWeight("C", "E", 10);
        graph.insertEdgeWeight("D", "E", 2);

        System.out.println("=== PRUEBAS DE ALGORITMOS ===");

        // Mostrar representaciones del grafo
        graph.mostrarAdyacencias();

        // Verificar conectividad
        System.out.println("\n¿El grafo es conexo? " + graph.isConexo());

        // Probar camino más corto con Dijkstra
        System.out.println("\n=== CAMINOS MÁS CORTOS (shortPath) ===");
        ArrayListLink<String> path1 = graph.shortPath("A", "E");
        graph.mostrarCaminoConDistancia(path1, "A", "E");

        ArrayListLink<String> path2 = graph.shortPath("A", "D");
        graph.mostrarCaminoConDistancia(path2, "A", "D");

        // Probar Dijkstra con Stack
        System.out.println("\n=== DIJKSTRA CON STACK ===");
        Semana_9.graph.StackLink<String> stack1 = graph.dijkstra("A", "E");
        graph.mostrarStack(stack1, "Camino A -> E");

        Semana_9.graph.StackLink<String> stack2 = graph.dijkstra("B", "E");
        graph.mostrarStack(stack2, "Camino B -> E");

        // Probar con vértice aislado
        graph.addVertex("F");
        System.out.println("\nDespués de agregar vértice aislado F:");
        System.out.println("¿El grafo es conexo? " + graph.isConexo());

        ArrayListLink<String> path3 = graph.shortPath("A", "F");
        graph.mostrarCaminoConDistancia(path3, "A", "F");
    }
}