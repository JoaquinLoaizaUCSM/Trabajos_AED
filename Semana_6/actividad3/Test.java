package actividad3;

public class Test {
    public static void main(String[] args) {
        PriorityQueue<String, Integer> colaPrioridad = new PriorityQueueLinkSort<>();

        colaPrioridad.enqueue("Paciente: Luis", 2);
        colaPrioridad.enqueue("Paciente: Marta", 5);
        colaPrioridad.enqueue("Paciente: Pedro", 1);
        colaPrioridad.enqueue("Paciente: Ana", 4);

        System.out.println("Cola con prioridades:");
        System.out.println(colaPrioridad);

        System.out.println("Frente de la cola (mayor prioridad): " + colaPrioridad.front());
        System.out.println("Último en la cola (menor prioridad): " + colaPrioridad.back());

        System.out.println("Se atiende a: " + colaPrioridad.dequeue());

        System.out.println("Cola luego del dequeue:");
        System.out.println(colaPrioridad);

        System.out.println("¿Está vacía?: " + colaPrioridad.isEmpty());
    }
}
