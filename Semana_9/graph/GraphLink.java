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
                System.err.println("Error inesperado: " + e.getMessage());
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

    

    
}
