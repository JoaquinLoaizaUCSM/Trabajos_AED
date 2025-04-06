/**
 * Solución del problema "El viaje más barato a través del río" utilizando programación dinámica.
 * Este programa encuentra el costo mínimo para viajar entre embarcaderos en un río,
 * considerando que solo se puede navegar río abajo.
 */
public class ViajeBaratoRio {
    
    /**
     * Calcula la matriz de costos mínimos utilizando programación dinámica.
     * Implementa la recurrencia: C[i,j] = min(T[i,j], min(C[i,k] + C[k,j])) para i < k < j
     * 
     * @param tarifas Matriz T con las tarifas directas entre embarcaderos
     * @return Matriz C con los costos mínimos entre todos los pares de embarcaderos
     */
    public static int[][] calcularMatrizCostosDP(int[][] tarifas) {
        int n = tarifas.length;
        // Matriz C para almacenar los resultados de subproblemas (memoización)
        int[][] C = new int[n][n];
        
        // Inicializar la matriz C según la definición del problema
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    // Caso base: costo de ir de un embarcadero a sí mismo es 0
                    C[i][j] = 0;
                } else {
                    // Inicialmente, todos los demás valores son "infinito"
                    C[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        // Programación dinámica: resolver de abajo hacia arriba
        // Calculamos primero para distancias pequeñas y luego para mayores
        for (int l = 1; l < n; l++) {  // l es la longitud de la distancia entre embarcaderos
            for (int i = 0; i < n - l; i++) {  // i es el embarcadero de origen
                int j = i + l;  // j es el embarcadero de destino
                
                // Caso directo: si hay una tarifa directa disponible
                if (tarifas[i][j] > 0) {
                    C[i][j] = tarifas[i][j];
                }
                
                // Comprobar si es mejor pasar por algún embarcadero intermedio k
                for (int k = i + 1; k < j; k++) {
                    if (C[i][k] != Integer.MAX_VALUE && C[k][j] != Integer.MAX_VALUE) {
                        int costoViaK = C[i][k] + C[k][j];
                        if (costoViaK < C[i][j]) {
                            C[i][j] = costoViaK;
                        }
                    }
                }
            }
        }
        
        return C;
    }
    
    /**
     * Reconstruye la ruta óptima (secuencia de embarcaderos) usando la matriz de costos.
     * 
     * @param C Matriz de costos mínimos
     * @param origen Embarcadero de origen
     * @param destino Embarcadero de destino
     * @return Array con la secuencia de embarcaderos que forman la ruta óptima
     */
    public static int[] reconstruirRuta(int[][] C, int[][] tarifas, int origen, int destino) {
        if (origen == destino) {
            return new int[]{origen};
        }
        
        // Si el costo mínimo es igual a la tarifa directa, vamos directo
        if (tarifas[origen][destino] > 0 && C[origen][destino] == tarifas[origen][destino]) {
            return new int[]{origen, destino};
        }
        
        // Buscar el embarcadero intermedio k que proporciona el costo mínimo
        for (int k = origen + 1; k < destino; k++) {
            if (C[origen][k] != Integer.MAX_VALUE && C[k][destino] != Integer.MAX_VALUE &&
                C[origen][destino] == C[origen][k] + C[k][destino]) {
                
                // Reconstruir recursivamente las subrutas
                int[] rutaOrigenK = reconstruirRuta(C, tarifas, origen, k);
                int[] rutaKDestino = reconstruirRuta(C, tarifas, k, destino);
                
                // Combinar las subrutas (excluyendo la duplicación de k)
                int[] rutaCompleta = new int[rutaOrigenK.length + rutaKDestino.length - 1];
                System.arraycopy(rutaOrigenK, 0, rutaCompleta, 0, rutaOrigenK.length);
                System.arraycopy(rutaKDestino, 1, rutaCompleta, rutaOrigenK.length, rutaKDestino.length - 1);
                
                return rutaCompleta;
            }
        }
        
        // Este punto no debería alcanzarse si la matriz C se calculó correctamente
        return new int[]{origen, destino};
    }
    
    /**
     * Imprime la matriz de costos mínimos de forma legible.
     * 
     * @param C Matriz de costos a imprimir
     */
    public static void imprimirMatrizCostos(int[][] C) {
        System.out.println("\nMatriz de costos mínimos (C[i,j]):");
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[i].length; j++) {
                if (C[i][j] == Integer.MAX_VALUE) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(C[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }
    
    /**
     * Imprime la ruta óptima de forma legible.
     * 
     * @param ruta Array con la secuencia de embarcaderos
     * @param C Matriz de costos para mostrar el costo total
     */
    public static void imprimirRuta(int[] ruta, int[][] C) {
        System.out.print("\nLa ruta óptima es: ");
        for (int i = 0; i < ruta.length; i++) {
            System.out.print(ruta[i]);
            if (i < ruta.length - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\nCosto total: " + C[ruta[0]][ruta[ruta.length - 1]]);
    }
    
    /**
     * Construye una matriz de tarifas de ejemplo para demostrar el funcionamiento.
     * 
     * @param n Número de embarcaderos
     * @return Matriz de tarifas con datos de ejemplo
     */
    public static int[][] construirMatrizTarifasEjemplo(int n) {
        int[][] tarifas = new int[n][n];
        
        // Establecer tarifas de ejemplo (solo para i < j, siguiendo el río abajo)
        tarifas[0][1] = 2;
        tarifas[0][2] = 5;
        tarifas[0][3] = 8;
        tarifas[0][4] = 10;
        tarifas[1][2] = 2;
        tarifas[1][3] = 7;
        tarifas[1][4] = 9;
        tarifas[2][3] = 3;
        tarifas[2][4] = 6;
        tarifas[3][4] = 2;
        
        return tarifas;
    }
    
    /**
     * Método principal para demostrar el funcionamiento del algoritmo.
     */
    public static void main(String[] args) {
        System.out.println("=== Test 1: Ejemplo Base (5 embarcaderos) ===");
        int n1 = 5; // Número de embarcaderos
        int[][] tarifas1 = construirMatrizTarifasEjemplo(n1);
        // Mostrar matriz de tarifas
        System.out.println("Matriz de tarifas directas (T[i,j]):");
        for (int i = 0; i < n1; i++) {
            for (int j = 0; j < n1; j++) {
                System.out.print(tarifas1[i][j] + "\t");
            }
            System.out.println();
        }
        int[][] C1 = calcularMatrizCostosDP(tarifas1);
        imprimirMatrizCostos(C1);
        int[] rutaOptima1 = reconstruirRuta(C1, tarifas1, 0, n1 - 1);
        imprimirRuta(rutaOptima1, C1);

        System.out.println("\n=== Test 2: Caso Simple con 2 Embarcaderos ===");
        int[][] tarifas2 = {
            {0, 10},
            {0, 0}
        };
        int[][] C2 = calcularMatrizCostosDP(tarifas2);
        imprimirMatrizCostos(C2);
        int[] rutaOptima2 = reconstruirRuta(C2, tarifas2, 0, 1);
        imprimirRuta(rutaOptima2, C2);

        System.out.println("\n=== Test 3: 3 Embarcaderos (Ruta Intermedia Más Barata) ===");
        int[][] tarifas3 = {
            {0, 10, 50},
            {0,  0, 20},
            {0,  0,  0}
        };
        int[][] C3 = calcularMatrizCostosDP(tarifas3);
        imprimirMatrizCostos(C3);
        int[] rutaOptima3 = reconstruirRuta(C3, tarifas3, 0, 2);
        imprimirRuta(rutaOptima3, C3);

        System.out.println("\n=== Test 4: 4 Embarcaderos con Varias Opciones ===");
        int[][] tarifas4 = {
            {0, 10, 15, 30},
            {0,  0,  5, 20},
            {0,  0,  0,  5},
            {0,  0,  0,  0}
        };
        int[][] C4 = calcularMatrizCostosDP(tarifas4);
        imprimirMatrizCostos(C4);
        int[] rutaOptima4 = reconstruirRuta(C4, tarifas4, 0, 3);
        imprimirRuta(rutaOptima4, C4);

        System.out.println("\n=== Test 5: Conexiones Escasas (Uso de INF) ===");
        int INF = Integer.MAX_VALUE; // Valor para representar 'infinito'
        int[][] tarifas5 = {
            {0, 5, INF, INF},
            {0, 0,  7,  INF},
            {0, 0,  0,   4},
            {0, 0,  0,   0}
        };
        int[][] C5 = calcularMatrizCostosDP(tarifas5);
        imprimirMatrizCostos(C5);
        int[] rutaOptima5 = reconstruirRuta(C5, tarifas5, 0, 3);
        imprimirRuta(rutaOptima5, C5);    
    }
}