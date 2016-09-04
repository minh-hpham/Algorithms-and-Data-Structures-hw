package assignment03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Minh Pham and To Tang
 *
 */
public class BinarySearchSetTest
{
	BinarySearchSet<String> stringSet;
	BinarySearchSet<Integer> integerSet;
	List<Integer> numbers, nullInt;
	List<String> names, nullString;

	@Before
	public void setUp() throws Exception
	{
		Comparator<String> comparator = new Comparator<String>()
		{
			@Override
			public int compare(String o1, String o2)
			{
				return o1.compareTo(o2);
			}
		};

		stringSet = new BinarySearchSet<String>(comparator);
		integerSet = new BinarySearchSet<Integer>();
		numbers = Arrays.asList(2, 7, 9, 13, 20);
		names = Arrays.asList("Adam", "Amanda", "April", "Jake", "Jessie");
		nullString = Arrays.asList("Milk", "eggs", "bread", null, null);
		nullInt = Arrays.asList(3, 5, 9, null, null);
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindFirstElementInEmptySet()
	{
		stringSet.first();
	}

	@Test
	public void testFindFirstElementInNonEmptySet()
	{
		stringSet.add("Milk");
		assertEquals("Milk", stringSet.first());
	}

	@Test(expected = NoSuchElementException.class)
	public void testFindLastElementInEmptySet()
	{
		stringSet.last();
	}

	@Test
	public void testFindLastElementInNonEmptySet()
	{
		stringSet.add("Milk");
		stringSet.add("Test");
		assertEquals("Test", stringSet.last());
	}

	@Test
	public void testAddANumberInEmptySet()
	{
		integerSet.add(5);
		assertEquals(1, integerSet.size());
	}

	@Test
	public void testAddANumberTwice()
	{
		integerSet.add(5);
		assertFalse(integerSet.add(5));
		assertEquals(1, integerSet.size());
	}

	@Test
	public void testAddNull()
	{
		assertFalse(integerSet.add(null));
	}

	@Test
	public void testAddManyNumbersToEmptySet()
	{
		integerSet.add(1);
		integerSet.add(2);
		integerSet.add(3);
		integerSet.add(4);
		integerSet.add(5);
		integerSet.add(6);
		integerSet.add(7);
		integerSet.add(8);
		assertEquals(8, integerSet.size());// set inital size = 5
	}

	@Test
	public void testAddAStringInEmptySet()
	{
		stringSet.add("Testing");
		assertEquals(1, stringSet.size());
	}

	@Test
	public void testAddAStringTwiceInEmptySet()
	{
		stringSet.add("Testing");
		stringSet.add("Testing");
		assertEquals(1, stringSet.size());
	}

	@Test
	public void testAddAllStringFromNonEmptySetToAnEmptySet()
	{
		assertTrue(stringSet.addAll(names));
		assertEquals(names.size(), stringSet.size());
	}

	@Test
	public void testAddAllASetWithStringAndNullToEmptySet()
	{
		assertTrue(stringSet.addAll(nullString));
		assertEquals(3, stringSet.size());
		assertFalse(stringSet.addAll(nullString));
	}

	@Test
	public void testAddAllIntFromNonEmptySetToAnEmptySet()
	{
		assertTrue(integerSet.addAll(numbers));
		assertEquals(numbers.size(), integerSet.size());
	}

	@Test
	public void testAddAllASetWithIntAndNullToEmptySet()
	{
		assertTrue(integerSet.addAll(nullInt));
		assertEquals(3, integerSet.size());
	}

	@Test
	public void testClearTheSet()
	{
		integerSet.add(3);
		integerSet.add(5);
		integerSet.add(8);
		integerSet.clear();
		assertEquals(0, integerSet.size());
	}

	@Test
	public void testContainsExistElement()
	{
		integerSet.add(5);
		integerSet.add(7);
		assertTrue(integerSet.contains(5));
		assertTrue(integerSet.contains(7));
	}

	@Test
	public void testContainsNonExistElement()
	{
		integerSet.add(5);
		integerSet.add(7);
		assertFalse(integerSet.contains(8));
	}

	@Test
	public void testContainsAll()
	{
		integerSet.addAll(numbers);
		assertTrue(integerSet.containsAll(numbers));
		assertFalse(integerSet.containsAll(nullInt));
		assertTrue(integerSet.containsAll(Arrays.asList(7, 9, 13)));
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(integerSet.isEmpty());
		integerSet.add(8);
		assertFalse(integerSet.isEmpty());
	}

	@Test
	public void testIteratorNextHasNextMethodsOfAQualifiedSet()
	{
		integerSet.addAll(numbers);
		Iterator<Integer> it = integerSet.iterator();
		assertTrue(it.hasNext());
		for (Integer item : numbers)
		{
			Integer iteratorItem = it.next();
			assertEquals(item, iteratorItem);
		}
		assertFalse(it.hasNext());
	}

	@Test
	public void testIteratorRemoveMethod()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		Iterator<Integer> it = integerSet.iterator();
		assertTrue(integerSet.contains(2));
		it.next();
		it.remove();
		assertFalse(integerSet.contains(2));
		assertTrue(integerSet.contains(7));
		it.next();
		it.remove();
		assertFalse(integerSet.contains(9));
		
	}
	@Test
	public void testIteratorRemoveAfterCallNextSeveralTimes()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		Iterator<Integer> it = integerSet.iterator();
		it.next();
		it.next();
		it.remove();
		assertFalse(integerSet.contains(7));
		
	}
	@Test(expected = IllegalStateException.class)
	public void testIteratorRemoveWithoutNextFirst()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		Iterator<Integer> it = integerSet.iterator();
		it.remove();
	}

	@Test
	public void testRemoveExistElement()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		assertTrue(integerSet.remove(2));
		assertFalse(integerSet.contains(2));
		assertTrue(integerSet.remove(9));
		assertFalse(integerSet.contains(9));
		assertTrue(integerSet.remove(20));
		assertFalse(integerSet.contains(20));
	}

	@Test
	public void testRemoveNonExistElement()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		assertFalse(integerSet.remove(8));
	}

	@Test
	public void testRemoveAllExistElement()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		assertTrue(integerSet.removeAll(Arrays.asList(2, 9, 20)));
		assertFalse(integerSet.contains(2));
		assertFalse(integerSet.contains(9));
		assertFalse(integerSet.contains(20));
	}

	@Test
	public void testRemoveAllNonExistElement()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		assertFalse(integerSet.removeAll(Arrays.asList(1, 8, 10)));
	}

	@Test
	public void testSizeOfNonEmptySet()
	{
		integerSet.addAll(numbers);// {2, 7, 9, 13, 20}
		assertEquals(5, integerSet.size());
	}

	@Test
	public void testSizeOfEmptySet()
	{
		assertEquals(0, integerSet.size());
	}

	@Test
	public void testToArray()
	{
		integerSet.addAll(Arrays.asList(8, 18, 28, 38, 48, 58));
		assertEquals(6, integerSet.toArray().length);
	}

	@Test
	public void testBinarySearchIntegerOddNumberArrayElementPresent()
	{
		Integer[] arrInt = new Integer[] { 2, 7, 9, 13, 20 };
		int expected = 0;
		int actualInt = integerSet.binarySearch(arrInt, 2, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 1;
		actualInt = integerSet.binarySearch(arrInt, 7, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 2;
		actualInt = integerSet.binarySearch(arrInt, 9, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 3;
		actualInt = integerSet.binarySearch(arrInt, 13, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 4;
		actualInt = integerSet.binarySearch(arrInt, 20, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);

		expected = -1;
		actualInt = integerSet.binarySearch(arrInt, 88, 0, arrInt.length - 1, true);
		assertEquals(expected, actualInt);

	}

	@Test
	public void testBinarySearchStringOddNumberArrayElementPresent()
	{
		String[] arrString = new String[] { "Adam", "Amanda", "April", "Jake", "Jessie" };
		int expected = 0;
		int actualInt = stringSet.binarySearch(arrString, "Adam", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 1;
		actualInt = stringSet.binarySearch(arrString, "Amanda", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 2;
		actualInt = stringSet.binarySearch(arrString, "April", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 3;
		actualInt = stringSet.binarySearch(arrString, "Jake", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);
		expected = 4;
		actualInt = stringSet.binarySearch(arrString, "Jessie", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);

		expected = -1;
		actualInt = stringSet.binarySearch(arrString, "June", 0, arrString.length - 1, true);
		assertEquals(expected, actualInt);

	}

	@Test
	public void testBinarySearchIntegerOddNumberArrayElementNotPresent()
	{
		Integer[] arrInt = new Integer[] { 2, 7, 9, 13, 20 };
		int expected = 0;
		int actualInt = integerSet.binarySearch(arrInt, 1, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 1;
		actualInt = integerSet.binarySearch(arrInt, 3, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 2;
		actualInt = integerSet.binarySearch(arrInt, 8, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 3;
		actualInt = integerSet.binarySearch(arrInt, 10, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 4;
		actualInt = integerSet.binarySearch(arrInt, 18, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 5;
		actualInt = integerSet.binarySearch(arrInt, 28, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);

		expected = -1;
		actualInt = integerSet.binarySearch(arrInt, 20, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);

	}

	@Test
	public void testBinarySearchIntegerEvenNumberArrayElementNotPresent()
	{
		Integer[] arrInt = new Integer[] { 2, 7, 9, 13 };
		int expected = 0;
		int actualInt = integerSet.binarySearch(arrInt, 1, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 1;
		actualInt = integerSet.binarySearch(arrInt, 3, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 2;
		actualInt = integerSet.binarySearch(arrInt, 8, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 3;
		actualInt = integerSet.binarySearch(arrInt, 10, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
		expected = 4;
		actualInt = integerSet.binarySearch(arrInt, 18, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);

		expected = -1;
		actualInt = integerSet.binarySearch(arrInt, 13, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);

	}

	@Test
	public void testBinarySearchIntegerEmptyArray()
	{
		Integer[] arrInt = new Integer[] {};
		int expected = 0;
		int actualInt = integerSet.binarySearch(arrInt, 100, 0, arrInt.length - 1, false);
		assertEquals(expected, actualInt);
	}
}
