package Trabajos_AED.Semana_11;
public class Main {
     public static void main(String[] args) {

        System.out.println("Ejercicio 1 - Insertar sin colisiones --------------");
        HashC hash1 = new HashC(7);
        hash1.insert(new Register(1, "A"));
        hash1.insert(new Register(2, "B"));
        hash1.insert(new Register(4, "C"));
        hash1.insert(new Register(5, "D"));
        hash1.printTable();


        System.out.println("Ejercicio 2 - Insertar con colisiones --------------");
        HashC hash2 = new HashC(6);
        int[] values = {12, 18, 24, 30};
        for (int val : values) 
        {
            System.out.println("Insertando " + val);
            hash2.insert(new Register(val, "Valor " + val));
            hash2.printTable();
        }


        System.out.println("Ejercicio 3 - Buscar claves ------------------------");
        HashC hash3 = new HashC(5);
        hash3.insert(new Register(10, "Juan"));
        hash3.insert(new Register(15, "Ana"));
        hash3.insert(new Register(20, "Luis"));
        hash3.insert(new Register(25, "Rosa"));
        hash3.printTable();

        Register found = hash3.search(20);
        System.out.println("Buscar clave 20: " + (found != null ? found : "No encontrada"));
        Register notFound = hash3.search(30);
        System.out.println("Buscar clave 30: " + (notFound != null ? notFound : "No encontrada"));
        System.out.println();


        System.out.println("Ejercicio 4 - Eliminar en hash cerrado -------------");
        HashC hash4 = new HashC(7);
        hash4.insert(new Register(5, "Uno"));
        hash4.insert(new Register(12, "Dos")); // 12 % 7 = 5 → colisión → va a 6
        hash4.insert(new Register(19, "Tres")); // 19 % 7 = 5 → colisión → va a 0
        System.out.println("Antes de eliminar:");
        hash4.printTable();

        hash4.delete(12);
        System.out.println("Despues de eliminar 12:");
        hash4.printTable();

        System.out.println("Buscar clave 19 despues de eliminar 12:");
        Register result = hash4.search(19);
        System.out.println(result != null ? result : "No encontrada");
    }
}
