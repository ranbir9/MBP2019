package makeup6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import makeup6.SinglyLinkedList;

public class SinglyLinkedListTester {
    private SinglyLinkedList<Integer> smallList = new SinglyLinkedList<>();

    @BeforeEach
    public void setUp() throws Exception {
        // SinglyLinkedList<Integer> smallList = new SinglyLinkedList<Integer>();
        smallList.insert(0,10);
        smallList.insert(0, 20);
        smallList.insert(0, 30);
        smallList.insert(0, 40);

    }


    // AutoGrade test: remove index size-1 on small list, check return value
    @Test
    public void removeLastIndexOnSmallListCheckRetVal() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        // test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        test.insert(0, 40);

        // remove index size-1
        var actualVal = smallList.delete(smallList.size() - 1);
        Integer expectedVal = 10;

        // test output
        System.out.println("smallList: " + smallList.toString());
        System.out.println("test: " + test.toString());

        assertEquals(actualVal, expectedVal);
        assertEquals(test.toString(), smallList.toString());
    }

    // AutoGrader test: getFirst on small list, check return value
    @Test
    public void getFirstOnSmallList() {
        final Integer expectedVal = 40;
        var actualVal = smallList.get(0);

        assertEquals(actualVal, expectedVal);
    }

    // AutoGrader test: add to a list at index size, check contents
    @Test
    public void addOneElementToEndContentsCheck() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(50);
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        test.insertFirst(40);

        // add 50 at index size
        smallList.insert(smallList.size(), 50);

        System.out.println("smallList: " + smallList.toString());
        System.out.println("test: " + test.toString());

        assertEquals(test.toString(), smallList.toString());
    }

    // AutoGrader test: adds to a small SinglyLinkedList
    @Test
    public void testAdd() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        test.insertFirst(40);
        test.insertFirst(0);

        // adding 0 by using the insertFirst()
        smallList.insertFirst(0);
        assertEquals(test.toString(), smallList.toString());
    }

    // AutoGrader test: remove index size-1 on small List, check contents
    @Test
    public void removeLastIndexOnSmallListContentsCheck() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(20);
        test.insertFirst(30);
        test.insertFirst(40);
        // delete node with value 10
        smallList.delete(smallList.size() - 1);

        assertEquals(test.toString(), smallList.toString());
    }

    // AutoGrader test: add to an empty list at index 0, check size
    @Test
    public void addOneElementSizeCheck() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insert(0, 5);
        assertEquals(1, test.size());
    }

    // AutoGrader test: add to list at index size, check size
    @Test
    public void addOneElementToEndSizeCheck() {
        final int expectedSize = 5;
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(0);
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        test.insertFirst(40);

        // add 0 at index size
        smallList.insert(smallList.size(), 0);
        assertEquals(expectedSize, smallList.size());
    }

    // AutoGrader tests: add to an empty list at index 0, check contents
    @Test
    public void addOneElementContentsCheck() {
        final String expectedStr = "{ 1 }";
        final Integer expectedVal = 1;
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insert(0, 1);
        var getIndex0 = test.get(0);

        assertEquals(expectedVal, getIndex0);
        assertEquals(expectedStr, test.toString());
    }

    // AutoGrader test: delete index 0 on small list, check contents
    @Test
    public void deleteIndex0OnSmallListCheckRetVal() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        // test.insertFirst(40);
        Integer expectedVal = 40;

        // remove index 0 from smallList
        var actualVal = smallList.delete(0);
        System.out.println("smallList: " + smallList.toString());
        System.out.println("test: " + test.toString());

        assertEquals(test.toString(), smallList.toString());
        assertEquals(expectedVal, actualVal);
    }

    /**
     * /** AutoGrader test: call iterator remove() for every other element,
     * check list contents
     */
    @Test
    public void iteratorRemoveEveryOtherElement() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        list.insertFirst(40);

        // Create an iterator and advance it by one element
        var iterator = list.iterator();
        iterator.next();

        // remove every other from list
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals("{ 40 --> 20 }", list.toString());

    }

    // delete index 0 on small list, check size
    @Test
    public void deleteIndex0OnSmallListSizeCheck() {
        final int expectedSize = 3;
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        // test.insertFirst(40);
        // delete index 0
        smallList.delete(0);
        int actualSize = smallList.size();
        assertEquals(expectedSize, actualSize);
    }

    // Auto Grader test: check indexOf() first element in small list returns 0
    @Test
    public void indexOfFirstElementSmallList() {
        int actualIndex = smallList.indexOf(40);
        assertEquals(0, actualIndex);
    }

    // Auto Grader test: clear() small list, check size()
    // Had to change clear() to set size to 0*********
    @Test
    public void clearOnSmallListSizeCheck() {
        smallList.clear();
        assertEquals(0, smallList.size());
    }

    /**
     * Auto Grader test: call iterator remove() for 2 consecutive
     * elements, check list contents
     */
    @Test
    public void iteratorRemove2ConsecutiveElements() {
        assertEquals(1, 0);
    }

    // Auto Grader test: call next() and remove() on single item list iterator
    @Test
    public void testSingleIterator() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.insertFirst(10);
        list.insertFirst(20);
        list.insertFirst(30);
        list.insertFirst(40);

        // Create an iterator and advance it by one element
        var iterator = list.iterator();
        iterator.next();
        // remove every other from list
        iterator.remove();

        System.out.println(list.toString());
        assertEquals("{ 40 --> 20 --> 10 }", list.toString());
    }

    // Auto Grader test: deleteFirst on small list, check return value
    @Test
    public void deleteFirstOnSmallListReturnCheck() {
        final Integer expectedVal = 30;
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        // deletes head
        smallList.deleteFirst();
        // gets new head
        Integer actualVal = smallList.get(0);
        assertEquals(expectedVal, actualVal);
    }

    /**
     * Auto Grader test: check indexOf() last element in small
     * list return index size-1
     */
    @Test
    public void indexOfEndElementSmallList() {
        int expectedVal = smallList.indexOf(10);
        int lastIndex = smallList.size() - 1;
        assertEquals(lastIndex, expectedVal);
    }

    /**
     * Auto Grader Test: check simutaneous iterator
     * instances are accurate
     */
    @Test
    public void multipleIteratorsAtOnce() {
        assertEquals(1, 0);
    }

    // Auto Grader Test: removeFirst on small list, check size
    @Test
    public void removeFirstOnSmallListSizeCheck() {
        // size 4
        assertEquals(smallList.size(), 4);
        // delete and now size is 3
        smallList.deleteFirst();
        assertEquals(smallList.size(), 3);
    }

    /**
     * Auto Grader Test: Iteratore with next() until end,
     * then call next() again, expect exception
     */
    @Test
    public void nextThrowsProperExceptionAtEndOfIteration() {
        assertEquals(1, 0);
    }

    /*
     * Auto Grader Test: check indexOf() a middle element in
     * a small list returns correct index
     */
    @Test
    public void indexOfMiddleElementSmallList() {
        // add 5th element to smallList
        smallList.insertFirst(50);
        final int middle = smallList.size() / 2;
        assertEquals(middle, smallList.indexOf(30));
    }

    // Auto Grader test: delete index size-1 on small list, check size
    @Test
    public void removeLastIndexOnSmallListSizeCheck() {
        assertEquals(smallList.size(), 4);
        // delete last index of smallList, size now becomes 3
        smallList.delete(smallList.size() - 1);
        assertEquals(smallList.size(), 3);
    }

    // Auto Grader test: check toArray on Small list
    @Test
    public void smallListToArray() {
        var newArr = smallList.toArray();
        assertEquals(smallList.toString(), smallList.toString(newArr));
    }

    // Auto Grader test: delete index 0 on small list, check contents
    @Test
    public void deleteIndex0OnSmallListContentsCheck() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        smallList.delete(0);

        // 40 doesn't exist anymore, expect -1
        assertEquals(-1, smallList.indexOf(40));
        assertEquals(0, smallList.indexOf(30));
        assertEquals(1, smallList.indexOf(20));
        assertEquals(2, smallList.indexOf(10));
        assertEquals(test.toString(), smallList.toString());

    }

    /*
     * Auto Grader test: check indexOf() non-existent element
     * in small list return -1
     */
    @Test
    public void indexOfNonExistentElementInSmallList() {
        smallList.delete(0);

        // 40 doesn't exist anymore, expect -1
        assertEquals(-1, smallList.indexOf(40));
    }

    /*
     * Auto Grader test: many adds and removes randomly
     * in large list, check contents
     */
    @Test
    public void addAndRemoveRandomlyInLargeList() {
        List<Integer> test = new SinglyLinkedList<>();

        // add 100 elements to the list
        for (int i = 0; i < 100; i++) {
            test.insertFirst(i);
        }

        // remove 50 random elements from the list
        for (int i = 0; i < 50; i++) {
            int index = (int) (Math.random() * test.size());
            test.delete(index);
        }
        assertEquals(test.size(), 50);
    }

    // Auto Grader Test: Clear() small list, check contents
    @Test
    public void clearOnSmallListContentsCheck() {
        smallList.clear();
        final String expectedStr = "{  }";
        assertEquals(expectedStr, smallList.toString());
        assertEquals(0, smallList.size());
        assertEquals(true, smallList.isEmpty());
    }

    // Auto Grader Test: DeleteFirst on small list, check contents
    @Test
    public void removeFirstOnSmallListContentsCheck() {
        assertEquals(0, smallList.indexOf(40));
        smallList.deleteFirst();
        assertEquals(-1, smallList.indexOf(40));
    }

    /*
     * Auto Grader Test: create large list by adding
     * at random indexes, check contents
     */
    @Test
    public void addInRandomLocations() {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

        // add random elements
        for (int i = 0; i < 1000; i++) {
            int element = (int) (Math.random() * 1000);
            int index = (int) (Math.random() * (i + 1));
            list.insert(index, element);
        }
        assertEquals(1000, list.size());
    }

    // Auto Grader test: build a small stack, then clear() and check size()
    @Test
    public void clearSmallStackSizeCheck() {
        SinglyLinkedList<Integer> test = new SinglyLinkedList<>();
        test.insertFirst(10);
        test.insertFirst(20);
        test.insertFirst(30);
        test.clear();
        assertEquals(0, test.size());
    }

}
