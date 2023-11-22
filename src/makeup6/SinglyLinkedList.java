package makeup6;


import java.util.Iterator;
import java.util.NoSuchElementException;

import assign06.List;


/**
 * 
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version March 2, 2022
 */
class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
        next = null;
    }
}

public class SinglyLinkedList<E> implements List<E> {
    Node<E> head;
    int size = 0;

    /**
     * Constructor
     */
    SinglyLinkedList() {
        this.head = null;
    }

    public void insertFirst(E element) {

        // check is list is empty
        if (head == null) {
            Node<E> n = new Node<E>(element);
            head = n;
            size++;
            //System.out.println("head.data: " + head.data);
            return;
        }

        // if list has elements, insert at beginning of node
        Node<E> temp = head; // copy current head
        Node<E> n = new Node<>(element); // create new node with element
        head = n; // head is now new element
        n.next = temp; // next contains the address of next node, which is now the previous head
        size++;
        //.out.println("head.data: " + head.data);
    }

    /** Insert at end of linked list 
     * @param element
     */
    public void insertLast(E element) {
        //checks if list is empty, if so add element
        if (head == null) {
            insertFirst(element);
            return;
        }
    
        //currentNode is the node tracker
        Node<E> currentNode = head;

        //iterate currentNode until .next is null which means it is the last node in linkedList
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
    
        //add new element to current.next which is end of list 
        Node<E> newNode = new Node<>(element);
        currentNode.next = newNode;
        size++;
    }

    /** Insert element at a given index 
     * @param index
     * @param element
     */
    public void insert(int index, E element) throws IndexOutOfBoundsException {
        Node<E> n = new Node<>(element);
        int currentIndex = 0;
        Node<E> currentNode = head;

        //index is not valid, can't be less than 0 or greater than the size
        if(index < 0 || index > size())
            throw new IndexOutOfBoundsException("The index is less than 0 or it is greater than the size"); 

        //index is 0, so insert beginning of the list 
        if(index == 0){
            insertFirst(element);
            return;
        }
        

        
        while (currentNode != null) {
            if (currentIndex == index -1) {
                Node<E> temp = currentNode.next; 
                currentNode.next = n; 
                n.next = temp; 
                //Node<E> temp = currentNode;
                //currentNode = n;
                //n.next = temp;
                size++;
                return;
            } else {
                currentNode = currentNode.next;
                currentIndex++; 
            }
        }
        // no element inserted into index, index too large :)
        throw new IndexOutOfBoundsException();
    }

    /** Insert at beginning of linkedlist
     * 
     */
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
        return head.data;
    }

    /** Get element at a given index
     * @param index
     */
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> currentNode = head;
        int i = 0;

        while (currentNode != null) {
            if (i == index) {
                return currentNode.data;
            } else {
                currentNode = currentNode.next;
                i++;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    /** delete head of linked list
     * @throws NoSuchElementException 
     * 
     */
    public E deleteFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException();
        
        E temp = head.data; 
        size--;
        head = head.next; 
        return temp;
    }

    /** Deletes at a given index
     * @param index
     * @throws IndexOutOfBoundsExeption
     */
    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            System.out.println("size: " + size);
            throw new IndexOutOfBoundsException("Index is invalid");
        }
        
        //deletes first method from 
        if (index == 0) {
            return deleteFirst();
        }
        
        //copy head
        Node<E> current = head;
        Node<E> previous = null;
        int i = 0;
        
        while (i < index) {
            previous = current;
            current = current.next;
            i++;
        }
        // before previous.next would be current, now its current.next discarding current from list
        previous.next = current.next;
        size--;
        return current.data;
    }
    
    /** returns index of a given element 
     * @param element
     */
    public int indexOf(E element) {
        int index = 0;
        Node<E> currentNode = head;
    
        while (currentNode != null) {
            if (currentNode.data.equals(element)) {
                return index;
            }
            currentNode = currentNode.next;
            index++;
        }
        return -1; // element not found
    }

    /** Return linkedList size
     * @return int
     */
    public int size() {
        return size;
    }

    /** returns boolean if linkedList is empty
     * @return boolean
     */
    public boolean isEmpty() {
        if(head == null)
            return true;
        return false;
    }

    /** set head to null, clears linkedList
     * 
     */
    public void clear() {
        head = null;
        size = 0; 
    }

    /** Insert at end of linked list 
     * @return Object
     */
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E element : this) {
            array[i++] = element;
        }
        return array;
    }

    public String toString() {
        String result = "{ ";
        Node<E> current = head;
        boolean isFirst = true;
    
        while (current != null ) {
            E curr = current.data; 
            if(curr != null) {
                if (isFirst) {
                    result += curr;
                    isFirst = false;
                } else {
                    result += " --> " + curr;
                }
            }
            current = current.next;
        }
        result += " }";
        return result;
    }

    public String toString(Object[] toArray){
        String result = "{ ";
        for(int i = 0; i < toArray.length; i++){
            result += toArray[i]; 
            if(i < toArray.length -1)
                result += " --> ";
        }

        result += " }";
        return result; 
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<E> {

        private Node<E> current;
        private Node<E> previous;
        private boolean removeAllowed;

        public SinglyLinkedListIterator() {
            current = head;
            previous = null;
            removeAllowed = false;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() throws NoSuchElementException {
            if (!hasNext())
                throw new NoSuchElementException("No more elements in the list");

            E element = current.data; // fix: access the data field of current node
            previous = current;
            current = current.next;
            removeAllowed = true;

            return element;
        }

        public void remove() throws IllegalStateException {
            if (!removeAllowed)
                throw new IllegalStateException("remove() not allowed at this time");

            if (previous == null) {
                head = current.next; 
            } else {
                previous.next = current.next; 
            }

            current = current.next; 
            removeAllowed = false;
            size--;
        }
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        System.out.println("size: " + list.size);
        list.insert(0, 20);
        list.insert(0, 100);
        list.insert(0, 29);
        list.insert(3, 30); 
        list.insert(2, 22);
        System.out.println(list.toString());

        System.out.println("size: " + list.size);
      
    }

}

