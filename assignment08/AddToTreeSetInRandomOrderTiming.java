package assignment08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 *  Class that tests the run-time of the add method of the TreeSet class.
 *  Elements are added to the TreeSet in random order initially (to the tree that
 *  elements will be added to).
 *  
 * @author Hannah Potter and Minh Pham
 */
public class AddToTreeSetInRandomOrderTiming {
	/**
	 * Method that tests the run-time of the add method of the TreeSet class.
	 * Elements are added to the TreeSet in random order (to the tree that elements
	 * will be added to).
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// n is the number of elements in the TreeSet
		int n = 1000;
		long timesToLoop = 10_000;
		ArrayList<Integer> list = new ArrayList<Integer>();
		TreeSet<Integer> tree = new TreeSet<Integer>();

		while (n <= 10_000) {
			for (int i = 2; i <= 2*n; i+=2) {
				// fills an ArrayList with even numbers 2 through 2n inclusive
				list.add(i);
			}

			Collections.shuffle(list);

			for (int i = 0; i < n; i++) {
				// fills TreeSet with even numbers 2 through 2n inclusive, 
				// but in a random order
				tree.add(list.get(i));
			}

			long startTime, stopTime;

			// First, spin computing stuff until one second has gone by.
			// This allows this thread to stabilize.

			startTime = System.nanoTime();
			while (System.nanoTime() - startTime < 1000000000) { // empty block
			}

			// Now, run the test.
			long sumOfTime = 0;

			for (long i = 0; i < timesToLoop; i++) {
				// add all odd numbers i (inclusive) through 2n
				for (int j = 1; j< 2*n; j += 2)
				{
				startTime = System.nanoTime();
				tree.add(j);
				stopTime = System.nanoTime();
				sumOfTime += (stopTime - startTime);
				// Keep the size of the tree the same for each test
				tree.remove(j);
				}
			}

			// Compute the average running time
			double averageTime = (sumOfTime) / (timesToLoop * n);

			System.out.println(n + "\t" + averageTime);
			list.clear();
            tree.clear();
			// Double the size of the TreeSet we are testing
			n += 100;
		}

		System.out.println("Timing Complete");
	}
}
