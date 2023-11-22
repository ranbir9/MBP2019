package assign10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for finding the k largest items in
 * a list.
 * 
 * @author Erin Parker and Anuvesha Chilwal and Ranbir Singh
 */
public class FindKLargest {

	/**
	 * Determines the k largest items in the given list, using a binary max heap and
	 * the
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestHeap(List<E> items, int k) throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}

		ArrayList<E> newList = new ArrayList<>(k);
		BinaryMaxHeap<E> heap = new BinaryMaxHeap<>(items);

		for (int i = 0; i < k; i++) {
			// add k largest to list
			newList.add(heap.extractMax());
		}

		return newList;
	}

	/**
	 * Determines the k largest items in the given list, using a binary max heap.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestHeap(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
		if (k < 0 || k > items.size()) {
			throw new IllegalArgumentException();
		}

		ArrayList<E> newList = new ArrayList<>(k);

		// create heap using cmp
		BinaryMaxHeap<E> heap = new BinaryMaxHeap(items, cmp);

		//add items to heap 
		for (E item : items) {
			heap.add(item);
		}

		for (int i = 0; i < k; i++) {
			// add k largest to list from heap 
			newList.add(heap.extractMax());
		}

		return newList;
	}

	/**
	 * Determines the k largest items in the given list, using Java's sort routine
	 * and the
	 * natural ordering of the items.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E extends Comparable<? super E>> List<E> findKLargestSort(List<E> items, int k) throws IllegalArgumentException {
        if (k < 0 || k > items.size()) {
            throw new IllegalArgumentException();
        }

        List<E> newList = new ArrayList<>(items);
        Collections.sort(newList, Collections.reverseOrder());

        return newList.subList(0, k);
    }

	/**
	 * Determines the k largest items in the given list, using Java's sort routine.
	 * 
	 * @param items - the given list
	 * @param k     - the number of largest items
	 * @param cmp   - the comparator defining how to compare items
	 * @return a list of the k largest items, in descending order
	 * @throws IllegalArgumentException if k is negative or larger than the size of
	 *                                  the given list
	 */
	public static <E> List<E> findKLargestSort(List<E> items, int k, Comparator<? super E> cmp) throws IllegalArgumentException {
        if (k < 0 || k > items.size()) {
            throw new IllegalArgumentException();
        }

        List<E> newList = new ArrayList<>(items);
        Collections.sort(newList, Collections.reverseOrder(cmp));

        return newList.subList(0, k);
    }

	public static void main(String[] args){
		ArrayList<Integer> arr = new ArrayList<>(); 
		arr.add(10); 
		arr.add(20); 
		arr.add(55); 
		arr.add(100); 
		arr.add(1); 
		arr.add(101); 
		
		List<Integer> result = findKLargestSort(arr, 3); 

		for(Integer item : result){
			System.out.println("item: " + item);
		}
	}

}
