package actividad2;

public class Test {
    public static void main(String[] args) {
        Queue<String> cola = new QueueLink<>();

        cola.enqueue("Cliente: Ana");
        cola.enqueue("Cliente: Bruno");
        cola.enqueue("Cliente: Carla");

        System.out.println("Contenido actual de la cola:");
        System.out.println(cola);

        System.out.println("Frente de la cola: " + cola.front());
        System.out.println("Último en la cola: " + cola.back());

        System.out.println("Se hace dequeue: " + cola.dequeue());

        System.out.println("Contenido luego del dequeue:");
        System.out.println(cola);

        System.out.println("¿Está vacía?: " + cola.isEmpty());
    }
    
}
