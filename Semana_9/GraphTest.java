// package test
package Semana_9;

import Semana_9.graph.GraphLink;

public class GraphTest {
    public static void main(String[] args) {
        // Crear un grafo de strings
        GraphLink<String> graph = new GraphLink<>();
        
        System.out.println("=== Prueba de addVertex ===");
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        System.out.println("Vértices agregados: A, B, C, D, E");
        System.out.println(graph);

        
        System.out.println("\n=== Prueba de addEdge ===");
        graph.addEdge("A", "B", 12);
        graph.addEdge("A", "D", 87);
        graph.addEdge("B", "E", 11);
        graph.addEdge("C", "A", 19);
        graph.addEdge("D", "B", 23);
        graph.addEdge("D", "C", 10);
        graph.addEdge("E", "D", 43);
        System.out.println("Aristas agregadas:");
        System.out.println("A-B, A-D, B-E, C-A, D-B, D-C, E-D");
        System.out.println(graph);
        
        
        System.out.println("\n=== Prueba de BSF===");
        graph.BSF("A");

        
        System.out.println("\n=== Prueba de removeEdge ===");
        graph.removeEdge("A", "D");
        System.out.println("Arista A-B eliminada");
        System.out.println(graph);
    

        System.out.println("\n=== Prueba de removeVertex ===");
        graph.removeVertex("C");
        System.out.println("Vértice C eliminado");
        System.out.println(graph);

        System.out.println("\n=== Estado final del grafo ===");
        System.out.println(graph);


        System.out.println("\n=== Prueba de destroyGraph ===");
        graph.destroyGraph();
        System.out.println("Grafo destruido. Tamaño: ");
        System.out.println("Estado del grafo: " + graph);

    
    }
}    