package assignment07;

/**
 * Class used to test the run-time of the peek method in the LinkedListStack class.
 *  
 * @author Hannah Potter and Minh Pham
 */
public class PeekTiming
{
	/**
	 * Method that tests the run-time of the peek method in the LinkedListStack class
	 * @param args
	 */
	public static void main (String[] args)
    {
		// n is the number of elements in the LinkedListStack
        int n = 1024; // 2^10
        long timesToLoop = 1_000_000_000;

        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        while (n <= (int)Math.pow(2, 20))
        {
            for (int i = 1; i <= n; i++)
            {
                // fills stack with numbers 1 through n inclusive
                stack.push(i);
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
                stack.peek();
            }
            
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++)
            { // empty block
            }
            
            stopTime = System.nanoTime();

            // Compute the average running time

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (timesToLoop);

            System.out.println(n + "\t" + averageTime);

            // Double the size of LinkedListStack we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }
}
