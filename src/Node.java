/**********************************************************************
 * @file Node.java
 * @brief This program implements the Node class. Each node has a value and
 * possible left and right child nodes.
 * @author Wynne Greene
 * @date: September 26, 2024
 ***********************************************************************/
public class Node<T extends Comparable<T>> implements BinNode<T>{
    //Value is a generic type.
    private T value;
    //The node can have left and right nodes.
    private Node<T> right;
    private Node<T> left;

    //This is the default constructor.
    public Node() {
        right = null;
        left = null;
    }

    //This constructor accounts for a value.
    public Node(T val) {
        value = val;
        right = null;
        left = null;
    }

    //This constructor accounts for a value and left and right nodes.
    public Node(T val, Node<T> right, Node<T> left) {
        value = val;
        this.right = right;
        this.left = left;
    }

    //This method sets the value.
    public void setElement(T val) {
        value = val;
    }

    //This method sets the left node.
    public void setLeft(Node<T> n) {
        left = n;
    }

    //This method sets the right node.
    public void setRight(Node<T> n) {
        right = n;
    }

    //This method gets the left node.
    public Node<T> getLeft() {
        return left;
    }

    //This method get the right node.
    public Node<T> getRight() {
        return right;
    }

    //This method gets the value.
    public T getElement() {
        return value;
    }

    //This method checks if a node is a leaf.
    public boolean isLeaf() {
        return (this.left==null) && (this.right==null);
    }

    /*@Override
    public int compareTo(Node<T> e) {
        return this.getElement().compareTo(e.getElement());
    }*/
}
//Binary tree ADT
interface BinNode<T extends Comparable<T>> {
    //Get and set element values
    public T getElement();
    public void setElement(T v);
    //Return children
    public BinNode<T> getLeft();
    public BinNode<T> getRight();
    //Check if it is a leaf
    public boolean isLeaf();

}