package Semana_2;

public class Caja<T> {
    // atributo generico de lo que contiene
    private T contenido;
    // coonstructor
    public Caja(T contenido){
        this.contenido = contenido;
    }
    // retorne elemento generico
    public T getContenido(){
        return contenido;
    }
    //setter generico
    public void setContenido(T contenido){
        this.contenido = contenido;
    }
}




