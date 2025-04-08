package Actividad_2;

import javax.swing.*;
import java.awt.*;

public class PythagorasTree extends JPanel {
    private int profundidad;

    public PythagorasTree(int profundidad) {
        this.profundidad = profundidad;
        setPreferredSize(new Dimension(800, 800));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // Limpiar fondo
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // Establecer color de dibujo a verde
        g2d.setColor(Color.GREEN);
        
        // Comenzar a dibujar el Árbol de Pitágoras
        trazaArbol(g2d, 350, 700, 100, -90, profundidad);
    }

    private void trazaArbol(Graphics2D g, int x, int y, int lado, double angulo, int nivel) {
        // Caso base: detener recursión si el nivel es 0 o el lado es demasiado pequeño
        if (nivel == 0 || lado < 2) return;

        // Calcular coordenadas del nuevo punto final
        int x2 = x + (int) (lado * Math.cos(Math.toRadians(angulo)));
        int y2 = y + (int) (lado * Math.sin(Math.toRadians(angulo)));

        // Dibujar la línea que representa la rama actual
        g.drawLine(x, y, x2, y2);

        // Calcular tamaño de ramas más pequeñas (70% del lado actual)
        int nuevoLado = (int) (lado * 0.7);

        // Llamadas recursivas para ramas izquierda y derecha
        // Rama izquierda rotada 45 grados a la izquierda
        trazaArbol(g, x2, y2, nuevoLado, angulo - 45, nivel - 1);
        
        // Rama derecha rotada 45 grados a la derecha
        trazaArbol(g, x2, y2, nuevoLado, angulo + 45, nivel - 1);
    }

    public static void main(String[] args) {
        // Crear marcos para diferentes niveles del Árbol de Pitágoras
        int[] niveles = {6, 8, 10};
        
        for (int nivel : niveles) {
            JFrame marco = new JFrame("Árbol de Pitágoras - " + nivel + " Niveles");
            PythagorasTree arbol = new PythagorasTree(nivel);
            marco.add(arbol);
            marco.pack();
            marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            marco.setVisible(true);
        }
    }
}