package Semana_9.graph;
import Semana_9.list.ListLinked;
import Semana_9.list.QueueLink;
import Semana_9.list.Node;
import Semana_9.list.ExceptionIsEmpty;



public class GraphListEdge<V, E> {

    private ListLinked<VertexObj<V, E>> secVertex;
    private ListLinked<EdgeObj<V, E>> secEdge;

    public ListLinked<EdgeObj<V, E>> getSecEdge() {
        return secEdge;
    }

    public ListLinked<VertexObj<V, E>> getSecVertex() {
        return secVertex;
    }

    public GraphListEdge() {
        secVertex = new ListLinked<>();
        secEdge = new ListLinked<>();
    }

    public void insertVertex(V v) {
        if (searchVertex(v)) return;
        secVertex.add(new VertexObj<>(v, secVertex.size()));
    }

    public void insertEdge(V v, V z) {
        if (!searchVertex(v)) insertVertex(v);
        if (!searchVertex(z)) insertVertex(z);

        VertexObj<V, E> vert1 = getVertex(v);
        VertexObj<V, E> vert2 = getVertex(z);

        if (searchEdge(v, z)) return;

        secEdge.add(new EdgeObj<>(vert1, vert2, null, secEdge.size()));
    }

    public boolean searchVertex(V v) {
        Node<VertexObj<V, E>> current = secVertex.getHead();
        while (current != null) {
            if (current.getData().info.equals(v)) return true;
            current = current.getNext();
        }
        return false;
    }

    public boolean searchEdge(V v, V z) {
        Node<EdgeObj<V, E>> current = secEdge.getHead();
        while (current != null) {
            EdgeObj<V, E> edge = current.getData();
            if ((edge.endVertex1.info.equals(v) && edge.endVertex2.info.equals(z)) ||
                (edge.endVertex1.info.equals(z) && edge.endVertex2.info.equals(v))) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public void bfs(V start) {
        if (!searchVertex(start)) {
            System.out.println("Vértice no encontrado.");
            return;
        }

        ListLinked<VertexObj<V, E>> visited = new ListLinked<>();
        QueueLink<VertexObj<V, E>> queue = new QueueLink<>();

        VertexObj<V, E> startVertex = getVertex(start);
        visited.add(startVertex);
        queue.enqueue(startVertex);

        while (!queue.isEmpty()) {
            try {
                VertexObj<V, E> current = queue.dequeue();
                System.out.print(current.info + " ");

                Node<EdgeObj<V, E>> edgeNode = secEdge.getHead();
                while (edgeNode != null) {
                    EdgeObj<V, E> edge = edgeNode.getData();
                    VertexObj<V, E> neighbor = null;

                    if (edge.endVertex1.equals(current)) {
                        neighbor = edge.endVertex2;
                    } else if (edge.endVertex2.equals(current)) {
                        neighbor = edge.endVertex1;
                    }

                    if (neighbor != null && !isVisited(visited, neighbor)) {
                        visited.add(neighbor);
                        queue.enqueue(neighbor);
                    }

                    edgeNode = edgeNode.getNext();
                }

            } catch (ExceptionIsEmpty e) {
                System.err.println("Error en BFS.");
            }
        }
        System.out.println();
    }

    private boolean isVisited(ListLinked<VertexObj<V, E>> visited, VertexObj<V, E> v) {
        Node<VertexObj<V, E>> current = visited.getHead();
        while (current != null) {
            if (current.getData().info.equals(v.info)) return true;
            current = current.getNext();
        }
        return false;
    }

    private VertexObj<V, E> getVertex(V v) {
        Node<VertexObj<V, E>> current = secVertex.getHead();
        while (current != null) {
            if (current.getData().info.equals(v)) return current.getData();
            current = current.getNext();
        }
        return null;
    }

    public ListLinked<VertexObj<V, E>> bfsList(V start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'bfsList'");
    }


}
