package assignment02;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Minh Pham and To Tang
 */
public class LibraryGenericTestString
{
	private LibraryGeneric<String> emptyLibraryString = new LibraryGeneric<String>();
	private LibraryGeneric<String> mediumLibraryString = new LibraryGeneric<String>();

	@Before
	public void setup()
	{
		emptyLibraryString = new LibraryGeneric<String>();
		mediumLibraryString = new LibraryGeneric<String>();
		mediumLibraryString.addAll("Mushroom_Publishing.txt");
	}

	// test a library that uses names (String) to id patrons
	@Test
	public void testAddAllBooksFromAFile()
	{
		LibraryGeneric<String> testAddALLString = new LibraryGeneric<String>();
		int size = testAddALLString.getLibrary().size();
		testAddALLString.addAll("Mushroom_Publishing.txt");
		assertEquals(size + 23, testAddALLString.getLibrary().size());

	}

	@Test
	public void testMediumLibraryAddBook()
	{
		int size = mediumLibraryString.getLibrary().size();
		mediumLibraryString.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, mediumLibraryString.getLibrary().size());
		//assertEquals(size + 1, mediumLibraryString.getLibrary().size());

	}

	@Test
	public void testEmptyLibraryAddBook()
	{
		int size = emptyLibraryString.getLibrary().size();
		emptyLibraryString.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, emptyLibraryString.getLibrary().size());
	}

	@Test
	public void testAddAllBooksFromAList()
	{
		int size = emptyLibraryString.getLibrary().size();
		ArrayList<LibraryBookGeneric<String>> list = new ArrayList<LibraryBookGeneric<String>>();
		list.add(new LibraryBookGeneric<String>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		list.add(new LibraryBookGeneric<String>(9780330351690L, "Jon Krakauer", "Into the Wild"));
		list.add(new LibraryBookGeneric<String>(9780446580342L, "David Baldacci", "Simple Genius"));
		emptyLibraryString.addAll(list);
		assertEquals(size + list.size(), emptyLibraryString.getLibrary().size());
	}
	
	@Test
	public void testAddFileWithWrongFormat_LibraryKeepsSameSize()
	{
		int size = emptyLibraryString.getLibrary().size();
		emptyLibraryString.addAll("Wrong Format.txt");
		assertEquals(size, emptyLibraryString.getLibrary().size());
	}
	@Test
	public void testAddFileDoesNotExist_LibraryKeepsSameSize()
	{
		int size = emptyLibraryString.getLibrary().size();
		emptyLibraryString.addAll("Mushroom_Types.txt");
		assertEquals(size, emptyLibraryString.getLibrary().size());
	}
	@Test
	public void testLookupExistedISBN()
	{
		mediumLibraryString.checkout(9781843191230L, "holder", 01, 1, 2016);
		assertEquals("holder", mediumLibraryString.lookup(9781843191230L));
	}
	@Test
	public void testLookupNotExistedISBN()
	{
		assertNull(mediumLibraryString.lookup(9780883190110L));
	}

	@Test(expected = NullPointerException.class)
	public void testLookupNullISBN()
	{
		mediumLibraryString.lookup(null);
	}

	@Test
	public void testLookupHolderHasNoBook()
	{
		assertEquals(true, new ArrayList<LibraryBookGeneric<String>>().equals(mediumLibraryString.lookup("MinhPham")));
	}

	@Test(expected = NullPointerException.class)
	public void testLookupNullHolder()
	{
		mediumLibraryString.lookup(null);
	}

	@Test
	public void testLookupHolderWithNameHasSomeBook()
	{
		mediumLibraryString.checkout(9781843190363L, "holder", 12, 30, 2014);
		mediumLibraryString.checkout(9781843190394L, "holder", 12, 30, 2014);
		ArrayList<LibraryBookGeneric<String>> bookCheckedOutByHolder = new ArrayList<LibraryBookGeneric<String>>();
		bookCheckedOutByHolder.add(new LibraryBookGeneric<String>(9781843190363L, "Emma Lorant", "Cloner"));
		bookCheckedOutByHolder
				.add(new LibraryBookGeneric<String>(9781843190394L, "Kate Clarke", "The Royal United Hospital"));
		assertTrue(bookCheckedOutByHolder.equals(mediumLibraryString.lookup("holder")));
	}

	@Test
	public void testCheckoutISBNwithNoBook()
	{
		assertEquals(false, mediumLibraryString.checkout(97818431999999L, "holder", 2, 1, 2016));
	}

	@Test
	public void testCheckoutBookCheckedOut()
	{
		mediumLibraryString.checkout(9781843190004l, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(false, mediumLibraryString.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015));
	}

	@Test
	public void testCheckoutValidISBN()
	{
		assertEquals(true, mediumLibraryString.checkout(9781843190936L, "Moyra Caldecott", 01, 01, 2015));
	}

	@Test
	public void testCheckinBookWithValidISBN()
	{
		mediumLibraryString.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(true, mediumLibraryString.checkin(9781843190004L));

	}

//	@Test
//	public void testCheckinWithInvalidISBN()
//	{
//		mediumLibraryString.checkout(9781843190516L, "Moyra Caldecott", 01, 01, 2015);
//		assertEquals(false, mediumLibraryString.checkin(978184L));
//	}

	@Test
	public void testCheckinNoBookWithSpecifiedISBN()
	{
		mediumLibraryString.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015);
		assertEquals(false, mediumLibraryString.checkin(58546599926269L));
	}

	@Test
	public void testCheckinCheckedInBooks()
	{
		assertTrue(mediumLibraryString.checkout(9781843190004L, "Moyra Caldecott", 01, 01, 2015));// checkout
																								// book
		assertTrue(mediumLibraryString.checkin(9781843190004L));// checkin the
																// book
		assertFalse(mediumLibraryString.checkin(9781843190004L));// checkin the
																	// book
																	// again
	}

	@Test
	public void testCheckinHoldersNoBooks()
	{
		assertEquals(false, mediumLibraryString.checkin("Minh Pham"));
	}

	@Test
	public void testCheckinHoldersWithBooks()
	{
		mediumLibraryString.checkout(9781843193319L, "To Tang", 01, 01, 2015);
		mediumLibraryString.checkout(9781843192954L, "To Tang", 01, 01, 2015);
		assertEquals(2, mediumLibraryString.lookup("To Tang").size());
		assertTrue(mediumLibraryString.checkin("To Tang"));
		assertEquals(true, new ArrayList<LibraryBookGeneric<String>>().equals(mediumLibraryString.lookup("To Tang")));
	}

	@Test(expected = NullPointerException.class)
	public void testCheckinNullHolders()
	{
		mediumLibraryString.checkin(null);
	}

	@Test
	public void testGetInventoryList()
	{
		ArrayList<LibraryBookGeneric<String>> orderedISBNList = mediumLibraryString.getInventoryList();
		for (int i = 0; i < orderedISBNList.size(); i++)
		{
			LibraryBookGeneric<String> booki = orderedISBNList.get(i);
			for (int j = i + 1; j < orderedISBNList.size(); j++)
			{
				LibraryBookGeneric<String> bookj = orderedISBNList.get(j);
				assertTrue(booki.getIsbn() <= bookj.getIsbn());
			}
		}
	}

	@Test
	public void testGetOrderedByAuthor()
	{
		ArrayList<LibraryBookGeneric<String>> orderedAuthorList = mediumLibraryString.getOrderedByAuthor();
		for (int i = 0; i < orderedAuthorList.size(); i++)
		{
			LibraryBookGeneric<String> booki = orderedAuthorList.get(i);
			for (int j = i + 1; j < orderedAuthorList.size(); j++)
			{
				LibraryBookGeneric<String> bookj = orderedAuthorList.get(j);
				assertTrue(booki.getAuthor().compareTo(bookj.getAuthor()) <= 0);
				if (booki.getAuthor().compareTo(bookj.getAuthor()) == 0)
				{
					assertTrue(booki.getTitle().compareTo(bookj.getTitle()) <= 0);
				}
			}
		}
	}

	@Test
	public void testGetOverdueList()
	{
		mediumLibraryString.checkout(9781843190028L, "holder", 1, 18, 2015);
		mediumLibraryString.checkout(9781843190875L, "holder", 1, 28, 2015);
		mediumLibraryString.checkout(9781843190998L, "holder", 8, 1, 2016);
		mediumLibraryString.checkout(9781843193319L, "holder", 1, 8, 2016);
		ArrayList<LibraryBookGeneric<String>> overDueBookList = mediumLibraryString.getOverdueList(8, 8, 2015);
		ArrayList<LibraryBookGeneric<String>> reference = new ArrayList<LibraryBookGeneric<String>>();
		reference.add(mediumLibraryString.getByISBN(9781843190028L));
		reference.add(mediumLibraryString.getByISBN(9781843190875L));
		assertEquals(reference, overDueBookList);
	}
}
