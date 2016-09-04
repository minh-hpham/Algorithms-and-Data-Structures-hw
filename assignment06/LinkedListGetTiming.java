package assignment06;

/**
 * Class for timing the get method in the DoublyLinkedList class
 * 
 * @author Hannah Potter and Minh Pham
 */
public class LinkedListGetTiming
{
	/**
	 * Method for timing the get method in the DoublyLinkedList class
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
                    linkedList.get(j);
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

            // Double the size of the DoublyLinkedList that we are testing
            n *= 2;
        }
    }
}
