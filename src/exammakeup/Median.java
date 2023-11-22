package exammakeup;

import java.util.Collections;
import java.util.Random;


/**
 * Method for computing the median of an int[], along with some other helpers
 * Author: Ben Jones
 **/
public class Median {

    public static int median(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        quickSort(arr);
        int middleIndex = arr.length / 2;
        if (arr.length % 2 == 0) {
            return (arr[middleIndex - 1] + arr[middleIndex]) / 2;
        } else {
            return arr[middleIndex];
        }
    }
    
    public static int median(int[] arr, int begin, int end) {
        int pivotIndex = getPivotIndex(begin, end);
        int updatedPivotIndex = partition(arr, begin, end, pivotIndex);
        int middleIndex = (begin + end) / 2;
    
        if (updatedPivotIndex == middleIndex) {
            return arr[updatedPivotIndex];
        } else if (updatedPivotIndex < middleIndex) {
            return median(arr, updatedPivotIndex + 1, end);
        } else {
            return median(arr, begin, updatedPivotIndex);
        }
    }
    
    
    
    
    
    

    /**
     * Driver for quicksort
     * @param arr the array to sort
     */
    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length);
    }

    /**
     * Recursive quicksort method
     * @param arr
     * @param begin inclusive index
     * @param end exclusive index
     */
    private static void quickSort(int[] arr, int begin, int end){
        if((end - begin) < 2) 
            return;
        var pivot = partition(arr, begin, end, getPivotIndex(begin, end));
        quickSort(arr, begin, pivot);
        quickSort(arr, pivot + 1, end);
    }


    /**
     * Partition the sub-array so all elements left of the pivot are less than or equal to it
     * and all elements to its right are greater than it
     * @param arr the array to partition
     * @param begin first element in the range to be partitioned
     * @param end one-past the end of the range to be partitioned (ie end is EXclusive)
     * @param pivotIndex index of element to use as the pivot
     * @return index of the pivot element after partitioning
     */
    public static int partition(int[] arr, int begin, int end, int pivotIndex){
        int left = begin;
        int right = end  -1;
        var pivot = arr[pivotIndex];

        //move pivot out of the way
        swap(arr, pivotIndex, right);
        right--;

        while(left <= right){
            while(left < end -1 && arr[left] <= pivot){ left++; }
            while(right >= begin && arr[right] > pivot){ right--;}
            if(left < right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        swap(arr, left, end -1);
        return left;
    }

    private static void swap(int[] arr, int i, int j){
        var tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void shuffle(int[] arr){
        Random r = new Random();
        for(var i = arr.length - 1; i > 0; i--){
            var j = r.nextInt(i + 1);
            swap(arr, i, j);
        }
    }


    
    private static int getPivotIndex(int begin, int end){
        return (begin + end)/2;
    }

}
