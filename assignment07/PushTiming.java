package assignment07;

/**
 * Class used to test the run-time of the push method of the LinkedListStack class.
 * 
 * @author Hannah Potter and Minh Pham
 */
public class PushTiming
{
	/**
	 * Method that test the run-time of the push method of the LinkedListStack class.
	 * @param args
	 */
	public static void main (String[] args)
    {
		// n is the number of elements in the LinkedListStack
        int n = 1024; // 2^10
        long timesToLoop = 2_500_000;

        LinkedListStack<Integer> stack = new LinkedListStack<Integer>();
        while (n <= (int)Math.pow(2, 28))
        {
            for (int i = 1; i <= n; i++)
            {
                // fills stack with numbers 1 through n inclusive
                stack.push(i);
            }

            long startTime, stopTime;

            // First, spin computing stuff until one second has gone by.
            // This allows this thread to stabilize.

            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            { // empty block
            }

            // Now, run the test.
            long sumOfTime = 0;

            for (long i = 0; i < timesToLoop; i++)
            {
            	startTime = System.nanoTime();
                stack.push((int)i);
                stopTime = System.nanoTime();
                sumOfTime += (stopTime - startTime);
                // Keep the size of the stack the same for each test
                stack.pop();
            }

            // Compute the average running time
            double averageTime = (sumOfTime) / (timesToLoop);

            System.out.println(n + "\t" + averageTime);
    
            // Double the size of the LinkedListStack we are testing
            n *= 2;
        }
        
        System.out.println("Timing Complete");
    }
}
