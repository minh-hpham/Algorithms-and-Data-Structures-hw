package assignment06;

import java.util.ArrayList;

/**
 * Class to time the remove method in Java's ArrayList
 * 
 * @author Hannah Potter and Minh Pham
 */
public class ArrayListRemoveTiming {
	/**
	 * Test to time the remove method in Java's ArrayList
	 * 
	 * @param args
	 */
    public static void main (String[] args)
    {
        // n is the number of elements trying to check
        int n = (int) Math.pow(2, 10); // 2^10
        long timesToLoop;
		
		ArrayList<Integer> linkedList = new ArrayList<Integer>();
        
        while (n <= (int) Math.pow(2, 20))
        {
    		// Create a ArrayList of n elements
        	for (int i = 0; i < n; i++)
        	{
        		linkedList.add(i, i);
        	}

            long startTime, stopTime, loopStartTime, loopStopTime;

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

            double sumOfTime = 0;

            for (long i = 0; i < timesToLoop; i++)
            {
                for (int j = 0; j < n; j++)
                {
                	startTime = System.nanoTime();
                    linkedList.remove(j);
                    stopTime = System.nanoTime();
                    sumOfTime += (stopTime - startTime);
                    // Put an item back in the ArrayList to keep its size the same
                    linkedList.add(0, j);
                }
            }

            loopStartTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.

            for (long i = 0; i < timesToLoop; i++)
            { // empty block
            }

            loopStopTime = System.nanoTime();
            
            long loopRunTime = loopStopTime - loopStartTime;

            // Compute the average time
            double averageTime = (sumOfTime - loopRunTime) / (timesToLoop * n);

            System.out.println(averageTime + "\t" + n);

            // Double the size of the ArrayList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
