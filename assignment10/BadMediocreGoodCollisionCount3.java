package assignment10;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class counts the number of collisions resulting from a BadHashFunctor, MediocreHashFunctor, and GoodHashFunctor.
 */
public class BadMediocreGoodCollisionCount3
{
    final static int INIT_CAPACITY = 1000;
    final static int WORD_LENGTH = 1;
    static Random rand = new Random();

    public static void main (String[] args)
    {
         QuadProbeHashTable badHashTable;
         QuadProbeHashTable mediocreHashTable;
         QuadProbeHashTable goodHashTable;

//        ChainingHashTable badHashTable;
//        ChainingHashTable mediocreHashTable;
//        ChainingHashTable goodHashTable;
         BadHashFunctor bad = new BadHashFunctor();
         MediocreHashFunctor med = new MediocreHashFunctor();
         GoodHashFunctor good = new GoodHashFunctor();

        ArrayList<String> strings;

        long startTime, stopTime, badHashTableTime, mediocreHashTableTime, goodHashTableTime;

        // n is the number of elements in the DoublyLinkedList
        int n = (int) Math.pow(2, 9);

        // *******************************************************************************************************************//
        // Adding strings to a QuadProbeHashTable using a BadHashFunctor and getting the number of collisions.
        System.out.println("Timing hashFunctor collisions");
        System.out.println("N\tbad\tmed\tgood");
        while (n <= (int) Math.pow(2, 18))
        {
             badHashTable = new QuadProbeHashTable(n, new BadHashFunctor());
             mediocreHashTable = new QuadProbeHashTable(n, new MediocreHashFunctor());
             goodHashTable = new QuadProbeHashTable(n, new GoodHashFunctor());

//            badHashTable = new ChainingHashTable(n, new BadHashFunctor());
//            mediocreHashTable = new ChainingHashTable(n, new MediocreHashFunctor());
//            goodHashTable = new ChainingHashTable(n, new GoodHashFunctor());

            // create a table of size n.
            strings = generateStrings(n);

            // spin the clock
            startTime = System.nanoTime();
            while (System.nanoTime() - startTime < 1000000000)
            {
                // empty block
            }

            startTime = System.nanoTime();
            for (String str: strings)
            {
            	bad.hash(str);
            }
            stopTime = System.nanoTime();
            badHashTableTime = (stopTime - startTime) / strings.size();

            startTime = System.nanoTime();
            for (String str: strings)
            {
            	med.hash(str);
            }
            stopTime = System.nanoTime();
            mediocreHashTableTime = (stopTime - startTime) / strings.size();

            startTime = System.nanoTime();
            for (String str: strings)
            {
            	good.hash(str);
            }
            stopTime = System.nanoTime();
            goodHashTableTime = (stopTime - startTime) / strings.size();

            // print the values for each collision or the time it took to add n elements
//            System.out.println(n + "\t" + badHashTable.collisions() + "\t" + mediocreHashTable.collisions() + "\t"
//                    + goodHashTable.collisions());
             System.out.println(n + "\t" + badHashTableTime + "\t" + mediocreHashTableTime + "\t" +
             goodHashTableTime);
            n *= 2;
        }
        // ************************************************************************************************************************//
        System.out.println("Complete!");
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
            stringArr.add(randomString(rand.nextInt(30)));
        }
        return stringArr;
    }
}
