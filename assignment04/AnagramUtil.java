package assignment04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class determines if two words are anagrams and finds the largest group
 * of anagrams in a list of words. Two words are anagrams if they contain the
 * same letters with the same frequency.
 * 
 * @author Minh Pham and To Tang
 */
public class AnagramUtil
{
	/**
	 * This method returns the sorted version of the input string. The sorting
	 * must be accomplished using an insertion sort.
	 * 
	 * @return String
	 */
	public static String sort(String input)
	{
		if (input == null || input.isEmpty())
			return "";
		Character[] chars = new Character[input.length()];
		for (int i = 0; i < input.length(); i++)
		{
			char ch = input.charAt(i);
			chars[i] = ch;
		}

		Comparator<Character> comparator = new Comparator<Character>()
		{
			@Override
			public int compare(Character ch1, Character ch2)
			{
				Character lhs = Character.toLowerCase(ch1);
				Character rhs = Character.toLowerCase(ch2);
				return lhs.compareTo(rhs);
			}
		};

		insertionSort(chars, comparator);
		String result = "";
		for (char ch : chars)
		{
			result += ch;
		}
		return result;
	}

	/**
	 * This generic method sorts the input array using an insertion sort and the
	 * input Comparator object.
	 * 
	 * @throws NullPointerException
	 *             with null input
	 */
	public static <T> void insertionSort(T[] list, Comparator<? super T> comparator)
	{
		for (int i = 1; i < list.length; i++)
		{
			T index = list[i];
			int j = i;
			while (j > 0 && comparator.compare(list[j - 1], index) > 0)
			{
				list[j] = list[j - 1];
				list[j - 1] = index;
				j--;
			}
		}
	}

	/**
	 * This method returns true if the two input strings are anagrams of each
	 * other, otherwise returns false.
	 * 
	 * @return boolean
	 */
	public static boolean areAnagrams(String string1, String string2)
	{
		if (string1 == null || string2 == null || string1.equals("") || string2.equals(""))
			return false;
		return (sort(string1)).compareToIgnoreCase(sort(string2)) == 0;
	}

	/**
	 * This method returns the largest group of anagrams in the input array of
	 * words, in no particular order. It returns an empty array if there are no
	 * anagrams in the input array.
	 * 
	 * @return String[]
	 */
	public static String[] getLargestAnagramGroup(String[] input)
	{
		if (input == null || input[0] == null)
			return new String[input.length];
		insertionSort(input, new OrderByAnagram());
		ArrayList<String> maxSoFar = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();
		String currentKey = null;
		for (String item : input)
		{
			String key = sort(item).toLowerCase();
			if (key.equals(currentKey) == false)
			{
				currentKey = key;
				current = new ArrayList<String>();
			}
			current.add(item);
			if (current.size() > maxSoFar.size())
			{
				maxSoFar = current;
			}
		}
		if (maxSoFar.size() <= 1)
			return new String[1];
		String[] result = new String[maxSoFar.size()];
		result = maxSoFar.toArray(result);
		return result;
	}

	public static String[] getLargestAnagramGroupWithJavaArraySort(String[] input)
	{
		Arrays.sort(input, new OrderByAnagram());
		ArrayList<String> maxSoFar = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();
		String currentKey = null;
		for (String item : input)
		{
			String key = sort(item).toLowerCase();
			if (key.equals(currentKey) == false)
			{
				currentKey = key;
				current = new ArrayList<String>();
			}
			current.add(item);
			if (current.size() > maxSoFar.size())
			{
				maxSoFar = current;
			}
		}
		String[] result = new String[maxSoFar.size()];
		result = maxSoFar.toArray(result);
		return result;
	}

	/**
	 * Behaves the same as the previous method, but reads the list of words from
	 * the input file. It is assumed that the file contains one word per line.
	 * If the file does not exist or is empty, the method returns an empty array
	 * because there are no anagrams.
	 * 
	 * @param filename
	 *            of a text file
	 * @return String[]
	 */
	public static String[] getLargestAnagramGroup(String filename)
	{
		ArrayList<String> input = new ArrayList<String>();
		String[] inputArr = new String[1];

		try (Scanner fileIn = new Scanner(new File(filename)))
		{
			while (fileIn.hasNextLine())
			{
				String line = fileIn.nextLine();
				try (Scanner lineIn = new Scanner(line))
				{
					if (lineIn.hasNext())
					{
						String word = lineIn.next();
						input.add(word.trim());
					}
				}
			}
			inputArr = new String[input.size()];
			inputArr = input.toArray(inputArr);
		} catch (FileNotFoundException e)
		{
			return inputArr;
		}
		return getLargestAnagramGroup(inputArr);
	}

	/**
	 * Comparator class to order words by anagram
	 */
	protected static class OrderByAnagram implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2)
		{
			return sort(o1).compareToIgnoreCase(sort(o2));
		}

	}
}
