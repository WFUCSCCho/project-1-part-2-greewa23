/**********************************************************************
 * @file BST.java
 * @brief This program implements the BST and BSTIterator classes. BST contains
 * methods that can make changes to a binary search tree. The iterator class
 * traverses the tree.
 * @author Wynne Greene
 * @date: September 26, 2024
 ***********************************************************************/
import java.util.Stack;

public class BST<T extends Comparable<T>> {
    //The root is a node of a generic type.
    private Node<T> root;
    //countN is the number of nodes.
    private int countN;

    //The constructor sets default values to the root and countN.
    public BST() {
        root = null;
        countN = 0;
    }

    //Remove elements of the BST by making the root to null.
    //Update the size of the BST.
    public void clear() {
        root = null;
        countN = 0;
    }

    //countN keeps track of the number of nodes in the tree.
    public int size() {
        return countN;
    }

    //The insert method inserts value passed in as a parameter.
    public void insert(T value) {
        //root is updated with the new node.
        root = insertHelp(root, value);
        //countN is updated to reflect the new node.
        countN++;
    }

    //This method is called by the insert method to recursively insert a value.
    private Node<T> insertHelp(Node<T> r, T key) {
        //Check if the node is null.
        if(r==null || r.getElement()==null) {
            return new Node<T>(key);
        }

        //Compare the node's value to the key then
        //Update the left or right nodes.
        if(r.getElement().compareTo(key) > 0) {
            r.setLeft(insertHelp(r.getLeft(), key));
        }
        else {
            r.setRight(insertHelp(r.getRight(), key));
        }
        return r;
    }

    //This method removes a node from the tree.
    public T remove(T key) {
        //Find the key with findHelp()
        T temp = searchHelp(root, key);
        //Remove the key if it is found in the tree.
        if(temp != null) {
            root = removeHelp(root, key);
            //Update countN.
            countN--;
        }
        return temp;
    }

    //This method is called by remove() to recursively remove the key.
    private Node<T> removeHelp(Node<T> nodeVal, T key) {
        if(nodeVal==null)
            return null;
        //Compare the key with the current node. If its value is less than the current
        //Node, it should be in the node's left subtree. Otherwise, it goes in the right.
        if(nodeVal.getElement().compareTo(key)>0) {
            nodeVal.setLeft(removeHelp(nodeVal.getLeft(), key));
        }
        else if(nodeVal.getElement().compareTo(key)<0) {
            nodeVal.setRight(removeHelp(nodeVal.getRight(), key));
        }
        else {
            //After finding the key, remove it.
            //Check for a leaf node.
            if(nodeVal.getLeft() == null && nodeVal.getRight() == null ) {
                return null;
            }
            //Check for if the node has one child.
            if(nodeVal.getLeft()==null)
                return nodeVal.getRight();
            else if(nodeVal.getRight()==null)
                return nodeVal.getLeft();
            else {
                //The node has two children. Replace the node's data with
                //the smallest data in the right subtree and delete the node.
                Node<T> temp = successor(nodeVal.getRight());
                nodeVal.setElement(temp.getElement());
                nodeVal.setRight(removeHelp(nodeVal.getRight(), temp.getElement()));
            }
        }
        return nodeVal;
    }

    //This method finds the node with the smallest data in the right subtree.
    public Node<T> successor(Node<T> rt) {
        while(rt.getLeft()!=null)
            rt = rt.getLeft();
        return rt;
    }

    //This method searches for the key.
    public T search(T key) {
        return searchHelp(root, key);
    }

    //This method is called by search() to find the key.
    private T searchHelp(Node<T> nodeVal, T key) {
        if(nodeVal==null){ //|| nodeVal.getElement() == null) {
            return null;
        }
        //If the key is less than the value of the current node, search
        //the left subtree. Otherwise, if the key is not equal to the node, the right
        //subtree is searched.
        if(nodeVal.getElement().compareTo(key)>0) {
            return searchHelp(nodeVal.getLeft(), key);
        }
        else if(nodeVal.getElement().compareTo(key)==0) {
            //check if the objects are equal, if not continue
            return nodeVal.getElement();
        }
        else
            return searchHelp(nodeVal.getRight(), key);
    }

    //This is the iterator method that return the contents of the tree.
    public String iterator() {
        BSTIterator itr = new BSTIterator(root);
        String s = "";
        while(itr.hasNext()) {
            T element = itr.next();
            s += element.toString();
            if(itr.hasNext())
                s += "\n";
        }
        return s;
    }


    //This class allows the tree to be traversed.
    class BSTIterator implements Iterator<T>{
        //The stack stores the node.
        public Stack<Node<T>> stack;

        //This is the constructor.
        public BSTIterator(Node<T> root) {
            this.stack = new Stack<Node<T>>();
            // Call to the helper function with root node
            this.inorder(root);
        }

        //This method returns the value of the next node.
        public T next() {
            Node<T> root = this.stack.pop();
            if(root.getRight()!=null) {
                this.inorder(root.getRight());
            }
            return root.getElement();
        }

        //Checks if there is a next node.
        public boolean hasNext() {
            return stack.size()>0;
        }

        //This inorder method pushes values into the stack.
        public void inorder(Node<T> root) {
            while(root!=null) {
                stack.push(root);
                root = root.getLeft();
            }
        }
    }
}

interface Iterator<T extends Comparable<T>> {
    public T next();
    public boolean hasNext();
    public void inorder(Node<T> root);
}