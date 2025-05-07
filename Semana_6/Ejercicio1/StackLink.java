package Ejercicio1;
import actividad1.Stack;

public class StackLink<T> implements Stack<T> 
{
    private static class Node<T> 
    {
        private T data;
        private Node<T> next;

        public Node(T data) 
        {
            this.data = data;
            this.next = null;
        }

        public T getData() 
        {
            return data;
        }

        public Node<T> getNext() 
        {
            return next;
        }

        public void setNext(Node<T> next) 
        {
            this.next = next;
        }
    }

    private Node<T> top;

    public StackLink()
     {
        this.top = null;
    }

    @Override
    public boolean isEmpty() 
    {
        return top == null;
    }

    @Override
    public void push(T dato) 
    {
        Node<T> nuevo = new Node<>(dato);
        nuevo.setNext(top);
        top = nuevo;
    }

    @Override
    public T top() 
    {
        if (isEmpty())
         {
            throw new RuntimeException("Pila vacia");
        }
        return top.getData();
    }

    @Override
    public T pop()
     {
        if (isEmpty()) 
        {
            throw new RuntimeException("Pila vacía");
        }
        T dato = top.getData();
        top = top.getNext();
        return dato;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        Node<T> actual = top;
        while (actual != null) 
        {
            sb.append(actual.getData()).append(" -> ");
            actual = actual.getNext();
        }
        sb.append("null");
        return sb.toString();
    }
}
