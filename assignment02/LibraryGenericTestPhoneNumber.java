package assignment02;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author To Tang, Minh Pham
 */

public class LibraryGenericTestPhoneNumber
{
	private LibraryGeneric<PhoneNumber> emptyLibraryPhone = new LibraryGeneric<PhoneNumber>();
	private LibraryGeneric<PhoneNumber> mediumLibraryPhone = new LibraryGeneric<PhoneNumber>();
	PhoneNumber patron2 = new PhoneNumber("801.555.1234");
	PhoneNumber patron1 = new PhoneNumber("801.888.1234");

	@Before
	public void setup()
	{
		emptyLibraryPhone = new LibraryGeneric<PhoneNumber>();
		mediumLibraryPhone = new LibraryGeneric<PhoneNumber>();
		mediumLibraryPhone.addAll("Mushroom_Publishing.txt");
	}

	// test a library that uses phone number to id patrons
	@Test
	public void testAddAllBooksFromAFile()
	{
		LibraryGeneric<PhoneNumber> testAddALLString = new LibraryGeneric<PhoneNumber>();
		int size = testAddALLString.getLibrary().size();
		testAddALLString.addAll("Mushroom_Publishing.txt");
		assertEquals(size + 23, testAddALLString.getLibrary().size());

	}
	@Test
	public void testAddFileWithWrongFormat_LibraryKeepsSameSize()
	{
		int size = emptyLibraryPhone.getLibrary().size();
		emptyLibraryPhone.addAll("Wrong Format.txt");
		assertEquals(size, emptyLibraryPhone.getLibrary().size());
	}
	@Test
	public void testAddFileDoesNotExist_LibraryKeepsSameSize()
	{
		int size = emptyLibraryPhone.getLibrary().size();
		emptyLibraryPhone.addAll("Mushroom_Types.txt");
		assertEquals(size, emptyLibraryPhone.getLibrary().size());
	}
	@Test
	public void testMediumLibraryAddBook()
	{
		int size = mediumLibraryPhone.getLibrary().size();
		mediumLibraryPhone.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, mediumLibraryPhone.getLibrary().size());
		//assertEquals(size + 1, mediumLibraryPhone.getLibrary().size());

	}

	@Test
	public void testEmptyLibraryAddBook()
	{
		int size = emptyLibraryPhone.getLibrary().size();
		emptyLibraryPhone.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
		assertEquals(size + 1, emptyLibraryPhone.getLibrary().size());
	}

	@Test
	public void testAddAllBooksFromAList()
	{
		int size = emptyLibraryPhone.getLibrary().size();
		ArrayList<LibraryBookGeneric<PhoneNumber>> list = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		list.add(new LibraryBookGeneric<PhoneNumber>(9780374292799L, "Thomas L. Friedman", "The World is Flat"));
		list.add(new LibraryBookGeneric<PhoneNumber>(9780330351690L, "Jon Krakauer", "Into the Wild"));
		list.add(new LibraryBookGeneric<PhoneNumber>(9780446580342L, "David Baldacci", "Simple Genius"));
		emptyLibraryPhone.addAll(list);
		assertEquals(size + list.size(), emptyLibraryPhone.getLibrary().size());
	}

	@Test
	public void testLookupExistedISBN()
	{
		assertNull(mediumLibraryPhone.lookup(9781843190110L));
		mediumLibraryPhone.checkout(9781843190110L, patron2, 01, 1, 2016);
		assertEquals(patron2, mediumLibraryPhone.lookup(9781843190110L));
	}

	@Test
	public void testLookupNotExistedISBN()
	{
		assertNull(mediumLibraryPhone.lookup(9780883190110L));
	}

	@Test(expected = NullPointerException.class)
	public void testLookupNullISBN()
	{
		mediumLibraryPhone.lookup(null);
	}

	@Test
	public void testLookupHolderHasNoBook()
	{
		assertEquals(true, new ArrayList<LibraryBookGeneric<PhoneNumber>>().equals(mediumLibraryPhone.lookup(patron2)));
	}

	@Test(expected = NullPointerException.class)
	public void testLookupNullHolder()
	{
		mediumLibraryPhone.lookup(null);
	}

	@Test
	public void testLookupHolderHasSomeBook()
	{
		mediumLibraryPhone.checkout(9781843190363L, patron2, 12, 30, 2014);
		mediumLibraryPhone.checkout(9781843190394L, patron2, 12, 30, 2014);
		ArrayList<LibraryBookGeneric<PhoneNumber>> bookCheckedOutByHolder = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		bookCheckedOutByHolder.add(new LibraryBookGeneric<PhoneNumber>(9781843190363L, "Emma Lorant", "Cloner"));
		bookCheckedOutByHolder
				.add(new LibraryBookGeneric<PhoneNumber>(9781843190394L, "Kate Clarke", "The Royal United Hospital"));
		assertTrue(bookCheckedOutByHolder.equals(mediumLibraryPhone.lookup(patron2)));
	}

	@Test
	public void testCheckoutISBNwithNoBook()
	{
		assertEquals(false, mediumLibraryPhone.checkout(97818431999999L, patron2, 2, 1, 2016));
	}

	@Test
	public void testCheckoutBookCheckedOut()
	{
		mediumLibraryPhone.checkout(9781843190004l, patron2, 01, 01, 2015);
		assertEquals(false, mediumLibraryPhone.checkout(9781843190004L, patron1, 01, 01, 2015));
	}

	@Test
	public void testCheckoutValidISBN()
	{
		assertEquals(true, mediumLibraryPhone.checkout(9781843190936L, patron2, 01, 01, 2015));
	}

	@Test
	public void testCheckinWithValidISBN()
	{
		mediumLibraryPhone.checkout(9781843190004L, patron2, 01, 01, 2015);
		assertEquals(true, mediumLibraryPhone.checkin(9781843190004L));

	}

	@Test
	public void testCheckinWithInvalidISBN()
	{
		mediumLibraryPhone.checkout(9781843190516L, patron2, 01, 01, 2015);
		assertEquals(false, mediumLibraryPhone.checkin(978184L));
	}

	@Test
	public void testCheckinWithNoBookWithThisISBN()
	{
		mediumLibraryPhone.checkout(9781843190004L, patron2, 01, 01, 2015);
		assertEquals(false, mediumLibraryPhone.checkin(58546599926269L));
	}

	@Test
	public void testCheckinCheckedInBooks()
	{
		assertTrue(mediumLibraryPhone.checkout(9781843190004L, patron2, 01, 01, 2015));// checkout
																						// a
																						// book
		assertTrue(mediumLibraryPhone.checkin(9781843190004L));// checkin the
																// book
		assertFalse(mediumLibraryPhone.checkin(9781843190004L));// checkin the
																// book again
	}

	@Test
	public void testCheckinHoldersNoBooks()
	{
		assertEquals(false, mediumLibraryPhone.checkin(patron2));
	}

	@Test
	public void testCheckinHoldersWithBooks()
	{
		mediumLibraryPhone.checkout(9781843193319L, patron2, 01, 01, 2015);
		mediumLibraryPhone.checkout(9781843192954L, patron2, 01, 01, 2015);
		assertEquals(2, mediumLibraryPhone.lookup(patron2).size());
		assertTrue(mediumLibraryPhone.checkin(patron2));
		assertEquals(true, new ArrayList<LibraryBookGeneric<PhoneNumber>>().equals(mediumLibraryPhone.lookup(patron2)));
	}

	@Test(expected = NullPointerException.class)
	public void testCheckinNullHolders()
	{
		mediumLibraryPhone.checkin(null);
	}

	@Test
	public void testGetInventoryList()
	{
		ArrayList<LibraryBookGeneric<PhoneNumber>> orderedISBNList = mediumLibraryPhone.getInventoryList();
		for (int i = 0; i < orderedISBNList.size(); i++)
		{
			LibraryBookGeneric<PhoneNumber> booki = orderedISBNList.get(i);
			for (int j = i + 1; j < orderedISBNList.size(); j++)
			{
				LibraryBookGeneric<PhoneNumber> bookj = orderedISBNList.get(j);
				assertTrue(booki.getIsbn() <= bookj.getIsbn());
			}
		}
	}

	@Test
	public void testGetOrderedByAuthor()
	{
		ArrayList<LibraryBookGeneric<PhoneNumber>> orderedAuthorList = mediumLibraryPhone.getOrderedByAuthor();
		for (int i = 0; i < orderedAuthorList.size(); i++)
		{
			LibraryBookGeneric<PhoneNumber> booki = orderedAuthorList.get(i);
			for (int j = i + 1; j < orderedAuthorList.size(); j++)
			{
				LibraryBookGeneric<PhoneNumber> bookj = orderedAuthorList.get(j);
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
		mediumLibraryPhone.checkout(9781843190028L, patron2, 1, 18, 2015);
		mediumLibraryPhone.checkout(9781843190875L, patron2, 1, 28, 2015);
		mediumLibraryPhone.checkout(9781843190998L, patron2, 8, 1, 2016);
		mediumLibraryPhone.checkout(9781843193319L, patron2, 1, 8, 2016);
		ArrayList<LibraryBookGeneric<PhoneNumber>> overDueBookList = mediumLibraryPhone.getOverdueList(8, 8, 2015);
		ArrayList<LibraryBookGeneric<PhoneNumber>> reference = new ArrayList<LibraryBookGeneric<PhoneNumber>>();
		reference.add(mediumLibraryPhone.getByISBN(9781843190028L));
		reference.add(mediumLibraryPhone.getByISBN(9781843190875L));
		assertEquals(reference, overDueBookList);
	}
}
