package assign05;

import java.util.ArrayList;
import java.util.Random;

public class QS {
    public static void main(String[] args){

    
    Random rand = new Random();

    ArrayList<Integer> arr = new ArrayList<Integer>();
     // Add 10 random nums
     for (int i = 0; i < 10; i++) {
        int value = rand.nextInt(15);
        arr.add(value);
    }
    System.out.println("Unsorted: " + arr.toString());

    quicksort(arr, 0, arr.size()-1); 
    System.out.println("Sorted: " + arr.toString());
}

    private static void quicksort(ArrayList<Integer> arr, int start, int end) {
        if(end <= start)// BASE CASE
            return; 

        int pivot = partition(arr, start, end); 
        quicksort(arr, start, pivot-1);
        quicksort(arr, pivot+1, end);

    }

    private static int partition(ArrayList<Integer> arr, int start, int end) {
        int pivot = arr.get(end); // Select the pivot element, which is the last element of the sublist
        int i = start - 1; // Initialize the index i to one less than the start index
    
        // Loop over the sublist from start to end-1
        for (int j = start; j <= end - 1; j++) {
            if (arr.get(j) < pivot) { // If the current element is less than the pivot
                i++; // Increment i
                Integer temp = arr.get(i); // Swap the current element with arr[i]
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        i++; // Increment i to get to the index of the pivot element
        Integer temp = arr.get(i); // Swap the pivot element with the element at index i
        arr.set(i, arr.get(end));
        arr.set(end, temp);
    
        return i; // Return the index of the pivot element
    }
    


    
}






