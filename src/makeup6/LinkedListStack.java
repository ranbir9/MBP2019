package makeup6;

import java.util.NoSuchElementException;

/**
 * 
 * 
 * @author Anuvesha Chilwal & Ranbir Singh
 * @version March 2, 2022
 */
public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list;

    /**
     * Constructor
     */
    public LinkedListStack() {
        list = new SinglyLinkedList<E>();
    }

    /**
     * Clears list
     */
    public void clear() {
        list.clear();
    }

    /**
     * returns boolean if list is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * return element at top of stack
     * @return E
     */
    public E peek() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.getFirst();
    }

    /**
     * 
     * remove value of top of stack 
     * @return E
     */
    public E pop() throws NoSuchElementException {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.deleteFirst();
    }

    /**
     * Add element to top of stack
     * 
     */
    public void push(E element) {
        list.insertFirst(element);
    }

    /**
     * return size of stack 
     * @return int 
     */
    public int size() {
        return list.size();
    }

}
