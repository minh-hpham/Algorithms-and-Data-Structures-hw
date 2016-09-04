package assignment06;

/**
 * Class to time the addFirst method in DoublyLinkedList
 * 
 * @author Hannah Potter and Minh Pham
 */
public class LinkedListAddFirstTiming {
	/**
	 * Test to time the addFirst method in DoublyLinkedList
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
                    linkedList.addFirst((int) i);
                    stopTime = System.nanoTime();
                    sumOfTime += (stopTime - startTime);
                    // Remove the item just added to keep the same DoublyLinkedList
                    // size that you are adding to
                    linkedList.remove(0);
            }

            // Compute the average time for run-time of the addFirst method
            double averageTime = sumOfTime / (timesToLoop * n);
            System.out.println(averageTime + "\t" + n);

            // Double the size of the DoublyLinkedList that we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }

}
