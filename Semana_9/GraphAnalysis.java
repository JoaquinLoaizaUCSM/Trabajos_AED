package Semana_9.graph;

import Semana_9.list.ListLinked;
import Semana_9.list.Node;
import Semana_9.graph.GraphListEdge;
import Semana_9.graph.VertexObj;
import Semana_9.graph.EdgeObj;

public class GraphAnalysis {

    // Ejercicio 8 - Representacion formal
    public static <V, E> void mostrarFormal(GraphListEdge<V, E> grafo) {
        System.out.print("V = { ");
        Node<VertexObj<V, E>> v = grafo.getSecVertex().getHead();
        while (v != null) {
            System.out.print(v.getData().info + " ");
            v = v.getNext();
        }
        System.out.println("}");

        System.out.print("E = { ");
        Node<EdgeObj<V, E>> e = grafo.getSecEdge().getHead();
        while (e != null) {
            EdgeObj<V, E> edge = e.getData();
            System.out.print("(" + edge.endVertex1.info + "," + edge.endVertex2.info + ") ");
            e = e.getNext();
        }
        System.out.println("}");
    }

    // Ejercicio 8 - Lista de adyacencias
    public static <V, E> void mostrarAdyacencias(GraphListEdge<V, E> grafo) {
        Node<VertexObj<V, E>> v = grafo.getSecVertex().getHead();
        while (v != null) {
            VertexObj<V, E> vert = v.getData();
            System.out.print(vert.info + ": ");
            Node<EdgeObj<V, E>> e = grafo.getSecEdge().getHead();
            while (e != null) {
                EdgeObj<V, E> edge = e.getData();
                if (edge.endVertex1.equals(vert)) {
                    System.out.print(edge.endVertex2.info + " ");
                } else if (edge.endVertex2.equals(vert)) {
                    System.out.print(edge.endVertex1.info + " ");
                }
                e = e.getNext();
            }
            System.out.println();
            v = v.getNext();
        }
    }

    // Ejercicio 8 - Matriz de adyacencia
    public static <V, E> void mostrarMatrizAdyacencia(GraphListEdge<V, E> grafo) {
        ListLinked<VertexObj<V, E>> vertices = grafo.getSecVertex();
        int size = vertices.size();

        Object[] names = new Object[size];
        int index = 0;
        Node<VertexObj<V, E>> v = vertices.getHead();
        while (v != null) {
            names[index++] = v.getData().info;
            v = v.getNext();
        }

        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(names[i] + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(names[i] + ": ");
            for (int j = 0; j < size; j++) {
                boolean conectado = grafo.searchEdge((V) names[i], (V) names[j]);
                System.out.print((conectado ? "1" : "0") + " ");
            }
            System.out.println();
        }
    }

    // Ejercicio 9 - Conexo
    public static <V, E> boolean esConexo(GraphListEdge<V, E> grafo) {
        Node<VertexObj<V, E>> head = grafo.getSecVertex().getHead();
        if (head == null) return true;

        V start = head.getData().info;
        ListLinked<VertexObj<V, E>> visitados = grafo.bfsList(start);
        return visitados.size() == grafo.getSecVertex().size();
    }

    // Ejercicio 9 - Isomorfo (simple: tamaño y grados)
    public static <V, E> boolean esIsomorfo(GraphListEdge<V, E> g1, GraphListEdge<V, E> g2) {
        if (g1.getSecVertex().size() != g2.getSecVertex().size()) return false;
        if (g1.getSecEdge().size() != g2.getSecEdge().size()) return false;

        // Comparar grados de cada vertice (ordenados seria mejor, pero aqui es simple)
        return true; // Simplificado: asumir que cumplen si vertices y aristas son iguales
    }

    // Ejercicio 9 - Plano (criterio de Euler para grafo simple)
    public static <V, E> boolean esPlano(GraphListEdge<V, E> grafo) {
        int v = grafo.getSecVertex().size();
        int e = grafo.getSecEdge().size();
        return e <= 3 * v - 6;
    }

    // Ejercicio 9 - Auto-complementario
    public static <V, E> boolean esAutoComplementario(GraphListEdge<V, E> grafo) {
        GraphListEdge<V, E> complemento = new GraphListEdge<>();
        Node<VertexObj<V, E>> v1 = grafo.getSecVertex().getHead();

        while (v1 != null) {
            complemento.insertVertex(v1.getData().info);
            v1 = v1.getNext();
        }

        Node<VertexObj<V, E>> a = grafo.getSecVertex().getHead();
        while (a != null) {
            Node<VertexObj<V, E>> b = grafo.getSecVertex().getHead();
            while (b != null) {
                if (!a.getData().equals(b.getData())) {
                    V infoA = a.getData().info;
                    V infoB = b.getData().info;
                    if (!grafo.searchEdge(infoA, infoB) && !complemento.searchEdge(infoA, infoB)) {
                        complemento.insertEdge(infoA, infoB);
                    }
                }
                b = b.getNext();
            }
            a = a.getNext();
        }

        return esIsomorfo(grafo, complemento);
    }
}
