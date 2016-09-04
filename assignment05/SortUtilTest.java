package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

/**
 * Class used to test methods in the SortUtil class
 * 
 * @author Hannah Potter and Minh Pham
 */
public class SortUtilTest
{
	static ArrayList<Integer> descendingOrder;
	static ArrayList<Integer> ascendingOrder;
	static ArrayList<Integer> randomOrder;
	static ArrayList<Integer> duplicates;
	static ArrayList<Integer> empty;
	
	static ArrayList<Integer> orderedDescendingAndAscending = SortUtil.generateBestCase(10);
	static ArrayList<Integer> orderedForRandom;
	static ArrayList<Integer> orderedDuplicates;
	static Comparator<Integer> comparator;
	
	/**
	 * Run before each test to create the ArrayLists that will be used to test
	 * the methods in SortUtil
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUpBefore() throws Exception
	{
		descendingOrder = SortUtil.generateWorstCase(10);
		ascendingOrder = SortUtil.generateBestCase(10);
		randomOrder = new ArrayList<>();
		randomOrder.add(3);
		randomOrder.add(7);
		randomOrder.add(20);
		randomOrder.add(10);
		randomOrder.add(4);
		randomOrder.add(-7);
		randomOrder.add(13);
		randomOrder.add(0);
		randomOrder.add(1);
		randomOrder.add(5);
		
		orderedForRandom = new ArrayList<>();
		orderedForRandom.add(-7);
		orderedForRandom.add(0);
		orderedForRandom.add(1);
		orderedForRandom.add(3);
		orderedForRandom.add(4);
		orderedForRandom.add(5);
		orderedForRandom.add(7);
		orderedForRandom.add(10);
		orderedForRandom.add(13);
		orderedForRandom.add(20);
		
		duplicates = new ArrayList<>();
		duplicates.add(-7);
		duplicates.add(5);
		duplicates.add(0);
		duplicates.add(1);	
		duplicates.add(4);
		duplicates.add(5);
		duplicates.add(-7);
		duplicates.add(5);
		duplicates.add(3);
		duplicates.add(10);
		
		orderedDuplicates = new ArrayList<>();
		orderedDuplicates.add(-7);
		orderedDuplicates.add(-7);
		orderedDuplicates.add(0);
		orderedDuplicates.add(1);
		orderedDuplicates.add(3);
		orderedDuplicates.add(4);
		orderedDuplicates.add(5);
		orderedDuplicates.add(5);
		orderedDuplicates.add(5);
		orderedDuplicates.add(10);

		empty = new ArrayList<>();
		comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2)
			{
				return o1.compareTo(o2);
			}
		};
		
	}
	
	/**
	 * Testing the quicksort method for a list in descending order
	 */
	@Test
	public void testQuicksortDescendingOrder()
	{
		SortUtil.quicksort(descendingOrder, comparator);
		assertArrayEquals(orderedDescendingAndAscending.toArray(), descendingOrder.toArray());
	}
	
	/**
	 * Testing the quicksort method for a list in ascending order
	 */
	@Test
	public void testQuicksortAscendingOrder()
	{
		SortUtil.quicksort(ascendingOrder, comparator);
		assertArrayEquals(orderedDescendingAndAscending.toArray(), ascendingOrder.toArray());
	}
	
	/**
	 * Testing the quicksort method for a list in random order
	 */
	@Test
	public void testQuicksortRandomOrder()
	{
		SortUtil.quicksort(randomOrder, comparator);
		assertArrayEquals(orderedForRandom.toArray(), randomOrder.toArray());
	}
	
	/**
	 * Testing the quicksort method for a list that contains duplicates
	 */
	@Test
	public void testQuicksortContainsDuplicates()
	{
		SortUtil.quicksort(duplicates, comparator);
		assertArrayEquals(orderedDuplicates.toArray(), duplicates.toArray());
	}
	
	/**
	 * Testing the quicksort method for an empty list
	 */
	@Test
	public void testQuicksortEmptyList()
	{
		SortUtil.quicksort(new ArrayList<Integer>(), comparator);
	}
	
	/**
	 * Testing the mergesort method on a list that is in descending order
	 */
	@Test
	public void testMergesortDescendingOrder()
	{
		SortUtil.mergesort(descendingOrder, comparator);
		assertArrayEquals(orderedDescendingAndAscending.toArray(), descendingOrder.toArray());
	}
	
	/**
	 * Testing the mergesort method on a list that is in ascending order
	 */
	@Test
	public void testMergesortAscendingOrder()
	{
		SortUtil.mergesort(ascendingOrder, comparator);
		assertArrayEquals(orderedDescendingAndAscending.toArray(), ascendingOrder.toArray());
	}
	
	/**
	 * Testing the mergesort method on a list that is in random order
	 */
	@Test
	public void testMergesortRandomOrder()
	{
		SortUtil.mergesort(randomOrder, comparator);
		assertArrayEquals(orderedForRandom.toArray(), randomOrder.toArray());
	}
	
	/**
	 * Testing the mergesort method on a list that contains duplicates
	 */
	@Test
	public void testMergesortContainsDuplicates()
	{
		SortUtil.mergesort(duplicates, comparator);
		assertArrayEquals(orderedDuplicates.toArray(), duplicates.toArray());
	}
	
	/**
	 * Testing the mergesort method on an empty list
	 */
	@Test
	public void testMergesortEmptyList()
	{
		SortUtil.mergesort(new ArrayList<Integer>(), comparator);
	}

}
