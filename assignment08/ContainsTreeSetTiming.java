package assignment08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 *  Class that tests the run-time of the contains method of Java's TreeSet class.
 *  Elements are added to the TreeSet in random order.
 *  
 * @author Hannah Potter and Minh Pham
 */
public class ContainsTreeSetTiming {
	/**
	 * Method that tests the run-time of the contains method of the TreeSet class.
	 * Elements are added to the TreeSet in random order.
	 * 
	 * @param args
	 */
	public static void main (String[] args)
    {
		// n is the number of elements in the TreeSet
        int n = 1000; 
        long timesToLoop = 50_000;
        ArrayList<Integer> list = new ArrayList<Integer>();
        TreeSet<Integer> tree = new TreeSet<Integer>();
        
        while (n <= 10_000)
        {
        	for (int i = 1; i <= n; i++)
            {
                // fills an ArrayList with numbers 1 through n inclusive, but in a random order
                list.add(i);
            }
        	
        	Collections.shuffle(list);
            
            for (int i = 0; i < n; i++)
            {
                // fills TreeSet with numbers 1 through n inclusive, but in a random order
                tree.add(list.get(i));
            }

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            { // empty block
            }
            
            startTime = System.nanoTime();

            // Now, run the test.
            for (long i = 0; i < timesToLoop; i++)
            {
            	for (int j = 0; j < n; j++)
                {
            		tree.contains(j);   
                }
            }
            
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++)
            { // empty block
            }
            
            stopTime = System.nanoTime();

            // Compute the average running time

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (timesToLoop * n);

            System.out.println(n + "\t" + averageTime);
            list.clear();
            tree.clear();
            // Increase by 100 the size of TreeSet we are testing
            n += 100;
        }
        
        System.out.println("Timing Complete");
    }
}
