package Semana_2;

import java.util.ArrayList;
import java.util.Iterator;

public class Bolsa < T > implements Iterable < T > {

    private ArrayList < T > lista = new ArrayList < T > ();
    private int tope;

    public Bolsa(int tope) {
        super();
        this.tope = tope;
    }

    public Bolsa() {
        super();
    }

    public void add(T objeto) {
        if (lista.size() >= tope) {
            lista.add(objeto);
        } else {
            throw new RuntimeException("no caben mas");
        }
    }

    public Iterator < T > iterator() {
        return lista.iterator();
    }


}
class Chocolatina {
    private String marca;
    public Chocolatina(String marca) {
        this.marca = marca;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
}

class Golosina {
    private String nombre;
    private double peso;
    public Golosina(String nombre, double peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getPeso() {
        return this.peso;
    }
    public void setPeso(double peso) {
        this.peso = peso;
    }
}

class Principal {
    public static void main(String[] args) {
        Bolsa < Chocolatina > bolsaCho = new Bolsa < Chocolatina > ();
        Chocolatina c = new Chocolatina("milka");
        Chocolatina c1 = new Chocolatina("milka");
        Chocolatina c2 = new Chocolatina("ferrero");
        bolsaCho.add(c);
        bolsaCho.add(c1);
        bolsaCho.add(c2);
        for (Chocolatina chocolatina: bolsaCho) {
            System.out.println(chocolatina.getMarca());
        }

        // nueva bolsa de golosinas
        Bolsa<Golosina> bolsaGol = new Bolsa<Golosina>();
        // Llenado
        Golosina g1 = new Golosina("chicle", 0.5);
        Golosina g2 = new Golosina("papitas", 0.3);
        Golosina g3 = new Golosina("caramelo", 0.2);
        bolsaGol.add(g1);
        bolsaGol.add(g2);
        bolsaGol.add(g3);
        // Recorrido
        for (Golosina golosina: bolsaGol) {
            System.out.println(golosina.getNombre() + " " + golosina.getPeso());
        }
    }
}

