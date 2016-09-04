package assignment09;

/**
 * Class created to make sure that the solveMaze method of PathFinder has an appropriate run-time (no
 * more than 10 seconds for any maze-- an 100X100 maze will be the largest size that needs to fit this
 * time constraint).
 * 
 * @author Hannah Potter and Minh Pham
 */
public class TestPathFinder {
	
	/**
	 * Method to make sure that the solveMaze method of PathFinder has an appropriate run-time (no
	 * more than 10 seconds for any maze-- an 100X100 maze will be the largest size that needs to fit this
	 * time constraint).
	 */
	public static void main(String[] args) 
	{
		long startTime, stopTime;
		
		startTime = System.nanoTime();
		PathFinder.solveMaze("tinyMaze.txt", "tinyMazeOutput.txt");
		stopTime = System.nanoTime();
		
		System.out.println("Run-time for tinyMaze.txt: " + (stopTime - startTime)/(Math.pow(10.0, 9)) + " seconds");
		
		startTime = System.nanoTime();
		// test on 100X100 maze -- largest size that needs to fit within the time constraint
		PathFinder.solveMaze("randomMaze.txt", "randomMazeOutput.txt");
		stopTime = System.nanoTime();
		
		System.out.println("Run-time for randomMaze.txt: " + (stopTime - startTime)/(Math.pow(10.0, 9)) + " seconds");
	}
}
