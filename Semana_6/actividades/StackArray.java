package actividades;

class StackArray<E> implements Stack<E> {
    private E[] array;
    private int tope;

    public StackArray(int n) {
        this.array = (E[]) new Object[n]; // casteo
        this.tope = -1;
    }

    @Override
    public void push(E x) {
        if (isFull()) {
            throw new RuntimeException("La pila está llena");
        }
        array[++tope] = x;
    }

    @Override
    public E pop() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        E value = array[tope];
        array[tope--] = null; // Evita memory leaks
        return value;
    }

    @Override
    public E top() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty();
        }
        return array[tope];
    }

    @Override
    public boolean isEmpty() {
        return this.tope == -1;
    }

    public boolean isFull() {
        return this.tope == array.length - 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = tope; i >= 0; i--) {
            sb.append(array[i]);
            if (i > 0) sb.append(" -> ");
        }
        return sb.toString();
    }
}
