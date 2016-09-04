package assignment08;

import java.util.ArrayList;
import java.util.Collections;

/**
 *  Class that tests the run-time of the add method of the BinarySearchTree class.
 *  Elements are added to the BinarySearchTree in random order initially (to the tree that
 *  elements will be added to).
 *  
 * @author Hannah Potter and Minh Pham
 */
public class AddToBSTInRandomOrderTiming {
	/**
	 * Method that tests the run-time of the add method of the BinarySearchTree class.
	 * Elements are added to the BinarySearchTree in random order (to the tree that elements
	 * will be added to).
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		// n is the number of elements in the BinarySearchTree
		int n = 1000;
		long timesToLoop = 1_000;
		ArrayList<Integer> list = new ArrayList<Integer>();
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

		while (n <= 10_000) {
			for (int i = 2; i <= 2*n; i+=2) {
				// fills an ArrayList with even numbers 2 through 2n inclusive
				list.add(i);
			}

			Collections.shuffle(list);

			for (int i = 0; i < n; i++) {
				// fills BinarySearchTree with even numbers 2 through 2n inclusive,
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
				// add all odd numbers 1 (inclusive) through 2n 
				for (int j = 1; j < 2*n; j+=2)
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
			// Double the size of the BinarySearchTree we are testing
			n += 100;
		}

		System.out.println("Timing Complete");
	}
}
