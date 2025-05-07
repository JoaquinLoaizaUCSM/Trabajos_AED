package actividades;

public class Test {
    public static void main(String[] args) throws ExceptionIsEmpty {
        System.out.println("===== ACTIVIDAD 1: StackArray =====");
        testActividad1();

        System.out.println("\n===== ACTIVIDAD 2: QueueLink =====");
        testActividad2();

        System.out.println("\n===== ACTIVIDAD 3: PriorityQueueLinkSort =====");
        testActividad3();
    }

    static void testActividad1() {
        System.out.println("Iniciando prueba de PILA (StackArray)...\n");

        Stack<String> pila = new StackArray<>(3);

        System.out.println("¿Está vacía?: " + pila.isEmpty());
        System.out.println("Agregando elementos a la pila...");

        pila.push("Libro: Fundamentos de Programación");
        System.out.println("Después de push 1: " + pila);

        pila.push("Libro: Estructuras de Datos");
        System.out.println("Después de push 2: " + pila);

        pila.push("Libro: Complejidad Algorítmica");
        System.out.println("Después de push 3: " + pila);

        // Probar isFull
        try {
            pila.push("Libro: Sistemas Operativos");
        } catch (RuntimeException e) {
            System.out.println("Excepción esperada al hacer push en pila llena: " + e.getMessage());
        }

        System.out.println("\nElemento en el tope: " + pila.top());

        System.out.println("\nRealizando pops...");
        while (!pila.isEmpty()) {
            System.out.println("Se retira: " + pila.pop());
            System.out.println("Estado actual: " + pila);
        }

        // Probar excepción con pila vacía
        try {
            pila.pop();
        } catch (ExceptionIsEmpty e) {
            System.out.println("Excepción esperada al hacer pop en pila vacía: " + e.getMessage());
        }

        System.out.println("¿Está vacía finalmente?: " + pila.isEmpty());
    }

    static void testActividad2() throws ExceptionIsEmpty {
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

    static void testActividad3() throws ExceptionIsEmpty {
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