package assignment11;

import java.util.Random;

/**
 * Class timing for PriorityQueue.java add, findMin, and deleteMin methods.
 * 
 * @author Sara Adamson, Minh Pham
 */
public class PriorityQueueTiming
{
    static Random rand = new Random();
    static Integer[] array;

    public static void main (String[] args)
    {
        // n is the number of elements in the DoublyLinkedList
        int n = 1;
        PriorityQueue<Integer> queue;
        long sumOfTime;

        // *******************************************************************************************************************//
        // queue.add timing
        System.out.println("N\tadd");
        while (n <= 1_000_001)
        {
            long startTime, stopTime;
            double averageAddTime;
            queue = new PriorityQueue<>();
            sumOfTime = 0;
            
            generateArray(n);
            for (int i = 0; i < n; i++)
            {
                queue.add(array[i]);
            }
            
            startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 1000000000)
            {
                // spin the clock
            }
            
            startTime = System.nanoTime();

            for (int i = 0; i < n; i++)
            {
                startTime = System.nanoTime();
                queue.add(array[i]);
                stopTime = System.nanoTime();
                sumOfTime += stopTime - startTime;
                queue.deleteMin();
            }

            averageAddTime = sumOfTime / n;

            // *****************************************************************************************************************//
            System.out.println(n + "\t" + averageAddTime);

            n += 100_000;
        }
     // *******************************************************************************************************************//
        // queue.add and queue.delete timing
        System.out.println("N\tdelete");
        n = (int)Math.pow(2, 9);
        while (n <= (int)Math.pow(2, 24))
        {
            long startTime, stopTime;
            double  averageDeleteTime;
            sumOfTime = 0;
            
            queue = new PriorityQueue<>();
            generateArray(n);
            for (int i = 0; i < n; i++)
            {
                queue.add(array[i]);
            }
            
            startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 1000000000)
            {
                // spin the clock
            }
            
            startTime = System.nanoTime();

            for (int i = 0; i < n; i++)
            {
                startTime = System.nanoTime();
                queue.deleteMin();
                stopTime = System.nanoTime();
                sumOfTime += stopTime - startTime;
                queue.add(rand.nextInt());
            }

            averageDeleteTime = sumOfTime / n;

            // *****************************************************************************************************************//
            System.out.println(n + "\t" + averageDeleteTime);

            n *=2;
        }
        // *******************************************************************************************************************//
        // queue.findMin() timing
        n = 1;
        System.out.println("n\tfindMin");
        while (n <= 1_000_001)
        {
            long startTime, stopTime;
            double averageTime;

            queue = new PriorityQueue<>();

            generateArray(n);

            sumOfTime = 0;

            for (int i = 0; i < n; i++)
            {
                queue.add(array[i]);
            }

            startTime = System.nanoTime();

            while (System.nanoTime() - startTime < 1000000000)
            {
                // spin the clock
            }

            for (int i = 0; i < n; i++)
            {
                startTime = System.nanoTime();
                queue.findMin();
                stopTime = System.nanoTime();
                sumOfTime += stopTime - startTime;
                queue.deleteMin();
                queue.add(rand.nextInt());
            }

            averageTime = sumOfTime / n;

            // ********************************************************************************************************************************//
            System.out.println(n + "\t" + averageTime);
            n += 100_000;
        }
        System.out.println("Complete!");
    }

    /**
     * Generates an array of random Integers of the given size.
     */
    private static void generateArray (int size)
    {
        array = new Integer[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = (Integer) rand.nextInt();
        }
    }

}
