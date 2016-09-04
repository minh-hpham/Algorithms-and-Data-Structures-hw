package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Minh Pham and To Tang
 */
public class LibraryTestModified
{

	private Library emptyLibrary = new Library();

	private Library mediumLibrary = new Library();

	@Before
	public void setup(){
		emptyLibrary = new Library();
		mediumLibrary = new Library();
		mediumLibrary.addAll("Mushroom_Publishing.txt");
	}
	
	@Test
	public void testAddAllFromAFile()
	{
		Library testAddALLString = new Library();
		int size = testAddALLString.getLibrary().size();
		testAddALLString.addAll("Mushroom_Publishing.txt");
		assertEquals(size+23, testAddALLString.getLibrary().size());

	}
	@Test
	public void testAddFileWithWrongFormat_LibraryKeepsSameSize()
	{
		int size = emptyLibrary.getLibrary().size();
		emptyLibrary.addAll("Wrong Format.txt");
		assertEquals(size, emptyLibrary.getLibrary().size());
	}
	@Test
	public void testAddFileDoesNotExist_LibraryKeepsSameSize()
	{
		int size = emptyLibrary.getLibrary().size();
		emptyLibrary.addAll("Mushroom_Types.txt");
		assertEquals(size, emptyLibrary.getLibrary().size());
	}
	@Test
	public void testMediumLibraryAddBook()
	{
		int size = mediumLibrary.getLibrary().size();
		mediumLibrary.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, mediumLibrary.getLibrary().size());
		assertEquals(size + 1, mediumLibrary.getLibrary().size());

	}
	
	@Test
	public void testEmptyLibraryAddBook()
	{
		int size = emptyLibrary.getLibrary().size();
		emptyLibrary.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, emptyLibrary.getLibrary().size());
	}

	@Test
	public void testAddAllBooksFromAList()
	{
		int size = emptyLibrary.getLibrary().size();
		ArrayList<LibraryBook> list = new ArrayList<LibraryBook>();
		list.add(new LibraryBook(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		list.add(new LibraryBook(9780330351690L, "Jon Krakauer", "Into the Wild"));
		list.add(new LibraryBook(9780446580342L, "David Baldacci", "Simple Genius"));
		emptyLibrary.addAll(list);
		assertEquals(size + list.size(), emptyLibrary.getLibrary().size());
	}

	@Test
	public void testLookupExistedISBN()
	{
		assertNull(mediumLibrary.lookup(9781843190110L));
		mediumLibrary.checkout(9781843190110L, "holder", 01, 1, 2016);
		assertEquals("holder",mediumLibrary.lookup(9781843190110L));
	}

	@Test
	public void testLookupNotExistISBN()
	{
		assertNull(mediumLibrary.lookup(9780883190110L));
	}

	@Test (expected=NullPointerException.class)
	public void testLookupNullISBN()
	{
		mediumLibrary.lookup(null);
	}
	
	@Test
	public void testLookupHolderHasNoBook()
	{		
		assertEquals(true, new ArrayList<LibraryBook>().equals(mediumLibrary.lookup("MinhPham")));
	}
	
	@Test(expected = NullPointerException.class)
	public void testLookupNullHolder()
	{		
		mediumLibrary.lookup(null);
	}
	
	@Test
	public void testLookupHolderHasSomeBook()
	{		
		mediumLibrary.checkout(9781843190363L,"holder", 12, 30, 2014);
		mediumLibrary.checkout(9781843190394L,"holder", 12, 30, 2014);
		ArrayList<LibraryBook> bookCheckedOutByHolder = new ArrayList<LibraryBook>();
		bookCheckedOutByHolder.add(new LibraryBook(9781843190363L,"Emma Lorant", "Cloner"));
		bookCheckedOutByHolder.add(new LibraryBook(9781843190394L,"Kate Clarke", "The Royal United Hospital"));
		assertTrue(bookCheckedOutByHolder.equals(mediumLibrary.lookup("holder")));
	}

	@Test
	public void testCheckoutISBNwithNoBook()
	{
		assertEquals(false, mediumLibrary.checkout(97818431999999L,"holder",2,1,2016));
	}

	@Test
	public void testCheckoutBookCheckedOut()
	{
		mediumLibrary.checkout(9781843190004l, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(false, mediumLibrary.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015));
	}

	@Test
	public void testCheckoutBookWithSpecificedISBN()
	{
		assertEquals(true, mediumLibrary.checkout(9781843190936L, "Moyra Caldecott", 01, 01, 2015));
	}

	@Test
	public void testCheckinWithValidISBN()
	{
		mediumLibrary.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(true, mediumLibrary.checkin(9781843190004L));

	}

	@Test
	public void testCheckinWithNotAvailableISBN()
	{
		mediumLibrary.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(false, mediumLibrary.checkin(58546599926269L));
	}
	
	@Test
	public void testCheckinCheckedInBooks()
	{
		assertTrue(mediumLibrary.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015));//checkout a book
		assertTrue(mediumLibrary.checkin(9781843190004L));//checkin the book
		assertFalse(mediumLibrary.checkin(9781843190004L));//checkin the book again
	}

	@Test
	public void testCheckinHoldersNoBooks()
	{
		assertEquals(false, mediumLibrary.checkin("Minh Pham"));
	}

	@Test
	public void testCheckinHoldersWithBooks()
	{
		mediumLibrary.checkout(9781843193319L, "To Tang", 01, 01, 2015);
		mediumLibrary.checkout(9781843192954L, "To Tang", 01, 01, 2015);
		assertEquals(2, mediumLibrary.lookup("To Tang").size());
		assertTrue(mediumLibrary.checkin("To Tang"));
		assertEquals(true, new ArrayList<LibraryBook>().equals(mediumLibrary.lookup("To Tang")));
	}

	@Test(expected = NullPointerException.class)
	public void testCheckinNullHolders()
	{
		mediumLibrary.checkin(null);
	}
}
