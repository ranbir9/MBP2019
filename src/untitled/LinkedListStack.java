package untitled; 
import java.util.NoSuchElementException;

public class LinkedListStack<E> implements Stack<E> {

    private SinglyLinkedList<E> list;

    public LinkedListStack() {
        list = new SinglyLinkedList<>();
    }

    public void clear() {
        list.clear();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.getFirst();
    }

    public E pop() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.deleteFirst();
    }

    public void push(E element) {
        list.insertFirst(element);
    }

    public int size() {
        return list.size();
    }
}
