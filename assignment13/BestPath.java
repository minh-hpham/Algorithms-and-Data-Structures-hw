/**
 * 
 */
package assignment13;

import java.util.ArrayList;

/**
 * This class is what we will test your code on. If your BestPath objects equal ours (using the .equals method given)
 * then you will pass the tests. Do no modify anything that is given (use it of course but don't change names etc.)
 * 
 * @author CS2420 Teaching Staff - Spring 2016
 * @author Sara Adamson, Minh Pham
 */
public class BestPath
{

    /**
     * This should contain the nodes between the origin and destination inclusive. For example if I want the path
     * between SLC and MEM it should have SLC (index 0), MEM (index 1). If there are lay overs it should include them in
     * between (turns out you can fly to Memphis from here directly).
     */
    private ArrayList<String> path;

    /**
     * Since some path costs are going to be doubles sometimes use a double when costs are integers cast to a double.
     */
    private double pathLength;

    /**
     * Creates an empty BestPath object.
     */
    public BestPath ()
    {
        this(new ArrayList<String>(), 0.0);
    }

    /**
     * Creates a BestPath object with the given path, and path length.
     */
    public BestPath (ArrayList<String> path, double pathLength)
    {
        this.path = path;
        this.pathLength = pathLength;
    }

    /**
     * Sets the path to a BestPath object.
     */
    public void setPath (ArrayList<String> path)
    {
        this.path = path;
    }

    /**
     * returns true if this object equals the given BathPath object. That is if they have the same pathLengh and path
     * values.
     */
    @Override
    public boolean equals (Object o)
    {
        if (o instanceof BestPath)
        {
            BestPath other = (BestPath) o;
            return this.pathLength == other.pathLength && this.path.equals(other.path);
        }
        return false;
    }

    /**
     * String Representation for a BestPath object
     */
    @Override
    public String toString ()
    {
        return "Path Length: " + pathLength + "\nPath: " + this.path;
    }

    /**
     * Sets the path length to a BestPath object.
     */
    public void setPathLength (double pathLength)
    {
        this.pathLength = pathLength;
    }
}
