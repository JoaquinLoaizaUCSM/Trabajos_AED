package Actividades;

public class Main 
{
    public static void main(String[] args) {
        // 1. Crear una instancia de GestorDeTareas2<T> para tareas pendientes
        GestorDeTareas2<Tarea> tareasPendientes = new GestorDeTareas2<>();
        
        // Crear una lista genérica adicional para tareas completadas
        LinkedList<Tarea> tareasCompletadas = new LinkedList<>();

        // 2. Agregar tareas
        System.out.println("--- Agregando tareas pendientes ---");
        tareasPendientes.agregarTarea(new Tarea("Estudiar Java", 3));
        tareasPendientes.agregarTarea(new Tarea("Hacer ejercicio", 2));
        tareasPendientes.agregarTarea(new Tarea("Comprar víveres", 4));
        tareasPendientes.agregarTarea(new Tarea("Llamar al médico", 5));
        tareasPendientes.agregarTarea(new Tarea("Revisar correo", 1));

        // 3. Eliminar una tarea
        System.out.println("\n--- Eliminando una tarea ---");
        Tarea tareaEliminar = new Tarea("Revisar correo", 1);
        if (tareasPendientes.eliminarTarea(tareaEliminar)) {
            System.out.println("Tarea eliminada: " + tareaEliminar);
        } else {
            System.out.println("No se encontró la tarea: " + tareaEliminar);
        }

        // 4. Imprimir todas las tareas actuales
        System.out.println("\n--- Tareas pendientes actuales ---");
        tareasPendientes.imprimirTareas();

        // 5. Verificar si cierta tarea existe
        System.out.println("\n--- Verificando existencia de tareas ---");
        Tarea tareaBuscada = new Tarea("Estudiar Java", 3);
        System.out.println("¿Existe 'Estudiar Java' en pendientes? " + 
                          tareasPendientes.contieneTarea(tareaBuscada));
        System.out.println("¿Existe 'Revisar correo' en pendientes? " + 
                          tareasPendientes.contieneTarea(tareaEliminar));

        // 6. Invertir la lista
        System.out.println("\n--- Invirtiendo la lista de tareas pendientes ---");
        tareasPendientes.invertirTareas();
        System.out.println("Tareas pendientes después de invertir:");
        tareasPendientes.imprimirTareas();

        // 7. Transferir una tarea a una lista de "tareas completadas"
        System.out.println("\n--- Transfiriendo tarea a completadas ---");
        Tarea tareaCompletar = new Tarea("Hacer ejercicio", 2);
        if (tareasPendientes.contieneTarea(tareaCompletar)) {
            if (tareasPendientes.eliminarTarea(tareaCompletar)) {
                tareasCompletadas.insertLast(tareaCompletar);
                System.out.println("Tarea transferida a completadas: " + tareaCompletar);
            }
        } else {
            System.out.println("No se encontró la tarea para transferir: " + tareaCompletar);
        }

        // Transferir otra tarea para tener más ejemplos
        Tarea tareaCompletar2 = new Tarea("Estudiar Java", 3);
        if (tareasPendientes.eliminarTarea(tareaCompletar2)) {
            tareasCompletadas.insertLast(tareaCompletar2);
            System.out.println("Tarea transferida a completadas: " + tareaCompletar2);
        }

        // 8. Mostrar ambas listas
        System.out.println("\n--- Lista de tareas pendientes ---");
        tareasPendientes.imprimirTareas();

        System.out.println("\n--- Lista de tareas completadas ---");
        Node<Tarea> current = tareasCompletadas.first;
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }

        // Estadísticas finales
        System.out.println("\n--- Estadísticas ---");
        System.out.println("Número de tareas pendientes: " + tareasPendientes.contarTareas());
        System.out.println("Número de tareas completadas: " + tareasCompletadas.length());

        // Mostrar la tarea más prioritaria de las pendientes
        Tarea tareaMasPrioritaria = tareasPendientes.obtenerTareaMasPrioritaria();
        if (tareaMasPrioritaria != null) {
            System.out.println("Tarea pendiente más prioritaria: " + tareaMasPrioritaria);
        } else {
            System.out.println("No hay tareas pendientes para mostrar prioridad.");
        }
    }
}