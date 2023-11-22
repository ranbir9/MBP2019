package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void testFindLargestNumber() {
        Integer[] arr1 = {1, 45, 9};
        BigInteger expected1 = new BigInteger("9451");
        assertEquals(expected1, LargestNumberSolver.findLargestNumber(arr1));

        Integer[] arr2 = {0, 0, 0};
        BigInteger expected2 = new BigInteger("0");
        assertEquals(expected2, LargestNumberSolver.findLargestNumber(arr2));
    }

    @Test
    void testFindLargestInt() {
        Integer[] arr = {999, 639, 1, 7, 58, 9};
        String expectedMessage = "The value is too large for the Largest number too large for int data type data type";
        String actualMessage = ""; 
        

		try {
			var result = LargestNumberSolver.findLargestInt(arr);
		} catch (OutOfRangeException e) {
			// System.out.println(e.toString());
            // System.out.println(e.getMessage());
			actualMessage = e.toString();
		}
		assertEquals(true, actualMessage.contains(expectedMessage));
    }

    @Test
    void testFindLargestLong() {
        Integer[] arr = {999, 639, 1, 7, 58, 9};
        
        long expected = 99997639581L;
        long result = Long.valueOf(0); 
        String actualMessage = ""; 
        

		try {
			 result = LargestNumberSolver.findLargestLong(arr);
            
		} catch (OutOfRangeException e) {
			System.out.println(e.toString());
            System.out.println(e.getMessage());
			actualMessage = e.toString();
		}
		assertEquals(result, expected);
        
    }

    @Test
    void testSum() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{88, 51});
        list.add(new Integer[]{7, 42, 97});

        BigInteger expected = new BigInteger("106593");
        assertEquals(expected, LargestNumberSolver.sum(list));
    }

    @Test
    void testFindKthLargest() {
        List<Integer[]> list = new ArrayList<>();
        list.add(new Integer[]{88, 51});
        //System.out.println(list.size());
        list.add(new Integer[]{7, 42, 97});
        //System.out.println(list.size());

        Integer[] expected1 = {7, 42, 97};
        assertArrayEquals(expected1, LargestNumberSolver.findKthLargest(list, 0));

        Integer[] expected2 = {88, 51};
        assertArrayEquals(expected2, LargestNumberSolver.findKthLargest(list, 1));

       
    }
}

