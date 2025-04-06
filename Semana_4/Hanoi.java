package Semana_4;

public class Hanoi {

    public static void main(String[] args) {
        System.out.println("Total de movimientos realizados: " + resoluHanoi(5, 'O', 'T', 'D'));
        
    }
    public static int resoluHanoi(int disco, char torre1, char torre2, char torre3){
        int contador = 0;

        if (disco == 1){
            System.out.println("Se mueve disco "+disco+" de torre "+ torre1 + " a torre " + torre3);
            contador += 1;
        }

        else{
            contador += resoluHanoi(disco -1 ,  torre1, torre2, torre3);
            resoluHanoi(disco -1 , torre1, torre2, torre3);
            System.out.println("Se mueve disco " + disco + " de torre "+ torre1 + " a torre " + torre3);
            contador += 1;
            contador += resoluHanoi(disco -1, torre1, torre2, torre3);
        }

        return contador;
    }
}
