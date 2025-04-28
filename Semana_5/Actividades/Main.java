package Actividades;

public class Main 
{
       public static void main(String[] args) {
               // Crear instancias
       GestorDeTareas<Tarea> tareasPendientes = new GestorDeTareas<>();
       GestorDeTareas<Tarea> tareasCompletadas = new GestorDeTareas<>();

       // 1. Agregar tareas
       System.out.println("--- Agregando tareas pendientes ---");
       tareasPendientes.agregarTarea(new Tarea("Estudiar Java", 3));
       tareasPendientes.agregarTarea(new Tarea("Hacer ejercicio", 2));
       tareasPendientes.agregarTarea(new Tarea("Comprar víveres", 4));
       tareasPendientes.agregarTarea(new Tarea("Llamar al médico", 5));
       tareasPendientes.agregarTarea(new Tarea("Revisar correo", 1));
       tareasPendientes.agregarTarea(new Tarea("Entregar informe", 5));

       // 2. Completando tareas (ANTES de imprimir)
       System.out.println("\n--- Completando una tarea ---");
       Tarea tareaCompletada1 = new Tarea("Hacer ejercicio", 2);
       if (tareasPendientes.eliminarTarea(tareaCompletada1)) {
           tareasCompletadas.agregarTarea(tareaCompletada1);
           System.out.println("Tarea completada: " + tareaCompletada1);
       } else {
           System.out.println("No se encontró la tarea: " + tareaCompletada1);
       }

       // 3. Imprimir tareas actuales
       System.out.println("\n--- Tareas pendientes actuales ---");
       tareasPendientes.imprimirTareas();

       // 4. Verificar existencia de tareas
       System.out.println("\n--- Verificando existencia de tareas ---");
       Tarea tareaBuscada = new Tarea("Estudiar Java", 3);
       System.out.println("¿Existe 'Estudiar Java' en pendientes? " + tareasPendientes.contieneTarea(tareaBuscada));
       System.out.println("¿Existe 'Hacer ejercicio' en pendientes? " + tareasPendientes.contieneTarea(tareaCompletada1));

       // 5. Invertir lista
       System.out.println("\n--- Invirtiendo la lista de tareas pendientes ---");
       tareasPendientes.invertirTareas();
       System.out.println("Tareas pendientes después de invertir:");
       tareasPendientes.imprimirTareas();

       // 6. Completar otra tarea
       System.out.println("\n--- Completando otra tarea ---");
       Tarea tareaCompletada2 = new Tarea("Revisar correo", 1);
       if (tareasPendientes.eliminarTarea(tareaCompletada2)) {
           tareasCompletadas.agregarTarea(tareaCompletada2);
           System.out.println("Tarea completada: " + tareaCompletada2);
       } else {
           System.out.println("No se encontró la tarea: " + tareaCompletada2);
       }

       // 7. Mostrar ambas listas
       System.out.println("\n--- Lista de tareas pendientes ---");
       tareasPendientes.imprimirTareas();

       System.out.println("\n--- Lista de tareas completadas ---");
       tareasCompletadas.imprimirTareas();

       // 8. Estadísticas finales
       System.out.println("\n--- Estadísticas ---");
       System.out.println("Número de tareas pendientes: " + tareasPendientes.contarTareas());
       System.out.println("Número de tareas completadas: " + tareasCompletadas.contarTareas());

       // 9. Mostrar la tarea más prioritaria de las pendientes
       Tarea tareaMasPrioritaria = tareasPendientes.obtenerTareaMasPrioritaria();
       if (tareaMasPrioritaria != null) {
           System.out.println("Tarea pendiente más prioritaria: " + tareaMasPrioritaria);
       } else {
           System.out.println("No hay tareas pendientes para mostrar prioridad.");
       }


           //Contar Nodos proporcionando un nodo cabeza
           // Explicitly specify the type parameter <Tarea> for the static generic method call
           System.out.println("Numero de Nodos: " + GestorDeTareas.<Tarea>ContarNodos(tareasPendientes.ObtenerPrimero()));
           //Insertal al final de la lista una tarea
           tareasPendientes.InsertarAlFinal(tareasPendientes.ObtenerPrimero(), new Tarea("Estudiar Listas Enlazadas", 4));
           //Cuenta nuevamente los nodos depues de añadir una nueva tarea
           // Explicitly specify the type parameter <Tarea> for the static generic method call
           System.out.println("Numero de Nodos: " + GestorDeTareas.<Tarea>ContarNodos(tareasPendientes.ObtenerPrimero()));

   }
}