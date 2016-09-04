package assignment04.Pham;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

/**
 * construct a program that determines if two words are anagrams and finds the
 * largest group of anagrams in a list of words. Two words are anagrams if they
 * contain the same letters in the same frequency. For example, alert and later
 * are anagrams. To check if two words are anagrams, simply sort the characters
 * in each word. If the sorted versions are the same, the words are anagrams of
 * each other. To find the largest group of anagrams in a list of words, sort
 * the list with a Comparator that compares the sorted character representations
 * of the words. After the sort, any group of words that are anagrams of each
 * other will be adjacent in the list.
 * 
 * @author To Tang and Minh Pham
 */

public class AnagramUtil
{
	public static void main(String[] args)
	{
		String [] input = new String[] {
				"carets","Caters","caster","crates","Reacts","recast","traces"
		};
		System.out.println(Arrays.toString(getLargestAnagramGroup(input)));
	}
	/**
	 * This method returns the sorted version of the input string. The sorting
	 * must be accomplished using an insertion sort.
	 * 
	 * @param sring
	 * @return
	 */

	public static String sort(String str)

	{
		String sortedVersion = "";
	
			String[] splitArray = str.split("");
			Comparator<String> comparator = new Comparator<String>()
			{

				@Override
				public int compare(String a, String b)
				{
					return a.toLowerCase().compareTo(b.toLowerCase());
				}
			};
			
			insertionSort(splitArray, comparator);
			for (String x: splitArray)
			{
				sortedVersion+=x;
			}

		return sortedVersion;

	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 */
	public static <T> void insertionSort(T[] arr, Comparator<? super T> comparator)
	{

		T index = null;
		for (int i = 1; i < arr.length; i++)
		{
			index = arr[i];
			int j = i;
			while (j > 0 && comparator.compare(arr[j - 1], index) > 0)
			{
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = index;
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each
	 * other, otherwise returns false. This method returns true if the two input
	 * strings are anagrams of each other, otherwise returns false.
	 * 
	 * @return
	 */
	public static boolean areAnagrams(String str1, String str2)
	{
		if (sort(str1).equals(sort(str2)))
			return true;
		return false;

	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 * 
	 * @return
	 */
	public static String[] getLargestAnagramGroup(String[] array)
	{
		Map<String, List<String>> groupMap = new HashMap<String, List<String>>();
		
		int maxSize = 0;
		String maxKey = null;
		for (String x : array)
		{
			String key = sort(x).toLowerCase();
			List<String> group = groupMap.get(key );
			if (group == null )
			{
				group = new ArrayList<>();
				groupMap.put(key, group);
			}
			group.add(x);
			if (group.size() > maxSize)
			{
				maxSize = group.size();
				maxKey = key;
			}
		}
		List<String> maxGroup = groupMap.get(maxKey);
		String[] result = new String[maxGroup.size()];
		result = maxGroup.toArray(result);
		return result;

	}

	/**
	 * Behaves the same as the previous method, but reads the list of words from
	 * the input filename. It is assumed that the file contains one word per
	 * line. If the file does not exist or is empty, the method returns an empty
	 * array because there are no anagrams.
	 * 
	 * @param filename
	 * @return
	 */
	public static String[] getLargestAnagramGroup(String filename)
	{
		return null;

	}
}
