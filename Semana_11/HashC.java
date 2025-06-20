package Trabajos_AED.Semana_11;

public class HashC 
{
    private static class Element 
    {
        Register register;
        int mark; 

        public Element() 
        {
            this.register = null;
            this.mark = 0;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) 
    {
        this.size = size;
        table = new Element[size];
        for (int i = 0; i < size; i++) 
        {
            table[i] = new Element();
        }
    }

    public int hash(int key) 
    {
        return key % size;
    }

    public void insert(Register reg) 
    {
        int index = hash(reg.getKey());
        int startIndex = index;

        do {
            if (table[index].mark == 0 || table[index].mark == -1) 
            {
                table[index].register = reg;
                table[index].mark = 1; 
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("La tabla  llena");
    }

    public Register search(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index].mark == 0) {
                return null; // nunca ocupado, paramos
            }
            if (table[index].mark == 1 && table[index].register.getKey() == key) {
                return table[index].register;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        return null;
    }

    public void delete(int key) {
        int index = hash(key);
        int startIndex = index;

        do {
            if (table[index].mark == 1 && table[index].register.getKey() == key) 
            {
                table[index].mark = -1; // eliminado
                return;
            }
            if (table[index].mark == 0) 
            {
                System.out.println("Clave " + key + " no encontrada");
                return;
            }
            index = (index + 1) % size;
        } while (index != startIndex);

        System.out.println("Clave " + key + " no encontrada");
    }

    public void printTable() 
    {
        System.out.println("Contenido de la tabla hash:");
        for (int i = 0; i < size; i++) 
        {
            if (table[i].mark == 1) 
            {
                System.out.println(i + " -> " + table[i].register);
            } else if (table[i].mark == -1) {
                System.out.println(i + " -> (Eliminado)");
            } else {
                System.out.println(i + " -> (Vacio)");
            }
        }
    }
}
