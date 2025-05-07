package Ejercicio2;
import actividad2.Queue;
import actividad1.ExceptionIsEmpty;

public class QueueArray<T> implements Queue<T>
{
    private T[] data;
    private int first;
    private int last;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public QueueArray(int capacity)
     {
        this.capacity = capacity;
        data = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        size = 0;
    }

    @Override
    public void enqueue(T item) 
    {
        if (isFull()) 
        {
            System.out.println("La cola esta llena. No se puede insertar");
            return;
        }
        data[last] = item;
        last = (last + 1) % capacity;
        size++;
    }

    @Override
    public T dequeue() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("La cola esta vacia");
        }
        T item = data[first];
        data[first] = null; // Evitar referencias colgantes
        first = (first + 1) % capacity;
        size--;
        return item;
    }

    @Override
    public T front() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("La cola esta vacia");
        }
        return data[first];
    }

    @Override
    public T back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        int backIndex = (last - 1 + capacity) % capacity;
        return data[backIndex];
    }

    @Override
    public boolean isEmpty() 
    {
        return size == 0;
    }

    @Override
    public boolean isFull() 
    {
        return size == capacity;
    }
}

