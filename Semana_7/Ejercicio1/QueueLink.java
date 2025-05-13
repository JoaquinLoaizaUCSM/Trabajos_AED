package Semana_7.Ejercicio1;
import Semana_7.Actividad4.Exceptions.*;


public class QueueLink<E> implements Queue<E> 
{
    private Node<E> first;
    private Node<E> last;

    public QueueLink() 
    {
        this.first = null;
        this.last = null;
    }

    @Override
    public void enqueue(E x) 
    {
        Node<E> aux = new Node<>(x);
        if (this.isEmpty()) 
        {
            this.first = aux;
        } else {
            this.last.setNext(aux);
        }
        this.last = aux;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty
     {
        if (this.isEmpty()) 
        {
            throw new ExceptionIsEmpty("No se puede desencolar: cola vacía.");
        }
        E data = this.first.getData();
        this.first = this.first.getNext();
        if (this.first == null)
         {
            this.last = null;
        }
        return data;
    }

    @Override
    public E back() throws ExceptionIsEmpty 
    {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("Cola vacia");
        }
        return this.last.getData();
    }

    @Override
    public E front() throws ExceptionIsEmpty 
    {
        if (this.isEmpty()) 
        {
            throw new ExceptionIsEmpty("Cola vacia");
        }
        return this.first.getData();
    }

    @Override
    public boolean isEmpty() 
    
    {
        return this.first == null;
    }

    @Override
    public boolean isFull() 
    {
    // En una cola basada en lista enlazada, este metodo no sirve
    // ya que la cola puede crecer dinamicamente segun sea necesario
    return false; // Siempre devuelve false porque la cola nunca esta llena
    //Esta solo para cumplir con la interfaz
    //Su verdadero uso esta en Ejercicio2/QueueArray.java 
    }


    @Override
    public String toString()
     {
        StringBuilder sb = new StringBuilder();
        Node<E> current = this.first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) 
            {
                sb.append(" -> ");
            }
            current = current.getNext();
        }
        return sb.toString();
    }

    public int size() 
    {
    int count = 0;
    Node<E> current = this.first;
    while (current != null) 
    {
        count++;
        current = current.getNext();
    }
    return count;
}

}