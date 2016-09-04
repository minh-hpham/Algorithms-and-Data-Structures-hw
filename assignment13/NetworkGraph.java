package assignment13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <p>
 * This class represents a graph of flights and airports along with specific data about those flights. It is recommended
 * to create an airport class and a flight class to represent nodes and edges respectively. There are other ways to
 * accomplish this and you are free to explore those.
 * </p>
 * 
 * <p>
 * Testing will be done with different criteria for the "best" path so be sure to keep all information from the given
 * file. Also, before implementing this class (or any other) draw a diagram of how each class will work in relation to
 * the others. Creating such a diagram will help avoid headache and confusion later.
 * </p>
 * 
 * <p>
 * Be aware also that you are free to add any member variables or methods needed to completed the task at hand
 * </p>
 * 
 * @author CS2420 Teaching Staff - Spring 2016
 * @author Minh Pham, Sara Adamson
 */
public class NetworkGraph
{
    /** all of the flights */
    HashMap<String, Flight> flights = new HashMap<>();
    /** all of the airports, no duplicates may be added */
    HashMap<String, Airport> airports = new HashMap<>();
    /** true if a carrier for a search has been specified */
    boolean carrierSpecified = true;

    /**
     * <p>
     * Constructs a NetworkGraph object and populates it with the information contained in the given file. See the
     * sample files or a randomly generated one for the proper file format.
     * </p>
     * 
     * <p>
     * You will notice that in the given files there are duplicate flights with some differing information. That
     * information should be averaged and stored properly. For example some times flights are canceled and that is
     * represented with a 1 if it is and a 0 if it is not. When several of the same flights are reported totals should
     * be added up and then reported as an average or a probability (value between 0-1 inclusive).
     * </p>
     * 
     * @param flightInfoPath - The path to the file containing flight data. This should be a *.csv(comma separated
     *            value) file
     * 
     * @throws FileNotFoundException The only exception that can be thrown in this assignment and only if a file is not
     *             found.
     */
    public NetworkGraph (String flightInfoPath) throws FileNotFoundException
    {
        Scanner scanner = new Scanner(new FileReader(new File(flightInfoPath)));
        String[] inputArr;
        // skip the first line of the file which contains information about the form of the file.
        scanner.nextLine();
        while (scanner.hasNextLine())
        {
            // ORIGIN,DESTINATION,CARRIER,DELAY,CANCELED,TIME,DISTANCE,COST
            inputArr = scanner.nextLine().split(",");
            if (airports.containsKey(inputArr[1]) == false)
            {
                airports.put(inputArr[1], new Airport(inputArr[1]));
            }
            if (airports.containsKey(inputArr[0]) == false)
            {
                airports.put(inputArr[0], new Airport(inputArr[0]));
            }

            String keyOfFlight = inputArr[0] + inputArr[1];
            if (flights.containsKey(keyOfFlight)) // if the flight exists, update the variables of the flight
            {
                flights.get(keyOfFlight).updateFlight(Double.parseDouble(inputArr[3]), Integer.parseInt(inputArr[4]),
                        Double.parseDouble(inputArr[5]), Double.parseDouble(inputArr[6]),
                        Double.parseDouble(inputArr[7]), inputArr[2]);
            }
            else
            // if the flight doesn't exist add it.
            {
                flights.put(
                        keyOfFlight,
                        new Flight(getAirport(inputArr[0]), getAirport(inputArr[1]), inputArr[2], Double
                                .parseDouble(inputArr[3]), Integer.parseInt(inputArr[4]), Double
                                .parseDouble(inputArr[5]), Double.parseDouble(inputArr[6]), Double
                                .parseDouble(inputArr[7])));
            }
            // we don't want to add duplicate airports.

        }
        scanner.close();
        // after the file has been read in, update each airports neighbors.
        createAirportNeighbors();
    }

    /**
     * This method returns a BestPath object containing information about the best way to fly to the destination from
     * the origin. "Best" is defined by the FlightCriteria parameter <code>enum</code>. This method should throw no
     * exceptions and simply return a BestPath object with information dictating the result. For example, if a
     * destination or origin is not contained in this instance of NetworkGraph simply return a BestPath with no path
     * (not a <code>null</code> path). If origin or destination are <code>null</code> return a BestPath object with null
     * as origin or destination (which ever is <code>null</code>.
     * 
     * @param origin - The starting location to find a path from. This should be a 3 character long string denoting an
     *            airport.
     * 
     * @param destination - The destination location from the starting airport. Again, this should be a 3 character long
     *            string denoting an airport.
     * 
     * @param criteria - This enum dictates the definition of "best". Based on this value a path should be generated and
     *            return.
     * 
     * @return - An object containing path information including origin, destination, and everything in between.
     */
    public BestPath getBestPath (String origin, String destination, FlightCriteria criteria)
    {
        carrierSpecified = false;
        // finds the best path searching for any airliner.
        return getBestPath(origin, destination, criteria, "");
    }

    /**
     * Finds and returns the shortest path in the dataSet from the origin to the destination.
     * 
     * @param dataSet the set of nodes we are searching through.
     */
    private BestPath dijkstra (Airport origin, Airport destination, String carrier)
    {

        PriorityQueue<Airport> priorityQ = new PriorityQueue<>();
        Airport current = null;
        Flight flight;
        double weightToNeighbor;

        // initialize the cost of starting node
        origin.setWeight(0.0);

        // enqueue the start
        priorityQ.add(origin);
        while (priorityQ.isEmpty() == false)
        {
            current = priorityQ.poll();
            current.setVisited(true);
            if (current.getName().equals(destination.getName()))
            {
                carrierSpecified = true;
                return traceBestPath(origin, current);
            }

            // look at each neighbor of current that has not been visited. If the weight to get to the neighbor from
            // current is less than the weight of the visited node.
            // Update the visited node and add it back to the priority queue. If the visited node is the destination.
            // Return the path.
            for (Airport neighbor : current.getNeighbors())
            {
                flight = flights.get(current.getName() + neighbor.getName());

                if (flightConditionsMet(carrier, flight, neighbor))
                {
                    // find the weight of current node and edge to compare with the weight of the neighbor that is
                    // visited
                    weightToNeighbor = current.getWeight() + flight.getWeight();
                    // update the neighbor if necessary
                    if (neighbor.getWeight() > (weightToNeighbor))
                    {
                        if (priorityQ.contains(neighbor))
                        {
                            priorityQ.remove(neighbor);
                        }
                        neighbor.update(weightToNeighbor, current);
                        priorityQ.add(neighbor);
                    }
                }
            }
        }
        carrierSpecified = true;
        return new BestPath();
    }

    /**
     * Returns true if: The given neighbor is not visited. The specified carrier has either not been restricted or the
     * services the flight. The weight to that neighbor is not negative. Otherwise returns false.
     */
    private boolean flightConditionsMet (String carrier, Flight flight, Airport neighbor)
    {
        return (neighbor.isVisited() == false) && (carrierSpecified == false || flight.getCarriers().contains(carrier))
                && (flight.getWeight() >= 0);
    }

    /**
     * Resets the values generated by dijkstra for each airport.
     */
    private void resetAirportData ()
    {
        for (Airport a : airports.values())
        {
            a.resetData();
        }

    }

    /**
     * Traces the shortest path from the destination to the origin
     */
    private BestPath traceBestPath (Airport origin, Airport current)
    {
        ArrayList<String> bestPath = new ArrayList<>();
        double pathLength = current.getWeight();
        while (current.getName().equals(origin.getName()) == false)
        {
            bestPath.add(0, current.getName());
            current = current.getCameFrom();
        }
        bestPath.add(0, current.getName());

        return new BestPath(bestPath, pathLength);
    }

    /**
     * <p>
     * This overloaded method should do the same as the one above only when looking for paths skip the ones that don't
     * match the given airliner.
     * </p>
     * 
     * @param origin - The starting location to find a path from. This should be a 3 character long string denoting an
     *            airport.
     * 
     * @param destination - The destination location from the starting airport. Again, this should be a 3 character long
     *            string denoting an airport.
     * 
     * @param criteria - This enum dictates the definition of "best". Based on this value a path should be generated and
     *            return.
     * 
     * @param airliner - a string dictating the airliner the user wants to use exclusively. Meaning no flights from
     *            other airliners will be considered.
     * 
     * @return - An object containing path information including origin, destination, and everything in between.
     */
    public BestPath getBestPath (String origin, String destination, FlightCriteria criteria, String airliner)
    {
        if (!airports.containsKey(origin) || !airports.containsKey(destination))
        {
            return new BestPath();
        }
        if (origin == null || destination == null)
        {
            ArrayList<String> path = new ArrayList<>();
            path.add(origin);
            path.add(destination);
            return new BestPath(path, 0.0);
        }
        resetAirportData();
        updateFlightWeight(criteria);
        return dijkstra(getAirport(origin), getAirport(destination), airliner);
    }

    /**
     * Updates the flights weight based on the flight searching criteria.
     * 
     * @param criteria
     * @param flights
     */
    private void updateFlightWeight (FlightCriteria criteria)
    {
        for (Flight flight : flights.values())
        {
            switch (criteria)
            {
                case COST:
                    flight.setWeight(flight.getCost());
                    break;
                case DISTANCE:
                    flight.setWeight(flight.getDistance());
                    break;
                case DELAY:
                    flight.setWeight(flight.getDelay());
                    break;
                case CANCELED:
                    flight.setWeight(flight.getCanceled());
                    break;
                case TIME:
                    flight.setWeight(flight.getTime());
                    break;
            }
        }
    }

    /**
     * Update all the neighbors of airport to include the given flights.
     * 
     * @param flightsInScope
     */
    private void createAirportNeighbors ()
    {
        Airport neighbor;
        // update the neighbors of each airport
        for (Airport airport : airports.values())
        {
            for (Flight flight : flights.values())
            {
                if (airport != null && flight != null)
                {
                    if (airport.getName().equals(flight.getOrigin().getName()))
                    {
                        neighbor = flight.getDestination();
                        airport.addNeigbor(neighbor);
                    }
                }
            }
        }
    }

    /**
     * @return an airport with the given name. if one does not exist returns null.
     */
    private Airport getAirport (String string)
    {
        return airports.get(string);
    }

}
