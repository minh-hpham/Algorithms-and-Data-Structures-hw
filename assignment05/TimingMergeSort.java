package assignment05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * Class to time the mergesort method in SortUtil-- changing threshold
 * 
 * @author Hannah Potter and Minh Pham
 */
public class TimingMergeSort {
	/**
	 * Test to time the mergesort method on an ArrayList of Integers. Test run changing the 
	 * threshold member variable in SortUtil each time the test is run.
	 * 
	 * @param args
	 */
    public static void main (String[] args)
    {
        // n is the number of elements trying to check
        int n = (int) Math.pow(2, 10); // 2^10
        long timesToLoop;
        // Comparator for the ArrayList of Integer
        Comparator<Integer> comparator = new Comparator<Integer>()
		{
			@Override
			public int compare(Integer o1, Integer o2)
			{
				return o1.compareTo(o2);
			}
		};
		
		// ArrayList of elements to be sorted
		 ArrayList<Integer> integerArray = new ArrayList<Integer>(); 
		// Temporary ArrayList that will be sorted -- every array will start out the same
		ArrayList<Integer> tempArray = new ArrayList<Integer>();
		
		// Seeded random number generator
		long seed = 123456;
		Random random = new Random(seed);
        
        while (n <= (int) Math.pow(2, 20))
        {
    		// Set the threshold you want to be testing
    		SortUtil.setThreshold((int)(n/1000));
    		
            // Creates a randomly ordered ArrayList of elements 1 through n -- uses seed to 
        	// ensure that you get the same permuted list of random numbers for each time
        	// time you run this test
        	for (int i = 0; i < n; i++)
        	{
        		integerArray.add(random.nextInt(n));
        	}
            tempArray = new ArrayList<Integer>();
            tempArray.addAll(integerArray);

            long startTime, stopTime;
            
            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            { // empty block
            }

            timesToLoop = 500;
            // Now, run the test.
           if (n > (int) Math.pow(2, 14))
           {
              timesToLoop = 200;
           }

            long sumOfTime = 0;

            for (long i = 0; i < timesToLoop; i++)
            {
                    startTime = System.nanoTime();
                    SortUtil.mergesort(tempArray, comparator);
                    stopTime = System.nanoTime();
                    sumOfTime += (stopTime - startTime);
                    // Unorder the ArrayList
                    tempArray.clear();
                    tempArray.addAll(integerArray);
            }

            // Compute the average time for run-time of the mergesort method
            double averageTime = sumOfTime / (timesToLoop * n);
            System.out.println(averageTime + "\t" + n);

            // Double the size of the ArrayList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
