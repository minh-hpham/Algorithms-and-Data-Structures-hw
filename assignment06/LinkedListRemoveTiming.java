package assignment06;

/**
 * Class to time the remove method in DoublyLinkedList
 * 
 * @author Hannah Potter and Minh Pham
 */
public class LinkedListRemoveTiming {
	/**
	 * Test to time the remove method in DoublyLinkedList
	 * 
	 * @param args
	 */
    public static void main (String[] args)
    {
        // n is the number of elements trying to check
        int n = (int) Math.pow(2, 10); // 2^10
        long timesToLoop;
		
		DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<Integer>();
        
        while (n <= (int) Math.pow(2, 20))
        {
    		// Create a DoublyLinkedList of n elements
        	for (int i = 0; i < n; i++)
        	{
        		linkedList.addFirst(i);
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
                    // Put an item back in the DoublyLinkedList to keep its size the same
                    linkedList.addFirst(j);
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

            // Double the size of the DoublyLinkedList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
