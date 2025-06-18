package Semana_10.btree;

/**
 * Se lanza cuando el archivo no representa un B-Tree válido.
 */
public class ItemNoFound extends Exception{
    public ItemNoFound(String mensaje){
        super(mensaje);
    }
}
