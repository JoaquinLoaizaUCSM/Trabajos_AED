package Semana_9.graph;

public class EdgeObj<V, E> {
    public E info;
    public VertexObj<V, E> endVertex1;
    public VertexObj<V, E> endVertex2;
    public int position;

    public EdgeObj(VertexObj<V, E> v1, VertexObj<V, E> v2, E info, int position) {
        this.endVertex1 = v1;
        this.endVertex2 = v2;
        this.info = info;
        this.position = position;
    }
}
