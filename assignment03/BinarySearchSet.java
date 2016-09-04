/**
 * 
 */
package assignment03;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A set that provides a total ordering on its elements. The elements are
 * ordered using their natural ordering, or by a Comparator provided at sorted
 * set creation time. Thus, all elements inserted into a sorted set must
 * implement the Comparable interface or be accepted by the specified
 * Comparator. The set's iterator will traverse the set in ascending element
 * order.
 * 
 * @author To Tang and Minh Pham
 * 
 * @param <E>
 *            -- the type of elements maintained by this set
 */
public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E>
{

	private static final int INIT_SIZE = 5;
	private Comparator<? super E> mComparator;
	private E[] array;
	private int count = 0; // count how many elements are stored in the array

	/**
	 * If this constructor is used to create the sorted set, it is assumed that
	 * the elements are ordered using their natural ordering (i.e., E implements
	 * Comparable<? super E>)
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchSet()
	{
		array = (E[]) new Object[INIT_SIZE];
		mComparator = null;
	}

	/**
	 * If this constructor is used to create the sorted set, it is assumed that
	 * the elements are ordered using the provided comparator.
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchSet(Comparator<? super E> comparator)
	{
		array = (E[]) new Object[INIT_SIZE];
		mComparator = comparator;
	}

	/**
	 * @return The comparator used to order the elements in this set, or null if
	 *         this set uses the natural ordering of its elements (i.e., uses
	 *         Comparable).
	 */
	@Override
	public Comparator<? super E> comparator()
	{
		return mComparator;
	}

	/**
	 * @return the first (lowest, smallest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E first() throws NoSuchElementException
	{
		if (count == 0)
		{
			throw new NoSuchElementException();
		}
		else
			return array[0];
	}

	/**
	 * @return the last (highest, largest) element currently in this set
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public E last() throws NoSuchElementException
	{
		if (count == 0)
		{
			throw new NoSuchElementException();
		}
		return array[count - 1];
	}

	/**
	 * Adds the specified element to this set if it is not already present and
	 * not set to null.
	 * 
	 * @param o
	 *            -- element to be added to this set
	 * @return true if this set did not already contain the specified element
	 */
	@Override
	public boolean add(E element)
	{
		if (element == null)
		{
			return false;
		}
		if (count == array.length)
		{
			growArray();
		}
		int indexOfAddingElement = binarySearch(array, element, 0, count - 1, false);
		if (indexOfAddingElement < 0)
		{
			return false;
		}
		else
		{
			for (int i = count; i > indexOfAddingElement; i--)
			{
				array[i] = array[i - 1];
			}
			array[indexOfAddingElement] = element;
			count++;
			return true;
		}
	}

	private void growArray()
	{
		E[] temp = Arrays.copyOf(array, count * 2);
		array = temp;
	}

	/**
	 * Adds all of the elements in the specified collection to this set if they
	 * are not already present and not set to null.
	 * 
	 * @param c
	 *            -- collection containing elements to be added to this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean addAll(Collection<? extends E> elements)
	{
		int size = count;
		for (E element : elements)
		{
			add(element);
		}
		return !(count == size);
	}

	/**
	 * Removes all of the elements from this set. The set will be empty after
	 * this call returns.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void clear()
	{
		array = (E[]) new Object[INIT_SIZE];
		count = 0;
	}

	/**
	 * @param o
	 *            -- element whose presence in this set is to be tested
	 * @return true if this set contains the specified element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object element)
	{
		return (binarySearch(array, (E) element, 0, count - 1, true) >= 0);
	}

	/**
	 * @param c
	 *            -- collection to be checked for containment in this set
	 * @return true if this set contains all of the elements of the specified
	 *         collection
	 */
	@Override
	public boolean containsAll(Collection<?> elements)
	{
		for (Object element : elements)
		{
			if (contains(element) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * @return true if this set contains no elements
	 */
	@Override
	public boolean isEmpty()
	{
		return (count == 0);
	}

	/**
	 * @return an iterator over the elements in this set, where the elements are
	 *         returned in sorted (ascending) order.
	 * @throws IllegalStateException
	 *             When calling remove before calling next first
	 */
	@Override
	public Iterator<E> iterator() throws IllegalStateException
	{
		return new Iterator<E>()
		{
			int index = 0;

			@Override
			public boolean hasNext()
			{
				return (index < count);

			}

			@Override
			public E next()
			{
				return array[index++];
			}

			@Override
			public void remove()
			{
				if (index == 0)
				{
					throw new IllegalStateException();
				}
				else
				{
					BinarySearchSet.this.remove(array[index - 1]);

				}
			}
		};
	}

	/**
	 * Removes the specified element from this set if it is present.
	 * 
	 * @param o
	 *            -- object to be removed from this set, if present
	 * @return true if this set contained the specified element
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object input)
	{
		if (input == null)
		{
			return false;
		}
		E element = (E) input;
		int index = binarySearch(array, element, 0, count - 1, true);
		if (index < 0)
		{
			return false;
		}
		else
		{
			for (int i = index; i < count - 1; i++)
			{
				array[i] = array[i + 1];
			}
			count--;
			return true;
		}
	}

	/**
	 * Removes from this set all of its elements that are contained in the
	 * specified collection.
	 * 
	 * @param c
	 *            -- collection containing elements to be removed from this set
	 * @return true if this set changed as a result of the call
	 */
	@Override
	public boolean removeAll(Collection<?> elements)
	{
		int size = count;
		for (Object element : elements)
		{
			remove(element);
		}
		return !(count == size);
	}

	/**
	 * @return the number of elements in this set
	 */
	@Override
	public int size()
	{
		return count;
	}

	/**
	 * @return an array containing all of the elements in this set, in sorted
	 *         (ascending) order.
	 */
	@Override
	public Object[] toArray()
	{
		E[] result = Arrays.copyOf(array, count);
		return result;
	}

	/**
	 * Compares its two arguments for order. Returns a negative integer, zero,
	 * or a positive integer as the first argument is less than, equal to, or
	 * greater than the second. If the objects be compared are not in natural
	 * order, use the compare() method of Comparator. Else, use compareTo() of
	 * Comparable
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int myCompare(E left, E right)
	{
		if (mComparator != null)
		{
			return mComparator.compare(left, right);
		}
		return ((Comparable<E>) left).compareTo(right);
	}

	/**
	 * This binarySearch is used either to find the index of an element in the
	 * BinarySearchSet ( If it is existed in the set, return its index. Else
	 * return -1) or to find the index of where a new element should be added. (
	 * If it is existed in the set, return -1. Else return its-should-be-index)
	 * 
	 * @param arr
	 * @param element
	 * @param lowIndex
	 * @param highIndex
	 * @param findPosition
	 *            True if using this binarySearch for finding the index of the
	 *            existed element in BinarySearchSet. False otherwise
	 */
	public int binarySearch(E[] arr, E element, int lowIndex, int highIndex, boolean findPosition)
	{
		// findPosition is true if need to find the position of the element that
		// already exist in the array, for remove method.
		if (lowIndex > highIndex && findPosition)
		{
			return -1; // element doesn't exist
		}
		else if (lowIndex > highIndex)
		{
			return lowIndex;
		}
		else
		{
			int midIndex = (lowIndex + highIndex) / 2;
			if (myCompare(element, arr[midIndex]) < 0)
			{
				highIndex = midIndex - 1;
			}
			else if (myCompare(element, arr[midIndex]) > 0)
			{
				lowIndex = midIndex + 1;
			}
			else if (findPosition)
			{
				return midIndex; // element found, return index in array
			}
			else
			{
				return -1;
			}
			return binarySearch(arr, element, lowIndex, highIndex, findPosition);
		}
	}

}
