package Semana_9.list;

public class ExceptionIsEmpty extends RuntimeException {

    // Constructor por defecto con mensaje genérico
    public ExceptionIsEmpty() {
        super("Está vacía.");
    }
    // Constructor con mensaje personalizado
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}