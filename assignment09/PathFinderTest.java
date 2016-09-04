package assignment09;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.Test;

/**
 * Tests for the solveMaze method in the PathFinder class.
 * 
 * @author Hannah Potter and Minh Pham
 */
public class PathFinderTest
{
	@Test
	public void testSolveMazeBigMaze1()
	{
		PathFinder.solveMaze("bigMaze.txt", "bigMazeOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("bigMazeSol.txt", "bigMazeOutput.txt"));
	}
	
	@Test
	public void testSolveMazeBigMaze2()
	{
		PathFinder.solveMaze("bigMaze2.txt", "bigMaze2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("bigMaze2Sol.txt", "bigMaze2Output.txt"));
	}

	@Test
	public void testSolveMazeClassicMaze1()
	{
		PathFinder.solveMaze("classic.txt", "classicOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("classicSol.txt", "classicOutput.txt"));
	}
	
	@Test
	public void testSolveMazeClassicMaze2()
	{
		PathFinder.solveMaze("classic2.txt", "classic2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("classic2Sol.txt", "classic2Output.txt"));
	}

	@Test
	public void testSolveMazeDemoMaze1()
	{
		PathFinder.solveMaze("demoMaze.txt", "demoMazeOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("demoMazeSol.txt", "demoMazeOutput.txt"));
	}
	
	@Test
	public void testSolveMazeDemoMaze2()
	{
		PathFinder.solveMaze("demoMaze2.txt", "demoMaze2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("demoMaze2Sol.txt", "demoMaze2Output.txt"));
	}

	@Test
	public void testSolveMazeMediumMaze1()
	{
		PathFinder.solveMaze("mediumMaze.txt", "mediumMazeOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("mediumMazeSol.txt", "mediumMazeOutput.txt"));
	}
	
	@Test
	public void testSolveMazeMediumMaze2()
	{
		PathFinder.solveMaze("mediumMaze2.txt", "mediumMaze2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("mediumMaze2Sol.txt", "mediumMaze2Output.txt"));
	}

	@Test
	public void testSolveMazeRandomMaze()
	{
		PathFinder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("randomMazeSol.txt", "randomMazeOutput.txt"));
	}

	@Test
	public void testSolveMazeStraightMaze1()
	{
		PathFinder.solveMaze("straight.txt", "straightOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("straightSol.txt", "straightOutput.txt"));
	}
	
	@Test
	public void testSolveMazeStraightMaze2()
	{
		PathFinder.solveMaze("straight2.txt", "straight2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("straight2Sol.txt", "straight2Output.txt"));
	}

	@Test
	public void testSolveMazeTinyMaze1()
	{
		PathFinder.solveMaze("tinyMaze.txt", "tinyMazeOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("tinyMazeSol.txt", "tinyMazeOutput.txt"));
	}
	
	@Test
	public void testSolveMazeTinyMaze2()
	{
		PathFinder.solveMaze("tinyMaze2.txt", "tinyMaze2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("tinyMaze2Sol.txt", "tinyMaze2Output.txt"));
	}

	@Test
	public void testSolveMazeTinyOpenMaze1()
	{
		PathFinder.solveMaze("tinyOpen.txt", "tinyOpenOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("tinyOpenSol.txt", "tinyOpenOutput.txt"));
	}
	
	@Test
	public void testSolveMazeTinyOpenMaze2()
	{
		PathFinder.solveMaze("tinyOpen2.txt", "tinyOpen2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("tinyOpen2Sol.txt", "tinyOpen2Output.txt"));
	}

	@Test
	public void testSolveMazeTurnMaze1()
	{
		PathFinder.solveMaze("turn.txt", "turnOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("turnSol.txt", "turnOutput.txt"));
	}
	
	@Test
	public void testSolveMazeTurnMaze2()
	{
		PathFinder.solveMaze("turn2.txt", "turn2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("turn2Sol.txt", "turn2Output.txt"));
	}

	@Test
	public void testSolveMazeUnsolvableMaze1()
	{
		PathFinder.solveMaze("unsolvable.txt", "unsolvableOutput.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("unsolvableSol.txt", "unsolvableOutput.txt"));
	}
	
	@Test
	public void testSolveMazeUnsolvableMaze2()
	{
		PathFinder.solveMaze("unsolvable2.txt", "unsolvable2Output.txt");
		// first listed file name is what the solution to the maze is supposed to be
		assertTrue(sameContents("unsolvable2Sol.txt", "unsolvable2Output.txt"));
	}

	@Test // make sure that the method used for the test works
	public void testSameContents()
	{
		assertTrue(sameContents("turnSol.txt", "turnSol.txt"));
		assertFalse(sameContents("turnSol.txt", "bigMaze.txt"));
		assertTrue(sameContents("turnSol.txt", "turnOutput.txt"));
		// only change in last character of last line
		assertFalse(sameContents("sameContentsTest1.txt", "sameContentsTest2.txt"));
		// same except one file has one more line
		assertFalse(sameContents("oneMoreLine.txt", "oneLessLine.txt"));
	}

	/**
	 * Compares the contents of two files. Returns true if the contents of the files are identical
	 * (order must be the same) and false if the contents of the files are not identical. Also returns
	 * false if at least one of the files cannot be found.
	 * 
	 * @param file1 String name of a file
	 * @param file2 String name of a file
	 * @return true if the contents of the files are identical
	 * 			(order must be the same) and false if the contents of the files are not identical or
	 * 			false if at least one of the files cannot be found
	 * 
	 */
	private boolean sameContents(String file1, String file2)
	{
		try (Scanner input1 = new Scanner(new File(file1)))
		{
			try (Scanner input2 = new Scanner(new File(file2)))
			{
				String lineFrom1 = null;
				String lineFrom2 = null;
				while (input1.hasNextLine() && input2.hasNextLine())
				{
					lineFrom1 = input1.nextLine();
					lineFrom2 = input2.nextLine();

					// if the two lines (same line number for each file) are not the same
					// returns false
					if (lineFrom1.compareTo(lineFrom2) != 0)
					{
						return false;
					}
				}

				// if one file has no more lines, but the other does, the files do not
				// have the same contents
				if (input1.hasNextLine() || input2.hasNextLine())
				{
					return false;
				}
			
				// the files have the same contents
				return true;
			} 
		}	
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			// at least one of the files cannot be found
			return false;
		}
	}
}
