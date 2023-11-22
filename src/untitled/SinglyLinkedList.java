package untitled; 
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
            this.next = null;
        }
    }

    public void insertFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public void insert(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertFirst(element);
            return;
        }
        Node<E> newNode = new Node<>(element);
        Node<E> prev = getNode(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
        if (newNode.next == null) {
            tail = newNode;
        }
        size++;
    }

    public E getFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.element;
    }

    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = getNode(index);
        return node.element;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public E deleteFirst() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E element = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return element;
    }

    public E delete(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return deleteFirst();
        }
        Node<E> prev = getNode(index - 1);
        E element = prev.next.element;
        prev.next = prev.next.next;
        if (prev.next == null) {
            tail = prev;
        }
        size--;
        return element;
    }

    public int indexOf(E element) {
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            if (current.element.equals(element)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.element;
            current = current.next;
        }
        return array;
    }

    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<E> {
        private Node<E> current;
        private Node<E> previous;

        public SinglyLinkedListIterator() {
            current = head;
            previous = null;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = current.element;
            previous = current;
            current = current.next;
            return element;
        }

        public void remove() {
            if (previous == null) {
                throw new IllegalStateException();
            }
            Node<E> nodeToRemove = previous.next;
            previous.next = nodeToRemove.next;
            if (nodeToRemove == tail) {
                tail = previous;
            }
            nodeToRemove.next = null;
            size--;
            previous = null;
        }

        
    }
}
