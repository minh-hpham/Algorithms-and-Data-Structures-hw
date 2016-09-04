package assignment09;

import java.util.LinkedList;

/**
 * Represents a 2D maze. 
 * 
 * @author Hannah Potter and Minh Pham
 */
public class Graph
{
	/**
	 * 2D array of nodes to represent the 2D maze. 
	 */
	private Node[][] nodes;

	/**
	 * Makes graph a Graph essentially (note that graph will be changed when Graph is changed).
	 * 
	 * @param graph the 2D array that represents an array (will become the Graph essentially)
	 */
	public Graph(Node[][] graph)
	{
		nodes = graph;
	}

	/**
	 * Finds the shortest path from start to goal. If there is more than 
	 * one shortest path, any one of them could be marked. The Nodes that led to visiting each other
	 * will be marked (i.e. cameFrom for each Node will be set appropriately).
	 * Returns true if there is a path, false otherwise. 
	 * 
	 * @param start the start Node
	 * @param goal the goal Node
	 * @return true if there is a path from start to goal, false otherwise
	 */
	public boolean breadthFirstSearch(Node start, Node goal)
	{
		LinkedList<Node> queue = new LinkedList<>();
		
		queue.addLast(start);

		while (!queue.isEmpty())
		{
			Node current = queue.removeFirst();

			if (current == goal)
			{
				return true;
			}

			// right
			addToQueueIfValid(queue, current, nodes[current.getRow()][current.getColumn() + 1]);
			// down
			addToQueueIfValid(queue, current, nodes[current.getRow() - 1][current.getColumn()]);
			// up
			addToQueueIfValid(queue, current, nodes[current.getRow() + 1][current.getColumn()]);
			// left
			addToQueueIfValid(queue, current, nodes[current.getRow()][current.getColumn() - 1]);
		}

		// goal never reached
		return false;
	}

	/**
	 * Helper method for breadthFirstSearch method.
	 * Checks to see if a Node is valid to add to the queue used in the breadthFirstSearch method. 
	 * If a Node is null, its data is equal to X (it is a wall space), or it has been visited, it is not
	 * valid to add the the queue. 
	 * 
	 * @param queue the queue used in the breadthFirstSearch method
	 * @param current the Node thats neighbors are being checked to see if they are valid (from where node came)
	 * @param node the Node that is being checked to see if it is valid
	 */
	private void addToQueueIfValid(LinkedList<Node> queue, Node current, Node node)
	{
		if (node != null && node.getData() != 'X' && !node.isVisited())
		{
			queue.addLast(node);
			node.setCameFrom(current);
			node.setVisited(true);
		}
	}

}
