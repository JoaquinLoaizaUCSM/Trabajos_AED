package Semana_7.Ejercicio1;

import Semana_7.Actividad4.Exceptions.*;
import Semana_7.Actividad5.BinarySearchTree;

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> 
{

    private class Node 
    {
        E data;
        Node left, right;

        Node(E data) 
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public LinkedBST() 
    {
        this.root = null;
    }

    @Override
    public boolean isEmpty() 
    {
        return this.root == null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated 
    {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated 
    {
        if (node == null) return new Node(data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("Elemento duplicado: " + data);
        }

        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound 
    {
        return searchRec(root, data);
    }

    private E searchRec(Node node, E data) throws ItemNotFound 
    {
        if (node == null) throw new ItemNotFound("Elemento no encontrado: " + data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0) return searchRec(node.left, data);
        else if (cmp > 0) return searchRec(node.right, data);
        else return node.data;
    }

    @Override
    public void delete(E data) throws ExceptionIsEmpty 
    {
        if (isEmpty()) throw new ExceptionIsEmpty("El BST esta vacio");
        root = deleteRec(root, data);
    }

    private Node deleteRec(Node node, E data) 
    {
        if (node == null) return null;

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = deleteRec(node.left, data);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, data);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node minRight = findMin(node.right);
            node.data = minRight.data;
            node.right = deleteRec(node.right, minRight.data);
        }

        return node;
    }

    private Node findMin(Node node) 
    {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString().trim();
    }

    private void inOrder(Node node, StringBuilder sb) 
    {
        if (node != null) {
            inOrder(node.left, sb);
            sb.append(node.data).append(" ");
            inOrder(node.right, sb);
        }
    }

    public String preOrderRecorrido() 
    {
        StringBuilder sb = new StringBuilder();
        preOrder(root, sb);
        return sb.toString().trim();
    }

    private void preOrder(Node node, StringBuilder sb) 
    {
        if (node != null) {
            sb.append(node.data).append(" ");
            preOrder(node.left, sb);
            preOrder(node.right, sb);
        }
    }

    public String postOrderRecorrido() 
    {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString().trim();
    }

    private void postOrder(Node node, StringBuilder sb) 
    
    {
        if (node != null) 
        {
            postOrder(node.left, sb);
            postOrder(node.right, sb);
            sb.append(node.data).append(" ");
        }
    }

    // --------------------
    // EJERCICIO 1 (EXTENSIONES)
    // --------------------

    // a. Eliminar todos los nodos del arbol
    public void destroyNodes() throws ExceptionIsEmpty 
    {
        if (isEmpty()) 
        {
            throw new ExceptionIsEmpty("El arbol ya esta vacio");
        }
        root = null;
    }

    // b. Contar nodos NO hoja
    public int countAllNodes() 
    {
        return countNonLeafNodes(root);
    }

    private int countNonLeafNodes(Node node) 
    {
        // Si el nodo es nulo o es hoja, no contamos
        if (node == null || (node.left == null && node.right == null)) 
        {
            return 0;
        }
        // Si no es hoja, contamos 1 y seguimos con los hijos
        return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
    }


    // d. Altura de un subarbol con raiz x (iterativo)
    public int height(E x) throws ExceptionIsEmpty 
    {
        Node start = findNodeIteratively(root, x);
        if (start == null) return -1;
        return calculateHeightIteratively(start);
    }

    private Node findNodeIteratively(Node node, E x) 
    {
        while (node != null) 
        {
            int cmp = x.compareTo(node.data);
            if (cmp == 0) { return node; }
            else if (cmp < 0) { node = node.left; }
            else {node = node.right;}
        }
        return null;
    }

    private int calculateHeightIteratively(Node node) throws ExceptionIsEmpty 
    {
        if (node == null) return -1;

        QueueLink<Node> queue = new QueueLink<>();
        queue.enqueue(node);
        int height = -1;

        while (!queue.isEmpty()) 
        {
            int size = queue.size(); 
            height++;
            for (int i = 0; i < size; i++) 
            {
                Node current = queue.dequeue();
                if (current.left != null) queue.enqueue(current.left);
                if (current.right != null) queue.enqueue(current.right);
            }
        }

        return height;
    }

    // e. Amplitud: cantidad de nodos en un nivel dado
    public int amplitude(int nivel) 
    {
        return countNodesAtLevel(root, nivel);
    }

    private int countNodesAtLevel(Node node, int targetLevel) 
    {
        if (node == null || targetLevel < 0) return 0;

        QueueLink<Node> queue = new QueueLink<>();
        QueueLink<Integer> levels = new QueueLink<>();
        queue.enqueue(node);
        levels.enqueue(0);
        int count = 0;

        while (!queue.isEmpty()) 
        {
            try {
                Node current = queue.dequeue();
                int currentLevel = levels.dequeue();

                // Imprimir para depuración
                System.out.println("Nodo: " + current.data + ", Nivel: " + currentLevel);

                if (currentLevel == targetLevel) 
                {
                    count++;
                }

                if (current.left != null) {
                    queue.enqueue(current.left);
                    levels.enqueue(currentLevel + 1);
                }

                if (current.right != null) {
                    queue.enqueue(current.right);
                    levels.enqueue(currentLevel + 1);
                }
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error interno en la cola: " + e.getMessage());
                break;
            }
        }

        return count;
    }

}
