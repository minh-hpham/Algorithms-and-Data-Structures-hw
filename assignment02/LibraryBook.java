package assignment02;
import java.util.GregorianCalendar;
/**
 * Class representation of a library book containing the library book's holder (a String) and due date that can be changed when
 * books are checked in or checked out
 * @author Minh Pham and To Tang
 */
public class LibraryBook extends Book
{
	private String holder;
	private GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title)
	{
		super(isbn, author, title);
		
	}
	/**
	 * 
	 * @return the Holder
	 */
	public String getHolder()
	{
		return holder;		
	}
	/**
	 * 
	 * @return the DueDate
	 */
	public GregorianCalendar getDueDate()
	{
		return dueDate;
		
	}
	/**
	 * If a library book is checked in, its holder and due date should be set to null
	 */
	public void checkIn()
	{
		holder = null;
		dueDate = null;
	}
	/**
	 * Set name and date for holder and dueDate
	 * @param holder
	 * @param date
	 */
	public void checkOut (String holder, GregorianCalendar date){
		this.holder=holder;
		this.dueDate=date;
	}
}
