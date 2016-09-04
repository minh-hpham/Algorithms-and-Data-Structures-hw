package assignment13;

import java.util.ArrayList;

/**
 * Class representation of an airport. This is a vertex in a graph.
 * 
 * @author Sara Adamson, Minh Pham
 */
public class Airport implements Comparable<Airport>
{
    /** name of the airport */
    private String name;

    /** value/cost to get to the airport */
    private Double weight;

    /** airport taken to get to this airport */
    private Airport cameFrom;

    /** true if the airport has been visited */
    private boolean visited;

    /** airports that this airport flies to */
    private ArrayList<Airport> neighbors;

    /**
     * Creates an airport with the given name
     * 
     */
    public Airport (String name)
    {
        this.name = name;
        visited = false;
        cameFrom = null;
        weight = Double.MAX_VALUE;
        neighbors = new ArrayList<>();
    }

    /**
     * returns the airport taken to get to this airport. (Previous airport)
     * 
     * @return
     */
    public Airport getCameFrom ()
    {
        return cameFrom;
    }

    /**
     * Sets the previous airport.
     */
    public void setCameFrom (Airport cameFrom)
    {
        this.cameFrom = cameFrom;
    }

    /**
     * @return true if this airport has been visited.
     */
    public boolean isVisited ()
    {
        return visited;
    }

    /**
     * @param sets the airport to be visited or not visited.
     */
    public void setVisited (boolean visited)
    {
        this.visited = visited;
    }

    /**
     * @return the name of the airport.
     */
    public String getName ()
    {
        return name;
    }

    /**
     * @return the value to get to this airport.
     */
    public Double getWeight ()
    {
        return weight;
    }

    /**
     * set the value to get to this airport.
     */
    public void setWeight (Double weight)
    {
        this.weight = weight;
    }

    /**
     * Update the weight and cameFrome variables of this node
     */
    public void update (double updatedWeight, Airport current)
    {
        this.cameFrom = current;
        this.weight = updatedWeight;
    }

    /**
     * @return the airports this airport flies to.
     */
    public ArrayList<Airport> getNeighbors ()
    {
        return neighbors;
    }

    /**
     * Add an airport to the list of airport this airport flies to.
     */
    public void addNeigbor (Airport neighbor)
    {
        this.neighbors.add(neighbor);
    }

    /**
     * resets weight, cameFrom, and visited status of an airport.
     */
    public void resetData ()
    {
        this.cameFrom = null;
        this.weight = Double.MAX_VALUE;
        this.visited = false;

    }

    /**
     * String representation of an airport object.
     */
    @Override
    public String toString ()
    {
        return name;
    }

    /**
     * Compares two airports. Returns a negative integer, zero, or a positive integer as this object is less than, equal
     * to, or greater than the specified object.
     */
    @Override
    public int compareTo (Airport airport)
    {
        return (int) (this.getWeight() - airport.getWeight());
    }
}
