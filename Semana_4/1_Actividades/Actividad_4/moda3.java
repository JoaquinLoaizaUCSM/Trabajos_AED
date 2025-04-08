package Actividad_4;

import java.util.*;

public class moda3 {
    // Clase para manejar los limites de los subarreglos
    static class Limits {
        int[] a; // el arreglo
        int prim; // indice inicial del subarreglo
        int ult; // indice final del subarreglo

        public Limits(int[] a, int prim, int ult) {
            this.a = a;
            this.prim = prim;
            this.ult = ult;
        }
    }
    // Clase que gestiona los vectores de subarreglos
    static class SetVectors {
        private List<Limits> set; //Lista de los subarreglos

        public SetVectors() {
            set = new ArrayList<>();
        }

        public void insertar(Limits p) { 
            set.add(p); // Inserta un nuevo subarreglo
        }

        // Encuentra el subarreglo con el mayor rango
        public Limits mayor() {
            Limits mayor = null;
            for (Limits l : set) {
                if (mayor == null || (l.ult - l.prim) > (mayor.ult - mayor.prim)) {
                    mayor = l; // Actualiza el subarreglo con el mayor rango
                }
            }
            return mayor;
        }
        // Se verifica si el conjunto esta vacio
        public boolean esVacio() {
            return set.isEmpty();
        }
        // retorna la longitud del subarreglo con el mayor rango
        public int longMayor() {
            if (set.isEmpty()) return 0;
            return (mayor().ult - mayor().prim);
        }
        // limpia el conjunto de subarreglos
        public void destruir() {
            set.clear();
        }
    }
    // Funcion de particion similar al algoritmo de quicksort
    public static void pivote2(int[] a, int mediana, int prim, int ult, int[] izq, int[] der) {
        int i = prim, j = ult;
        while (i <= j) {
            // Mueve los indices para encontrar los elementos fuera de lugar
            while (a[i] < mediana) i++;
            while (a[j] > mediana) j--;
            if (i <= j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp; // Intercambia los elementos (por eso la var temporal)
                i++;
                j--;
            }
        }
        // Guarda los nuevos limites de los subarreglos
        izq[0] = i;
        der[0] = j;
    }

    public static int moda3(int[] a, int prim, int ult) {
        Limits p, p1, p2, p3;
        SetVectors homogeneo = new SetVectors(); // Conjunto de subarreglos homogeneos
        SetVectors heterogeneo = new SetVectors(); // Conjunto de subarreglos heterogeneos
        int mediana;// Conjunto de subarreglos heterogeneos
        int[] izq = new int[1], der = new int[1]; // Variables para los limites de los subarreglos

        p = new Limits(a, prim, ult); // Inserta el primer subarreglo completo en el conjunto heterogeneo
        heterogeneo.insertar(p);

        // Mientras haya subarreglos heterogeneos que analizar
        while (heterogeneo.longMayor() > homogeneo.longMayor()) {
            // Obtiene el subarreglo mas grande (el de mayor rango)
            p = heterogeneo.mayor();
            // Calcula la mediana del subarreglo actual
            mediana = a[(p.prim + p.ult) / 2];
            // Particiona el arreglo en tres partes
            pivote2(a, mediana, p.prim, p.ult, izq, der);

            // Crea los nuevos subarreglos
            p1 = new Limits(a, p.prim, izq[0] - 1);
            p2 = new Limits(a, izq[0], der[0]);
            p3 = new Limits(a, der[0] + 1, p.ult);
            // Inserta los subarreglos en los conjuntos correspondientes
            if (p1.prim <= p1.ult) heterogeneo.insertar(p1);
            if (p3.prim <= p3.ult) heterogeneo.insertar(p3);
            if (p2.prim <= p2.ult) homogeneo.insertar(p2);
        }

        // Si no hay subarreglos homogeneos, devuelve el primer elemento
        if (homogeneo.esVacio()) return a[prim];

        // Obtiene el subarreglo homogeneo mas grande y devuelve su primer elemento como la moda
        p = homogeneo.mayor();
        heterogeneo.destruir(); // Limpia el conjunto heterogeneo.
        homogeneo.destruir(); // Limpia el conjunto homogeneo.
        return a[p.prim]; // Devuelve el valor de la moda
    }

    public static void main(String[] args) 
    {
        int[] a = {1, 2, 2, 3, 3, 3, 4, 5, 6};
        // Llamamos al metodo moda3 pasandole el arreglo y los indices iniciales (0) y finales (a.length - 1) del arreglo
        int resultado = moda3(a, 0, a.length - 1);
        System.out.println("La moda es: " + resultado);
    }
}
