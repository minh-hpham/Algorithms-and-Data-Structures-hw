package assignment04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import assignment03.BinarySearchSet;

public class AnagramTester
{

	private static Random rand;
	private static final int ITER_COUNT = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// Set up the random number generator for the randomString function
		rand = new Random();
		rand.setSeed(System.currentTimeMillis());

		// Reads a text file with a single word per line, returns them as an
		// array of Strings
		String[] words = readFile("sample_word_list.txt");
		//timingAreAnagrams();
		timingGetLargestAnagramsGroup();

	}

	// Create a random string [a-z] of specified length
	public static String randomString(int length)
	{
		String retval = "";
		for (int i = 0; i < length; i++)
		{
			// ASCII values a-z,A-Z are contiguous (52 characters)
			retval += (char) ('a' + (rand.nextInt(26)));
		}
		return retval;
	}

	// Reads words from a file (assumed to contain one word per line)
	// Returns the words as an array of strings.
	public static String[] readFile(String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while (input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		String[] retval = new String[1];
		return results.toArray(retval);
	}

	public static void timingAreAnagrams()
	{
		// prime the machine to time more accurate
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000)
			;

		for (int exp = 1; exp <= 20; exp++)
		{
			int size = (int) Math.pow(2, exp);
			// SET UP!
			String a = randomString(size);
			String b = randomString(size);
			long totalTime = 0;
			for (int iter = 0; iter < ITER_COUNT; iter++)
			{
				// TIME IT!
				long start = System.nanoTime();
				AnagramUtil.areAnagrams(a, b);
				long stop = System.nanoTime();
				totalTime += stop - start;
			}
			double averageTime = totalTime / (double) ITER_COUNT;
			System.out.println(size + "\t" + averageTime); // print to
			System.gc(); // console

		}

	}
	public static void timingGetLargestAnagramsGroup()
	{
		// prime the machine to time more accurate
		String[] input = null;
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 1000000000);
		
		for (int exp = 5; exp <= 15; exp++)
		{
			int size = (int) Math.pow(2, exp);
			//SET UP
			input = new String[size];
			for (int assign = 0; assign < size; assign++)
			{
				int wordLength = 5+ rand.nextInt(10);
				input[assign] = randomString(wordLength);
			}
			
			long totalTime = 0;
			long start = System.nanoTime();
			for (int iter = 0; iter < ITER_COUNT; iter++)
			{
				// TIME IT!
				//long start = System.nanoTime();
				AnagramUtil.getLargestAnagramGroup(input);
				//AnagramUtil.getLargestAnagramGroupWithJavaArraySort(input);
				//long stop = System.nanoTime();
				//totalTime += stop - start;
			}
			long stop = System.nanoTime();
			totalTime += stop - start;
			double averageTime = totalTime / (double) ITER_COUNT;
			System.out.println(size + "\t" + averageTime); // print to console
			System.gc(); 

		}

	}

}
