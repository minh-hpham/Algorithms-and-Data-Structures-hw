package assignment06;

import java.util.ArrayList;

/**
 * Class for timing the get method in Java's ArrayList class
 * 
 * @author Hannah Potter and Minh Pham
 */
public class ArrayListGetTiming
{
	/**
	 * Method for timing the get method in Java's ArrayList class
	 * 
	 * @param args
	 */
    public static void main (String[] args)
    {
        // n is the number of elements trying to check
        int n = (int) Math.pow(2, 10); // 2^10
        long timesToLoop;
        
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        
        while (n <= (int) Math.pow(2, 20))
        {
    		// Create a ArrayList of n elements
        	for (int i = 0; i < n; i++)
        	{
        		arrayList.add(i, i);
        	}

            long startTime, midpointTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            { // empty block
            }

            timesToLoop = 1000;
            // Now, run the test.
            if (n > (int) Math.pow(2, 14))
            {
                timesToLoop = 250;
            }

            startTime = System.nanoTime();

            for (long i = 0; i < timesToLoop; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    arrayList.get(j);
                }
            }

            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++)
            { // empty block
            }

            stopTime = System.nanoTime();

            // Compute the average time
            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (timesToLoop * n);

            System.out.println(averageTime + "\t" + n);

            // Double the size of the ArrayList that we are testing
            n *= 2;
        }
    }
}
