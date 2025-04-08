package Ejercicios.Ej_3;

public class Rio {

    public static int[][] calcularCostosMinimos(int[][] T) {
        int n = T.length;
        int[][] C = new int[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (i == j) {
                    C[i][j] = 0;
                } else {
                    C[i][j] = T[i][j]; // costo directo
                    for (int k = i + 1; k < j; k++) {
                        C[i][j] = Math.min(C[i][j], T[i][k] + C[k][j]);
                    }
                }
            }
        }
        return C;
    }

    public static void main(String[] args) {
        int[][] T = {
                {0, 1, 5, 10},
                {0, 0, 2, 4},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        int[][] C = calcularCostosMinimos(T);
        System.out.println("Costo mínimo de 0 a 3: " + C[0][3]); // Esperado: 4
    }
}