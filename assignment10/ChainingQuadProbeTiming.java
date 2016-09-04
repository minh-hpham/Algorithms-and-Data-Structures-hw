package assignment10;

import java.util.ArrayList;
import java.util.Random;

public class ChainingQuadProbeTiming
{
	 final static int INIT_CAPACITY = 100;
	    // final static int WORD_LENGTH = 20;
	    final static int ARRAY_LENGTH = 1000;
	    static Random rand = new Random();
	    public static int wordLength = 1;

	    public static void main (String[] args)
	    {
	        // n is the multiplier for the Good Hash Functor
	        int n = 10;

	        ArrayList<String> strings;

	      	ChainingHashTable chainingHashTable;
	        QuadProbeHashTable quadProbHT;
	        
	        long startTime, stopTime, chainingTime, quadProbTime;

	        // **********************Timing********************************************************/
	        System.out.println("Number Of Collisions In QuadProb HT and Chaning HT using GoodHF");
	       // System.out.println("Running Time required by QuadProb HT and Chaning HT using Good HF");
	        System.out.println("N\tQuadratic Probling\tChaninng");
	        while (n <= (int) Math.pow(2, 18))
	        {
	            // badHashTable = new QuadProbeHashTable(INIT_CAPACITY, new BadHashFunctor());
	            // mediocreHashTable = new QuadProbeHashTable(INIT_CAPACITY, new MediocreHashFunctor());
	            // goodHashTable = new QuadProbeHashTable(INIT_CAPACITY, new GoodHashFunctor());

	            chainingHashTable = new ChainingHashTable(INIT_CAPACITY, new GoodHashFunctor());
	            quadProbHT = new QuadProbeHashTable(INIT_CAPACITY, new GoodHashFunctor());
	            // create a table of size n.
	            strings = generateStrings(n);

	            // spin the clock
	            startTime = System.nanoTime();
	            while (System.nanoTime() - startTime < 1000000000)
	            {
	                // empty block
	            }

	            startTime = System.nanoTime();
	            chainingHashTable.addAll(strings);
	            stopTime = System.nanoTime();
	            chainingTime = (stopTime - startTime) / strings.size();

	            startTime = System.nanoTime();
	            quadProbHT.addAll(strings);
	            stopTime = System.nanoTime();
	            quadProbTime = (stopTime - startTime) / strings.size();

	           

	            // print the values for each collision or the time it took to add n elements
	            System.out.println(n + "\t" + quadProbHT.collisions() + "\t" + chainingHashTable.collisions() );
	            // System.out.println(n + + "\t" + quadProbTime + "\t" + chainingTime);
	            n *= 2;
	        }
	        // ************************************************************************************************************************//
	        System.out.println("Complete!");
	    }

	    /**
	     * Method to find the next largest prime number. If n is prime, it returns the next largest prime above n.
	     */
	    private static int nextPrime (int n)
	    {
	        // if n is prime, return the next largest prime number
	        n++;
	        if (n % 2 == 0)
	        {
	            n++;
	        }
	        for (; !isPrime(n); n += 2)
	            ;
	        return n;
	    }

	    /**
	     * Method to check if a number is prime
	     */
	    private static boolean isPrime (int n)
	    {
	        if (n <= 1)
	        {
	            return false;
	        }

	        for (int i = 2; i <= Math.sqrt(n); i++)
	        {
	            if (n % i == 0)
	            {
	                return false;
	            }
	        }
	        return true;
	    }

	    /**
	     * Create a random string [a-z] of specified length
	     */
	    public static String randomString (int wordLength)
	    {
	        String retval = "";
	        for (int i = 0; i < wordLength; i++)
	        {
	            // ASCII values a-z,A-Z are contiguous (52 characters)
	            retval += (char) ('a' + (rand.nextInt(26)));
	        }
	        return retval;
	    }

	    /**
	     * Creates an array list of random strings of WORD_LENGTH.
	     */
	    public static ArrayList<String> generateStrings (int arrayLength)
	    {
	        ArrayList<String> stringArr = new ArrayList<>();
	        for (int i = 0; i < arrayLength; i++)
	        {
	            // stringArr.add(randomString(rand.nextInt(30)));
	            stringArr.add(randomString(wordLength));
	        }
	        return stringArr;
	    }
}
