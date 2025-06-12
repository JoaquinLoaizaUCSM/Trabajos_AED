package Semana_10.btree;

import java.util.ArrayList;

public class BNode<E extends Comparable<E>> { 
     
    protected ArrayList<E> keys;   
    protected ArrayList<BNode<E>> childs;  
    protected int count; 
    protected int idNode;
    private static int nextNodeId = 0;
    

    // Constructor to initialize a BNode with a specified capacity
    public BNode (int n){ 
        this.keys = new ArrayList<E>(n); 
        this.childs = new ArrayList<BNode<E>>(n); 
        this.count = 0; 
        this.idNode = nextNodeId++;
 
     for(int i=0; i < n; i++){ 
            this.keys.add(null); 
            this.childs.add(null); 
        } 
    } 

    //Check if the current node is full 
    public boolean nodeFull () { 
        return this.count == this.keys.size(); 
    }

    public boolean nodeFull (int n) { 
        return this.count == n; 
    }

    //Check if the current node is empty 
    public boolean nodeEmpty () { 
        return this.count == 0; 
    } 

    //Search for a key in the current node, if found it returns true and  
    //the position where it is located, otherwise, returns false and the  
    //position of the child where it should descend. 
    
    public boolean searchNode (E key) { 
        for (int i = 0; i < this.count; i++) { 
            if (this.keys.get(i).equals(key)) { 
                return true; 
            } 
        } 
        return false; 
    }

    public boolean searchNode (E key, int[] pos) { 
        for (int i = 0; i < this.count; i++) { 
            if (this.keys.get(i).equals(key)) { 
                pos[0] = i; 
                return true; 
            } else if (this.keys.get(i).compareTo(key) > 0) { 
                pos[0] = i; 
                return false; 
            } 
        } 
        pos[0] = this.count; 
        return false; 
    }

    //Return the keys found in the node. 
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Node ID: ").append(this.idNode).append(" | ");
        sb.append("Keys: ");
        for (int i = 0; i < this.count; i++) {
            sb.append(this.keys.get(i)).append(" ");
        }
        return sb.toString();
    }
}
