package Semana_9.Ejercicio3;

import Semana_9.graph.GraphListEdge;

public class MainGraphListEdge {
    public static void main(String[] args) {
        GraphListEdge<String, Object> graph = new GraphListEdge<>();

        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        graph.insertEdge("A", "B");
        graph.insertEdge("A", "C");
        graph.insertEdge("B", "D");
        graph.insertEdge("C", "D");

        System.out.println("¿Existe el vértice B? " + graph.searchVertex("B"));
        System.out.println("¿Existe arista entre A y C? " + graph.searchEdge("A", "C"));
        System.out.println("¿Existe arista entre A y D? " + graph.searchEdge("A", "D"));

        System.out.println("\nRecorrido BFS desde A:");
        graph.bfs("A");
    }
}
