package assignment04;

import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.BeforeClass;
import org.junit.Test;

public class AnagramUtilTest
{
	static String [] input;
	static String [] sortedInput ;
	static String [] emptyArray;
	static Comparator<String> comparatorForString;
	static String[] largestGroup ;
	static String [] largestGroupInfileName;

	@BeforeClass
	public static void setUp() throws Exception
	{
		 input = new String [] {"bacd","Abcd","CDAB","dcab","cat","tac","act"};
		 sortedInput = new String[] {"Abcd", "act", "bacd", "cat", "CDAB", "dcab", "tac"};
		 emptyArray = new String[5];
		 largestGroup = new String[]{"bacd","Abcd","CDAB","dcab"};
		 largestGroupInfileName = new String[]{ "carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};		 		
		 comparatorForString = new Comparator<String>()
		 {
			@Override
			public int compare(String a, String b)
			{
				return a.compareToIgnoreCase(b);
			}
		};
	}

	@Test
	public void testSortNullOrEmptyInput()
	{
		assertEquals("", AnagramUtil.sort(null));
		assertEquals("", AnagramUtil.sort(""));
	}

	@Test	
	public void testSortNonEmptyString()
	{
		assertEquals("abcd", AnagramUtil.sort("cbad"));
	}
	
	@Test
	public void testInsertionSortForNonEmptyArray()
	{
		
		AnagramUtil.insertionSort(input, comparatorForString);
		assertArrayEquals(sortedInput, input);
	}

	public void testAreAnagrams()
	{
		assertTrue(AnagramUtil.areAnagrams("chahs", "cahhs"));
		assertTrue(AnagramUtil.areAnagrams("chahs", "CAhhs"));
		assertFalse(AnagramUtil.areAnagrams("cds", "cdsadf"));
	}
	@Test
	public void testAreAnagramsWithOneInputisNullOrEmpty()
	{
		assertFalse(AnagramUtil.areAnagrams("fjkdafjd", null));
		assertFalse(AnagramUtil.areAnagrams( "","skfjs"));
	}
	@Test
	public void testGetLargestAnagramGroupStringArray()
	{
//		String[] largestGroup = new String[]{"bacd","Abcd","CDAB","dcab"};
		assertArrayEquals(largestGroup, AnagramUtil.getLargestAnagramGroup(input));
	}
	@Test
	public void testGetLargestAnagramGroupsWithInputNullOrEmptyElement()
	{
		String[] group = new String[] {"bacd","Abcd",null, "CDAB","dcab","cat","tac","act"};
		assertArrayEquals(largestGroup, AnagramUtil.getLargestAnagramGroup(group));
	}
	
	@Test
	public void testGetLargestAnagramGroupsWithInputNullOrEmpty()
	{
		String[] group = new String[5];
		assertArrayEquals(emptyArray, AnagramUtil.getLargestAnagramGroup(group));
	}
	
	@Test
	public void testGetLargestAnagramGroupsWithNoAnagram()
	{
		String[] group = new String[] {"abc","abcd"};
		assertArrayEquals(new String [1], AnagramUtil.getLargestAnagramGroup(group));
	}
	@Test
	public void testGetLargestAnagramGroupStringFileName()
	{
		assertArrayEquals(largestGroupInfileName, AnagramUtil.getLargestAnagramGroup("sample_word_list.txt"));
	}
	@Test
	public void testGetLargestAnagramGroupStringFileNameWithNotExistedFileName()
	{
		assertArrayEquals(new String[1], AnagramUtil.getLargestAnagramGroup("sample.txt"));
	}

}
