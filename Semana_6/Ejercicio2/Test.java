package Ejercicio2;
import actividad2.Queue;
import actividad1.ExceptionIsEmpty;

public class Test {
    public static void main(String[] args) 
    {
        // Cola de enteros
        Queue<Integer> colaInt = new QueueArray<>(5);
        colaInt.enqueue(10);
        colaInt.enqueue(20);
        colaInt.enqueue(30);

        System.out.println("---- Cola de Enteros ----");
        try 
        {
            System.out.println("Frente: " + colaInt.front());
            System.out.println("Final: " + colaInt.back());
            System.out.println("Elemento removido: " + colaInt.dequeue());
            System.out.println("Nuevo frente: " + colaInt.front());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        // Cola de Strings
        Queue<String> colaStr = new QueueArray<>(3);
        colaStr.enqueue("Uno");
        colaStr.enqueue("Dos");

        System.out.println("---- Cola de Cadenas ----");
        try 
        {
            System.out.println("Frente: " + colaStr.front());
            System.out.println("Final: " + colaStr.back());
            System.out.println("Elemento removido: " + colaStr.dequeue());
            System.out.println("Nuevo frente: " + colaStr.front());
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }


        System.out.println("¿Cola de enteros vacía?: " + colaInt.isEmpty());
        System.out.println("¿Cola de strings llena?: " + colaStr.isFull());
    }
}
