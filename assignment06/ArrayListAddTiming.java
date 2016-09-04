package assignment06;

import java.util.ArrayList;

/**
 * Class to time the add method at index 0 for Java's ArrayList class
 * 
 * @author Hannah Potter and Minh Pham
 */
public class ArrayListAddTiming {
	/**
	 * Test to time the add method at index 0 for Java's ArrayList class
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

            long startTime, stopTime;
            
            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            { // empty block
            }

            timesToLoop = 25000000;
            // Now, run the test.

            double sumOfTime = 0;

            for (long i = 0; i < timesToLoop; i++)
            {
                    startTime = System.nanoTime();
                    // Add the element at the first index
                    arrayList.add(0, (int) i);
                    stopTime = System.nanoTime();
                    sumOfTime += (stopTime - startTime);
                    // Remove the item just added to keep the same ArrayList
                    // size that you are adding to
                    arrayList.remove(0);
            }

            // Compute the average time for run-time of the add method
            double averageTime = sumOfTime / (timesToLoop * n);
            System.out.println(averageTime + "\t" + n);

            // Double the size of the ArrayList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
