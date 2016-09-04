/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package assignment01;

import static org.junit.Assert.*;
import org.junit.Test;

public class MatrixTester {
//	@SuppressWarnings("null")
	public static void main(String[] args)
	{			
		Matrix M1 = new Matrix(new int[][]
		                                 {{1, 2, 3},
										  {2, 5, 6}});
		
		Matrix M2 = new Matrix(new int[][]
		                                 {{4, 5},
										  {3, 2},
										  {1, 1}});
		
		Matrix M3 = new Matrix(new int[][]
									    {{4, 5},
										 {1, 1}});
		Matrix M4 = new Matrix (new int[0][0]);
		
		Matrix M5 = new Matrix(new int[][]
										{{1, 2, 3},
									  	 {2, 5, 6}});
		
		// this is the known correct result of multiplying M1 by M2
		Matrix referenceMultiply = new Matrix(new int[][]
		                                                {{13, 12},
														 {29, 26}});
		// this is the known correct result of adding M1 and M5
		Matrix referenceAddition = new Matrix (new int[][]
														{{2,4,6,},
														 {4,10,12}});
//		Matrix referenceMultiply2= null;
		
		System.out.println(M1); //this is to test toString method
		System.out.println(M2); //this is to test toString method
		
		/* 
		 * Note that none of the tests below will be correct until you have implemented all methods.
		 * This is just one example of a test, you must write more tests and cover all cases.
		 */
		
		// get the matrix computed by your times method
		Matrix computedMultiply = M1.times(M2);
		Matrix computedMultiply2 = M1.times(M3);
		Matrix computedMultiply3 = M3.times(M2);
		Matrix computedMultiply4 = M1.times(M4);
		Matrix computedMultiply5 = M4.times(M1);
		
		// get the matrix computed by the plus method
		Matrix computedAddition = M1.plus(M5);
		Matrix computedAddition2 = M5.plus(M1);
		Matrix computedAddition3 = M1.plus(M2);
		Matrix computedAddition4 = M1.plus(M4);
		Matrix computedAddition5 = M4.plus(M1);
		
		// exercises your toString method
		System.out.println("Computed result for M1 * M2:\n" + computedMultiply); 
		System.out.println("Computed result for M1 * M3:\n" + computedMultiply2);
		System.out.println("Computed result for M3 * M2:\n" + computedMultiply3);
		System.out.println("Computed result for M1 * M4:\n" + computedMultiply4);
		System.out.println("Computed result for M4 * M1:\n" + computedMultiply5);
		System.out.println("Computed result for M1 + M5:\n" + computedAddition);
		System.out.println("Computed result for M5 + M1:\n" + computedAddition2);
		System.out.println("Computed result for M1 + M2:\n" + computedAddition3);
		System.out.println("Computed result for M1 + M4:\n" + computedAddition4);
		System.out.println("Computed result for M4 + M1:\n" + computedAddition5);
		
		// exercises your .equals method, and makes sure that your computed result is the same as the known correct result
		if(!referenceMultiply.equals(computedMultiply))  {
			System.out.println("M1*M2 Should be:\n" + referenceMultiply);
		}
		if(computedMultiply2!=null)  {
			System.out.println("M1*M3 Should be:null\n");
		}
		if(computedMultiply3!=null)  {
			System.out.println("M3*M2 Should be:null\n");
		}
		if(computedMultiply4!=null)  {
			System.out.println("M1*M4 Should be:null\n");
		}
		if(computedMultiply5!=null)  {
			System.out.println("M4*M1 Should be:null\n");
		}
		if(!referenceAddition.equals(computedAddition))  {
			System.out.println("M1+M5 Should be:\n" + referenceAddition);
		}
		if(!referenceAddition.equals(computedAddition2))  {
			System.out.println("M5+M1 Should be:\n" + referenceAddition);
		}
		if(computedAddition3!=null)  {
			System.out.println("M1+M2 Should be:null\n");
		}
		if(computedAddition4!=null)  {
			System.out.println("M1+M4 Should be:null\n");
		}
		if(computedAddition5!=null)  {
			System.out.println("M4+M1 Should be:null\n");
		}
		//TODO: fill in more tests that fully exercise all of your methods
	}
	@Test // test method equals when matrix dimention is different
	public void testEquals1(){
		Matrix M1 = new Matrix(new int[][]
                						{{1, 2, 3},
				  						{2, 5, 6}});

		Matrix M2 = new Matrix(new int[][]
                						{{4, 5},
				  						{3, 2},
				  						{1, 1}});
		assertFalse(M1.equals(M2));
	}
	
	@Test // test method equals when dimention is the same but matrixs contain different value
	public void testEquals2(){
		Matrix M1 = new Matrix(new int[][]
                						{{1, 2, 3},
				  						{2, 5, 6}});

		Matrix M2 = new Matrix(new int[][]
										{{1, 8, 3},
										{2, 5, 6}});
		assertFalse(M1.equals(M2));
	}
	
	@Test // test method equals when dimention is the same and contain same values
	public void testEquals3(){
		Matrix M1 = new Matrix(new int[][]
                						{{1, 2, 3},
				  						{2, 5, 6}});

		Matrix M2 = new Matrix(new int[][]
										{{1, 2, 3},
										{2, 5, 6}});
		assertTrue(M1.equals(M2));
	}
}
