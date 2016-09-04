package assignment07;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * Tests for the BalancedSymbolChecker class
 * @author Hannah Potter and Minh Pham
 */
public class BalancedSymbolCheckerTest
{
	/**
	 * BalancedSymbolChecker to be used for the tests.
	 */
	BalancedSymbolChecker checker = new BalancedSymbolChecker();
	
	@Test (expected = FileNotFoundException.class)
	public void testCheckFileException() throws FileNotFoundException
	{
		// input is non-existent file
		checker.checkFile("madeUpfile.java");
	}
	
	@Test
	public void testCheckFileClass1() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class1.java").equals("ERROR: Unmatched symbol at line 6 and column 1. Expected ), but read } instead."));		
	}
	
	@Test
	public void testCheckFileClass2() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class2.java").equals("ERROR: Unmatched symbol at line 7 and column 1. Expected  , but read } instead." ));		
	}
	
	@Test
	public void testCheckFileClass3() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class3.java").equals( "No errors found. All symbols match." ));		
	}
	
	@Test
	public void testCheckFileClass4() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class4.java").equals( "ERROR: File ended before closing comment." ));		
	}
	
	@Test
	public void testCheckFileClass5() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class5.java").equals(  "ERROR: Unmatched symbol at line 3 and column 18. Expected ], but read } instead." ));		
	}
	
	@Test
	public void testCheckFileClass6() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class6.java").equals("No errors found. All symbols match."));		
	}
	
	@Test
	public void testCheckFileClass7() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class7.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ], but read ) instead."));		
	}
	
	@Test
	public void testCheckFileClass8() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class8.java").equals("ERROR: Unmatched symbol at line 5 and column 30. Expected }, but read ) instead."));		
	}
	
	@Test
	public void testCheckFileClass9() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class9.java").equals("ERROR: Unmatched symbol at line 3 and column 33. Expected ), but read ] instead."));		
	}
	
	@Test
	public void testCheckFileClass10() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class10.java").equals("ERROR: Unmatched symbol at line 5 and column 10. Expected }, but read ] instead."));		
	}
	
	@Test
	public void testCheckFileClass11() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class11.java").equals("ERROR: Unmatched symbol at the end of file. Expected }."));		
	}
	
	@Test
	public void testCheckFileClass12() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class12.java").equals("No errors found. All symbols match."));		
	}
	
	@Test
	public void testCheckFileClass13() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class13.java").equals("No errors found. All symbols match."));		
	}
	
	@Test
	public void testCheckFileClass14() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class14.java").equals("No errors found. All symbols match."));		
	}
	@Test
	public void testCheckFileOnlyHasComment() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class18.java").equals("No errors found. All symbols match."));		
	}
	@Test
	public void testCheckFileHasJavaDoc() throws FileNotFoundException
	{
		assertTrue(checker.checkFile("Class16.java").equals("No errors found. All symbols match."));		
	}
	@Test
	public void testCheckFileHasExtraClosedSymbol() throws FileNotFoundException
	{
		
		assertTrue(checker.checkFile("Class17.java").equals("ERROR: Unmatched symbol at line 10 and column 1. Expected  , but read } instead."));		
	}
	
}
