package OneTwo;

public class Stack {
    private Node front = null;

    // class node
    private class Node
    {
        // attributes
        char info;
        Node next;
    }

    public void push(char item)
    {
        Node oldfirst = front; // oldfirst is set to front
        front = new Node(); // front is new node
        front.info = item; // front info is set to the parameter
        front.next = oldfirst; // front next is oldfirst
    }

    public char pop() {
        char item = front.info; // item is set to front info
        front = front.next; // pop so we go forward
        return item;
    }

    // isEmpty returns if the stack is empty
    public boolean isEmpty()
    {  return front == null; }
}
