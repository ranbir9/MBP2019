package assign05;

import java.util.ArrayList;
import java.util.Random;

public class ALS2<T extends Comparable<? super T>> {

    private static final int threshold = 3; 

    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> list) {
        ArrayList<T> tempList = new ArrayList<>(list); 
        mergesort(list, tempList);
    }

    private static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr, ArrayList<T> tempList) {
        int length = arr.size();
        int middle = length / 2;

        // Base Case - if length is 1 or less, no need to recurse anymore
        if (length <= 1) {
            return;
        }

        // 1. Divide the array into two halves
        // ArrayList<T> left = new ArrayList<>(arr.subList(0, length / 2)); 
        // ArrayList<T> right = new ArrayList<>(arr.subList(middle, length)); 
        ArrayList<T> leftArray = new ArrayList<>(); 
        ArrayList<T> rightArray = new ArrayList<>(); 
        // 2. Copy values into two arrays 
        for(int i = 0; i < length; i++){
            if(i < middle) { // if less than middle, copy to left array
                leftArray.add(arr.get(i)); 
            } else { 
                rightArray.add(arr.get(i)); // greater than middle, copy to right array
            }
        }

        // 3. Sort the left and right arrays
        if (leftArray.size() <= threshold) { // Insertion sort if array is 3 or less
            insertionSort(leftArray);
        } else {
            mergesort(leftArray, tempList);
        }

        if (rightArray.size() <= threshold) { // Insertion sort if array is 3 or less
            insertionSort(rightArray);
        } else {
            mergesort(rightArray, tempList);
        }

        // 4. Merge the left and right arrays
        merge(leftArray, rightArray, arr, tempList);
    }

    private static <T extends Comparable<? super T>> void merge(ArrayList<T> leftArray, ArrayList<T> rightArray, ArrayList<T> arr, ArrayList<T> tempList) {
        int leftSize = leftArray.size();
        int rightSize = rightArray.size();
        int l = 0, r = 0;

        tempList.clear();

        //Merge two lists, comparing which element is less from each array and inserting to tempList
        while (l < leftSize && r < rightSize) { 
            if (leftArray.get(l).compareTo(rightArray.get(r)) < 0) {
                tempList.add(leftArray.get(l));
                l++;
            } else {
                tempList.add(rightArray.get(r));
                r++;
            }
        }

        //Add remaining elements if not added yet - left
        while (l < leftSize) {
            tempList.add(leftArray.get(l));
            l++;
        }
        
        //Add remaining elements if not added yet - right
        while (r < rightSize) {
            tempList.add(rightArray.get(r));
            r++;
        }

        //copy tempList to arr
        for (int j = 0; j < tempList.size(); j++) {
            arr.set(j, tempList.get(j));
        }
    }

    public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> list) {
        int start = 0;
        int end = list.size() - 1;
    
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

   


    public static void main(String[] ags) {
        Random rand = new Random();
        ArrayList<Integer> arr = new ArrayList<>();

        // Add 10 random nums
        for (int i = 0; i < 10; i++) {
            int value = rand.nextInt(15);
            arr.add(value);
        }

        // Print unsorted array
        System.out.println("Unsorted: " + arr);

        // Print sorted array
        mergesort(arr);
        System.out.println("Sorted: " + arr);
    }
}
