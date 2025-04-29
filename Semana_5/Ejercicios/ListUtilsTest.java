package Ejercicios;

import Actividades.LinkedList;
import Actividades.Node;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ListUtilsTest {

    @Test
    public void testBuscarElemento() {
        LinkedList<Integer> list = new LinkedList<>();
        list.insertLast(1);
        list.insertLast(2);
        list.insertLast(3);
        assertTrue(ListUtils.buscarElemento(list, 2));
        assertFalse(ListUtils.buscarElemento(list, 4));
    }

    @Test
    public void testInvertirLista() {
        LinkedList<String> list = new LinkedList<>();
        list.insertLast("a");
        list.insertLast("b");
        list.insertLast("c");
        LinkedList<String> reversed = ListUtils.invertirLista(list);
        assertEquals(3, reversed.length());
        Node<String> node = reversed.first;
        assertEquals("c", node.getData());
        node = node.getNext();
        assertEquals("b", node.getData());
        node = node.getNext();
        assertEquals("a", node.getData());
    }

    @Test
    public void testInsertarAlFinalYContarNodos() {
        Node<Integer> head = null;
        head = ListUtils.insertarAlFinal(head, 5);
        assertEquals(1, ListUtils.contarNodos(head));
        head = ListUtils.insertarAlFinal(head, 10);
        assertEquals(2, ListUtils.contarNodos(head));
        assertEquals(5, head.getData());
        assertEquals(10, head.getNext().getData());
    }

    @Test
    public void testConcatenarListas() {
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.insertLast(1);
        list1.insertLast(2);
        list2.insertLast(3);
        list2.insertLast(4);
        LinkedList<Integer> concatenated = ListUtils.concatenarListas(list1, list2);
        assertEquals(4, concatenated.length());
        Node<Integer> node = concatenated.first;
        assertEquals(1, node.getData());
        node = node.getNext();
        assertEquals(2, node.getData());
        node = node.getNext();
        assertEquals(3, node.getData());
        node = node.getNext();
        assertEquals(4, node.getData());
    }
}
