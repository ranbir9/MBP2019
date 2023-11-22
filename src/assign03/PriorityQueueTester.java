package assign03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Erin Parker and Ranbir Singh and Anuvesha Chilwal
 * @version Feburary 2, 2022
 */

public class PriorityQueueTester {
	private SimplePriorityQueue<Integer> test1 = new SimplePriorityQueue<Integer>();
	private SimplePriorityQueue<Integer> halfFullArray = new SimplePriorityQueue<Integer>();
	private ArrayList<Integer> arrayList = new ArrayList<>(); 

	

	@BeforeEach
	void setUp() throws Exception {
		halfFullArray.insert(3);
		halfFullArray.insert(10);
		halfFullArray.insert(103);
		halfFullArray.insert(10);
		halfFullArray.insert(10);
		halfFullArray.insert(120);
		halfFullArray.insert(60);
		halfFullArray.insert(20);

		arrayList.add(3);
		arrayList.add(5); 
		
		

	}

	@Test
	public void testInsert3Duplicates() {
		test1.insert(10);
		test1.insert(10);
		test1.insert(10);

		int actualVal = test1.size();
		System.out.println(test1.toString());
		assertEquals(3, actualVal);

	}

	@Test
	public void testInsert3IntergersDescending() {
		test1.insert(30);
		test1.insert(20);
		test1.insert(10);

		int actualVal = test1.size();
		System.out.println(test1.toString());
		assertEquals(3, actualVal);

	}

	@Test
	public void testInsert3IntergersAscending() {
		test1.insert(10);
		test1.insert(20);
		test1.insert(60);

		int actualVal = test1.size();
		System.out.println(test1.toString());
		assertEquals(3, actualVal);

	}

	@Test
	public void testInsertRandomIntegers() {
		test1.insert(10);
		test1.insert(20);
		test1.insert(60);
		test1.insert(5);
		test1.insert(60);
		test1.insert(5);
		test1.insert(20);
		test1.insert(200);
		test1.insert(65);
		String expected = "[ 5  5  10  20  20  60  60  65  200  null  null  null  null  null  null  null ]";

		int actualVal = test1.size();
		System.out.println(test1.toString());
		assertEquals(expected, test1.toString());

	}

	@Test
	public void testInsert17Elements() {
		test1.insert(10);
		test1.insert(20);
		test1.insert(60);
		test1.insert(5);
		test1.insert(60);
		test1.insert(5);
		test1.insert(20);
		test1.insert(200);
		test1.insert(65);
		test1.insert(10);
		test1.insert(20);
		test1.insert(60);
		test1.insert(5);
		test1.insert(60);
		test1.insert(5);
		System.out.println(test1.size());
		test1.insert(20);
		System.out.println(test1.size());
		test1.insert(200);

		int actualVal = test1.size();
		System.out.println(test1.toString());
		assertEquals(32, test1.arrLength());
	}

	/******** Tests for Clear() Method ********/

	@Test
	public void testIsEmptyOnEmptyArray() {
		boolean actual = test1.isEmpty();
		assertEquals(true, actual);
	}

	@Test
	public void testIsEmptyAfterCleared() {
		halfFullArray.clear();
		boolean actual = test1.isEmpty();
		System.out.println(halfFullArray.toString());
		assertEquals(true, actual);
	}

	@Test
	public void testIsEmptyAfterArraySizeDoubledThenCleared() {
		// insert 20 elements, this will make the array double
		for (int i = 0; i < 20; i++)
			halfFullArray.insert(i + 100);

		halfFullArray.clear();
		boolean actual = test1.isEmpty();
		System.out.println(halfFullArray.toString());

		assertEquals(true, actual);
	}

	/*
	 * Tests for Contains() method
	 */

	@Test
	public void testContainsOnOneElement() {
		test1.insert(1);
		boolean actual = test1.contains(1);
		assertEquals(true, actual);
	}

	@Test
	public void testContainsOnMissingElement() {
		test1.insert(1);
		boolean actual = test1.contains(9);
		assertEquals(false, actual);
	}

	@Test
	public void testContainsOnEmptyArray() {
		boolean actual = test1.contains(1);
		assertEquals(false, actual);
	}

	@Test
	public void testContainsInvalidParameter() {
		boolean actual = test1.contains(null);
		assertEquals(false, actual);
	}

	/*
	 * Tests for the Size() Method
	 */

	@Test
	public void TestSizeOnEmptyArray() {
		int actual = test1.size();
		assertEquals(0, actual);
	}

	@Test
	public void TestSizeOnOneElementArray() {
		test1.insert(4);
		int actual = test1.size();
		assertEquals(1, actual);
	}

	@Test
	public void TestSizeOnDoubledArray() {
		for (int i = 0; i < 20; i++)
			test1.insert(i + 100);

		int actual = test1.size();
		assertEquals(20, actual);
	}

	@Test
	public void TestSizeOnDoubledArrayThenCleared() {
		for (int i = 0; i < 20; i++)
			test1.insert(i + 100);

		test1.clear();

		int actual = test1.size();
		assertEquals(0, actual);
	}

	/*
	 * Tests for DeleteMax() Method
	 * 
	 */

	 @Test
	public void TestDeleteMaxOnEmptyArray() {
		String expectedMessage = "java.util.NoSuchElementException";
		String actualMessage = "";

		try {
			var max = test1.deleteMax();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
			actualMessage = e.toString();
		}
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void TestDeleteMaxFromHalfFullArray() {
		System.out.println(halfFullArray.toString());
		var max = halfFullArray.deleteMax();

		final SimplePriorityQueue<Integer> newHalfFullArray = new SimplePriorityQueue<Integer>();
		newHalfFullArray.insert(3);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(103);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(60);
		newHalfFullArray.insert(20);
		System.out.println(newHalfFullArray.toString());

		String expected = newHalfFullArray.toString(); 
		String actual = halfFullArray.toString(); 

		assertEquals(expected, actual);
		assertEquals(new Integer(120), max);

	}

	@Test
	public void TestDeleteMaxTwice(){
		System.out.println(halfFullArray.toString());
		var max = halfFullArray.deleteMax();
		var max2 = halfFullArray.deleteMax(); 


		final SimplePriorityQueue<Integer> newHalfFullArray = new SimplePriorityQueue<Integer>();
		newHalfFullArray.insert(3);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(10);
		newHalfFullArray.insert(60);
		newHalfFullArray.insert(20);
		System.out.println(newHalfFullArray.toString());

		String expected = newHalfFullArray.toString(); 
		String actual = halfFullArray.toString(); 

		assertEquals(expected, actual);
		assertEquals(new Integer(120), max);
		assertEquals(new Integer(103), max2);
	}

	/*
	 * Tests for FindMax() Method
	 */

	@Test
	public void TestFindMaxOnHalfFullArray() {
		var actual = halfFullArray.findMax();
		Integer expected = 120;
		assertEquals(expected, actual);
		System.out.println(actual);
	}

	@Test
	public void TestFindMaxOnEmptyArray() {
		String expectedMessage = "java.util.NoSuchElementException";
		String actualMessage = "";

		try {
			var max = test1.findMax();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
			actualMessage = e.toString();
		}
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void TestFindMaxOnClearedArray() {
		halfFullArray.clear();
		String expectedMessage = "java.util.NoSuchElementException";
		String actualMessage = "";

		try {
			var max = halfFullArray.findMax();
		} catch (NoSuchElementException e) {
			System.out.println(e.toString());
			actualMessage = e.toString();
		}
		assertEquals(expectedMessage, actualMessage);

	}

	/*
	 * Tests for InsertAll() Method
	 * 
	 */

	 @Test
	 public void TestInsertAllOnEmptyArray(){
		final SimplePriorityQueue<Integer> expected = new SimplePriorityQueue<Integer>();
		expected.insert(3);
		expected.insert(5);
		String expectedArr = expected.toString();

		test1.insertAll(arrayList);
		String after = test1.toString(); 
		assertEquals(expectedArr, after);
	}

	@Test
	 public void TestInsertAllOnHalfFullArray(){
		final SimplePriorityQueue<Integer> expected = new SimplePriorityQueue<Integer>();
		expected.insert(3);
		expected.insert(10);
		expected.insert(103);
		expected.insert(10);
		expected.insert(10);
		expected.insert(120);
		expected.insert(60);
		expected.insert(20);
		expected.insert(3);
		expected.insert(5);
		
		String expectedArr = expected.toString();

		halfFullArray.insertAll(arrayList);
		String after = halfFullArray.toString(); 
		assertEquals(expectedArr, after);
	}

}
