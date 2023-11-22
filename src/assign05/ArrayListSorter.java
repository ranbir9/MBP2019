package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * 
 * 
 * @author Ranbir Singh and Anuvesha Chilwal
 * @version Feburary 23, 2022
 */
public class ArrayListSorter {
    private static final int threshold = 10;

    /** This is the base driver method for mergesort that is to interface with the user 
     * @param <T>
     * @param list
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
        ArrayList<T> tempList = new ArrayList<>(list);
        mergesort(list, tempList, 0, list.size() - 1);
    }

    /** This is the private helper method for merger sort where the recurion occurs.
     * This contains logic to use mergesort or insertion sort 
     * @param <T>
     * @param list
     * @param tempList
     * @param left
     * @param right
     */
    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list, ArrayList<T> tempList, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            if (center - left < threshold) {
                insertionSort(list, left, center);
            } else {
                mergesort(list, tempList, left, center);
            }
            
            if (right - center < threshold) {
                insertionSort(list, center + 1, right);
            } else {
                mergesort(list, tempList, center + 1, right);
            }
            merge(list, tempList, left, center + 1, right);
        }
    }

    /** This method will merge the code back together 
     * @param <T>
     * @param list
     * @param tempList
     * @param leftPos
     * @param rightPos
     * @param rightEnd
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> list, ArrayList<T> tempList, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tempPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (list.get(leftPos).compareTo(list.get(rightPos)) <= 0) {
                tempList.set(tempPos++, list.get(leftPos++));
            } else {
                tempList.set(tempPos++, list.get(rightPos++));
            }
        }

        while (leftPos <= leftEnd) {
            tempList.set(tempPos++, list.get(leftPos++));
        }

        while (rightPos <= rightEnd) {
            tempList.set(tempPos++, list.get(rightPos++));
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            list.set(rightEnd, tempList.get(rightEnd));
        }
    }

    /**This is the insertion sort method 
     * @param <T>
     * @param list
     * @param start
     * @param end
     */
    private static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            T key = list.get(i);
            int j = i - 1;
            while (j >= start && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    
    /**This is the base driver method for the quick sort 
     * @param <T>
     * @param list
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
        quicksort(list, 0, list.size() - 1);
    }
    
    /**This is the private helper method for quicksort where logic is done
     * @param <T>
     * @param list
     * @param start
     * @param end
     */
    private static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list, int start, int end) {
        if (start < end) {
            int pivotIndex = partition(list, start, end);
            quicksort(list, start, pivotIndex - 1);
            quicksort(list, pivotIndex + 1, end);
        }
    }
    
    private static <T extends Comparable<? super T>> int partition(ArrayList<T> list, int start, int end) {
        T pivot = list.get(end);
        //T pivot = list.get(start);
        //T pivot = list.get(end/2);
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, end);
        return i + 1;
    }


    /**
     * This method generates and returns an ArrayList of integers 1 to size in ascending order.
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateAscending(int size){
        ArrayList<Integer> list = new ArrayList<>(); 

        if(size < 1)
            size = 1; 

        for(int i = 0; i < size; i++){
            list.add(i+1);
        }
        return list; 
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in permuted order 
     * (i,e., randomly ordered).  You may adapt the shuffle method written in Class Meeting 10 
     * or make use of Java's Collections.shuffle method.
     * @param size
     * @return
     */
    public static ArrayList<Integer> generatePermuted(int size){
        ArrayList<Integer> list = ArrayListSorter.generateAscending(size);
        Collections.shuffle(list);
        return list; 
    }

    /**
     * This method generates and returns an ArrayList of integers 1 to size in descending order.
     * @param size
     * @return
     */
    public static ArrayList<Integer> generateDescending(int size){
        ArrayList<Integer> list = ArrayListSorter.generateAscending(size); 
        ArrayList<Integer> temp = new ArrayList<>(); 
        for(int i = list.size() - 1; i >= 0; i--){
            temp.add(list.get(i)); 
        }
        return temp; 
    }

    public static void main(String[] args){
        System.out.println(ArrayListSorter.generatePermuted(5).toString()); 
        System.out.println(ArrayListSorter.generateAscending(5).toString()); 
        System.out.println(ArrayListSorter.generateDescending(10).toString());

    }
    

    
}
