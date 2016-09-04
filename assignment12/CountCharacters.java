package assignment12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountCharacters
{

	public static void main(String[] args) throws FileNotFoundException
	{
		System.out.println(countUniqueCharacters(stringOfFile(new File("original.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("original2.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("original3.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("alphabet.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("lotsOfAs.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("aristotle.txt"))));
		System.out.println(countUniqueCharacters(stringOfFile(new File("random.txt"))));

	}
	public static int countUniqueCharacters(String input) {
	    boolean[] isItThere = new boolean[Character.MAX_VALUE];
	    for (int i = 0; i < input.length(); i++) {
	        isItThere[input.charAt(i)] = true;
	    }

	    int count = 0;
	    for (int i = 0; i < isItThere.length; i++) {
	        if (isItThere[i] == true){
	            count++;
	        }
	    }

	    return count;
	}
	 private static String stringOfFile(File f1) throws FileNotFoundException
	    {
	        String original = "";
	        Scanner s = new Scanner(f1);
	        while (s.hasNextLine())
	        {
	            original += s.nextLine();
	        }
			return original;

	        
	    }

}
