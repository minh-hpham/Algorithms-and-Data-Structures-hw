package assignment05;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class to time the mergesort method in SortUtil-- changing what order the unsorted array is in
 * 
 * @author Hannah Potter and Minh Pham
 */
public class TimingMergesortComparison {
	/**
	 * Test to time the mergesort method on an ArrayList of Integers. Test run changing which case of
	 * order the unsorted ArrayList is in (ascending order, descending order, random order).
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
        
        while (n <= (int) Math.pow(2, 20))
        {
    		
    		// Set the desired threshold
    		SortUtil.setThreshold((int)(0));
            //Creates an array in the order you would like to test with
        	integerArray = SortUtil.generateWorstCase(n);
            tempArray = new ArrayList<Integer>();
            tempArray.addAll(integerArray);
        	
            long startTime, stopTime;
            
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
              timesToLoop = 500;
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
//            System.out.println(n + "\t" + averageTime);
            System.out.println(averageTime);

            // Double the size of the ArrayList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
