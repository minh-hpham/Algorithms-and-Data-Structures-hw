package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

/**
 * Class tests for the ChainingHashTable class
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class ChainingHashTableTest
{
    ChainingHashTable badHashTableMammals;
    ChainingHashTable mediocreHashTableMammals;
    ChainingHashTable goodHashTableMammals;

    ChainingHashTable badHashTableReptiles;
    ChainingHashTable mediocreHashTableReptiles;
    ChainingHashTable goodHashTableReptiles;

    ChainingHashTable emptyGoodHashTable;

    ArrayList<String> mammals;
    ArrayList<String> reptiles;
    
    final static int INITIAL_CAPACITY = 2;
    @Before
    public void setUp() throws Exception
    {
        badHashTableMammals = new ChainingHashTable(INITIAL_CAPACITY, new BadHashFunctor());
        mediocreHashTableMammals = new ChainingHashTable(INITIAL_CAPACITY, new MediocreHashFunctor());
        goodHashTableMammals = new ChainingHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

        badHashTableReptiles = new ChainingHashTable(INITIAL_CAPACITY, new BadHashFunctor());
        mediocreHashTableReptiles = new ChainingHashTable(INITIAL_CAPACITY, new MediocreHashFunctor());
        goodHashTableReptiles = new ChainingHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

        emptyGoodHashTable = new ChainingHashTable(INITIAL_CAPACITY, new GoodHashFunctor());

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

        //badHashTableMammals.addAll(mammals);
        //mediocreHashTableMammals.addAll(mammals);
        goodHashTableMammals.addAll(mammals);

//        badHashTableReptiles.addAll(reptiles);
//        mediocreHashTableReptiles.addAll(reptiles);
        goodHashTableReptiles.addAll(reptiles);
    }

    /**
     * Tests the add method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if the element has been added.
     */
    @Test
    public void testAdd ()
    {
        assertFalse(goodHashTableMammals.add(null)); //TODO: can we add null items? Jace said yes??
        assertFalse(goodHashTableMammals.add("hog"));
        assertTrue(goodHashTableMammals.add("bear")); // actually added
        assertFalse(goodHashTableMammals.add("bear"));
        assertTrue(goodHashTableMammals.add("cow"));
        mammals.add("bear");
        assertTrue(goodHashTableMammals.containsAll(mammals));
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
     * Tests the contains method to ensure that if the table contains the given element it returns true.
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
        assertEquals(10, goodHashTableReptiles.size());
    }

}
