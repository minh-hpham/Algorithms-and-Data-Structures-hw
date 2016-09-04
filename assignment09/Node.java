package assignment09;

/**
 * Represent a place in a 2D maze. 
 * 
 * @author Hannah Potter and Minh Pham
 */
public class Node
{
	/**
	 * The Character for the space in the maze. If the space is a wall, the Character is X. If the space is 
	 * an open space, the Character is a space-character. If the space is the start for the maze, the Character
	 * is S. If the space is the goal for the maze, the Character is G. If the space is part of the path for 
	 * the solution to the maze, the Character is the period-character. These are the only valid Characters. 
	 */
	private Character data;
	
	/**
	 * True if this Node has been visited, false otherwise. 
	 */
	private boolean visited;
	
	/**
	 * The row the space this Node represents is on in the 2D maze. 
	 */
	private int row;
	
	/**
	 * The column the space this Node represents is on in the 2D maze. 
	 */
	private int column;
	
	/**
	 * The Node that led to visiting this Node when searching for a path through the maze. 
	 */
	private Node cameFrom;
	
	/**
	 * Creates a Node. data is the Character for the space that this Node represents in the maze.
	 * The Character for the space in the maze. If the space is a wall, the Character is X. If the space is 
	 * an open space, the Character is a space-character. If the space is the start for the maze, the Character
	 * is S. If the space is the goal for the maze, the Character is G. If the space is part of the path for 
	 * the solution to the maze, the Character is the period-character. These are the only valid characters. 
	 * row is the row the space this Node represents is on in the 2D maze. column is the column the space this Node
	 * represents is on in the 2D maze. Member variable visited is initially false and cameFrom is initially null.
	 * 
	 * @param data the character for the space that this Node represents in the maze
	 * @param row the row this Node is on in the 2D maze
	 * @param column the column this Node is on in the 2D maze
	 */
	public Node(Character data, int row, int column)
	{
		this.data = data;
		visited = false;
		this.row = row;
		this.column = column;
		cameFrom = null;
	}

	/**
	 * Returns cameFrom, which is the Node that led to visiting this Node when searching for a path
	 * through the maze. null if the Node has not been visited.
	 */
	public Node getCameFrom()
	{
		return cameFrom;
	}

	/**
	 * Set cameFrom
	 * @param cameFrom the Node that led to visiting this Node when searching for a path through the maze. 
	 */
	public void setCameFrom(Node cameFrom) 
	{
		this.cameFrom = cameFrom;
	}

	/**
	 * Returns the Character for the space in the maze. If the space is a wall, the Character is X. If the space is 
	 * an open space, the Character is a space-character. If the space is the start for the maze, the Character
	 * is S. If the space is the goal for the maze, the Character is G. If the space is part of the path for 
	 * the solution to the maze, the Character is the period-character. These are the only valid Characters. 
	 */
	public Character getData()
	{
		return data;
	}

	/**
	 * Sets the Character for the space in the maze. What to set data as:
	 * If the space is a wall, the Character is X. If the space is an open space, 
	 * the Character is a space-character. If the space is the start for the maze, the Character
	 * is S. If the space is the goal for the maze, the Character is G. If the space is part of the path for 
	 * the solution to the maze, the Character is the period-character. These are the only valid Characters. 
	 */
	public void setData(Character data)
	{
		this.data = data;
	}

	/**
	 * Returns true if this Node has been visited and false otherwise. 
	 */
	public boolean isVisited() 
	{
		return visited;
	}

	/**
	 * Sets visited (should be true if the Node has been visited and false otherwise).
	 */
	public void setVisited(boolean visited)
	{
		this.visited = visited;
	}

	/**
	 * Returns the row the space this Node represents is on in the 2D maze.
	 */
	public int getRow() 
	{
		return row;
	}

	/**
	 * Returns the column the space this Node represents is on in the 2D maze.
	 */
	public int getColumn()
	{
		return column;
	}

}
