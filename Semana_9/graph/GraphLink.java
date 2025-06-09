package Semana_9.graph;

import Semana_9.list.ExceptionIsEmpty;
import Semana_9.list.ListLinked;
import Semana_9.list.Node;
import Semana_9.list.QueueLink;

public class GraphLink<E>{

    protected ListLinked<Vertex<E>> listVertex;
    boolean directed;

    public GraphLink(){
        this.listVertex = new ListLinked<Vertex<E>>();
        this.directed = false; // Por defecto, el grafo es no dirigido
    }

    public GraphLink(boolean directed){
        this.listVertex = new ListLinked<Vertex<E>>();
        this.directed = directed; // El grafo puede ser dirigido o no dirigido
    }

    public void addVertex(E data){
        if (findVertexData(data) != null) {
            return ;
        }

        Vertex<E> newVertex = new Vertex<>(data);
        listVertex.add(newVertex);
    }

    public void addEdge(E data1, E data2, int weight) {

        Vertex<E> v1 = findVertexData(data1);
        Vertex<E> v2 = findVertexData(data2);

        if (v1 == null || v2 == null) {
            System.out.println("No se encontro algunos de los nodos");
            return ;
        }

        Edges<E> edge = new Edges<>(v2, weight);
        v1.listAdj.add(edge);

        if (!directed) {
            Edges<E> reverseEdge = new Edges<>(v1, weight);
            v2.listAdj.add(reverseEdge);
        }

        System.out.println("Arista agregada exitosamente: " + data1 + " -> " + data2 + " con peso " + weight);

    }


    public boolean searchVertex(Vertex<E> data) {
        return findVertex(data) != null;
    }


    public Vertex<E> findVertex(Vertex<E> data) {
        return findVertexData(data.getData());
    }

    public Vertex<E> findVertexData(E data) {
    Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            if (current.getData().getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
    return null;
    }

    public Edges<E> searchEdge(E data){
        Vertex<E> vertex = findVertexData(data);
        if (vertex == null) {
            return null;
        }

        Node<Edges<E>> current = vertex.listAdj.getHead();
        while (current != null) {
            if (current.getData().getResDet().getData().equals(data)) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    public void removeVertex(E data) {
        Vertex<E> vertex = findVertexData(data);
        if (vertex == null) {
            System.out.println("El vertice no existe");
            return;
        }

        listVertex.remove(vertex);

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            current.getData().listAdj.remove(new Edges<>(vertex));
            current = current.getNext();
        }
    }

    public void removeEdge(E data1, E data2) {
        if (data1 == null || data2 == null) {
            System.out.println("Los datos de los nodos no pueden ser nulos");
            return;
        }
        Vertex<E> v1 = findVertexData(data1);
        Vertex<E> v2 = findVertexData(data2);

        if (v1 == null || v2 == null) {
            System.out.println("No se encontro algunos de los nodos");
            return ;
        }

        Node<Edges<E>> current = v1.listAdj.getHead();
        Node<Edges<E>> previous = null;

        while (current != null) {
            if (current.getData().getResDet().equals(v2)) {
                // Arista encontrada, removerla
                if (previous == null) {
                    // Es el primer nodo
                    v1.listAdj = new ListLinked<Edges<E>>();
                    Node<Edges<E>> next = current.getNext();
                    while (next != null) {
                        v1.listAdj.add(next.getData());
                        next = next.getNext();
                    }
                } else {
                    previous.setNext(current.getNext());
                }
                System.out.println("Arista eliminada exitosamente");

                if (!directed) {
                    // Si es no dirigido, eliminar la arista en el otro sentido
                    removeEdge(data2, data1);
                }
                return;
            }
            previous = current;
            current = current.getNext();
        }

        System.out.println("No se encontró la arista a eliminar");
    }

    public void BSF(E v) {
        if (isEmptyGraph()) {
            System.out.println("El grafo esta vacio");
            return;
        }

        if (v == null) {
            System.out.println("El vertice no puede ser nulo");
            return;
        }


        if (findVertexData(v) == null) {
            System.out.println("El vertice no existe");
            return;
        }
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        QueueLink<Vertex<E>> queue = new QueueLink<>();

        Vertex<E> startVertex = findVertexData(v);
        visited.add(startVertex);
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            try {
                Vertex<E> current = queue.dequeue();
                System.out.print(current.getData() + " ");

                Node<Edges<E>> adjNode = current.listAdj.getHead();
                while (adjNode != null) {
                    Vertex<E> adjVertex = adjNode.getData().getResDet();
                    if (!isVisited(visited, adjVertex)) {
                        visited.add(adjVertex);
                        queue.enqueue(adjVertex);
                    }
                    adjNode = adjNode.getNext();
                }
            } catch (ExceptionIsEmpty e) {
                // Esta excepción no debería ocurrir ya que verificamos !queue.isEmpty()
                System.err.println("Error : " + e.getMessage());
                break;
            }
        }
    }

    private boolean isVisited(ListLinked<Vertex<E>> visited, Vertex<E> vertex) {
        Node<Vertex<E>> current = visited.getHead();
        while (current != null) {
            if (current.getData().equals(vertex)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }


    public void destroyGraph() {
        listVertex.destroyList();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            sb.append(current.getData().toString());
            current = current.getNext();
        }
        return sb.toString();
    }

    public boolean isEmptyGraph() {
        return listVertex.getHead() == null;
    }

    // ==================== EJERCICIO 5  ====================

    /**
     * Calcula el grado de un nodo
     * @param dato El dato del nodo
     * @return El grado del nodo, o -1 si no existe
     */
    public int gradoNodo(E dato) {
        Vertex<E> v = findVertexData(dato);
        if (v == null) {
            System.out.println("El nodo " + dato + " no existe en el grafo");
            return -1;
        }
        return v.listAdj.size();
    }

    /**
     * Verifica si el grafo es de tipo camino (path graph)
     * Un camino tiene exactamente 2 nodos de grado 1 (extremos) y el resto de grado 2
     */
    public boolean esCamino() {
        if (isEmptyGraph()) return false;

        int vertices = listVertex.size();
        if (vertices < 2) return false;

        int extremos = 0;
        int intermedios = 0;

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            int grado = current.getData().listAdj.size();
            if (grado == 1) {
                extremos++;
            } else if (grado == 2) {
                intermedios++;
            } else {
                return false; // Un nodo con grado != 1 o 2 no es válido en un camino
            }
            current = current.getNext();
        }

        // Debe haber exactamente 2 extremos y el resto intermedios
        return extremos == 2 && (extremos + intermedios == vertices);
    }

    /**
     * Verifica si el grafo es de tipo ciclo
     * Un ciclo tiene todos los nodos con grado 2
     */
    public boolean esCiclo() {
        if (isEmptyGraph()) return false;

        int vertices = listVertex.size();
        if (vertices < 3) return false; // Un ciclo necesita al menos 3 vértices

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            if (current.getData().listAdj.size() != 2) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Verifica si el grafo es de tipo rueda (wheel graph)
     * Una rueda tiene 1 nodo central conectado a todos los demás,
     * y los demás forman un ciclo entre ellos
     */
    public boolean esRueda() {
        if (isEmptyGraph()) return false;

        int vertices = listVertex.size();
        if (vertices < 4) return false; // Una rueda necesita al menos 4 vértices

        int centro = 0;
        int perifericos = 0;

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            int grado = current.getData().listAdj.size();
            if (grado == vertices - 1) {
                centro++; // Nodo central conectado a todos los demás
            } else if (grado == 3) {
                perifericos++; // Nodos periféricos conectados a 2 vecinos + centro
            } else {
                return false;
            }
            current = current.getNext();
        }

        return centro == 1 && perifericos == vertices - 1;
    }

    /**
     * Verifica si el grafo es completo
     * En un grafo completo, cada nodo está conectado a todos los demás
     */
    public boolean esCompleto() {
        if (isEmptyGraph()) return false;

        int n = listVertex.size();
        if (n < 2) return true; // Un grafo con 0 o 1 vértice es completo por definición

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            int gradoEsperado = n - 1; // En un grafo completo, cada nodo se conecta a n-1 nodos
            if (current.getData().listAdj.size() != gradoEsperado) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Método auxiliar para mostrar el tipo de grafo identificado
     */
    public void identificarTipoGrafo() {
        System.out.println("\n=== IDENTIFICACIÓN DEL TIPO DE GRAFO ===");
        System.out.println("Número de vértices: " + listVertex.size());

        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return;
        }

        // Mostrar grados de cada nodo
        System.out.println("\nGrados de los nodos:");
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            System.out.println("Nodo " + current.getData().getData() +
                    ": grado " + current.getData().listAdj.size());
            current = current.getNext();
        }

        // Identificar tipo
        System.out.println("\nTipo de grafo:");
        if (esCompleto()) {
            System.out.println("- Es un grafo COMPLETO (K" + listVertex.size() + ")");
        }
        if (esCiclo()) {
            System.out.println("- Es un grafo CICLO (C" + listVertex.size() + ")");
        }
        if (esCamino()) {
            System.out.println("- Es un grafo CAMINO (P" + listVertex.size() + ")");
        }
        if (esRueda()) {
            System.out.println("- Es un grafo RUEDA (W" + (listVertex.size() - 1) + ")");
        }

        boolean esNinguno = !esCompleto() && !esCiclo() && !esCamino() && !esRueda();
        if (esNinguno) {
            System.out.println("- No corresponde a ningún tipo especial");
        }
    }

    // ==================== EJERCICIO 6 - MEJORADO ====================

    /**
     * Muestra el grafo en notación formal: V = {vertices}, E = {aristas}
     */
    public void mostrarFormal() {
        System.out.println("\n=== REPRESENTACIÓN FORMAL DEL GRAFO ===");

        // Mostrar conjunto de vértices
        System.out.print("V = {");
        Node<Vertex<E>> current = listVertex.getHead();
        boolean primero = true;
        while (current != null) {
            if (!primero) System.out.print(", ");
            System.out.print(current.getData().getData());
            primero = false;
            current = current.getNext();
        }
        System.out.println("}");

        // Mostrar conjunto de aristas
        System.out.print("E = {");
        ListLinked<String> aristasMostradas = new ListLinked<>();
        current = listVertex.getHead();
        primero = true;

        while (current != null) {
            Vertex<E> origen = current.getData();
            Node<Edges<E>> adyacente = origen.listAdj.getHead();

            while (adyacente != null) {
                Vertex<E> destino = adyacente.getData().getResDet();
                String arista = "(" + origen.getData() + ", " + destino.getData() + ")";
                String aristaInversa = "(" + destino.getData() + ", " + origen.getData() + ")";

                // Para grafos no dirigidos, evitar duplicados
                if (directed || !contieneArista(aristasMostradas, aristaInversa)) {
                    if (!primero) System.out.print(", ");
                    System.out.print(arista);
                    aristasMostradas.add(arista);
                    primero = false;
                }

                adyacente = adyacente.getNext();
            }
            current = current.getNext();
        }
        System.out.println("}");

        // Información adicional
        System.out.println("Tipo: Grafo " + (directed ? "DIRIGIDO" : "NO DIRIGIDO"));
        System.out.println("Número de vértices: " + listVertex.size());
        System.out.println("Número de aristas: " + contarAristas());
    }

    /**
     * Muestra la lista de adyacencias de cada vértice
     */
    public void mostrarAdyacencias() {
        System.out.println("\n=== LISTA DE ADYACENCIAS ===");

        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return;
        }

        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            Vertex<E> v = current.getData();
            System.out.print(v.getData() + " -> [");

            Node<Edges<E>> ady = v.listAdj.getHead();
            boolean primero = true;
            while (ady != null) {
                if (!primero) System.out.print(", ");
                System.out.print(ady.getData().getResDet().getData());

                // Si hay pesos, mostrarlos
                if (ady.getData().getWeight() != 0) {
                    System.out.print("(" + ady.getData().getWeight() + ")");
                }

                primero = false;
                ady = ady.getNext();
            }

            System.out.println("]");
            current = current.getNext();
        }
    }

    /**
     * Genera la matriz de adyacencia del grafo
     * @return Matriz donde matriz[i][j] = 1 si hay arista de i a j, 0 en caso contrario
     */
    public int[][] matrizAdyacencia() {
        int n = listVertex.size();
        int[][] matriz = new int[n][n];

        // Crear array de vértices para mapear índices
        Vertex<E>[] vertices = new Vertex[n];
        Node<Vertex<E>> current = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = current.getData();
            current = current.getNext();
        }

        // Llenar la matriz
        for (int i = 0; i < n; i++) {
            Node<Edges<E>> ady = vertices[i].listAdj.getHead();
            while (ady != null) {
                Vertex<E> destino = ady.getData().getResDet();
                // Buscar índice del destino
                for (int j = 0; j < n; j++) {
                    if (vertices[j].equals(destino)) {
                        matriz[i][j] = 1;
                        break;
                    }
                }
                ady = ady.getNext();
            }
        }

        return matriz;
    }

    /**
     * Muestra la matriz de adyacencia de forma visual
     */
    public void mostrarMatrizAdyacencia() {
        System.out.println("\n=== MATRIZ DE ADYACENCIA ===");

        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return;
        }

        int[][] matriz = matrizAdyacencia();
        int n = listVertex.size();

        // Obtener vértices para encabezados
        Vertex<E>[] vertices = new Vertex[n];
        Node<Vertex<E>> current = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = current.getData();
            current = current.getNext();
        }

        // Imprimir encabezado de columnas
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.printf("%-4s", vertices[i].getData());
        }
        System.out.println();

        // Imprimir línea separadora
        System.out.print("    ");
        for (int i = 0; i < n; i++) {
            System.out.print("----");
        }
        System.out.println();

        // Imprimir filas
        for (int i = 0; i < n; i++) {
            System.out.printf("%-4s", vertices[i].getData());
            for (int j = 0; j < n; j++) {
                System.out.printf("%-4d", matriz[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Genera la matriz de adyacencia con pesos
     * @return Matriz donde matriz[i][j] = peso de la arista de i a j, 0 si no hay arista
     */
    public int[][] matrizAdyacenciaPesos() {
        int n = listVertex.size();
        int[][] matriz = new int[n][n];

        // Crear array de vértices
        Vertex<E>[] vertices = new Vertex[n];
        Node<Vertex<E>> current = listVertex.getHead();
        for (int i = 0; i < n; i++) {
            vertices[i] = current.getData();
            current = current.getNext();
        }

        // Llenar la matriz con pesos
        for (int i = 0; i < n; i++) {
            Node<Edges<E>> ady = vertices[i].listAdj.getHead();
            while (ady != null) {
                Vertex<E> destino = ady.getData().getResDet();
                int peso = ady.getData().getWeight();

                for (int j = 0; j < n; j++) {
                    if (vertices[j].equals(destino)) {
                        matriz[i][j] = peso;
                        break;
                    }
                }
                ady = ady.getNext();
            }
        }

        return matriz;
    }

    /**
     * Muestra todas las representaciones del grafo
     */
    public void mostrarTodasRepresentaciones() {
        System.out.println("\n========================================");
        System.out.println("    TODAS LAS REPRESENTACIONES");
        System.out.println("========================================");

        mostrarFormal();
        mostrarAdyacencias();
        mostrarMatrizAdyacencia();
        identificarTipoGrafo();
    }

    // ==================== MÉTODOS AUXILIARES ====================

    /**
     * Verifica si una arista ya fue mostrada (para evitar duplicados)
     */
    private boolean contieneArista(ListLinked<String> lista, String arista) {
        Node<String> current = lista.getHead();
        while (current != null) {
            if (current.getData().equals(arista)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Cuenta el número total de aristas en el grafo
     */
    private int contarAristas() {
        int total = 0;
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            total += current.getData().listAdj.size();
            current = current.getNext();
        }

        // Si el grafo no es dirigido, cada arista se cuenta dos veces
        return directed ? total : total / 2;
    }
}
}
