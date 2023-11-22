package assign05;

import java.util.ArrayList;
import java.util.Random;

public class MergeCode {

    public static void main(String[] ags){
        Random rand = new Random(); 
        ArrayList<Integer> arr = new ArrayList<>(); 
        
        //Add 10 random nums 
        for(int i = 0; i < 10; i++){
            int value = rand.nextInt(15); 
            arr.add(value);
        }

        //print unsorted array
        System.out.println(arr);

        //print sorted array
        mergeSort(arr);
        System.out.println("\n" + arr);


    }

    private static void mergeSort(ArrayList<Integer> arr) {
        int length= arr.size(); 

        //Base Case - if length is 1 or less, no need to recurse anymore 
        if(length <= 1)
            return; 

        int middle = length / 2; 
        ArrayList<Integer> leftArray = new ArrayList<>(); 
        ArrayList<Integer> rightArray = new ArrayList<>(); 

        //1. Copy values into 2 arrays - 
        for(int i = 0; i < length; i++){
            if(i < middle) // if less than middle, copy to left array
                leftArray.add(arr.get(i)); 
            else 
                rightArray.add(arr.get(i)); // greater than middle, copy to right array

        }
        System.out.println("left arr: " + leftArray.toString());
        System.out.println("right arr: " + rightArray.toString());

        mergeSort(leftArray);
        mergeSort(rightArray);
        merge(leftArray, rightArray, arr); 
        

    }

    private static void merge(ArrayList<Integer> leftArray, ArrayList<Integer> rightArray, ArrayList<Integer> arr){
        int leftSize = leftArray.size();
        int rightSize = rightArray.size();
        int i = 0, l = 0, r = 0;

        // create a new list to store the merged elements
        ArrayList<Integer> merged = new ArrayList<>();

        while(l < leftSize && r < rightSize){
            if(leftArray.get(l) < rightArray.get(r)){
                merged.add(leftArray.get(l));
                l++;
            } else {
                merged.add(rightArray.get(r));
                r++;
            }
        }

        // copy any remaining elements from the left or right array
        while(l < leftSize){
            merged.add(leftArray.get(l));
            l++;
        }

        while(r < rightSize){
            merged.add(rightArray.get(r));
            r++;
        }

        // update the elements in the original array
        for(int j = 0; j < merged.size(); j++){
            arr.set(j, merged.get(j));
        }
    }
}





