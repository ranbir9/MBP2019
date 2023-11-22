package exammakeup;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class MedianTest {

    @Test
    public void testMedian() {
        int[] arr1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] arr2 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        int[] arr3 = { 3, 2, 1, 6, 5, 4, 9, 8, 7, 10 };
        int[] arr4 = { 5, 4, 3, 2, 1, 10, 9, 8, 7, 6 };
        int[] arr5 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            arr5[i] = 100000 - i;
        }

        assertEquals(5, Median.median(arr1));
        assertEquals(5, Median.median(arr2));
        assertEquals(6, Median.median(arr3));
        assertEquals(6, Median.median(arr4));
        assertEquals(50000, Median.median(arr5));
    }

    @Test
public void test2() {
    int[] arr = { 1, 2, 3, 4 };
    double expectedMedian = (2 + 3) / 2.0; // expected median is 2.5
    double actualMedian = Median.median(arr);
    assertEquals(expectedMedian, actualMedian, 0.0);
}

@Test
public void test4() {
    int[] arr = { 1, 2, 3, 4, 5 };
    int expectedMedian = 3; // expected median is 3 (middle value)
    int actualMedian = Median.median(arr);
    assertEquals(expectedMedian, actualMedian);
}

@Test
public void test5() {
    int[] arr = { 1, 2, 3, 4, 5 };
    int expectedMedian = 2; // expected median is 2 (middle value)
    int actualMedian = Median.median(arr);
    assertEquals(expectedMedian, actualMedian);
}

@Test
public void test6() {
    int[] arr = { 1, 2, 3, 4, 5, 6 };
    double expectedMedian = (4 + 5) / 2.0; // expected median is 4.5
    double actualMedian = Median.median(arr);
    assertEquals(expectedMedian, actualMedian, 0.0);
}

    @Test
    public void test7() {
        int[] arr = { 1, 2, 3, 4, 5, 6 };
        int expectedMedian = 2 + 3 / 2; // expected median is 2.5
        int actualMedian = Median.median(arr);
        assertEquals(expectedMedian, actualMedian);
    }

    @Test
    public void test8() {

        int[] arr = {};
        int expectedMedian = 0; // expected median is 0
        int actualMedian = Median.median(arr);
        assertEquals(expectedMedian, actualMedian);
    }

    @Test
    public void test9() {
        int[] arr = { 1 };
        int expectedMedian = 1; // expected median is 1
        int actualMedian = Median.median(arr);
        assertEquals(expectedMedian, actualMedian);
    }

    // @Test
    // public void test10() {
    //     int[] arr = new int[100000];
    //     Random rand = new Random();
    //     for (int i = 0; i < arr.length; i++) {
    //         arr[i] = rand.nextInt(100000);
    //     }
    //     int expectedMedian = findMedian(arr);
    //     int actualMedian = Median.median(arr);
    //     assertEquals(expectedMedian, actualMedian);
    // }

    @Test
    public void test3() {
    }

}
