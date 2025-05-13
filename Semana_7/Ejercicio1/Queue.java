package Semana_7.Ejercicio1;

import Semana_7.Actividad4.Exceptions.*;

public interface Queue<E> {
    void enqueue(E x);
    E dequeue() throws ExceptionIsEmpty;
    E front() throws ExceptionIsEmpty;
    E back() throws ExceptionIsEmpty;
    boolean isEmpty();
    boolean isFull();
    
}