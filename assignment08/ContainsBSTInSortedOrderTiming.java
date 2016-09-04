package assignment08;

/**
 *  Class that tests the run-time of the contains method of the BinarySearchTree class.
 *  Elements are added to the BinarySearchTree in ascending order.
 *  
 * @author Hannah Potter and Minh Pham
 */
public class ContainsBSTInSortedOrderTiming {
	/**
	 * Method that tests the run-time of the contains method of the BinarySearchTree class.
	 * Elements are added to the BinarySearchTree in ascending order.
	 * 
	 * @param args
	 */
	public static void main (String[] args)
    {
		// n is the number of elements in the BinarySearchTree
        int n = 1000; 
        long timesToLoop = 100;

        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        while (n <= 10_000)
        {
            for (int i = 1; i <= n; i++)
            {
                // fills BinarySearchTree with numbers 1 through n inclusive
                tree.add(i);
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
            	for (int j = 1; j <= n; j++)
                {
            		tree.contains(j);   
                }
            }
            
            midpointTime = System.nanoTime();

            // Run an empty loop to capture the cost of running the loop.
            for (long i = 0; i < timesToLoop; i++)
            { // empty block
            }
            
            stopTime = System.nanoTime();

            // Compute the average running time

            double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime)) / (timesToLoop * n);

            System.out.println(n + "\t" + averageTime);
            tree.clear();
            // Increase by 100 the size of BinarySearchTree we are testing
            n += 100;
        }
        
        System.out.println("Timing Complete");
    }
}
