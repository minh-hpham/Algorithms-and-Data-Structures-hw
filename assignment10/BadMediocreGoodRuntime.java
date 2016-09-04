package assignment10;

import java.util.ArrayList;
import java.util.Random;
import assignment04.AnagramTester;
import assignment04.AnagramUtil;

public class BadMediocreGoodRuntime
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

        QuadProbeHashTable badHashTable;
        QuadProbeHashTable mediocreHashTable;
        QuadProbeHashTable goodHashTable;

        // **********************Timing********************************************************/
        System.out.println("Optimizing multiplier by counting collisions");
        System.out.println("N\tbad\tmed\tgood");
        while (n <= 1000)
        {
            goodHashTable = new QuadProbeHashTable(INIT_CAPACITY, new GoodHashFunctor());
            mediocreHashTable = new QuadProbeHashTable(INIT_CAPACITY, new MediocreHashFunctor());
            badHashTable = new QuadProbeHashTable(INIT_CAPACITY, new BadHashFunctor());

            // GoodHashFunctor.setMultiplier(n);
            
            wordLength = n;
            strings = generateStrings(ARRAY_LENGTH);

            goodHashTable.addAll(strings);
            mediocreHashTable.addAll(strings);
            badHashTable.addAll(strings);

            System.out.println(n + "\t" + badHashTable.collisions() + "\t" + mediocreHashTable.collisions() + "\t"
                         + goodHashTable.collisions());
            n += 10;

        }
        // **********************************************************************************/
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
