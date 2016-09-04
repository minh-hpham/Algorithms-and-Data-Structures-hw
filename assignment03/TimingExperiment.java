package assignment03;

import java.awt.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JOptionPane;

import lab02.ContainsTimingExperiment;

public class TimingExperiment
{
	private static final int ITER_COUNT = 10000;

	public static void main(String[] args)
	{
		// Do you want to test the contains or add method?
		int input = Integer.parseInt(JOptionPane
				.showInputDialog("Do you want to test the contains or add method? 1 for Contains, 2 for Add: "));
		// you spin me round baby, right round
		long startTime = System.nanoTime();
		while (System.nanoTime() - startTime < 100000000)
			System.gc();

		
		try (FileWriter fw = new FileWriter(new File("contains_experiment.tsv")))
		{ // open up a file writer so we can write to file.
			
			
			for(int s=0; s < 5; s++)
			
			for (int exp = 10; exp <= 20; exp++)
			{ // This is used as the exponent to calculate the size of the set.
				int size = (int) Math.pow(2, exp); // or ..
				size = 1 << exp; // the two statements are equivalent, look into
									// bit-shifting if you're interested what is
									// going on here.

				// Do the experiment multiple times, and average out the results
				BinarySearchSet<Integer> set = new BinarySearchSet<Integer>();
				//ArrayList <Integer> set = new ArrayList<Integer>();
				for (int i = 0; i < size; i += 1)
				{
					set.add(i);
				}

				long totalTime = 0;
				
				for (int iter = 0; iter < ITER_COUNT; iter++)
				{
					
					// TIME IT!
					if (input == 1)
					{
						totalTime += ContainsTiming(set, size);
					}
					else
						totalTime += AddTiming(set, size);
					
				}
				double averageTime = totalTime / ((double) ITER_COUNT);
				System.out.println(size + "\t" + averageTime); // print to
				System.gc();																// console
				fw.write(size + "\t" + averageTime + "\n"); // write to file.
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	public static long ContainsTiming(BinarySearchSet<Integer> set, int size)
	{
		long totalTime = 0;
		int findElement = new Random().nextInt(size); // This gets me a random
														// in between 0 and
														// size;

		// TIME IT!
		long start = System.nanoTime();
		set.contains(findElement);
		long stop = System.nanoTime();
		totalTime += stop - start;
		return totalTime;
	}

	public static long AddTiming(BinarySearchSet<Integer> set, int size)
	{
		long totalTime = 0;
		int findElement = new Random().nextInt(size); // This gets me a random
														// in between 0 and
														// size;

		// TIME IT!
		long start = System.nanoTime();
		set.add(findElement);
		long stop = System.nanoTime();
		totalTime += stop - start;
		set.remove(findElement);
		return totalTime;
	}

}
