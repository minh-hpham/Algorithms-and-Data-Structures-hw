package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Class tests for the QuadProbeHashTable class
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class QuadProbeHashTableTest
{
    QuadProbeHashTable goodHashTableMammals;

    QuadProbeHashTable goodHashTableReptiles;

    QuadProbeHashTable emptyGoodHashTable;

    ArrayList<String> mammals;
    ArrayList<String> reptiles;

    final static int INITIAL_CAPACITY = 2;
    @Before
    public void create ()
    {
        goodHashTableMammals = new QuadProbeHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

        goodHashTableReptiles = new QuadProbeHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

        emptyGoodHashTable = new QuadProbeHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

        // this list only has 9/10 elements added.
        mammals = new ArrayList<>();
        mammals.add("cat");
        mammals.add("dog");
        mammals.add("dophin");
        mammals.add("squirrel");
        mammals.add("bat");
        mammals.add("hog");
        mammals.add("elephant");
        mammals.add("hippopotamus");
        mammals.add("lion");

        // this list is full.
        reptiles = new ArrayList<>();
        reptiles.add("gecko");
        reptiles.add("lizzard");
        reptiles.add("iguana");
        reptiles.add("kimono dragon");
        reptiles.add("gila monster");
        reptiles.add("crocodile");
        reptiles.add("aligator");
        reptiles.add("boa constrictor");
        reptiles.add("tuatara");
        reptiles.add("horned toad");

        goodHashTableMammals.addAll(mammals);

        goodHashTableReptiles.addAll(reptiles);
    }

    /**
     * Tests the add method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if the element has been added.
     */
    @Test
    public void testAdd ()
    {
        assertFalse(goodHashTableMammals.add(null));
        assertFalse(goodHashTableMammals.add("hog"));

        assertTrue(goodHashTableMammals.add("dove")); // actually added
        assertFalse(goodHashTableMammals.add("dove"));
        assertTrue(goodHashTableMammals.add("cow"));
        
        //mammals has more elements than than goodHashTable 
        mammals.add("bear");
        mammals.add("human");
        assertFalse(goodHashTableMammals.containsAll(mammals));
    }

    /**
     * Tests the addAll method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if any elements in the set have been added.
     */
    @Test
    public void testAddAll ()
    {
        assertTrue(emptyGoodHashTable.addAll(reptiles));
        assertFalse(emptyGoodHashTable.addAll(reptiles));
        assertTrue(emptyGoodHashTable.containsAll(reptiles));
        reptiles.add("rattle snake");
        assertFalse(emptyGoodHashTable.addAll(reptiles));
        // it is ok for more than the capacity to be added. 
        assertTrue(emptyGoodHashTable.containsAll(reptiles));
    }

    /**
     * Tests the clear method to ensure that after a hashTable has been cleared, there are no elements remaining.
     */
    @Test
    public void testClear ()
    {
        goodHashTableReptiles.clear();
        assertTrue(goodHashTableReptiles.size() == 0);
        assertFalse(goodHashTableReptiles.containsAll(reptiles));
        assertTrue(goodHashTableReptiles.isEmpty());

        emptyGoodHashTable.clear();
        assertTrue(emptyGoodHashTable.size() == 0);
        assertTrue(emptyGoodHashTable.isEmpty());
    }

    /**
     * Tests the contains method to ensure that if the table contains the given element it returns true. TODO: What
     * should we do if a null element is checked or added? Ignore it?
     */
    @Test
    public void testContains ()
    {
        assertFalse(goodHashTableReptiles.contains("null"));
        // first element
        assertTrue(goodHashTableReptiles.contains("gecko"));
        // middle element
        assertTrue(goodHashTableReptiles.contains("crocodile"));
        // last element
        assertTrue(goodHashTableReptiles.contains("horned toad"));
        // element not in the list
        assertFalse(goodHashTableReptiles.contains("mouse"));

    }

    /**
     * Tests the containsAll method to ensure that if the table contains the all of the given elements in the collection
     * it returns true. If it only contains one of the elements ore no elements it returns false.
     */
    @Test
    public void testContainsAll ()
    {
        // table contains all of the collection
        emptyGoodHashTable.addAll(reptiles);
        assertTrue(goodHashTableReptiles.containsAll(reptiles));
        assertTrue(goodHashTableMammals.containsAll(mammals));
        assertTrue(emptyGoodHashTable.containsAll(reptiles));

        emptyGoodHashTable.clear();// should be treated as empty

        // table contains some of the collection
        mammals.add("bear");
        assertFalse(goodHashTableMammals.containsAll(mammals));

        // table contains none of the collection
        assertFalse(emptyGoodHashTable.containsAll(mammals));
        assertFalse(goodHashTableReptiles.containsAll(mammals));
    }

    /**
     * Tests the isEmpty method to ensure that is returns true if the table is empty.
     */
    @Test
    public void testIsEmpty ()
    {
        assertTrue(emptyGoodHashTable.isEmpty());
        emptyGoodHashTable.addAll(mammals);
        assertFalse(emptyGoodHashTable.isEmpty());
        emptyGoodHashTable.clear();
        emptyGoodHashTable.add("bear");
        assertFalse(emptyGoodHashTable.isEmpty());
        assertFalse(goodHashTableMammals.isEmpty());
        goodHashTableMammals.clear();
        assertTrue(goodHashTableMammals.isEmpty());
    }

    /**
     * Tests the size method to ensure that it returns the correct size of the table.
     */
    @Test
    public void testSize ()
    {
        assertTrue(emptyGoodHashTable.size() == 0);
        assertEquals(9, goodHashTableMammals.size());
        assertTrue(goodHashTableReptiles.size() == 10);
    }
}
