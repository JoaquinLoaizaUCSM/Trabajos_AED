package Actividades.Actividad_1;

public class Hanoi {

    public static void main(String[] args) {
        System.out.println("Total de movimientos realizados: " + resoluHanoi(5, 'A', 'B', 'C'));
    }

    public static int resoluHanoi(int discos, char torre1, char torre2, char torre3) {
        int contador = 0;

        if (discos == 1) {
            System.out.println("Se mueve disco " + discos + " de torre " + torre1 + " a torre " + torre3);
            return 1; 
        }

        contador += resoluHanoi(discos - 1, torre1, torre3, torre2);
        System.out.println("Se mueve disco " + discos + " de torre " + torre1 + " a torre " + torre3);
        contador += 1;
        contador += resoluHanoi(discos - 1, torre2, torre1, torre3);

        return contador;
    }
}
