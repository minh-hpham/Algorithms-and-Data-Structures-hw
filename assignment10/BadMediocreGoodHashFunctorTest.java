package assignment10;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Class tests for the bad, mediocre, and goodHashFunctor class
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class BadMediocreGoodHashFunctorTest
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
    
    @Before
    public void setUp() throws Exception
    {
        badHashTableMammals = new ChainingHashTable(10, new BadHashFunctor());
        mediocreHashTableMammals = new ChainingHashTable(10, new MediocreHashFunctor());
        goodHashTableMammals = new ChainingHashTable(10, new GoodHashFunctor());

        badHashTableReptiles = new ChainingHashTable(10, new BadHashFunctor());
        mediocreHashTableReptiles = new ChainingHashTable(10, new MediocreHashFunctor());
        goodHashTableReptiles = new ChainingHashTable(10, new GoodHashFunctor());

        emptyGoodHashTable = new ChainingHashTable(10, new GoodHashFunctor());

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

        badHashTableMammals.addAll(mammals);
        mediocreHashTableMammals.addAll(mammals);
        goodHashTableMammals.addAll(mammals);

        badHashTableReptiles.addAll(reptiles);
        mediocreHashTableReptiles.addAll(reptiles);
        goodHashTableReptiles.addAll(reptiles);
    }
    /**
     * Tests the add method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if the element has been added.
     */
    @Test
    public void testAddGood ()
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
     * Tests the add method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if the element has been added.
     */
    @Test
    public void testAddMediocre ()
    {
        assertFalse(mediocreHashTableMammals.add(null));
        assertFalse(mediocreHashTableMammals.add("hog"));

        assertTrue(mediocreHashTableMammals.add("dove")); // actually added
        assertFalse(mediocreHashTableMammals.add("dove"));
        assertTrue(mediocreHashTableMammals.add("cow"));
        
        //mammals has more elements than than goodHashTable 
        mammals.add("bear");
        mammals.add("human");
        assertFalse(mediocreHashTableMammals.containsAll(mammals));
    }
    /**
     * Tests the add method to ensure that no duplicates can be added, that no null values can be added, and that no
     * more than the capacity can be added. The add method returns true if the element has been added.
     */
    @Test
    public void testAddBad ()
    {
        assertFalse(badHashTableMammals.add(null));
        assertFalse(badHashTableMammals.add("hog"));

        assertTrue(badHashTableMammals.add("dove")); // actually added
        assertFalse(badHashTableMammals.add("dove"));
        assertTrue(badHashTableMammals.add("cow"));
        
        //mammals has more elements than than goodHashTable 
        mammals.add("bear");
        mammals.add("human");
        assertFalse(badHashTableMammals.containsAll(mammals));
    }
    
    /**
     * Tests the contains method to ensure that if the table contains the given element it returns true. TODO: What
     * should we do if a null element is checked or added? Ignore it?
     */
    @Test
    public void testContainsGood ()
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
     * Tests the contains method to ensure that if the table contains the given element it returns true. TODO: What
     * should we do if a null element is checked or added? Ignore it?
     */
    @Test
    public void testContainsMediocre ()
    {
        assertFalse(mediocreHashTableReptiles.contains("null"));
        // first element
        assertTrue(mediocreHashTableReptiles.contains("gecko"));
        // middle element
        assertTrue(mediocreHashTableReptiles.contains("crocodile"));
        // last element
        assertTrue(mediocreHashTableReptiles.contains("horned toad"));
        // element not in the list
        assertFalse(mediocreHashTableReptiles.contains("mouse"));

    }
    /**
     * Tests the contains method to ensure that if the table contains the given element it returns true. TODO: What
     * should we do if a null element is checked or added? Ignore it?
     */
    @Test
    public void testContainsBad ()
    {
        assertFalse(badHashTableReptiles.contains("null"));
        // first element
        assertTrue(badHashTableReptiles.contains("gecko"));
        // middle element
        assertTrue(badHashTableReptiles.contains("crocodile"));
        // last element
        assertTrue(badHashTableReptiles.contains("horned toad"));
        // element not in the list
        assertFalse(badHashTableReptiles.contains("mouse"));

    }

}
