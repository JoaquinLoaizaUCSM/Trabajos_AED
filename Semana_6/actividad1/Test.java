package actividad1;

public class Test {
    public static void main(String[] args) {
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
    
}
