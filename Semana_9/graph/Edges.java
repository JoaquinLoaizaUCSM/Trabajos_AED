package Semana_9.graph;

public class Edges<E> {
    private Vertex<E> resDet;
    private int weight;


    public Edges(Vertex<E> v, int weight){
        resDet = v;
        this.weight = weight;
    }

    public Edges(Vertex<E> v){
        this(v, -1);
    }

    public Vertex<E> getResDet() {
        return resDet;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        if(this.weight > -1) return resDet.getData() + "["+this.weight+"], ";
        else return resDet.getData() + ", ";
    }



    public boolean equals(Object o ){
        if(o instanceof Edges<?>){
            Edges<E> e = (Edges<E>)o;
            return this.resDet.equals(e.getResDet()) && this.weight == e.getWeight();
        }
        return false;
    }
    
}
