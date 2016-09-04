/**
 * 
 */
package assignment11;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Class tests for PriorityQueue.java methods
 * 
 * @author Sara Adamson, Minh Pham
 */
public class PriorityQueueTest
{

    PriorityQueue<String> animals;
    String[] animalsOrdered;

    PriorityQueue<String> empty;
    
    @Before
    public void setUp ()
    {
        animals = new PriorityQueue<>();
        animalsOrdered = new String[20];

        empty = new PriorityQueue<>();

        animals.add("dog");// 0
        animals.add("cat");// 1
        animals.add("monkey");// 13
        animals.add("fish");// 9
        animals.add("elephant");// 7
        animals.add("polar bear");// 15
        animals.add("black bear");// .5
        animals.add("gorilla");// 11
        animals.add("duck");// 6
        animals.add("swan");// 18
        animals.add("snake");// 17
        animals.add("deer");// 5
        animals.add("elk");// 8
        animals.add("moose");// 12
        animals.add("caribou");// 2
        animals.add("cow");// 4
        animals.add("sheep");// 16
        animals.add("pig");// 14
        animals.add("goat");// 10
        animals.add("chicken");// 3

        animalsOrdered[0] = "black bear";
        animalsOrdered[1] = "caribou";
        animalsOrdered[2] = "cat";
        animalsOrdered[3] = "chicken";
        animalsOrdered[4] = "cow";
        animalsOrdered[5] = "deer";
        animalsOrdered[6] = "dog";
        animalsOrdered[7] = "duck";
        animalsOrdered[8] = "elephant";
        animalsOrdered[9] = "elk";
        animalsOrdered[10] = "fish";
        animalsOrdered[11] = "goat";
        animalsOrdered[12] = "gorilla";
        animalsOrdered[13] = "monkey";
        animalsOrdered[14] = "moose";
        animalsOrdered[15] = "pig";
        animalsOrdered[16] = "polar bear";
        animalsOrdered[17] = "sheep";
        animalsOrdered[18] = "snake";
        animalsOrdered[19] = "swan";
    }

    /**
     * Method designed to test the add method for a priority queue.
     */
    @Test
    public void testAdd ()
    {
        empty.add("Minh");
        assertEquals(1, empty.size());
        String[] emptyArr = { "Minh" };
        assertArrayEquals(emptyArr, empty.toArray());
        empty.add("Sara");
        assertEquals(2, empty.size());
        String[] emptyArr2 = {"Minh",  "Sara" };
        assertArrayEquals(emptyArr2, empty.toArray());
        animals.add("zebra");
        animals.generateDotFile("animals");
        animals.add("zebra");
        animalsOrdered = Arrays.copyOf(animalsOrdered, 22);
        animalsOrdered[20] = "zebra";
        animalsOrdered[21] = "zebra";
        for(int i = 0; i <= 21; i++)
        {
           assertEquals(animalsOrdered[i], animals.deleteMin());
        }
        
    }

    /**
     * Method designed to test the clear method for a priority queue.
     */
    @Test
    public void testClear ()
    {
        animals.clear();
        assertEquals(0, animals.size());
        empty.clear();
        assertEquals(0, empty.size());
        assertArrayEquals(new String[0], animals.toArray());
        assertArrayEquals(new String[0], empty.toArray());
    }

    /**
     * Method designed to test the deleteMin method for a priority queue.
     */
    @Test
    public void testDeleteMin ()
    {
        assertEquals("black bear", animals.deleteMin());
        assertEquals("caribou", animals.deleteMin());

        empty.add("minh");
        assertEquals("minh", empty.deleteMin());
        assertArrayEquals(new String[0], empty.toArray());

    }

    /**
     * If the priorityQueue is empty, throws a NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void testDeleteMinException ()
    {
        empty.deleteMin();
    }

    /**
     * Method designed to test the findMin method for a priority queue.
     */
    @Test
    public void testFindMin ()
    {
        assertEquals("black bear", animals.findMin());
        animals.deleteMin();
        assertEquals("caribou", animals.findMin());
    }
    
    /**
     * If the priorityQueue is empty, throws a NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void testFindMinException ()
    {
        empty.findMin();
    }
    
    /**
     * Method designed to test the size method for a priority queue.
     */
    @Test
    public void testSize ()
    {
        assertEquals(20, animals.size());
        animals.add("cow");
        assertEquals(21, animals.size());
        assertEquals(0, empty.size());
    }
}
