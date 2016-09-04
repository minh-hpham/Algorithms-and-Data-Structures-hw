package assignment09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Provides a method to find the shortest path to solve a maze.
 * 
 * @author Hannah Potter and Minh Pham
 */
public class PathFinder 
{

	/**
	 * Solves a maze by finding the shortest path from a start to an end place. The file associated with 
	 * inputFile must be in the following format: 
	 * 1) The first line contains two numbers, separated by a space. The first number is the height of the maze,
	 *  and the second is the width. 
	 * 2) The rest of the lines contain the layout of the field. The characters have the following meaning:
	 *		X - A single wall segment (when finding a path, walls cannot be jumped or gone threw). 
	 *		S - The starting point of the path that we are trying to find. This is an open space (no wall).
	 *		G - The ending point of the path we are trying to find. This is an open space (no wall).
	 *		(space) - An open space on the field.
	 * 3) The input maze must be rectangular. All of the border positions around the perimeter of the
	 *  field must be walls (the field is fully enclosed).
	 *  
	 * The file associated with the outputFile will be in a similar format. The first line will be the same
	 * as that of the inputFile and will have the same characters throughout with the exception that the shortest
	 * path from the start to the goal will be marked by a period character (.). If there are multiple shortest 
	 * paths, any one of them may be marked. If there is no shortest path, nothing will be marked
	 * (the file associated with outputFile will have the same contents as that of inputFile).
	 * There is a single newline character after the last 'X' in the output. 
	 *
	 * @param inputFile the name of the file that contains the maze to be solved
	 * @param outputFile the name of the file where the solution to the maze will be written
	 */
	public static void solveMaze(String inputFile, String outputFile)
	{
		Scanner input;
		try 
		{
			input = new Scanner(new File(inputFile));

			// Get the dimensions for the 2D array / the maze
			String[] dimensions = input.nextLine().split(" ");
			int row = Integer.parseInt(dimensions[0]);
			int column = Integer.parseInt(dimensions[1]);
			Node[][] maze = new Node[row][column];
			
			// Reset row and column to zero to get ready to go through the 2D array
			row = 0;
			column = 0;
			Node start = null;
			Node goal = null;

			// Put the maze into a 2D array
			while (input.hasNextLine() ) 
			{
				String line = input.nextLine();
				
				for (int i = 0; i < line.length(); i++)
				{
					Node node = new Node(line.charAt(i), row, column);
					maze[row][column] = node;
					
					// Set the start Node when the symbol for the start-place is read 
					if (line.charAt(i) == 'S') 
					{
						start = node;
					} 
					// Set the goal node when the symbol for the goal-place is read 
					else if (line.charAt(i) == 'G')
					{
						goal = node;
					}
					
					column++;
				}
				
				row++;
				column = 0;
			}

			Graph graph = new Graph(maze);
			
			// Solve the maze and mark the path
			graph.breadthFirstSearch(start, goal);
			markPath(goal, start);
			
			// Write the solution
			writeFile(outputFile, dimensions, maze);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}

	}

	/**
	 * Helper method for solveMaze method.
	 * Marks the path for the solution of the maze. The path will be marked out by the period character (.).
	 * If there is no solution, nothing will be marked. 
	 * 
	 * @param goal the goal Node for the maze 
	 * @param start the start Node for the maze
	 */
	private static void markPath(Node goal, Node start) 
	{
		// If there is no solution to the maze, return without marking anything
		if (goal.getCameFrom() == null)
		{
			return;
		}
		
		Node current = goal.getCameFrom();

		while (current != start)
		{
			current.setData('.');
			current = current.getCameFrom();
		}
	}

	/**
	 * Helper method for solveMaze method.
	 * Writes the file for the maze. The first line contains two numbers, separated by a space.
	 * The first number is the height of the maze, and the second is the width. The rest of the 
	 * lines are the maze/the data in the maze. 
	 * There is a single newline character after the last 'X' in the output. 
	 * 
	 * @param fileName the name of the file where the file for the maze is to be written
	 * @param dimensions the dimensions of the maze 
	 * @param maze the maze that is to be written 
	 */
	private static void writeFile(String fileName, String[] dimensions, Node[][] maze) 
	{
		try
		{
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(fileName));
			
			// Write the dimension line of the maze 
			output.println(dimensions[0] + " " + dimensions[1]);
			
			// Write out the maze (with its solution)
			for (int row = 0; row < maze.length; row++)
			{
				for (int column = 0; column < maze[0].length; column++)
				{
					output.print(maze[row][column].getData());
				}
				output.println();
			}

			output.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
