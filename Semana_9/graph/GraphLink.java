package Semana_9.graph;

import Semana_9.list.ArrayListLink;
import Semana_9.list.ExceptionIsEmpty;
import Semana_9.list.ListLinked;
import Semana_9.list.Node;
import Semana_9.list.QueueLink;
import Semana_9.list.StackLink;

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
        System.out.println("No se encontró algunos de los nodos");
        return;
    }

    boolean removed = false;
    Node<Edges<E>> current = v1.listAdj.getHead();
    Node<Edges<E>> previous = null;

    while (current != null) {
        if (current.getData().getResDet().equals(v2)) {
            // Eliminar la arista sin recrear la lista
            if (previous == null) {
                v1.listAdj.setHead(current.getNext());
            } else {
                previous.setNext(current.getNext());
            }
            removed = true;
            break;
        }
        previous = current;
        current = current.getNext();
    }

    if (removed) {
        System.out.println("Arista eliminada exitosamente");
        if (!directed) {
            // Eliminar la arista inversa de v2 a v1 de forma iterativa
            Node<Edges<E>> currRev = v2.listAdj.getHead();
            Node<Edges<E>> prevRev = null;
            while (currRev != null) {
                if (currRev.getData().getResDet().equals(v1)) {
                    if (prevRev == null) {
                        v2.listAdj.setHead(currRev.getNext());
                    } else {
                        prevRev.setNext(currRev.getNext());
                    }
                    break;
                }
                prevRev = currRev;
                currRev = currRev.getNext();
            }
        }
    } else {
        System.out.println("No se encontró la arista a eliminar");
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

    System.out.print("Recorrido BFS desde " + v + ": ");

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
            System.err.println("Error: " + e.getMessage());
            break;
        }
    }
    
    System.out.println(); // Nueva línea al final
}

    // ==================== EJERCICIO 1 - SIN ARRAYLIST INTERNO ====================
    
    /**
     * Encuentra el camino más corto entre dos vértices usando BFS
     * @param v Vértice de origen
     * @param z Vértice de destino
     * @return ArrayListLink con el camino desde v hasta z, null si no existe camino
     */
    public ArrayListLink<E> bfsPath(E v, E z) {
        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return null;
        }

        if (v == null || z == null) {
            System.out.println("Los vértices no pueden ser nulos");
            return null;
        }

        Vertex<E> startVertex = findVertexData(v);
        Vertex<E> endVertex = findVertexData(z);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo");
            return null;
        }

        // Si el vértice origen y destino son el mismo
        if (v.equals(z)) {
            ArrayListLink<E> path = new ArrayListLink<>();
            path.add(v);
            return path;
        }

        // Estructuras para BFS - solo listas enlazadas propias
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        QueueLink<Vertex<E>> queue = new QueueLink<>();
        
        // Listas enlazadas para mantener la relación padre-hijo
        ListLinked<Vertex<E>> vertices = new ListLinked<>();
        ListLinked<Vertex<E>> padres = new ListLinked<>();

        // Inicializar BFS
        visited.add(startVertex);
        queue.enqueue(startVertex);
        vertices.add(startVertex);
        padres.add(null);  // El vértice inicial no tiene padre

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            try {
                Vertex<E> current = queue.dequeue();

                Node<Edges<E>> adjNode = current.listAdj.getHead();
                while (adjNode != null && !found) {
                    Vertex<E> adjVertex = adjNode.getData().getResDet();
                    
                    if (!isVisited(visited, adjVertex)) {
                        visited.add(adjVertex);
                        queue.enqueue(adjVertex);
                        
                        // Agregar relación padre-hijo usando listas enlazadas
                        vertices.add(adjVertex);
                        padres.add(current);

                        // Si encontramos el vértice destino
                        if (adjVertex.equals(endVertex)) {
                            found = true;
                        }
                    }
                    adjNode = adjNode.getNext();
                }
            } catch (ExceptionIsEmpty e) {
                System.err.println("Error en BFS: " + e.getMessage());
                break;
            }
        }

        // Si no se encontró el camino
        if (!found) {
            System.out.println("No existe camino entre " + startVertex.getData() + " y " + endVertex.getData());
            return null;
        }

        // Reconstruir el camino usando solo las listas enlazadas
        return reconstruirCamino(startVertex, endVertex, vertices, padres);
    }

    /**
     * Reconstruye el camino desde el destino hasta el origen
     * usando solo las estructuras ListLinked del proyecto
     */
    private ArrayListLink<E> reconstruirCamino(Vertex<E> start, Vertex<E> end, 
                                                   ListLinked<Vertex<E>> vertices, 
                                                   ListLinked<Vertex<E>> padres) {
        
        // Lista temporal para construir el camino (de destino a origen)
        ListLinked<E> caminoTemporal = new ListLinked<>();
        
        Vertex<E> current = end;
        
        // Recorrer desde el destino hasta el origen
        while (current != null) {
            caminoTemporal.add(current.getData());
            current = buscarPadre(current, vertices, padres);
        }
        
        // Convertir a ArrayListLink invirtiendo el orden (de origen a destino)
        ArrayListLink<E> resultado = new ArrayListLink<>();
        
        // Contar elementos en la lista temporal
        int tamaño = contarElementos(caminoTemporal);
        
        // Crear array temporal para facilitar la inversión
        Object[] temp = new Object[tamaño];
        
        Node<E> node = caminoTemporal.getHead();
        for (int i = 0; i < tamaño && node != null; i++) {
            temp[i] = node.getData();
            node = node.getNext();
        }
        
        // Agregar al ArrayListLink en orden inverso
        for (int i = tamaño - 1; i >= 0; i--) {
            resultado.add((E) temp[i]);
        }
        
        return resultado;
    }

    /**
     * Busca el padre de un vértice en las listas enlazadas
     */
    private Vertex<E> buscarPadre(Vertex<E> vertex, ListLinked<Vertex<E>> vertices, ListLinked<Vertex<E>> padres) {
        Node<Vertex<E>> nodeVertices = vertices.getHead();
        Node<Vertex<E>> nodePadres = padres.getHead();
        
        // Recorrer ambas listas en paralelo
        while (nodeVertices != null && nodePadres != null) {
            if (nodeVertices.getData().equals(vertex)) {
                return nodePadres.getData(); // Puede ser null para el vértice inicial
            }
            nodeVertices = nodeVertices.getNext();
            nodePadres = nodePadres.getNext();
        }
        
        return null; // No se encontró
    }

    /**
     * Cuenta los elementos en una ListLinked
     */
    private int contarElementos(ListLinked<E> lista) {
        int count = 0;
        Node<E> current = lista.getHead();
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    /**
     * Método auxiliar para mostrar un camino de forma legible
     * @param path El camino a mostrar
     */
    public void mostrarCamino(ArrayListLink<E> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No hay camino que mostrar");
            return;
        }

        System.out.print("Camino: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        System.out.println("Longitud del camino: " + (path.size() - 1) + " aristas");
    }
    
    // ==================== EJERCICIO 2  ====================

    /**
     * Inserta una arista del vértice v a z con peso w
     * @param v Vértice origen
     * @param z Vértice destino
     * @param w Peso de la arista
     */
    public void insertEdgeWeight(E v, E z, int w) {
        if (v == null || z == null) {
            System.out.println("Los vértices no pueden ser nulos");
            return;
        }

        Vertex<E> vertex1 = findVertexData(v);
        Vertex<E> vertex2 = findVertexData(z);

        if (vertex1 == null || vertex2 == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo");
            return;
        }

        // Verificar si la arista ya existe
        Node<Edges<E>> current = vertex1.listAdj.getHead();
        while (current != null) {
            if (current.getData().getResDet().equals(vertex2)) {
                System.out.println("La arista ya existe, actualizando peso");
                current.getData().setWeight(w);
                
                // Si es no dirigido, actualizar también la arista inversa
                if (!directed) {
                    Node<Edges<E>> currentRev = vertex2.listAdj.getHead();
                    while (currentRev != null) {
                        if (currentRev.getData().getResDet().equals(vertex1)) {
                            currentRev.getData().setWeight(w);
                            break;
                        }
                        currentRev = currentRev.getNext();
                    }
                }
                return;
            }
            current = current.getNext();
        }

        // Crear nueva arista
        Edges<E> edge = new Edges<>(vertex2, w);
        vertex1.listAdj.add(edge);

        // Si es no dirigido, agregar arista inversa
        if (!directed) {
            Edges<E> reverseEdge = new Edges<>(vertex1, w);
            vertex2.listAdj.add(reverseEdge);
        }

        System.out.println("Arista agregada: " + v + " -> " + z + " con peso " + w);
    }

    /**
     * Calcula la ruta más corta entre dos vértices usando el algoritmo de Dijkstra
     * @param v Vértice origen
     * @param z Vértice destino
     * @return ArrayListLink con la ruta más corta, null si no existe
     */
    public ArrayListLink<E> shortPath(E v, E z) {
        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return null;
        }

        if (v == null || z == null) {
            System.out.println("Los vértices no pueden ser nulos");
            return null;
        }

        Vertex<E> startVertex = findVertexData(v);
        Vertex<E> endVertex = findVertexData(z);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo");
            return null;
        }

        if (v.equals(z)) {
            ArrayListLink<E> path = new ArrayListLink<>();
            path.add(v);
            return path;
        }

        // Estructuras para Dijkstra - usando solo listas enlazadas paralelas
        ListLinked<Vertex<E>> verticesDistancia = new ListLinked<>();
        ListLinked<Integer> distancias = new ListLinked<>();
        ListLinked<Vertex<E>> verticesPredecesores = new ListLinked<>();
        ListLinked<Vertex<E>> predecesores = new ListLinked<>();
        PriorityQueueLink<VertexDistance<E>> cola = new PriorityQueueLink<>();
        ListLinked<Vertex<E>> visitados = new ListLinked<>();

        // Inicializar distancias usando listas paralelas
        Node<Vertex<E>> current = listVertex.getHead();
        while (current != null) {
            Vertex<E> vertex = current.getData();
            verticesDistancia.add(vertex);
            verticesPredecesores.add(vertex);
            
            if (vertex.equals(startVertex)) {
                distancias.add(0);
                cola.offer(new VertexDistance<>(vertex, 0));
            } else {
                distancias.add(Integer.MAX_VALUE);
            }
            predecesores.add(null);
            current = current.getNext();
        }

        while (!cola.isEmpty()) {
            VertexDistance<E> actual = cola.poll();
            Vertex<E> u = actual.getVertex();

            if (isVisited(visitados, u)) {
                continue;
            }

            visitados.add(u);

            if (u.equals(endVertex)) {
                break;
            }

            // Examinar vecinos
            Node<Edges<E>> adjNode = u.listAdj.getHead();
            while (adjNode != null) {
                Vertex<E> vecino = adjNode.getData().getResDet();
                int peso = adjNode.getData().getWeight();
                
                if (!isVisited(visitados, vecino)) {
                    int distanciaActual = getDistancia(u, verticesDistancia, distancias);
                    int nuevaDistancia = distanciaActual + peso;
                    int distanciaVecino = getDistancia(vecino, verticesDistancia, distancias);
                    
                    if (nuevaDistancia < distanciaVecino) {
                        setDistancia(vecino, nuevaDistancia, verticesDistancia, distancias);
                        setPredecesor(vecino, u, verticesPredecesores, predecesores);
                        cola.offer(new VertexDistance<>(vecino, nuevaDistancia));
                    }
                }
                adjNode = adjNode.getNext();
            }
        }

        // Verificar si se encontró un camino
        if (getDistancia(endVertex, verticesDistancia, distancias) == Integer.MAX_VALUE) {
            System.out.println("No existe camino entre " + v + " y " + z);
            return null;
        }

        // Reconstruir el camino
        ArrayListLink<E> path = new ArrayListLink<>();
        Vertex<E> currentVertex = endVertex;

        // Crear una lista temporal para construir el camino en orden inverso
        StackLink<E> tempStack = new StackLink<>();
        while (currentVertex != null) {
            tempStack.push(currentVertex.getData());
            currentVertex = getPredecesor(currentVertex, verticesPredecesores, predecesores);
        }

        // Pasar del stack al ArrayListLink para tener el orden correcto
        while (!tempStack.isEmpty()) {
            path.add(tempStack.pop());
        }

        return path;
    }

    /**
     * Verifica si el grafo es conexo
     * @return true si el grafo es conexo, false en caso contrario
     */
    public boolean isConexo() {
        if (isEmptyGraph()) {
            return true; // Un grafo vacío se considera conexo
        }

        int totalVertices = listVertex.size();
        if (totalVertices == 1) {
            return true; // Un grafo con un solo vértice es conexo
        }

        // Realizar BFS desde el primer vértice
        Vertex<E> startVertex = listVertex.getHead().getData();
        ListLinked<Vertex<E>> visited = new ListLinked<>();
        QueueLink<Vertex<E>> queue = new QueueLink<>();

        visited.add(startVertex);
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            try {
                Vertex<E> current = queue.dequeue();

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
                System.err.println("Error en BFS: " + e.getMessage());
                break;
            }
        }

        // El grafo es conexo si visitamos todos los vértices
        return visited.size() == totalVertices;
    }

    /**
     * Implementación del algoritmo de Dijkstra que retorna un Stack con la ruta más corta
     * @param v Vértice origen
     * @param w Vértice destino
     * @return Stack con la ruta más corta desde v hasta w
     */
    public StackLink<E> dijkstra(E v, E w) {
        StackLink<E> result = new StackLink<>();

        if (isEmptyGraph()) {
            System.out.println("El grafo está vacío");
            return result;
        }

        if (v == null || w == null) {
            System.out.println("Los vértices no pueden ser nulos");
            return result;
        }

        Vertex<E> startVertex = findVertexData(v);
        Vertex<E> endVertex = findVertexData(w);

        if (startVertex == null || endVertex == null) {
            System.out.println("Uno o ambos vértices no existen en el grafo");
            return result;
        }

        if (v.equals(w)) {
            result.push(v);
            return result;
        }

        // Usar el método shortPath para obtener el camino
        ArrayListLink<E> path = shortPath(v, w);
        
        if (path == null || path.isEmpty()) {
            System.out.println("No existe camino entre " + v + " y " + w);
            return result;
        }

        // Convertir ArrayListLink a Stack (el último elemento del path será el top del stack)
        for (int i = path.size() - 1; i >= 0; i--) {
            result.push(path.get(i));
        }

        return result;
    }

    /**
     * Método auxiliar para mostrar un camino con su distancia total
     * @param path El camino a mostrar
     * @param origen Vértice origen
     * @param destino Vértice destino
     */
    public void mostrarCaminoConDistancia(ArrayListLink<E> path, E origen, E destino) {
        if (path == null || path.isEmpty()) {
            System.out.println("No hay camino que mostrar");
            return;
        }

        System.out.print("Camino más corto de " + origen + " a " + destino + ": ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        // Calcular distancia total
        int distanciaTotal = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            E actual = path.get(i);
            E siguiente = path.get(i + 1);
            
            Vertex<E> vertexActual = findVertexData(actual);
            Node<Edges<E>> adjNode = vertexActual.listAdj.getHead();
            
            while (adjNode != null) {
                if (adjNode.getData().getResDet().getData().equals(siguiente)) {
                    distanciaTotal += adjNode.getData().getWeight();
                    break;
                }
                adjNode = adjNode.getNext();
            }
        }

        System.out.println();
        System.out.println("Distancia total: " + distanciaTotal);
        System.out.println("Número de aristas: " + (path.size() - 1));
    }

    /**
     * Método auxiliar para mostrar el contenido de un Stack
     * @param stack2 El stack a mostrar
     * @param mensaje Mensaje descriptivo
     */
    public void mostrarStack(StackLink<String> stack2, String mensaje) {
        if (stack2.isEmpty()) {
            System.out.println(mensaje + ": Stack vacío");
            return;
        }

        System.out.print(mensaje + ": ");
        StackLink<E> temp = new StackLink<>();
        
        // Mostrar el stack (el top es el primer elemento del camino)
        while (!stack2.isEmpty()) {
            E elemento = (E) stack2.pop();
            temp.push(elemento);
            System.out.print(elemento);
            if (!stack2.isEmpty()) {
                System.out.print(" -> ");
            }
        }
        
        // Restaurar el stack original
        while (!temp.isEmpty()) {
            stack2.push((String) temp.pop());
        }
        
        System.out.println();
    }

    // ==================== CLASE AUXILIAR PARA DIJKSTRA ====================

    /**
     * Clase auxiliar para manejar vértices con distancias en la cola de prioridad
     */
    private static class VertexDistance<E> implements Comparable<VertexDistance<E>> {
        private Vertex<E> vertex;
        private int distance;

        public VertexDistance(Vertex<E> vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<E> getVertex() {
            return vertex;
        }

        @Override
        public int compareTo(VertexDistance<E> other) {
            return Integer.compare(this.distance, other.distance);
        }
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

    // ==================== MÉTODOS AUXILIARES PARA LISTAS PARALELAS ====================
    
    /**
     * Busca la distancia de un vértice en las listas paralelas
     */
    private Integer getDistancia(Vertex<E> vertex, ListLinked<Vertex<E>> vertices, ListLinked<Integer> distancias) {
        Node<Vertex<E>> vNode = vertices.getHead();
        Node<Integer> dNode = distancias.getHead();
        
        while (vNode != null && dNode != null) {
            if (vNode.getData().equals(vertex)) {
                return dNode.getData();
            }
            vNode = vNode.getNext();
            dNode = dNode.getNext();
        }
        return Integer.MAX_VALUE; // No encontrado
    }
    
    /**
     * Actualiza la distancia de un vértice en las listas paralelas
     */
    private void setDistancia(Vertex<E> vertex, Integer nuevaDistancia, ListLinked<Vertex<E>> vertices, ListLinked<Integer> distancias) {
        Node<Vertex<E>> vNode = vertices.getHead();
        Node<Integer> dNode = distancias.getHead();
        
        while (vNode != null && dNode != null) {
            if (vNode.getData().equals(vertex)) {
                dNode.setData(nuevaDistancia);
                return;
            }
            vNode = vNode.getNext();
            dNode = dNode.getNext();
        }
    }
    
    /**
     * Busca el predecesor de un vértice en las listas paralelas
     */
    private Vertex<E> getPredecesor(Vertex<E> vertex, ListLinked<Vertex<E>> vertices, ListLinked<Vertex<E>> predecesores) {
        Node<Vertex<E>> vNode = vertices.getHead();
        Node<Vertex<E>> pNode = predecesores.getHead();
        
        while (vNode != null && pNode != null) {
            if (vNode.getData().equals(vertex)) {
                return pNode.getData(); // Puede ser null para el vértice inicial
            }
            vNode = vNode.getNext();
            pNode = pNode.getNext();
        }
        return null; // No encontrado
    }
    
    /**
     * Actualiza el predecesor de un vértice en las listas paralelas
     */
    private void setPredecesor(Vertex<E> vertex, Vertex<E> predecesor, ListLinked<Vertex<E>> vertices, ListLinked<Vertex<E>> predecesores) {
        Node<Vertex<E>> vNode = vertices.getHead();
        Node<Vertex<E>> pNode = predecesores.getHead();
        
        while (vNode != null && pNode != null) {
            if (vNode.getData().equals(vertex)) {
                pNode.setData(predecesor);
                return;
            }
            vNode = vNode.getNext();
            pNode = pNode.getNext();
        }
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
