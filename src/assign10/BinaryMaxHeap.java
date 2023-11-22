package assign10;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {

    private E[] heap;
    private int size;
    private Comparator<? super E> cmp;

    public BinaryMaxHeap() {
        this((Comparator<? super E>) null);
    }

    public BinaryMaxHeap(Comparator<? super E> cmp) {
        this.heap = (E[]) new Object[40];
        this.size = 0;
        this.cmp = cmp;
    }

    public BinaryMaxHeap(List<? extends E> items) {
        this(items, null);
    }

    public BinaryMaxHeap(List<? extends E> items, Comparator<? super E> cmp) {
        this.cmp = cmp;
        this.size = items.size();
        this.heap = (E[]) new Object[this.size + 1];
        for (int i = 0; i < this.size; i++) {
            this.heap[i + 1] = items.get(i);
        }
        buildHeap();
    }

    private void buildHeap() {
        int startingInternalNode = size / 2; 
        for (int i = startingInternalNode; i >= 1; i--) {
            percolateDown(i);
        }
    }

    private void percolateUp(int index) {
        E val = heap[index];
        while (index > 1) {
            int parentIndex = index / 2;
            E parent = heap[parentIndex];
            if (innerCompare(val, parent) <= 0) {
                break;
            }
            heap[index] = parent;
            index = parentIndex;
        }
        heap[index] = val;
    }

    private void percolateDown(int index) {
        E val = heap[index];
        while (2 * index <= size) {
            int left = 2 * index;
            int right = left+1; 
            if (left < size && innerCompare(heap[right], heap[left]) > 0) {
                left++;
            }

            if (innerCompare(heap[left], val) <= 0) {
                break;
            }
            heap[index] = heap[left];
            index = left;
        }
        heap[index] = val;
    }

    private int innerCompare(E a, E b) {
        if (cmp != null) {
            return cmp.compare(a, b);
        } else {
            return ((Comparable<? super E>) a).compareTo(b);
        }
    }

    @Override
    public void add(E item) {
        if (size == heap.length - 1) {
            resize(heap.length * 2);
        }
        heap[size + 1] = item;
        size++;
        percolateUp(size);
    }

    private void resize(int newSize) {
        E[] newHeap = (E[]) new Object[newSize];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap[1];
    }

    @Override
    public E extractMax() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        
        E max = heap[1];
        E last = heap[size];
        heap[1] = last;
        heap[size] = null;
        size--;
        percolateDown(1);
        return max;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        heap = (E[]) new Object[40];
    }

    @Override
    public Object[] toArray() {
        return heap;
    }

    public String toString() {
        String str = "[ ";
        for (int i = 0; i < size; i++) {
            str += heap[i];
            if (i != size - 1) {
                str += ", ";
            }
        }
        return str += " ]";
    }

    public static void main(String[] args) {
        BinaryMaxHeap<Integer> heap = new BinaryMaxHeap<>();
        heap.add(10);
        heap.add(12);
        heap.add(15);
        heap.add(16);
        heap.add(19);
        heap.add(9);
        heap.add(55);
        heap.add(130);
        heap.add(8);

        System.out.println(heap.toString());
        heap.percolateDown(3);
        System.out.println(heap.toString());
        System.out.println("max: " + heap.extractMax());
        // ArrayList<Integer> arr = new ArrayList<>();
        // arr.add(10);
        // arr.add(12);
        // arr.add(15);
        // arr.add(16);
        // arr.add(19);
        // arr.add(9);
        // arr.add(55);
        // arr.add(130);
        // arr.add(8);

        // BinaryMaxHeap<Integer> bin = new BinaryMaxHeap<>(arr);
        // System.out.println(bin.toString());

    }

}