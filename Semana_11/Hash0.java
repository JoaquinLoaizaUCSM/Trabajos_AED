package Trabajos_AED.Semana_11;
    import java.util.LinkedList;

    public class Hash0 
    {
        private LinkedList<Register>[] table;
        private int size;

        public Hash0(int size) {
            this.size = siguientePrimo(size);
            this.table = new LinkedList[size];
            for (int i = 0; i < size; i++) 
            {
                table[i] = new LinkedList<>();
            }
        }

        private int hash(int key) 
        {
            return key % size;
        }

        public void insert(Register reg) 
        {
            int key = reg.getKey();
            int index = hash(key);
            for (Register r : table[index]) 
            {
                if (r.getKey() == key)
                {
                    System.out.println("Clave duplicada: " + key);
                    return;
                }
            }
            table[index].add(reg);
        }

        public Register search(int key) 
        {
            int index = hash(key);
            for (Register reg : table[index]) 
            {
                if (reg.getKey() == key) 
                {
                    return reg;
                }
            }
            return null;
        }

        public void delete(int key)
        {
            int index = hash(key);
            for (Register reg : table[index])
            {
                if (reg.getKey() == key) {
                    table[index].remove(reg);
                    return;
                }
            }
            System.out.println("Clave " + key + " no encontrada para eliminar");
        }

        private int siguientePrimo(int n) 
        {
            while (!Primo(n)) 
            {
                n++;
            }
            return n;
        }
    
        private boolean Primo(int num) {
            if (num <= 1) return false;
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) return false;
            }
            return true;
        }

        public void printTable() 
        {
            System.out.println("Contenido de la tabla hash:");
            for (int i = 0; i < size; i++) 
            {
                System.out.print(i + " -> ");
                for (Register reg : table[i])
                {
                    System.out.print(reg + " ");
                }
                System.out.println();
            }
        }
    }
