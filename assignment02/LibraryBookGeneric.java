package assignment02;

import java.util.GregorianCalendar;

/**
 * Class representation of a library book containing the library book's holder and due date that can be changed when
 * books are checked in or checked out. Type of book's holder is generic
 * @author Minh Pham and To Tang
 */
public class LibraryBookGeneric<Type> extends Book
{

	public LibraryBookGeneric(long isbn, String author, String title)
	{
		super(isbn, author, title);

	}

	private Type bookHolder;
	
	private GregorianCalendar dueDate;

	/**
	 * 
	 * @return Holder
	 */
	public Type getHolder()
	{
		return bookHolder;
	}
	/**
	 * 
	 * @return dueDate
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}

	/**
	 * Set name and date for holder and dueDate
	 * @param holder
	 * @param date
	 */
	public void checkOut(Type holder, GregorianCalendar date)
	{
		// checkedIn=false;
		this.bookHolder = holder;
		this.dueDate = date;
	}
	/**
	 * If a library book is checked in, its holder and due date should be set to null
	 */
	public void checkIn()
	{
		// checkedIn=true;
		this.bookHolder = null;
		this.dueDate = null;
	}

}
