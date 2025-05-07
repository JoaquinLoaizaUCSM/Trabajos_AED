package Ejercicio1;
import actividad1.Stack;
import actividad1.ExceptionIsEmpty;

public class Test 
{
    public static void main(String[] args)
    {
        Stack<Integer> pilaInt = new StackLink<>();
        Stack<String> pilaStr = new StackLink<>();

        try 
        {
            pilaInt.push(1);
            pilaInt.push(2);
            pilaInt.push(3);
            System.out.println("--------Pila de Enteros--------");
            System.out.println("Tope: " + pilaInt.top());
            System.out.println("Tope Eliminado: " + pilaInt.pop());
            System.out.println("Nuevo Tope: " + pilaInt.top());

            pilaStr.push("Uno");
            pilaStr.push("Dos");
            pilaStr.push("Tres");
            System.out.println("--------Pila de Strings--------");
            System.out.println("Tope: " + pilaStr.top());
            System.out.println("Tope Eliminado: " + pilaStr.pop());
            System.out.println("Nuevo Tope: " + pilaStr.top());
        } catch (ExceptionIsEmpty e) 
        {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

