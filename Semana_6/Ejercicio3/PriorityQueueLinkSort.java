package Ejercicio3;

import actividad1.ExceptionIsEmpty;
import actividad2.QueueLink;
import actividad3.PriorityQueue;

class PriorityQueueLinked<E,P extends Number> implements PriorityQueue<E,P>{

    private QueueLink<E>[] priorityQueueItems;
    private int numPriorities;

    @SuppressWarnings("unchecked")
    public PriorityQueueLinked(int pr){
        this.numPriorities = pr;
        this.priorityQueueItems = new QueueLink[pr];

        for (int i = 0; i < pr; i++) {
            priorityQueueItems[i] = new QueueLink<E>();
        }
    }

    public PriorityQueueLinked() {
        // Llama al constructor que recibe int pr
        this(10); 
    }

    @Override
    public void enqueue(E x, P pr) {
        int priorityIndex = pr.intValue();
        
        if (priorityIndex < 0 || priorityIndex >= numPriorities) {
            throw new IllegalArgumentException("Prioridad fuera de rango: " + priorityIndex);
        }

        priorityQueueItems[priorityIndex].enqueue(x);
    }


    @Override
    public E dequeue() throws ExceptionIsEmpty {

        for (int i = 0; i < numPriorities; i++) {
            if (!priorityQueueItems[i].isEmpty()) {
                return priorityQueueItems[i].dequeue();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }

    @Override
    public E front() throws ExceptionIsEmpty {

        for (int i = 0; i < numPriorities; i++) {
            if (!priorityQueueItems[i].isEmpty()) {
                return priorityQueueItems[i].front();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }


    @Override
    public E back() throws ExceptionIsEmpty {

        for (int i = numPriorities - 1; i >= 0; i--) {
            if (!priorityQueueItems[i].isEmpty()) {
                return priorityQueueItems[i].back();
            }
        }
        throw new ExceptionIsEmpty("La cola de prioridad está vacía.");
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < numPriorities; i++) {
            if (!priorityQueueItems[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numPriorities; i++) {
            sb.append("Prioridad ").append(i).append(": ")
              .append(priorityQueueItems[i].toString()).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 1) PriorityQueue que maneje Strings con prioridad Integer
        PriorityQueue<String, Integer> pqStrInt = new PriorityQueueLinked<>(4);
        // El constructor 'PriorityQueueLinked(int n)' define 4 niveles de prioridad: 0..3
        
        System.out.println("=== PriorityQueue<String,Integer> ===");
        
        // Encolar con diversas prioridades
        pqStrInt.enqueue("TareaA", 0);
        pqStrInt.enqueue("TareaB", 2);
        pqStrInt.enqueue("TareaC", 0);
        pqStrInt.enqueue("TareaD", 3);
        
        System.out.println("Después de encolar 4 elementos (Tarea A, B, C, D):");
        System.out.println(pqStrInt);  // Muestra cada cola según la prioridad

        try {
            System.out.println("front() => " + pqStrInt.front()); 
            System.out.println("back() => " + pqStrInt.back());   
            
        //    pqStrInt.dequeue();
        //    pqStrInt.dequeue();
        //    pqStrInt.dequeue();
            String removed = pqStrInt.dequeue();
            System.out.println("Desencolado: " + removed);
            
            System.out.println("Luego de dequeue:\n" + pqStrInt);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }

        // 2) PriorityQueue que maneje enteros con prioridad entera
        PriorityQueue<Integer, Integer> pqIntInt = new PriorityQueueLinked<>(3);
        System.out.println("\n=== PriorityQueue<Integer,Integer> ===");
        
        // Insertar elementos con diferentes prioridades en [0..2]
        pqIntInt.enqueue(50, 2);
        pqIntInt.enqueue(10, 0);
        pqIntInt.enqueue(20, 1);
        pqIntInt.enqueue(40, 0);
        
        System.out.println("Después de encolar 4 enteros:");
        System.out.println(pqIntInt);

        try {
            System.out.println("front() => " + pqIntInt.front()); 
            System.out.println("back() => " + pqIntInt.back());   
            
            int removedInt = pqIntInt.dequeue();
            System.out.println("Desencolado: " + removedInt);
            System.out.println("Luego de dequeue:\n" + pqIntInt);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
        
        // 3) PriorityQueue que maneje un tipo Person y prioridad entera
        // Definamos una clase Person rápida (o usa la que ya tengas)
        class Person {
            String nombre;
            int edad;
            public Person(String nombre, int edad) {
                this.nombre = nombre;
                this.edad = edad;
            }
            @Override
            public String toString() {
                return nombre + "(" + edad + ")";
            }
        }
        
        PriorityQueue<Person, Integer> pqPerson = new PriorityQueueLinked<>(5);
        System.out.println("\n=== PriorityQueue<Person,Integer> ===");
        
        pqPerson.enqueue(new Person("Ana", 30), 0);
        pqPerson.enqueue(new Person("Luis", 25), 1);
        pqPerson.enqueue(new Person("Maria", 28), 0);
        
        System.out.println("Después de encolar Ana, Luis, Maria:");
        System.out.println(pqPerson);

        try {
            System.out.println("front() => " + pqPerson.front());  // persona con prioridad 0 primero
            Person removedP = pqPerson.dequeue();
            System.out.println("Desencolado: " + removedP);
            System.out.println("Luego de dequeue:\n" + pqPerson);
        } catch (ExceptionIsEmpty e) {
            System.out.println(e.getMessage());
        }
        
        // Ejemplo de verificar si está vacía
        System.out.println("\n¿Está vacía la pqStrInt? " + pqStrInt.isEmpty());
        System.out.println("¿Está vacía la pqIntInt? " + pqIntInt.isEmpty());
        System.out.println("¿Está vacía la pqPerson? " + pqPerson.isEmpty());
    }

}