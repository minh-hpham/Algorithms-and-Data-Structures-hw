package assignment13;

import java.util.HashSet;

/**
 * Class representation of a flight. This is an edge in a graph
 * 
 * @author Sara Adamson, Minh Pham
 */
public class Flight
{
    // flights have a cost
    double cost;
    // a distance
    double distance;
    // a time
    double time;
    // may be canceled a 1 represents a canceled flight, and a 0 represents a flight that has not been canceled.
    double canceled;
    // may be delayed
    double delay;// TODO: how is this represented?
    // the value the user is using to search for a flight.
    double weight;
    // an origin airport
    Airport origin;
    // and a destination airport
    Airport destination;
    // and a carrier
    HashSet<String> carriers = new HashSet<>();
    double flights;

    /**
     * Creates a flight object.
     * 
     * @param origin Airport this flight starts at.
     * @param destination Ariport this flight flies to.
     * @param carrier of this flight
     * @param delay
     * @param canceled
     * @param time
     * @param distance
     * @param cost
     */
    public Flight (Airport origin, Airport destination, String carrier, double delay, int canceled, double time,
            double distance, double cost)
    {
        this.origin = origin;
        this.destination = destination;
        this.carriers.add(carrier);
        this.carriers.add("");
        this.delay = delay;
        this.canceled = canceled;
        this.time = time;
        this.distance = distance;
        this.cost = cost;
        weight = 0;
        flights = 1;
    }

    /**
     * update this flight if the HashMap flights contains duplicates
     */
    public void updateFlight (double delay, int canceled, double time, double distance, double cost, String carrier)
    {
        this.delay += delay;
        this.canceled += canceled;
        this.time += time;
        this.distance += distance;
        this.cost += cost;
        this.carriers.add(carrier);
        flights++;
    }

    /**
     * return the cost of this flight.
     */
    public double getCost ()
    {
        return cost / flights;
    }

    /**
     * @return the distance of the flight.
     */
    public double getDistance ()
    {
        return distance / flights;
    }

    /**
     * @return the time(duration) of the flight.
     */
    public double getTime ()
    {
        return time / flights;
    }

    /**
     * Returns the probability this flight will be canceled.
     */
    public double getCanceled ()
    {
        return canceled / flights;
    }

    /**
     * Returns the average delay for this flight.
     */
    public double getDelay ()
    {
        return delay / flights;
    }

    /**
     * Returns the Airport object this flight originates from.
     */
    public Airport getOrigin ()
    {
        return origin;
    }

    /**
     * @return the destination Airport object of this flight.
     */
    public Airport getDestination ()
    {
        return destination;
    }

    /**
     * @return the list of carriers that service this flight.
     */
    public HashSet<String> getCarriers ()
    {
        return carriers;
    }

    /**
     * Sets the weight to be used when computing paths to airports.
     */
    public void setWeight (double weight)
    {
        this.weight = weight;
    }

    /**
     * @return the weight being used to compute the paths to airports.
     */
    public Double getWeight ()
    {
        return weight;
    }

    /**
     * String representation of this flight object
     */
    @Override
    public String toString ()
    {
        return origin + " " + destination;
    }
}
