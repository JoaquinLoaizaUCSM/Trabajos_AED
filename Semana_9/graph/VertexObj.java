package Semana_9.graph;

public class VertexObj<V, E> 
{
    public V info;
    public int position;

    public VertexObj(V info, int position) 
    {
        this.info = info;
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof VertexObj<?, ?>) 
        {
            VertexObj<V, E> v = (VertexObj<V, E>) o;
            return this.info.equals(v.info);
        }
        return false;
    }
}
