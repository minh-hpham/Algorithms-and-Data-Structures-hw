package assignment13;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * Class provides tests for NetWorkGraphs
 * @author Sara Adamson, Minh Pham
 */
public class NetworkGraphTest
{
    NetworkGraph airportGraphSmall = null;
    NetworkGraph airportGraphNegative = null;
    NetworkGraph airportGraphNoPath = null;
    BestPath expected = new BestPath();
    BestPath expected2 = new BestPath();
    ArrayList<String> expectedPath = new ArrayList<>();
    ArrayList<String> expectedPath2 = new ArrayList<>();

    @Before
    public void setUp () throws Exception
    {
        try
        {
            airportGraphSmall = new NetworkGraph("smallMap.csv");
            airportGraphNegative = new NetworkGraph("negMap.csv");
            airportGraphNoPath = new NetworkGraph("noPathMap.csv");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * General tests to ensure the algorithm is working.
     */
    @Test
    public void testGeneral ()
    {
        expectedPath.add("BBB");
        expectedPath.add("GGG");
        expected.setPath(expectedPath);
        expected.setPathLength(6.0);
        expectedPath2.add("AAA");
        expectedPath2.add("DDD");
        expectedPath2.add("GGG");
        expectedPath2.add("FFF");
        expected2.setPath(expectedPath2);
        expected2.setPathLength(4.0);
        BestPath shortestDistancePath = airportGraphSmall.getBestPath("BBB", "GGG", FlightCriteria.DISTANCE);
        assertEquals(expected, shortestDistancePath);
        BestPath shortestDistancePath2 = airportGraphSmall.getBestPath("AAA", "FFF", FlightCriteria.TIME, "ZB");
        assertEquals(expected2, shortestDistancePath2);
    }

    /**
     * Test for negative numbers.
     */
    @Test
    public void testNegatives ()
    {
        expectedPath.add("BBB");
        expectedPath.add("CCC");
        expectedPath.add("EEE");
        expectedPath.add("GGG");
        expected.setPath(expectedPath);
        expected.setPathLength(4.0);
        BestPath shortestDistancePath = airportGraphNegative.getBestPath("BBB", "GGG", FlightCriteria.TIME);
        assertEquals(expected, shortestDistancePath);
    }

    /**
     * Test for a path that does not exist.
     */
    @Test
    public void testNoPath ()
    {
        BestPath noPath = airportGraphNoPath.getBestPath("BBB", "HHH", FlightCriteria.TIME);
        assertEquals(expected, noPath);
        BestPath noPath2 = airportGraphSmall.getBestPath("BBB", "GGG", FlightCriteria.DISTANCE, "ZB");
        assertEquals(expected, noPath2);
    }

    /**
     * Test for paths with the carrier specified.
     */
    @Test
    public void testCarrierSpecified ()
    {
        BestPath bestPath = airportGraphSmall.getBestPath("CCC", "GGG", FlightCriteria.TIME, "ZA");
        assertEquals(expected, bestPath);

        expectedPath2.add("AAA");
        expectedPath2.add("EEE");
        expectedPath2.add("GGG");
        expected2.setPath(expectedPath2);
        expected2.setPathLength(5);
        bestPath = airportGraphSmall.getBestPath("AAA", "GGG", FlightCriteria.TIME, "ZA");
        assertEquals(expected2, bestPath);

        expectedPath.add("AAA");
        expectedPath.add("DDD");
        expectedPath.add("GGG");
        expected.setPathLength(3.0);
        expected.setPath(expectedPath);
        bestPath = airportGraphSmall.getBestPath("AAA", "GGG", FlightCriteria.TIME, "ZB");
        assertEquals(expected, bestPath);
    }
    /**
     * Test for null or empty input.
     */
    @Test
    public void testNull ()
    {
        expected.setPath(expectedPath);
        BestPath noPath = airportGraphSmall.getBestPath("", "GGG", FlightCriteria.DISTANCE, "ZB");
        assertEquals(expected, noPath);
        
        noPath = airportGraphNoPath.getBestPath("BBB", "AAA", FlightCriteria.TIME, "");
        assertEquals(expected, noPath);
        
        noPath = airportGraphSmall.getBestPath("", "GGG", FlightCriteria.DISTANCE, null);
        assertEquals(expected, noPath);
        
    }
}
