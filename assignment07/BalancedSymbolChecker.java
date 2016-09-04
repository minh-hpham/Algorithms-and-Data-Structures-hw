package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class containing the checkFile method for checking if the (, [, and { symbols
 * in an input file are correctly matched.
 * 
 * @author Hannah Potter and Minh Pham
 */
public class BalancedSymbolChecker
{
	/**
	 * Row in the file. This row number is currently being checked.
	 */
	private int row;
	/**
	 * Column of the row of the file. This column number is currently being checked.
	 */
	private int column;
	/**
	 * The stack. Can only contain the opening symbols (, {, [. 
	 */
	private LinkedListStack<Character> stack;
	/**
	 * Contains the lines of the file. Each element in the ArrayList contains one line
	 * of the file. 
	 */
	private ArrayList<String> linesOfFile;
	/**
	 * Is true only if there was an opening comment (i.e. /*) found in the file and the close
	 * of the comment has not been found yet.
	 */
	private boolean inComment = false;
	/**
	 * Is true only if there was an opening to a string literal (i.e. ") found in the file and the close
	 * of the string literal has not been found yet.
	 */
	private boolean inStringLiteral = false;
	/**
	 * Is true only if there was an opening to a char literal (i.e. ') found in the file and the close
	 * of the char literal has not been found yet.
	 */
	private boolean inCharLiteral = false;

	/**
	 * Returns a message indicating whether the input file has unmatched
	 * symbols. Throws FileNotFoundException if the file does not exist.
	 */
	public String checkFile(String filename) throws FileNotFoundException 
	{
		try (Scanner fileIn = new Scanner(new File(filename))) 
		{
			stack = new LinkedListStack<Character>();
			linesOfFile = new ArrayList<String>();
			
			// Puts each line of the file into the ArrayList (one element for each 
			// line) in order.
			while (fileIn.hasNextLine())
			{
				linesOfFile.add(fileIn.nextLine());
			}
			// Evaluates the contents of the lines of the file
			return readLines(linesOfFile);
		}
	}

	/**
	 * Reads through each line of the file and evaluates whether or not there are errors with 
	 * balancing of symbols. Tests for symbols (), {}, and [] to match up appropriately. Ignores
	 * symbols found in comments, string literals, and char literals. Returns the correct error
	 * (or non-error) message for the file.
	 * 
	 * @param linesOfFile ArrayList where each element is one of the lines of the file in order
	 * @return the correct error (or non-error) message for the file
	 */
	private String readLines(ArrayList<String> linesOfFile) 
	{
		String messageForFile = null;
		row = 1;
		column = 0;
		for (int i = 0; i < linesOfFile.size(); i++)
		{
			String line = linesOfFile.get(i);
			messageForFile = handleLine(line);

			// Returns the error message if one was found on this particular line
			if (messageForFile != null)
			{
				return messageForFile;
			}	
			
			row++;
			column = 0;
		}
		
		// Indicates that there was an opening to a comment (i.e. /*) but never a close
		if (inComment)
		{
			return unfinishedComment();
		}
		// If there are characters left on the stack when all the lines have been evaluated,
		// there was an unmatched symbol at the end of the file (error message for unmatched
		// symbol earlier on in the file would have already been returned)
		else if (!stack.isEmpty())
		{
			return unmatchedSymbolAtEOF(matchOf(stack.pop()));
		}
		else 
		{
			return allSymbolsMatch();
		}
	}

	/**
	 * Evaluates each a line character by character. If an opening symbol is found, it is pushed onto the 
	 * stack that keeps track of the opening symbols. If a closing symbol is found and the first element on the 
	 * stack is the corresponding opening symbol, the opening symbol is popped off the stack. Returns the 
	 * correct error message if one is found in this line, otherwise returns null.
	 * 
	 * @param line the line of the file being checked
	 * @return the correct error message or null if there are no errors found in this line
	 */
	private String handleLine(String line) 
	{
		for (int i = 0; i < line.length(); i++)
		{
			column++;

			char ch = line.charAt(i);
			
			// If any true, look for end of that particular comment/string
			// literal/char literal
			if (inComment || inStringLiteral || inCharLiteral)
			{
				// Look for the end of the comment, making sure that you do not look at an index
				// that is >= to line.length()
				if (inComment && ch == '*' && (i + 1 < line.length() && line.charAt(i + 1) == '/'))
				{
					inComment = false;
				} 
				// Look for the end of the string literal, making sure that you do not look at an index
				// less than 0 and that the end quote is not preceded by a backslash
				else if (inStringLiteral && ch == '\"' && (i - 1 >= 0 && line.charAt(i -1) != '\\'))
				{
					inStringLiteral = false;
				} 
				// Look for the end of a char literal, making sure that you do not look at an index 
				// less than 0 and that the end ' is not preceded by a backslash
				else if (inCharLiteral && ch == '\'' && (i - 1 >= 0 && line.charAt(i -1) != '\\'))
				{
					inCharLiteral = false;
				}
			}
			
			// Does no look at the rest of a line after a single-line comment. Makes sure
			// you do not look at an index >= line.length()
			else if (ch == '/' && (i + 1 < line.length() && line.charAt(i + 1) == '/'))
			{
				return null;
			}
			// Sets the inComment to true if the beginning of a comment is found, making
			// sure not to look at an index that is >= line.length()
			else if (ch == '/' && (i + 1 < line.length() && line.charAt(i + 1) == '*'))
			{
				inComment = true;
			}
			// Sets the inCharLiteral to true if the beginning of a char literal is found, making
			// sure not to look at an index that is less than 0
			else if (ch == '\'' && (i - 1 >= 0 && line.charAt(i -1) != '\\'))
			{
				inCharLiteral = true;
			}
			// Sets the inStingLiteral to true if the beginning of a string literal is found, making
			// sure not to look at an index that is less than 0
			else if (ch == '\"' && (i - 1 >= 0 && line.charAt(i -1) != '\\'))
			{
				inStringLiteral = true;
			}

			// Check if the char is an opening or a closing character
			else
			{
				switch (ch)
				{
				// If the symbol is an open symbol, push it onto the stack
				case '(':
				case '[':
				case '{':
					stack.push(ch);
					break;

				// If the symbol is a close symbol
				case ')':
				case ']':
				case '}':
					// If the stack is empty and a close symbol is found, there is an unmatched symbol
					// where nothing was expected
					if (stack.isEmpty())
					{
						return unmatchedSymbol(row, column, ch, ' ');
					}
					
					// Pop the top open symbol off the stack
					char openedSymbol = stack.pop();
					
					// Check if the openedSymbol and closedSymbol are matched
					// if not call unmatchedSymbol
					if (openedSymbol == '(' && ch != ')' || openedSymbol == '{' && ch != '}'
							|| openedSymbol == '[' && ch != ']')
					{
						return unmatchedSymbol(row, column, ch, matchOf(openedSymbol));
					}
					break;
					
				default: // The char is neither an opening nor a closing char, so do nothing
					break;
				}
			}
		}
		
		return null; // If this is reached, there were no problems found on this particular line
					 // of the file

	}

	/**
	 * Returns the closedSymbol (i.e. ), }, ]) that is expected to match the openedSymbol (i.e. (, {, [).
	 */
	private char matchOf(char openedSympol)
	{
		if (openedSympol == '{')
			return '}';
		else if (openedSympol == '[')
			return ']';
		return ')';
	}

	/**
	 * Returns an error message for unmatched symbol at the input line and
	 * column numbers. Indicates the symbol match that was expected and the
	 * symbol that was read.
	 */
	private String unmatchedSymbol(int lineNumber, int colNumber, char symbolRead, char symbolExpected)
	{
		return "ERROR: Unmatched symbol at line " + lineNumber + " and column " + colNumber + ". Expected "
				+ symbolExpected + ", but read " + symbolRead + " instead.";
	}

	/**
	 * Returns an error message for unmatched symbol at the end of file.
	 * Indicates the symbol match that was expected.
	 */
	private String unmatchedSymbolAtEOF(char symbolExpected)
	{
		return "ERROR: Unmatched symbol at the end of file. Expected " + symbolExpected + ".";
	}

	/**
	 * Returns an error message for a file that ends with an open /* comment.
	 */
	private String unfinishedComment()
	{
		return "ERROR: File ended before closing comment.";
	}

	/**
	 * Returns a message for a file in which all symbols match.
	 */
	private String allSymbolsMatch()
	{
		return "No errors found. All symbols match.";
	}
}