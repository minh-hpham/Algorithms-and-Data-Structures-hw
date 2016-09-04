package assignment07;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the LinkedListStack class
 * 
 * @author Hannah Potter and Minh Pham
 */
public class LinkedListStackTest
{
	/**
	 * LinkedListStack of Integers to be used for the test. Will be empty at the beginning of each test.
	 */
	LinkedListStack<Integer> stack;
	@Before
	public void setUp() throws Exception
	{
		 stack = new LinkedListStack<Integer>();
	}

	@Test
	public void testLinkedListStack()
	{
		assertEquals(0, stack.size());
	}

	@Test
	public void testClearEmpty()
	{
		//shouldn't throw an exception
		stack.clear();
		assertEquals(0, stack.size());
	}
	
	@Test
	public void testClearNonEmpty()
	{
		stack.push(5);
		stack.push(6);
		stack.clear();
		
		assertEquals(0, stack.size());
	}

	@Test
	public void testIsEmpty()
	{
		assertTrue(stack.isEmpty());
		
		stack.push(5);
		assertFalse(stack.isEmpty());
	}

	@Test (expected = NoSuchElementException.class)
	public void testPeekOnEmpty()
	{
		stack.peek();
	}
	
	@Test
	public void testPeekonNotEmpty()
	{
		stack.push(5);
		assertEquals(5, (int) stack.peek());
		// make sure size does not change
		assertEquals(1, stack.size());
		
		stack.push(3);
		stack.push(4);
		assertEquals(4, (int) stack.peek());
		assertEquals(3, stack.size());
	}

	@Test (expected = NoSuchElementException.class)
	public void testPopOnEmpty()
	{
		stack.pop();
	}
	
	@Test
	public void testPopOnNonEmpty()
	{
		stack.push(5);
		stack.push(4);
		stack.push(3);
		
		assertEquals(3, (int) stack.pop());
		assertEquals(2, stack.size());
		
		assertEquals(4, (int) stack.pop());
		assertEquals(1, stack.size());
		assertEquals(5, (int) stack.peek());
	}

	@Test
	public void testPush()
	{
		stack.push(5);
		assertEquals(1, stack.size());
		assertEquals(5, (int) stack.peek());
		
		stack.push(4);
		stack.push(3);
		assertEquals(3, stack.size());
		assertEquals(3, (int) stack.peek());
	}

	@Test
	public void testSize()
	{
		assertEquals(0, stack.size());
		
		stack.push(5);
		assertEquals(1, stack.size());
		
		stack.push(4);
		assertEquals(2, stack.size());
		
		stack.pop();
		assertEquals(1, stack.size());
	}

}
