package Semana_11;


public class Hash0<K, V> {
    private ListLinked<Register<K, V>>[] table;
    private int size;

    public Hash0(int size) {
        this.size = siguientePrimo(size);
        this.table = new ListLinked[this.size];
        for (int i = 0; i < this.size; i++) {
            table[i] = new ListLinked<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    public void insert(Register<K, V> reg) {
        K key = reg.getKey();
        int index = hash(key);

        Node<Register<K, V>> current = table[index].getHead();
        while (current != null) {
            if (current.getData().getKey().equals(key)) {
                System.out.println("Clave duplicada: " + key);
                return;
            }
            current = current.getNext();
        }

        table[index].add(reg);
    }

    public Register<K, V> search(K key) {
        int index = hash(key);

        Node<Register<K, V>> current = table[index].getHead();
        while (current != null) {
            if (current.getData().getKey().equals(key)) {
                return current.getData();
            }
            current = current.getNext();
        }

        return null;
    }

    public void delete(K key) {
        int index = hash(key);

        Node<Register<K, V>> current = table[index].getHead();
        Register<K, V> regToDelete = null;
        while (current != null) {
            if (current.getData().getKey().equals(key)) {
                regToDelete = current.getData();
                break;
            }
            current = current.getNext();
        }

        if (regToDelete != null) {
            table[index].remove(regToDelete);
        } else {
            System.out.println("Clave " + key + " no encontrada para eliminar");
        }
    }

    private int siguientePrimo(int n) {
        while (!Primo(n)) {
            n++;
        }
        return n;
    }

    private boolean Primo(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public void printTable() {
        System.out.println("Contenido de la tabla hash:");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " -> ");
            Node<Register<K, V>> current = table[i].getHead();
            while (current != null) {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            }
            System.out.println();
        }
    }
}
