// filepath: /workspaces/Trabajos_AED/Semana_9/TestBFS.java
package Semana_9;

import Semana_9.graph.GraphLink;
import Semana_9.list.ArrayListLink;

public class TestBFS {
    public static void main(String[] args) {
        GraphLink<String> graph = new GraphLink<>(false); // Grafo no dirigido

        // Agregar vértices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        // Agregar aristas
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "D", 1);
        graph.addEdge("C", "E", 1);
        graph.addEdge("D", "F", 1);
        graph.addEdge("E", "F", 1);

        System.out.println("=== PRUEBAS DE BFS ===");
        
        // Prueba del BFS normal
        System.out.println("\n1. Recorrido BFS desde A:");
        graph.BSF("A");
        System.out.println();

        // Pruebas del bfsPath
        System.out.println("\n2. Búsqueda de caminos:");
        
        // Camino que existe
        ArrayListLink<String> path1 = graph.bfsPath("A", "F");
        graph.mostrarCamino(path1);

        // Camino corto
        ArrayListLink<String> path2 = graph.bfsPath("A", "B");
        graph.mostrarCamino(path2);

        // Mismo vértice
        ArrayListLink<String> path3 = graph.bfsPath("A", "A");
        graph.mostrarCamino(path3);

        // Vértice que no existe
        ArrayListLink<String> path4 = graph.bfsPath("A", "Z");
        graph.mostrarCamino(path4);

        // Agregar un vértice aislado para probar camino inexistente
        graph.addVertex("G");
        ArrayListLink<String> path5 = graph.bfsPath("A", "G");
        graph.mostrarCamino(path5);
    }
}