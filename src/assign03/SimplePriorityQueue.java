package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;


/**
 * 
 * 
 * @author Erin Parker and Ranbir Singh and Anuvesha Chilwal
 * @version Feburary 2, 2022
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E>, Comparable<SimplePriorityQueue<E>> {
    private int arraySize;
    private E[] array;
    private E element;
    private Comparator<? super E> cmp;
    boolean useCmp = false; 

    // change to private before submitting the assignment
    @SuppressWarnings("unchecked")
    SimplePriorityQueue() {
        array = (E[]) new Object[16];
        arraySize = 0;

    }

    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        array = (E[]) new Object[16];
        arraySize = 0;
        this.cmp = cmp;
        this.useCmp = true; 

    }

    @Override
    /**
     * Compares objects
     * 
     * @return returns value relative to parameter
     * 
     */
    
    public int compareTo(SimplePriorityQueue<E> o) {
        
        return this.compareTo(o);

    }

    @Override 
    /**
     * ToString method
     * 
     * @return String
     * 
     */
    public String toString(){
        String arr = "["; 
        for(E element : array){
            arr += " " + element + " "; 
        }
        arr += "]"; 
        return arr; 
    }

    @Override
    /**
     * Retrieves, but does not remove, the maximum element in this priority queue.
     * 
     * @return the maximum element
     * @throws NoSuchElementException if the priority queue is empty
     */
    public E findMax() throws NoSuchElementException {

        // exception thrown in array size is less than 1
        if (arraySize < 1)
            throw new NoSuchElementException();
        return array[arraySize - 1];

    }

    @SuppressWarnings("unchecked")
    @Override
    /**
     * 
     * Retrieves and removes the maximum element in this priority queue.
     * 
     * 
     * 
     * @return the maximum element
     * 
     * @throws NoSuchElementException if the priority queue is empty
     * 
     */

    public E deleteMax() throws NoSuchElementException {
        // no elements in array
        if (arraySize < 1)
            throw new NoSuchElementException();

        
        E max = findMax();

        //set last element in array to null
        array[arraySize - 1] = null;
        arraySize--;

        

        return max;
    }

    @Override
    /**
     * 
     * Inserts the specified element into this priority queue.
     * 
     * 
     * 
     * @param item - the element to insert
     * 
     */

    public void insert(E item) {

        // returns index of item if exists, -1 if not found
        int index = BinarySearch(array, item);
        E[] newArray;
        // double array size and copy elements to new array

        if(arraySize == 0){
            array[0] = item; 
            arraySize++; 
            return; 
        }

        // double up array size if full
        if ((arraySize == array.length-1)) {
            newArray = (E[]) new Object[array.length * 2];
        } else {
            newArray = (E[]) new Object[array.length];
        }

       

        // ((Comparable<? super E>)array[i]).compareTo(item);
        //if(index == -1)

        int i = 0;
        while (i < arraySize && ((Comparable<? super E>) array[i]).compareTo(item) < 0) {
            newArray[i] = array[i];
            i++;
        }
        // not smaller anymore insert item
        newArray[i] = item;
        arraySize++;

        while (i < arraySize) {
            newArray[i + 1] = array[i];
            i++;
        }
        // store newArray as array
        array = newArray;

    }

    @Override

    /**
     * 
     * Inserts the specified elements into this priority queue.
     * 
     * 
     * 
     * @param coll - the collection of elements to insert
     * 
     */

    public void insertAll(Collection<? extends E> coll) {
        for(E element : coll){
            insert(element);
        }
    }

    @Override

    /**
     * 
     * Indicates whether this priority queue contains the specified element.
     * 
     * 
     * 
     * @param item - the element to be checked for containment in this priority
     * 
     *             queue
     * 
     */

    public boolean contains(E item) {

        int val = BinarySearch((E[]) array, (E) item);

        // -1 means the search reach the end and no matches were found
        if (val == -1)
            return false;
        return true;

    }

    // no static method to create the new compare method without extending

    // comparable

    @Override

    /**
     * 
     * @return the number of elements in this priority queue
     * 
     */

    public int size() {
        return arraySize;

    }

    /**
     * 
     * @return the size of the array
     * 
     */
    public int arrLength(){
        return array.length; 
    }

    @Override

    /**
     * 
     * @return true if this priority queue contains no elements, false otherwise
     * 
     */

    public boolean isEmpty() {

        if (size() == 0)
            return true;
        return false;
    }

    @Override

    /**
     * 
     * Removes all of the elements from this priority queue. The queue will be empty
     * 
     * when this call returns.
     * 
     */

    public void clear() {
        array = (E[]) new Object[array.length];
        this.arraySize = 0;

    }

    /**
     * 
     * Helper method adds specified elements into the array
     * 
     * 
     * 
     * @param element - adds the elements in a natural order
     * 
     */

    private void addElements(E element) {

    }

    /**
     * 
     * Helper method returns the element at the specified position
     * 
     * 
     * 
     * @param index - the index of the required element
     * 
     * @return element of type E at the given index
     * 
     */

    private E getElement(int index) {

        return array[index];

    }

    private <E> int BinarySearch(E[] array, E item) {

        int beginning = 0;
        //System.out.println("ArraySize: " + arraySize);
        int end = arraySize;
        int middle = 0;

        // array is currently empty
        if (arraySize == 0) {
            return -1; 
            //arraySize++;
        } else {

            while (beginning <= end) {
                middle = (beginning + end) / 2;
                // int result = array[middle].compareTo(item);
                if(array[middle] == null){
                    return -1;  
                }
                int result = ((Comparable<? super E>) array[middle]).compareTo(item);

                if(result == 0){
                    return 0; 
                } else if (result < 0){
                    beginning = middle + 1; 
                } else {
                    end = middle -1 ;
                }
            }
        }

        return -1;
    }

}