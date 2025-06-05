package Semana_9.graph;
import Semana_9.list.ListLinked;

public class Vertex<E> {

    private E data;
    protected ListLinked<Edges<E>> listAdj;

    public Vertex(E data){

        this.data = data;
        listAdj = new ListLinked<Edges<E>>();

    }

    public boolean equals(Object o ){
        if(o instanceof Vertex<?>){
            Vertex<E> v = (Vertex<E>)o;
            return this.data.equals(v.getData()) && this.listAdj.equals(v.listAdj);
        }
        return false;
    }

    public E getData() {
        return data;
    }

    public String toString(){
        return this.data + "-->" + this.listAdj.toString() + " \n ";
    }

}
