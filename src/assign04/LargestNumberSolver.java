package assign04;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * 
 * @author Anuvesha Chilwal and Ranbir Singh
 * @version Feburary 8, 2023
 */
public class LargestNumberSolver
{
    /**
     * Insertion sort algorithm
     * @param arr The array to sort
     * @param cmp The comparator to use for sorting
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {
        // loop through the array starting from the second element
        for (int i = 1; i < arr.length; i++) {
            // save the current element
            T key = arr[i];
            int j = i - 1;
            // shift elements of the array to the right to make room for the current element
            while (j >= 0 && cmp.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            // insert the current element into its correct position
            arr[j + 1] = key;
        }
    }

    /**
     * Find the largest number that can be made from a given integer array
     * @param arr The integer array
     * @return The largest number
     */
    public static BigInteger findLargestNumber(Integer[] arr) {

        //LAMBDA FUNCTION BELOW**********
        //sort the integer array using the custom comparator
        // insertionSort(arr, (a, b) -> {
        //     // create strings from the integers
        //     String str1 = String.valueOf(a);
        //     String str2 = String.valueOf(b);
        //     // compare the strings as concatenations and return the result
        //     return (str2 + str1).compareTo(str1 + str2);
        // });


        //WITHOUT Lambda way*************** 
        // Comparator<Integer> c1 = new Comparator<Integer>() {
        //     public int compare(Integer a, Integer b){
                
        //         //Get string value of a and b 
        //         String str1 = String.valueOf(a);
        //         String str2 = String.valueOf(b);

        //         // compare xy to yx
        //         return (str2 + str1).compareTo(str1 + str2);
        //         //return (str2 + str1).compareTo(str1 + str2);

        //     }
        // };
        //insertionSort(arr, c1);

        //sortLargest class defined at bottom
        insertionSort(arr, new sortLargest<>());
        
        //********************************************
        //insertionSort(arr, new sortLargest());

        // create a string builder to concatenate the elements of the sorted array
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }

        // return a new BigInteger object created from the concatenated string
        return new BigInteger(sb.toString());
    }

    /**
     * Find the largest int that can be made from a given integer array
     * @param arr The integer array
     * @return The largest int
     * @throws OutOfRangeException if the largest number is too large for an int
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        // find the largest number using findLargestNumber()
        BigInteger largestNum = findLargestNumber(arr);
        // check if the largest number is too large for an int
        if (largestNum.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new OutOfRangeException("Largest number too large for int data type");
        }
        // return the int value of the largest number
        return largestNum.intValue();
        
    }

    /**
     * Find the largest long that can be made from a given integer array
     * @param arr The integer array
     * @return The largest long
     * @throws OutOfRangeException if the largest number is too large for a long
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        // find the largest number using findLargestNumber()
        BigInteger largestNum = findLargestNumber(arr);
        // check if the largest number is too large for a long
        if (largestNum.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            throw new OutOfRangeException("Largest number too large for long data type");
        }
        // return the long value of the largest number
        return largestNum.longValue();
    }

    /**
    Calculates the sum of the largest numbers created from each integer array in the list.
    @param list list of integer arrays
    @return the sum of the largest numbers created from each array
    */
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger sum = BigInteger.ZERO;
        for (Integer[] arr : list) {
            sum = sum.add(findLargestNumber(arr));
        }
        return sum;
    }

    /**
     Finds the kth largest number created from the integer arrays in the list.
     @param list list of integer arrays
     @param k the position of the kth largest number
     @return the kth largest number created from the arrays
     @throws IllegalArgumentException if the position is invalid
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {
        //System.out.println("FIND Kth Largest: List.size(): " + list.size());

        if (k < 0 || k > list.size()) {
            throw new IllegalArgumentException("Invalid position in the list");
        }
        List<Integer[]> sortedList = new ArrayList<>(list);

        
        // insertionSort(sortedList.toArray(new Integer[0][0]), (a, b
        // ) -> {
        //     BigInteger num1 = findLargestNumber(a);
        //     BigInteger num2 = findLargestNumber(b);
        //     return num2.compareTo(num1);
        // });

        //insertionSort(sortedList.toArray(), new sortLargest<>());

        Object[] arr = sortedList.toArray(); 
        Arrays.sort(arr, new sortLargest<>());
        
        
        
        if (k >= sortedList.size() || k < 0) {
            throw new IllegalArgumentException("Invalid position");
        }
        
        return sortedList.get(k);
    }

    /**
     Reads integers from a file and adds them to a list of integer arrays.
     @param filename the name of the file to read from
     @return the list of integer arrays
     */
    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> list = new ArrayList<>();

        //try (BufferedReader br = new BufferedReader(new FileReader("src/assign04/integers.txt"))) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] strArr = line.split(" ");
                Integer[] intArr = new Integer[strArr.length];
                for (int i = 0; i < strArr.length; i++) {
                    intArr[i] = Integer.parseInt(strArr[i]);
                }
                list.add(intArr);
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     Main method that performs various operations on a list of integer arrays.
     @param args command line arguments
     */
    public static void main(String[] args) {
        List<Integer[]> list = readFile("src/assign04/integers.txt");

        BigInteger largestNumber = findLargestNumber(list.get(0));
        System.out.println("Largest number: " + largestNumber);

        try {
            int largestInt = findLargestInt(list.get(0));
            System.out.println("Largest int: " + largestInt);
        } catch (OutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        try {
            long largestLong = findLargestLong(list.get(0));
            System.out.println("Largest long: " + largestLong);
        } catch (OutOfRangeException e) {
            System.out.println(e.getMessage());
        }

        BigInteger sum = sum(list);
        System.out.println("Sum of largest numbers: " + sum);

        try {
            Integer[] kthLargest = findKthLargest(list, 0);
            System.out.println("Kth largest array: " + Arrays.toString(kthLargest));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

}

class sortLargest<Integer> implements Comparator<Integer>{

    public int compare(Integer a, Integer b){
                
        //Get string value of a and b 
        String str1 = String.valueOf(a);
        String str2 = String.valueOf(b);

        // compare xy to yx
        return (str2 + str1).compareTo(str1 + str2);
    }

}