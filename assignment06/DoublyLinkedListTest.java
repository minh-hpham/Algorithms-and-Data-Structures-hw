package assignment06;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DoublyLinkedListTest
{
	public static DoublyLinkedList<Integer> linkedList;
	@Before
	public void setUpBefore() throws Exception
	{
		linkedList = new DoublyLinkedList<Integer>();
	}
	
	@Test
	public void testDoublyLinkedList()
	{
		linkedList = new DoublyLinkedList<Integer>();
		assertEquals(0, linkedList.size());
		
	}

	@Test
	public void testAddFirst()
	{
		linkedList.addFirst(7);
		assertEquals(7, (int)linkedList.get(0));
		assertEquals(1, linkedList.size());
		
		linkedList.addFirst(3);
		assertEquals(3, (int)linkedList.get(0));
		assertEquals(7, (int)linkedList.get(1));
		assertEquals(2, linkedList.size());
		
		linkedList.addFirst(6);
		assertEquals(6, (int)linkedList.get(0));
		assertEquals(3, (int)linkedList.get(1));
		assertEquals(7, (int)linkedList.get(2));
		assertEquals(3, linkedList.size());
	}

	@Test
	public void testAddLast()
	{
		linkedList.addLast(7);
		assertEquals(7, (int)linkedList.get(0));
		assertEquals(1, linkedList.size());
		
		linkedList.addLast(3);
		assertEquals(7, (int)linkedList.get(0));
		assertEquals(3, (int)linkedList.get(1));
		assertEquals(2, linkedList.size());
		
		linkedList.addLast(6);
		assertEquals(7, (int)linkedList.get(0));
		assertEquals(3, (int)linkedList.get(1));
		assertEquals(6, (int)linkedList.get(2));
		assertEquals(3, linkedList.size());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddForExceptionIfLessThan0Input()
	{
		linkedList.add(-1, 7);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddForExceptionIfGreaterThanSize()
	{
		linkedList.addFirst(4);
		linkedList.addFirst(4);
		linkedList.add(3, 3);
	}
	
	@Test
	public void testAddNormalCase()
	{
		linkedList.add(0, 3);
		assertEquals(3, (int)linkedList.get(0));
		assertEquals(1, linkedList.size());
		
		linkedList.add(0, 4);
		linkedList.add(1, 5);
		linkedList.add(1, 8);
		linkedList.add(2, 6);
		assertEquals(4, (int)linkedList.get(0));
		assertEquals(8, (int)linkedList.get(1));
		assertEquals(6, (int)linkedList.get(2));
		assertEquals(5, (int)linkedList.get(3));
		assertEquals(3, (int)linkedList.get(4));
		assertEquals(5, linkedList.size());
	}

	@Test (expected = NoSuchElementException.class)
	public void testGetFirstException()
	{
		linkedList.getFirst();
	}
	
	@Test
	public void testGetFirstNormalCase()
	{
		linkedList.addFirst(7);
		assertEquals(7, (int)linkedList.getFirst());
		
		linkedList.addFirst(5);
		assertEquals(5, (int)linkedList.getFirst());
		
	}

	@Test (expected = NoSuchElementException.class)
	public void testGetLastException()
	{
		linkedList.getLast();
	}
	
	@Test
	public void testGetLastNormalCase()
	{
		linkedList.addLast(7);
		assertEquals(7, (int)linkedList.getLast());
		
		linkedList.addLast(5);
		assertEquals(5, (int)linkedList.getLast());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetForExceptionIfLessThan0Input()
	{
		linkedList.get(-1);
	}
	
	@Test (expected = IndexOutOfBoundsException.class)
	public void testGetForExceptionIfGreaterThanSize()
	{
		linkedList.addFirst(4);
		linkedList.addFirst(4);
		linkedList.get(2);
	}
	
	@Test
	public void testGetNormalCase()
	{
		linkedList.add(0, 3);
		assertEquals(3, (int)linkedList.get(0));
		assertEquals(1, linkedList.size());
		
		linkedList.add(0, 4);
		linkedList.add(1, 5);
		linkedList.add(1, 8);
		linkedList.add(2, 6);
		assertEquals(4, (int)linkedList.get(0));
		assertEquals(8, (int)linkedList.get(1));
		assertEquals(6, (int)linkedList.get(2));
		assertEquals(5, (int)linkedList.get(3));
		assertEquals(3, (int)linkedList.get(4));
		assertEquals(5, linkedList.size());
	}

	@Test (expected = NoSuchElementException.class)
	public void testRemoveFirstException()
	{
		linkedList.removeFirst();
	}
	
	@Test 
	public void testRemoveFirstNormalCase()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		assertEquals(2, (int)linkedList.removeFirst());
		assertEquals(5, linkedList.size());
		assertEquals(3, (int)linkedList.getFirst());
		
		assertEquals(3, (int)linkedList.removeFirst());
		assertEquals(4, linkedList.size());
		assertEquals(5, (int)linkedList.getFirst());
	}
	
	@Test
	public void testRemoveFirstOnlyOneItem()
	{
		linkedList.addFirst(5);
		assertEquals(5, (int)linkedList.removeFirst());
		assertEquals(0, linkedList.size());
	}

	@Test (expected = NoSuchElementException.class)
	public void testRemoveLastException()
	{
		linkedList.addFirst(5);
		linkedList.removeFirst();
		linkedList.removeLast();
	}
	
	@Test 
	public void testRemoveLastNormalCase()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);

		assertEquals(5, (int)linkedList.removeLast());
		assertEquals(5, linkedList.size());
		assertEquals(8, (int)linkedList.getLast());
		
		assertEquals(8, (int)linkedList.removeLast());
		assertEquals(4, linkedList.size());
		assertEquals(6, (int)linkedList.getLast());
	}
	
	@Test
	public void testRemoveLastOnlyOneItem()
	{
		linkedList.addFirst(5);
		assertEquals(5, (int)linkedList.removeLast());
		assertEquals(0, linkedList.size());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveLessThanZero()
	{
		linkedList.remove(-1);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void testRemoveIndexBiggerThanSize()
	{
		linkedList.addFirst(1);
		linkedList.remove(1);
	}
	
	@Test 
	public void testRemoveNormalCase()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		
		assertEquals(2, (int)linkedList.remove(0));
		assertEquals(5, linkedList.size());
		assertEquals(6, (int)linkedList.remove(2));
		assertEquals(4, linkedList.size());
		
		assertEquals(3, (int) linkedList.get(0));
		assertEquals(5, (int)linkedList.get(1));
		assertEquals(8, (int)linkedList.get(2));
		assertEquals(5, (int)linkedList.get(3));
		assertEquals(5, (int)linkedList.getLast());
	}
	
	@Test
	public void testRemoveOnlyOneItem()
	{
		linkedList.addFirst(5);
		assertEquals(5, (int)linkedList.remove(0));
		assertEquals(0, linkedList.size());
	}

	@Test
	public void testIndexOfNotContainedElement()
	{
		assertEquals(-1, linkedList.indexOf(5));
		
		linkedList.addFirst(1);
		linkedList.addFirst(2);
		assertEquals(-1 , linkedList.indexOf(3));
		
	}

	@Test
	public void testIndexOfDuplicatedList()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		assertEquals(2, linkedList.indexOf(5));
		
	}
	@Test
	public void testIndexOfNoDuplicatedList()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		assertEquals(4, linkedList.indexOf(5));
		
	}
	@Test
	public void testLastIndexOfNotContainedElement()
	{
		assertEquals(-1, linkedList.lastIndexOf(4));
		
		linkedList.addFirst(1);
		linkedList.addFirst(2);
		assertEquals(-1 , linkedList.lastIndexOf(3));
		
	}

	@Test
	public void testLastIndexOfDuplicatedList()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		assertEquals(5, linkedList.lastIndexOf(5));
		
	}

	@Test
	public void testSizeEmptyList()
	{
		assertEquals(0, linkedList.size());
	}
	@Test
	public void testSizeNonEmptyList()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		assertEquals(6, linkedList.size());
	}
	@Test
	public void testIsEmpty()
	{
		assertTrue(linkedList.isEmpty());
		linkedList.addFirst(8);
		assertFalse(linkedList.isEmpty());
	}

	@Test
	public void testClearForEmptyList()
	{
		// Shouldn't throw an exception
		linkedList.clear();
		assertEquals(0, linkedList.size());
	}
	
	@Test
	public void testClearForNonEmptyList()
	{

		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		linkedList.clear();
		assertEquals(0, linkedList.size());
	}

	@Test
	public void testToArrayForEmptyList()
	{
		assertArrayEquals(new Integer[0], linkedList.toArray());
	}

	@Test
	public void testToArrayForNonEmptyList()
	{
		linkedList.addFirst(5);
		linkedList.addFirst(8);
		linkedList.addFirst(6);
		linkedList.addFirst(5);
		linkedList.addFirst(3);
		linkedList.addFirst(2);
		
		Integer[] intArray = new Integer[]{2, 3, 5, 6, 8, 5};
		assertArrayEquals(intArray, linkedList.toArray());
	}
	
	//TODO: Ask if need to test iterator methods
	@Test
	public void testIteratorHasNext()
	{
		linkedList.addFirst(5);
		java.util.Iterator<Integer> it = linkedList.iterator();
		assertTrue(it.hasNext());		
		it.next();
		it.remove();
		assertFalse(it.hasNext());
		
		linkedList.addLast(10);
		assertTrue(it.hasNext());
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testNextIteratorHasNoMoreElements()
	{
		java.util.Iterator<Integer> it = linkedList.iterator();
		it.next();
		
		linkedList.addLast(3);
		it.next();
		it.remove();
	
	}
	
	@Test
	public void testIteratorNext()
	{
		java.util.Iterator<Integer> it = linkedList.iterator();
		linkedList.addFirst(4);
		linkedList.addLast(10);
		assertEquals(4, (int) it.next());
		assertEquals(10, (int)it.next());
		
	}
	
	@Test(expected = IllegalStateException.class)
	public void testRemoveIteratorWithoutCallingNext()
	{
		java.util.Iterator<Integer> it = linkedList.iterator();
		it.remove();
	}
	@Test(expected = IllegalStateException.class)
	public void testCallingRemoveIteratorMoreThanOnce()
	{
		linkedList.addFirst(1);
		linkedList.addFirst(2);
		
		java.util.Iterator<Integer> it = linkedList.iterator();
		it.next();
		it.remove();
		it.remove();
	}
	
	@Test
	public void testIteratorRemove()
	{
		linkedList.addFirst(1);
		linkedList.addFirst(2);
		
		java.util.Iterator<Integer> it = linkedList.iterator();
		it.next();
		it.remove();
		
		it.next();
		it.remove();
		
		linkedList.addLast(5);
		it.next();
		it.remove();
	}

}
