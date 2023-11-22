package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListSorterTester {

    private ArrayList<Integer> list;
    private ArrayList<Integer> emptyList = new ArrayList<>(); 
    private ArrayList<Integer> ascendingList = ArrayListSorter.generateAscending(15); 
    private ArrayList<Integer> descendingList = ArrayListSorter.generateDescending(15); 

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(rand.nextInt(100));
        }
    }

    @Test
    public void testQuicksort() {
        System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        //System.out.println(sortedList.toString());
        ArrayListSorter.quicksort(sortedList);
        System.out.println(sortedList.toString());

    }

    @Test
    public void testMergesort() {
        System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        //System.out.println(sortedList.toString());
        ArrayListSorter.mergesort(sortedList);
        System.out.println(sortedList.toString());
    }

    @Test
    public void testMergesortAddTwoLists() {
        //System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.addAll(new ArrayListSorter().generateDescending(10));
        ArrayListSorter.mergesort(sortedList);
        //System.out.println(sortedList.toString());
        boolean isValid = true; 

        //compare order 
        for(int i = 0; i < sortedList.size() - 1; i++){
            if(sortedList.get(i) > sortedList.get(i+1)){
                isValid = false;
                break;  
            }
            //System.out.print(i + " ");
        }
        assertEquals(true, isValid);
    }

    @Test
    public void testQuicksortAddTwoLists() {
        //System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.addAll(new ArrayListSorter().generateDescending(10));
        ArrayListSorter.quicksort(sortedList);
        //System.out.println(sortedList.toString());
        boolean isValid = true; 

        //compare order 
        for(int i = 0; i < sortedList.size() - 1; i++){
            if(sortedList.get(i) > sortedList.get(i+1)){
                isValid = false;
                break;  
            }
        }
        assertEquals(true, isValid);
    }

    @Test
    public void testMergeSortEmptyList(){
        ArrayList<Integer> expected = new ArrayList<>(); 
        ArrayList<Integer> list = new ArrayList<>(emptyList);
        ArrayListSorter.mergesort(list);
        System.out.println(list);
        assertEquals(true, list.equals(expected)); 
    }


    @Test
    public void testMergeSortAscendingList(){
        ArrayList<Integer> list = new ArrayList<>(ascendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(ascendingList); 
        ArrayListSorter.mergesort(list);
        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testMergeSortAscendingListThenClear(){
        ArrayList<Integer> list = new ArrayList<>(ascendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(); 
        ArrayListSorter.mergesort(list);
        list.clear();
        assertEquals(true, list.equals(expectedList));
    }


    @Test
    public void testQuickSortAscendingList(){
        ArrayList<Integer> list = new ArrayList<>(ascendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(ascendingList); 

        ArrayListSorter.quicksort(list);

        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testQuickSortAscendingListThenClear(){
        ArrayList<Integer> list = new ArrayList<>(ascendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(); 
        ArrayListSorter.quicksort(list);
        list.clear();
        
        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testMergeSortDescendingList(){
        ArrayList<Integer> list = new ArrayList<>(descendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(ascendingList); 
        ArrayListSorter.mergesort(list);
        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testMergeSortDescendingListThenClear(){
        ArrayList<Integer> list = new ArrayList<>(descendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(); 
        ArrayListSorter.mergesort(list);
        list.clear();

        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testQuickSortDescendingList(){
        ArrayList<Integer> list = new ArrayList<>(descendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(); 
        ArrayListSorter.quicksort(list);
        list.clear();
        assertEquals(true, list.equals(expectedList));
    }

    @Test
    public void testQuickSortDescendingListThenClear(){
        ArrayList<Integer> list = new ArrayList<>(descendingList);
        ArrayList<Integer> expectedList = new ArrayList<>(); 
        ArrayListSorter.quicksort(list);
        list.clear();
        assertEquals(true, list.equals(expectedList));

    }

    @Test
    public void testMergesortAddTwoListsPermutatedList() {
        //System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.addAll(new ArrayListSorter().generatePermuted(10));
        ArrayListSorter.mergesort(sortedList);
        //System.out.println(sortedList.toString());
        boolean isValid = true; 

        //compare order 
        for(int i = 0; i < sortedList.size() - 1; i++){
            if(sortedList.get(i) > sortedList.get(i+1)){
                isValid = false;
                break;  
            }
            //System.out.print(i + " ");
        }
        assertEquals(true, isValid);
    }

    @Test
    public void testQuickortAddTwoListsPermutatedList() {
        //System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.addAll(new ArrayListSorter().generatePermuted(10));
        ArrayListSorter.quicksort(sortedList);
        //System.out.println(sortedList.toString());
        boolean isValid = true; 

        //compare order 
        for(int i = 0; i < sortedList.size() - 1; i++){
            if(sortedList.get(i) > sortedList.get(i+1)){
                isValid = false;
                break;  
            }
            //System.out.print(i + " ");
        }
        assertEquals(true, isValid);
    }

    @Test
    public void testQuickortAddTwoListsPermutatedListAddPermutatedList() {
        System.out.println(list.toString());
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        sortedList.addAll(new ArrayListSorter().generatePermuted(10));
        ArrayListSorter.quicksort(sortedList);
        sortedList.addAll(new ArrayListSorter().generatePermuted(15));
        ArrayListSorter.quicksort(sortedList);
        System.out.println(sortedList.toString());
        boolean isValid = true; 

        //compare order 
        for(int i = 0; i < sortedList.size() - 1; i++){
            if(sortedList.get(i) > sortedList.get(i+1)){
                isValid = false;
                break;  
            }
            //System.out.print(i + " ");
        }
        assertEquals(true, isValid);
    }
}