package assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * Class representation of a library (a collection of library books).
 * @author To Tang, Minh Pham
 * 
 */
public class LibraryGeneric<Type>
{

	private ArrayList<LibraryBookGeneric<Type>> library;

	public ArrayList<LibraryBookGeneric<Type>> getLibrary()
	{
		return library;
	}

	public LibraryGeneric()
	{
		library = new ArrayList<LibraryBookGeneric<Type>>();
	}

	/**
	 * Add the specified book to the library, assume no duplicates.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be added
	 * @param author
	 *            -- author of the book to be added
	 * @param title
	 *            -- title of the book to be added
	 */
	public void add(long isbn, String author, String title)
	{
		library.add(new LibraryBookGeneric<Type>(isbn, author, title));
	}

	/**
	 * Add the list of library books to the library, assume no duplicates.
	 * 
	 * @param list
	 *            -- list of library books to be added
	 */
	public void addAll(ArrayList<LibraryBookGeneric<Type>> list)
	{
		library.addAll(list);
	}

	/**
	 * Add books specified by the input file. One book per line with ISBN,
	 * author, and title separated by tabs.
	 * 
	 * If file does not exist or format is violated, do nothing.
	 * 
	 * @param filename
	 */
	public void addAll(String filename)
	{
		ArrayList<LibraryBookGeneric<Type>> toBeAdded = new ArrayList<LibraryBookGeneric<Type>>();

		try (Scanner fileIn = new Scanner(new File(filename)))
		{

			int lineNum = 1;

			while (fileIn.hasNextLine())
			{
				String line = fileIn.nextLine();

				try (Scanner lineIn = new Scanner(line))
				{
					lineIn.useDelimiter("\\t");

					if (!lineIn.hasNextLong())
					{
						throw new ParseException("ISBN", lineNum);
					}
					long isbn = lineIn.nextLong();

					if (!lineIn.hasNext())
					{
						throw new ParseException("Author", lineNum);
					}
					String author = lineIn.next();

					if (!lineIn.hasNext())
					{
						throw new ParseException("Title", lineNum);
					}
					String title = lineIn.next();
					toBeAdded.add(new LibraryBookGeneric<Type>(isbn, author, title));
				}
				lineNum++;
			}
		} catch (FileNotFoundException e)
		{
			System.err.println(e.getMessage() + " Nothing added to the library.");
			return;
		} catch (ParseException e)
		{
			System.err.println(e.getLocalizedMessage() + " formatted incorrectly at line " + e.getErrorOffset()
					+ ". Nothing added to the library.");
			return;
		}

		library.addAll(toBeAdded);
	}

	/**
	 * Returns the holder of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns null.
	 * 
	 * @param isbn
	 *            -- ISBN of the book to be looked up
	 */
	public Type lookup(long isbn)
	{
		// FILL IN -- do not return null unless appropriate
		for (LibraryBookGeneric<Type> book : library)
		{
			if (book.getIsbn() == isbn)
				return book.getHolder();
		}
		return null;
	}

	/**
	 * Returns the list of library books checked out to the specified holder.
	 * 
	 * If the specified holder has no books checked out, returns an empty list.
	 * 
	 * @param holder
	 *            -- holder whose checked out books are returned
	 */
	public ArrayList<LibraryBookGeneric<Type>> lookup(Type holder)
	{
		// FILL IN -- do not return null
		ArrayList<LibraryBookGeneric<Type>> list = new ArrayList<>();
		for (LibraryBookGeneric<Type> book : library)
		{
			if (holder.equals(book.getHolder()))
			{
				list.add(book);
			}
		}
		return list;
	}

	/**
	 * Sets the holder and due date of the library book with the specified ISBN.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked out, returns
	 * false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked out
	 * @param holder
	 *            -- new holder of the library book
	 * @param month
	 *            -- month of the new due date of the library book
	 * @param day
	 *            -- day of the new due date of the library book
	 * @param year
	 *            -- year of the new due date of the library book
	 * 
	 */
	public boolean checkout(long isbn, Type holder, int month, int day, int year)
	{
		// FILL IN -- do not return false unless appropriate
		for (LibraryBookGeneric<Type> book : library)
		{
			if (book.getIsbn() == isbn && book.getHolder() == null)
			{
				book.checkOut(holder, new GregorianCalendar(year, month, day));
				return true;
			}
		}
		return false;
	}

	/**
	 * Unsets the holder and due date of the library book.
	 * 
	 * If no book with the specified ISBN is in the library, returns false.
	 * 
	 * If the book with the specified ISBN is already checked in, returns false.
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param isbn
	 *            -- ISBN of the library book to be checked in
	 */
	public boolean checkin(long isbn)
	{
		// FILL IN -- do not return false unless appropriate
		for (LibraryBookGeneric<Type> book : library)
		{
			if (book.getIsbn() == isbn && book.getHolder() != null)
			{
				book.checkIn();
				return true;
			}
		}
		return false;
	}

	/**
	 * Unsets the holder and due date for all library books checked out by the
	 * specified holder.
	 * 
	 * If no books with the specified holder are in the library, returns false;
	 * 
	 * Otherwise, returns true.
	 * 
	 * @param holder
	 *            -- holder of the library books to be checked in
	 */
	public boolean checkin(Type holder)
	{
		// FILL IN -- do not return false unless appropriate
		boolean bookReturned = false;
		for (LibraryBookGeneric<Type> book : library)
		{
			if (holder.equals(book.getHolder()))
			{
				book.checkIn();
				bookReturned = true;
			}
		}
		return bookReturned;
	}

	/**
	 * Returns the list of library books, sorted by ISBN (smallest ISBN first).
	 */
	public ArrayList<LibraryBookGeneric<Type>> getInventoryList()
	{
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByIsbn comparator = new OrderByIsbn();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books, sorted by author
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOrderedByAuthor()
	{
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		libraryCopy.addAll(library);

		OrderByAuthor comparator = new OrderByAuthor();

		sort(libraryCopy, comparator);

		return libraryCopy;
	}

	/**
	 * Returns the list of library books whose due date is older than the input
	 * date. The list is sorted by date (oldest first).
	 *
	 * If no library books are overdue, returns an empty list.
	 */
	public ArrayList<LibraryBookGeneric<Type>> getOverdueList(int month, int day, int year)
	{
		ArrayList<LibraryBookGeneric<Type>> libraryCopy = new ArrayList<LibraryBookGeneric<Type>>();
		GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
		for (LibraryBookGeneric<Type> book : library)
		{
			if (book.getDueDate() != null && dueDate.compareTo(book.getDueDate()) > 0)
			{
				libraryCopy.add(book);
			}
		}
		OrderByDueDate comparator = new OrderByDueDate();
		sort(libraryCopy, comparator);
		return libraryCopy;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 1. Find the smallest
	 * item in the list. 2. Swap the smallest item with the first item in the
	 * list. 3. Now let the list be the remaining unsorted portion (second item
	 * to Nth item) and repeat steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c)
	{
		for (int i = 0; i < list.size() - 1; i++)
		{
			int j, minIndex;
			for (j = i + 1, minIndex = i; j < list.size(); j++)
				if (c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the ISBN.
	 */
	protected class OrderByIsbn implements Comparator<LibraryBookGeneric<Type>>
	{

		/**
		 * Returns a negative value if lhs is smaller than rhs. Returns a
		 * positive value if lhs is larger than rhs. Returns 0 if lhs and rhs
		 * are equal.
		 */
		public int compare(LibraryBookGeneric<Type> lhs, LibraryBookGeneric<Type> rhs)
		{
			return (int) (lhs.getIsbn() - rhs.getIsbn());
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the author,
	 * and book title as a tie-breaker.
	 */
	protected class OrderByAuthor implements Comparator<LibraryBookGeneric<Type>>
	{

		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2)
		{
			if (o1 == o2)
			{
				return 0;
			}
			int result = o1.getAuthor().compareTo(o2.getAuthor());
			if (result == 0)
			{
				result = o1.getTitle().compareTo(o2.getTitle());
			}
			return result;
		}
	}

	/**
	 * Comparator that defines an ordering among library books using the due
	 * date.
	 */
	protected class OrderByDueDate implements Comparator<LibraryBookGeneric<Type>>
	{

		@Override
		public int compare(LibraryBookGeneric<Type> o1, LibraryBookGeneric<Type> o2)
		{
			if (o1 == o2)
			{
				return 0;
			} else if (o1.getDueDate() == null)
			{
				return -1;
			} else if (o2.getDueDate() == null)
			{
				return 1;
			} else
			{
				return o1.getDueDate().compareTo(o2.getDueDate());
			}
		}
	}
	
	/**
	 * for testing purpose helper method
	 */
	public LibraryBookGeneric<Type> getByISBN(long isbn){
		for(LibraryBookGeneric<Type> book: this.library){
			if(book.getIsbn() == isbn){
				return book;
			}
		}
		return null;
	}
}
