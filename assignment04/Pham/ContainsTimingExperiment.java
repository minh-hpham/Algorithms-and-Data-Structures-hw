package assignment04.Pham;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class ContainsTimingExperiment
{
	private static final int ITER_COUNT = 1000;
	public static void main(String[] args)
	{
		// you spin me round baby, right round
				long startTime = System.nanoTime();
				while (System.nanoTime() - startTime < 1000000000);
				
				try(FileWriter fw = new FileWriter(new File("contains_experiment.tsv"))) { //open up a file writer so we can write to file.
					for(int exp = 10; exp <= 30; exp++) { // This is used as the exponent to calculate the size of the set.
						//int size = (int) Math.pow(2, exp); // or .. 
						int size = exp;
						size = 1 << exp; // the two statements are equivalent, look into bit-shifting if you're interested what is going on here.
						
						
						// Do the experiment multiple times, and average out the results
						long totalTime = 0;
						for (int iter = 0; iter < ITER_COUNT; iter++) {
							// SET UP!
							
							// TIME IT!
							long start = System.nanoTime();
							for (int i = 0; i < size; i++)
								for (int j = 0; j < size/2; j++)
									System.out.println();
							long stop = System.nanoTime();
							totalTime += stop - start;
						}
						double averageTime = totalTime / (double)ITER_COUNT;
						System.out.println(size + "\t" + averageTime); // print to console
						fw.write(size + "\t" + averageTime + "\n"); // write to file.
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
		
	}

}
