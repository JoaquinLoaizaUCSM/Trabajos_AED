class AVLTree<E extends Comparable<E>> extends BStree<E> 
{

    public class NodeAVL extends Node<E> {
        protected int bf;

        public NodeAVL(E data) {
            super(data);
            this.bf = 0;
        }

        @Override
        public String toString() {
            return data + " (bf = " + bf + ")";
        }
    }

    private boolean heightChanged;

    public void insert(E x) {
        this.heightChanged = false;
        this.root = insert(x, (NodeAVL) this.root);
    }

    protected Node insert(E x, NodeAVL node) {
        NodeAVL currentnode = node;

        if (node == null) {
            this.heightChanged = true;
            currentnode = new NodeAVL(x);
        } else {
            int cmp = node.data.compareTo(x);

            if (cmp == 0) {
                System.out.println("Ya existe en el árbol");
                this.heightChanged = false;
            } else if (cmp < 0) {
                currentnode.right = insert(x, (NodeAVL) node.right);

                if (this.heightChanged) {
                    switch (currentnode.bf) {
                        case -1:
                            currentnode.bf = 0;
                            this.heightChanged = false;
                            break;
                        case 0:
                            currentnode.bf = 1;
                            this.heightChanged = true;
                            break;
                        case 1:
                            currentnode = balanceToLeft(currentnode);
                            this.heightChanged = false;
                            break;
                    }
                }
            } else {
                currentnode.left = insert(x, (NodeAVL) node.left);

                if (this.heightChanged) {
                    switch (currentnode.bf) {
                        case 1:
                            currentnode.bf = 0;
                            this.heightChanged = false;
                            break;
                        case 0:
                            currentnode.bf = -1;
                            this.heightChanged = true;
                            break;
                        case -1:
                            currentnode = balanceToRight(currentnode);
                            this.heightChanged = false;
                            break;
                    }
                }
            }
        }

        return currentnode;
    }

    private NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.right;
        switch (hijo.bf) { 
            case 1: // Rotación Simple a la Izquierda (SL)
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSL(node);
                break;
            case -1: //Rotación Doble (Derecha + Izquierda)
                NodeAVL nieto = (NodeAVL) hijo.left;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 0;
                        hijo.bf = 1;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = -1;
                        hijo.bf = 0;
                        break;
                }
                nieto.bf = 0;
                node.right = rotateSR(hijo);
                node = rotateSL(node);
                break;
        }
        return node;
    }

    private NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.left;
        switch (hijo.bf) {
            case -1:
                node.bf = 0;
                hijo.bf = 0;
                node = rotateSR(node);
                break;
            case 1:
                NodeAVL nieto = (NodeAVL) hijo.right;
                switch (nieto.bf) {
                    case -1:
                        node.bf = 1;
                        hijo.bf = 0;
                        break;
                    case 0:
                        node.bf = 0;
                        hijo.bf = 0;
                        break;
                    case 1:
                        node.bf = 0;
                        hijo.bf = -1;
                        break;
                }
                nieto.bf = 0;
                node.left = rotateSL(hijo);
                node = rotateSR(node);
                break;
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL root) {
        NodeAVL child = (NodeAVL) root.right;
        root.right = child.left;
        child.left = root;
        return child;
    }

    private NodeAVL rotateSR(NodeAVL root) {
        NodeAVL child = (NodeAVL) root.left;
        root.left = child.right;
        child.right = root;
        return child;
    }
    

    public void delete(E x) {
        this.heightChanged = false;
        this.root = delete(x, (NodeAVL) this.root);
    }

    private NodeAVL delete(E x, NodeAVL node) {
        if (node == null) {
            System.out.println("Elemento no encontrado.");
            this.heightChanged = false;
            return null;
        }

        NodeAVL current = node;
        int cmp = x.compareTo(current.data);

        if (cmp < 0) {
            current.left = delete(x, (NodeAVL) current.left);
            if (this.heightChanged) {
                current = balanceToLeftDelete(current);
            }
        } else if (cmp > 0) {
            current.right = delete(x, (NodeAVL) current.right);
            if (this.heightChanged) {
                current = balanceToRightDelete(current);
            }
        } else {
            if (current.left == null) {
                this.heightChanged = true;
                return (NodeAVL) current.right;
            } else if (current.right == null) {
                this.heightChanged = true;
                return (NodeAVL) current.left;
            } else {
                NodeAVL successor = findMin((NodeAVL) current.right);
                current.data = successor.data;
                current.right = delete(successor.data, (NodeAVL) current.right);
                if (this.heightChanged) {
                    current = balanceToRightDelete(current);
                }
            }
        }

        return current;
    }

    private NodeAVL findMin(NodeAVL node) {
        while (node.left != null) {
            node = (NodeAVL) node.left;
        }
        return node;
    }

    private NodeAVL balanceToLeftDelete(NodeAVL node) {
        switch (node.bf) {
            case -1:
                node.bf = 0;
                this.heightChanged = true;
                break;
            case 0:
                node.bf = 1;
                this.heightChanged = false;
                break;
            case 1:
                NodeAVL rightChild = (NodeAVL) node.right;
                if (rightChild.bf >= 0) {
                    node = rotateSL(node);
                    node.bf = 0;
                    ((NodeAVL) node.left).bf = 0;
                    this.heightChanged = rightChild.bf == 0;
                } else {
                    node = doubleRotateLeft(node);
                    this.heightChanged = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL balanceToRightDelete(NodeAVL node) {
        switch (node.bf) {
            case 1:
                node.bf = 0;
                this.heightChanged = true;
                break;
            case 0:
                node.bf = -1;
                this.heightChanged = false;
                break;
            case -1:
                NodeAVL leftChild = (NodeAVL) node.left;
                if (leftChild.bf <= 0) {
                    node = rotateSR(node);
                    node.bf = 0;
                    ((NodeAVL) node.right).bf = 0;
                    this.heightChanged = leftChild.bf == 0;
                } else {
                    node = doubleRotateRight(node);
                    this.heightChanged = true;
                }
                break;
        }
        return node;
    }

    private NodeAVL doubleRotateLeft(NodeAVL node) {
        node.right = rotateSR((NodeAVL) node.right);
        return rotateSL(node);
    }

    private NodeAVL doubleRotateRight(NodeAVL node) {
        node.left = rotateSL((NodeAVL) node.left);
        return rotateSR(node);
    }





    //INORDER
    public void printInOrder() {
        printInOrder((NodeAVL) root);
        System.out.println();
    }

    private void printInOrder(NodeAVL node) {
        if (node != null) {
            printInOrder((NodeAVL) node.left);
            System.out.println(node);
            printInOrder((NodeAVL) node.right);
        }
    }

    //PREORDER
    public void printPreOrder() {
        printPreOrder((NodeAVL) root);
        System.out.println();
    }

    private void printPreOrder(NodeAVL node) 
    {
        if (node != null) {

            System.out.println(node);
            printPreOrder((NodeAVL) node.left);
            printPreOrder((NodeAVL) node.right);
        }
    }


    //POSTORDER
    public void printPostOrder() {
        printPostOrder((NodeAVL) root);
        System.out.println();
    }

    private void printPostOrder(NodeAVL node) 
    {
        if (node != null) {

            printPostOrder((NodeAVL) node.left);
            printPostOrder((NodeAVL) node.right);
            System.out.println(node);
        }
    }
}