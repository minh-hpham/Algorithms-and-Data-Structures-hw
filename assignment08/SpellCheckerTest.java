package assignment08;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SpellCheckerTest {
	SpellChecker spellChecker;
	File helloWorldFile;
	List<String> helloWorldWords = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {
		helloWorldFile = new File("hello_world.txt");
		helloWorldWords.add("hello");
		helloWorldWords.add("there");
		helloWorldWords.add("world");
		helloWorldWords.add("nice");
		helloWorldWords.add("to");
		helloWorldWords.add("meet");
		helloWorldWords.add("you");

	}

	@Test
	public void testSpellChecker() {
		// creates a SpellChecker with no words in it
		spellChecker = new SpellChecker();

		// the list of misspelled words should be all of the words in the file
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(7, spellList.size());
		assertTrue(spellList.containsAll(helloWorldWords));
	}

	@Test
	public void testSpellCheckerListOfString() {
		// creates a SpellChecker with all of the helloWorldWords in it
		spellChecker = new SpellChecker(helloWorldWords);

		// should return an empty list because there should be no spelling
		// errors
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(0, spellList.size());
	}

	@Test
	public void testSpellCheckerFile() {
		// creates a SpellChecker with all of the helloWorldWords in it
		File helloWorldWordsFile = new File("helloWorldWords.txt");
		spellChecker = new SpellChecker(helloWorldWordsFile);

		// should return an empty list because there should be no spelling
		// errors
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(0, spellList.size());
	}

	@Test
	public void testAddToDictionary() {
		// creates a SpellChecker with all of the helloWorldWords in it
		File helloWorldWordsFile = new File("helloWorldWords.txt");
		spellChecker = new SpellChecker(helloWorldWordsFile);

		// after removing a word from the dictionary, that word when found in
		// the file will be "misspelled"
		spellChecker.removeFromDictionary("hello");
		spellChecker.removeFromDictionary("nice");
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(2, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));
		assertTrue(spellList.get(1).equals("nice"));

		// when the "misspelled" words are added to the dictionary, they will no
		// longer
		// be "misspelled" when the file is read
		spellChecker.addToDictionary("hello");
		spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(1, spellList.size());
		assertTrue(spellList.get(0).equals("nice"));

		spellChecker.addToDictionary("nice");
		spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(0, spellList.size());
	}

	@Test
	public void testRemoveFromDictionary() {
		// creates a SpellChecker with all of the helloWorldWords in it
		File helloWorldWordsFile = new File("helloWorldWords.txt");
		spellChecker = new SpellChecker(helloWorldWordsFile);

		// should return an empty list because there should be no spelling
		// errors
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(0, spellList.size());

		// after removing a word from the dictionary, that word when found in
		// the file will be "misspelled"
		spellChecker.removeFromDictionary("hello");
		spellList = spellChecker.spellCheck(helloWorldFile);

		assertEquals(1, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));

		// remove a word one more time
		spellChecker.removeFromDictionary("nice");
		spellList = spellChecker.spellCheck(helloWorldFile);

		assertEquals(2, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));
		assertTrue(spellList.get(1).equals("nice"));
	}

	@Test
	public void testRemoveFromDictionaryWordNotInDictionary() {
		// creates a SpellChecker with all of the helloWorldWords in it
		File helloWorldWordsFile = new File("helloWorldWords.txt");
		spellChecker = new SpellChecker(helloWorldWordsFile);

		// after removing a word from the dictionary, that word when found in
		// the file will be "misspelled"
		spellChecker.removeFromDictionary("hello");
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);

		assertEquals(1, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));

		// remove the same word again-- should do nothing
		spellChecker.removeFromDictionary("hello");
		spellList = spellChecker.spellCheck(helloWorldFile);

		assertEquals(1, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));
	}

	@Test
	public void testSpellCheck() {
		// creates a SpellChecker with all of the helloWorldWords in it
		File helloWorldWordsFile = new File("HelloWorldWordsMissingHelloAndNice.txt");
		spellChecker = new SpellChecker(helloWorldWordsFile);

		// should return a list with the two "misspelled" words-- hello and nice
		List<String> spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(2, spellList.size());
		assertTrue(spellList.get(0).equals("hello"));
		assertTrue(spellList.get(1).equals("nice"));
		
		// should be no spelling errors, so return an empty list
		spellChecker = new SpellChecker(new File("helloWorldWords.txt"));
		spellList = spellChecker.spellCheck(helloWorldFile);
		assertEquals(0, spellList.size());
		
	}

}
