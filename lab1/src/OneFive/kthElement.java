package OneFive;

import java.util.Iterator;
import java.util.Scanner;

public class kthElement<Item> implements Iterable<Item> {

    // private attributes
    private Node back;
    private Node head;
    private int counter;

    // contructor
    public kthElement(){
        this.counter = 0;
        this.back = null;
        this.head = null;
    }

    private class Node{
        Node nextNode;
        Node previousNode;
        Item info;

        // constructor
        public Node(Item info){
            this.info = info;
            this.nextNode = null;
            this.previousNode = null;
        }
    }

    // Iterator is a method from the interface
    // iterator that needs to be implemented
    public Iterator<Item> iterator() { //metod från iterable och måste implemteras
        return new IterableList();
    }

    // this is our class IterableList that implements the interface Iterator.
    // It has three methods, where two of them are used in the method
    // toString.
    private class IterableList implements Iterator<Item> {
        private Node current = head; // make current point to head
        // next is returning value of next node
        public Item next() {
            Item currentValue = current.info; // copy info from current to currentValue
            current = current.nextNode; // move currentValue to next node
            return currentValue;
        }
        // hasNext checks if there is a next node
        public boolean hasNext() {
            if (current != null)
            {
                return current != back; // if current = back it is false since only one node exists
                // if current is not back then we know that next exists
            }

            return false;
        }
        // we don't need this method
        public void remove() {

            throw new UnsupportedOperationException("Operation not supported");
        }
    }
    // checking if there are no nodes left
    public boolean isEmpty(){
        return (counter == 0);
    }

    // using the iterator, hasNext and next we
    // can convert all information to string
    // in order to be easily printed
    public String toString() {
        Iterator<Item> iterator = iterator(); // create an instance of the iterator
        StringBuilder s = new StringBuilder("["); // create a stringbuilder
        // the if statement below checks if no nodes exist by using
        // the method isEmpty
        if(isEmpty())
        {
            return "empty list"; // empty list will be printed when toString is called
        }
        // down below if next exist (by using hasNext) is true
        // we go through the nodes using the while loop and
        // append the next element
        if (iterator.hasNext()) {
            while (iterator.hasNext()) {
                s.append(iterator.next() + "],[");
            }
        }
        s.append(iterator.next() + "]");
        return s.toString(); // we return the string
    }

    public void addingFrontNode(Item info){
        Node newNode = new Node(info);
        // the if statements puts next and previous of the node to itself
        // it also puts head and back to the only node (newNode)
        if (counter == 0) // if the new node is the only one
        {
            head = newNode;
            newNode.nextNode = newNode;
            newNode.previousNode = newNode;
            back = newNode;
        }
        // else is entered when we have more than one node
        else
        {
            newNode.previousNode = back; // it's circular so first node prev goes to back
            back.nextNode = newNode; // back goes to the new node (first node)
            head.previousNode = newNode; // old first node goes to the new first
            newNode.nextNode = head; // the new one goes to old head
            head = newNode; // make the new one head
        }
        counter++;
        System.out.println("Adding new node to front");
        System.out.println(toString());

    }


    public void addingBackNode(Item info){
        Node newNode = new Node(info);
        // the if statements puts next and previous of the node to itself
        // it also puts head and back to the only node (newNode)
        if (counter == 0) // if the new node is the only one
        {
            head = newNode;
            newNode.nextNode = newNode;
            newNode.previousNode = newNode;
            back = newNode;
        }
        // else is entered when we have more than one node
        else
        {
            back.nextNode = newNode; // because its circular and the last node is newNode
            newNode.previousNode = back; // circular and the front previous goes to back
            newNode.nextNode = head; // newnode next goes old head
            head.previousNode = newNode; // the previous pointer of head points to newNode
            back = newNode; // we make the back be the new node
        }
        counter++;
        System.out.println("adding new back node");
        System.out.println(toString());

    }
    public void removingBackNode(){
        counter--; // decrement the size
        if (counter == 0)// if the new node is the only one
        {
            System.out.println("removing node");
            head = null; // head is null
            back = null; // back is null
            System.out.println("empty");
        }

        if(counter < 0) // if the index is out of bounds
        {
            System.out.println("no more nodes to remove");
            if(counter < 0){
                counter++; // increase counter so we don't get negative amount of nodes
            }
        }
        else if (counter > 0) // if we have more than one node
        {
            back.nextNode = null; // back nextpoiner to null
            back = back.previousNode; // make the back be the previous
            back.nextNode = head; // circular back next goes to head
            head.previousNode = back; // circular head prev goes to back
            System.out.println("removing node back");
            System.out.println(toString());
        }
    }

    public void removingFrontNode() {
        counter--; // decrement the size
        if (counter == 0) // if the new node is the only one
        {
            System.out.println("removing node");
            head = null; // head is null
            back = null; // back is null
            System.out.println(toString());
        }
        if(counter < 0)// if the index is out of bounds
        {
            System.out.println("no more nodes to remove");
            if(counter < 0){
                counter++; // increase counter so we don't get negative amount of nodes
            }
        }
        else if(counter > 0) // if we have more than one node
        {
            head.previousNode = null; // the previous pointer of head to null
            head = head.nextNode; // assign head to the next node
            head.previousNode = back; // this "new" head previous pointer goes to back
            back.nextNode = head; // last node next goes to this "new" head

            System.out.println("removing node front");
            System.out.println(toString());
        }
    }

    public int removingKthElement(int k){

        if(k == 1) // if we want to delete first node
        {
            removingFrontNode(); // call the method to remove front node
            return 0; // in order to exit the method
        }
        if(k == counter){
            removingBackNode(); // call the method remove back node
            return 0;
        }
        if ((k >= counter) || (k <= 0)) // if k is out of bounds
        {
            System.out.println("This is out of bounds");
            return 0;
        }

        Node tempNodeFront; // create temp node
        tempNodeFront = head; // set it equal to head
        tempNodeFront.nextNode = head.nextNode; // temp next is head next

        Node tempNodeAfter; // create another temp node
        System.out.println("deleting node at the " + k + "th element place"); // print here so k doesn't change
        while((k-1) > 1) // while we aren't next to the node that needs to be removed
        {
            tempNodeFront = tempNodeFront.nextNode; // go forward
            k = k - 1; // decrease k
        }
        tempNodeAfter = tempNodeFront.nextNode.nextNode; // set node after to front next next
        tempNodeFront.nextNode = tempNodeAfter; // front next goes to after instead of the deleted
        tempNodeAfter.previousNode = tempNodeFront; // after prev goes to front instead of the deleted element


        counter--;
        System.out.println(toString());

        return 0;
    }
    public static void main(String[] args){

        kthElement<Integer> list = new kthElement<>();

        Scanner scanner = new Scanner(System.in);
        int q = 0;

        while(q == 0)
        {
            System.out.println("1 = addFrontNode, 2 = addBackNode, 3 = removeFrontNode, 4 = removeBackNode, 5 = removeKthNode, 6 = quit");
            int in = scanner.nextInt();
            switch(in)
            {
                case 1:
                    System.out.println("Type what you want to add");
                    int add = scanner.nextInt();
                    list.addingFrontNode(add);
                    break;
                case 2:
                    System.out.println("Type what you want to add");
                    int add1 = scanner.nextInt();
                    list.addingBackNode(add1);
                    break;
                case 3:
                    list.removingFrontNode();
                    break;
                case 4:
                    list.removingBackNode();
                    break;
                case 5:
                    System.out.println("Type which position you want to delete, first is 1");
                    int removePos = scanner.nextInt();
                    list.removingKthElement(removePos);
                    break;
                case 6:
                    q = 1;
            }
        }
    }
}
