package assignment02;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *  * @author To Tang and Minh Pham
 */
public class BookTest {
	
	Book book1,book2,book3,book4,book5,book6;

	@Before
	public void setUp() {
		book1 = new Book (123L, "Mary Jane", "Story Book");
		book2 = new Book (123L, "Mary Jane", "Story Book");
		book3 = new Book (123L, "Mary Lane", "Story Book");
		book4 = new Book (123L, "Mary Jane", "Funny Fiction");
		book5 = new Book (456L, "Mary Jane", "Story Book");
		book6 = new Book (0L,null,null);
	}

	@Test
	public void testEqualsSameBook() {
		assertTrue (book1.equals(book2));
	}
	
	@Test
	public void testEqualsDifferentAuthor() {
		assertFalse (book1.equals(book3));
	}
	
	@Test
	public void testEqualsDifferentTitle() {
		assertFalse (book1.equals(book4));
	}
	
	@Test
	public void testEqualsDifferentISBN() {
		assertFalse (book1.equals(book5));
	}
	
	@Test
	public void testEqualsNullBook() {
		assertFalse (book1.equals(book6));
	}
}
