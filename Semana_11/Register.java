package Semana_11;

public class Register<K, V> {
    private K key;
    private V value;

    public Register(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Register [key=" + key + ", value='" + value + "']";
    }
}