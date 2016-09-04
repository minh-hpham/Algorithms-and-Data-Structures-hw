package assignment07;

import java.util.Iterator;
import java.util.NoSuchElementException;

import assignment06.List;

/**
 * Class for a DoublyLinkedList. Creates a linked-list that can be accessed/traversed from
 * both the front and the back of the list.
 * 
 * @author Hannah Potter and Minh Pham
 *
 * @param <E>
 */
public class DoublyLinkedList<E> implements List<E>, Iterable<E> 
{
	/**
	 * Number of elements in the DoublyLinkedList.
	 */
	private int size;
	
	/**
	 * The front/first/head Node of the DoublyLinkedList.
	 */
	private Node head;
	
	/**
	 * The back/last/tail Node of the DoublyLinkedList.
	 */
	private Node tail;

	/**
	 * Creates an empty DoublyLinkedList. 
	 */
	public DoublyLinkedList()
	{
		head = new Node(null);
		tail = new Node(null);
		size = 0;
	}

	/**
	 * Helper class that creates the Node of which the DoublyLinkedList will be made.
	 * Keeps track of the Node before and after this Node and the element in this Node.
	 */
	private class Node
	{
		/**
		 * The element that will be held in the Node.
		 */
		E item;
		
		/**
		 * The Node after this Node.
		 */
		Node next;
		
		/**
		 * The Node before this Node.
		 */
		Node prev;

		/**
		 * Creates a Node that holds an item. There is no Node before or after this Node (both set to null).
		 * 
		 * @param item the element being held in this Node
		 */
		public Node(E item) 
		{
			this(item, null, null);
		}

		/**
		 * Creates a Node that holds an item and the Nodes before and after this Node.
		 * 
		 * @param item the element being held in this Node
		 * @param next the Node after this Node
		 * @param prev the Node before this Node
		 */
		public Node(E item, Node next, Node prev) 
		{
			this.item = item;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * Inserts the specified element at the beginning of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addFirst(E element) 
	{
		// Inserting the element into an empty DoublyLinkedList
		if (size == 0) 
		{
			Node node = new Node(element, null, null);
			head = node;
			tail = node;
		}

		else 
		{
			Node node = new Node(element, head, null);
			head.prev = node;
			head = node;
		}
		// Increase the size of the DoublyLinkedList
		size++;

	}

	/**
	 * Inserts the specified element at the end of the list. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public void addLast(E element)
	{
		// If inserting into an empty DoublyLinkedList
		if (size == 0) 
		{
			Node node = new Node(element, null, null);
			head = node;
			tail = node;
		}
		else 
		{
			Node node = new Node(element, null, tail);
			tail.next = node;
			tail = node;
		}
		// Increase the size of the DoublyLinkedList
		size++;
	}

	/**
	 * Inserts the specified element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index > size()) O(N) for a doubly-linked list.
	 */
	@Override
	public void add(int index, E element) throws IndexOutOfBoundsException
	{
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if (isEmpty() || index == 0)
		{
			addFirst(element);
		}
		else if (index == size)
		{
			addLast(element);
		}
		else 
		{
			Node before = getNodeAt(index - 1);
			Node addedNode = new Node(element, before.next, before);
			addedNode.next.prev = addedNode;
			addedNode.prev.next = addedNode;
			size++;
		}
	}

	/**
	 * Gets the node at a particular index, starting from head or tail, depends
	 * on which one is closest. Only use this method with available index
	 * 
	 * @param index
	 *            the index of the node we want to return
	 * @return the node at the specified index
	 */
	private Node getNodeAt(int index)
	{
		// We're going to begin from the end of the list closest to index
		int mid = size / 2;
		Node node = null;

		// Look from head in (index in front half of the DoublyLinkedList)
		if (index <= mid) 
		{
			node = head;
			for (int i = 0; i < index; i++)
			{
				node = node.next;
			}
		} 
		// Look from tail in (index in the back half of the DoublyLinkedList)
		else 
		{
			node = tail;
			for (int i = size - 1; i > index; i--)
			{
				node = node.prev;
			}
		}

		return node;
	}

	/**
	 * Returns the first element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getFirst() throws NoSuchElementException
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		return head.item;
	}

	/**
	 * Returns the last element in the list. Throws NoSuchElementException if
	 * the list is empty. O(1) for a doubly-linked list.
	 */
	@Override
	public E getLast() throws NoSuchElementException 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		return tail.item;
	}

	/**
	 * Returns the element at the specified position in the list. Throws
	 * IndexOutOfBoundsException if index is out of range (index < 0 || index >=
	 * size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size) 
		{
			throw new IndexOutOfBoundsException();
		}
		return getNodeAt(index).item;
	}

	/**
	 * Removes and returns the first element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeFirst() throws NoSuchElementException 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		E removedElement = getNodeAt(0).item;
		
		// If removing the only element in the DoublyLinkedList
		if (size == 1) 
		{
			head = new Node(null);
			tail = new Node(null);
		}
		else 
		{
			head = head.next;
			head.prev = null;
		}
		
		size--;
		
		return removedElement;
	}

	/**
	 * Removes and returns the last element from the list. Throws
	 * NoSuchElementException if the list is empty. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public E removeLast() throws NoSuchElementException 
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();
		}
		
		E removedElement = getNodeAt(size - 1).item;

		// If removing the only element in the DoublyLinkedList
		if (size == 1)
		{
			head = new Node(null);
			tail = new Node(null);
		}
		else
		{
			tail = tail.prev;
			tail.next = null;
		}
		
		size--;
		
		return removedElement;
	}

	/**
	 * Returns the size of the DoublyLinkedList.
	 * @return
	 */
	public int getSize()
	{
		return size;
	}

	/**
	 * Removes and returns the element at the specified position in the list.
	 * Throws IndexOutOfBoundsException if index is out of range (index < 0 ||
	 * index >= size()) O(N) for a doubly-linked list.
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		E removedElement;
		
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		else if (index == 0)
		{
			return removeFirst();
		}
		else if (index == size - 1)
		{
			return removeLast();
		}
		else 
		{
			Node current = getNodeAt(index);

			removedElement = current.item;

			current.next.prev = current.prev;
			current.prev.next = current.next;

			size--;
		}

		return removedElement;
	}

	/**
	 * Returns the index of the first occurrence of the specified element in the
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int indexOf(E element)
	{
		// Looking from head in
		Node indexOf = head;
		
		int index = 0;
		
		while (index < size) 
		{
			if (indexOf.item.equals(element))
			{
				return index;
			}
			indexOf = indexOf.next;
			index++;
		}
		
		// If the element isn't found
		return -1;
	}

	/**
	 * Returns the index of the last occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. O(N) for a
	 * doubly-linked list.
	 */
	@Override
	public int lastIndexOf(E element) 
	{
		// Look from tail in
		Node indexOf = tail;
		
		int index = size - 1;
		
		while (index >= 0)
		{
			if (indexOf.item.equals(element))
			{
				return index;
			}
			indexOf = indexOf.prev;
			index--;
		}
		
		// If the element isn't found
		return -1;
	}

	/**
	 * Returns the number of elements in this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns true if this collection contains no elements. O(1) for a
	 * doubly-linked list.
	 */
	@Override
	public boolean isEmpty() 
	{
		return size == 0;
	}

	/**
	 * Removes all of the elements from this list. O(1) for a doubly-linked
	 * list.
	 */
	@Override
	public void clear()
	{
		head = new Node(null);
		tail = new Node(null);
		size = 0;
	}

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element). O(N) for a doubly-linked list.
	 */
	@Override
	public Object[] toArray() 
	{
		E[] array = (E[]) new Object[size];
		
		for (int i = 0; i < size; i++) 
		{
			array[i] = this.get(i);
		}
		
		return array;
	}

	/**
	 * Returns the Iterator for the DoublyLinkedList.
	 */
	@Override
	public Iterator<E> iterator() throws IllegalStateException
	{
		return new Iterator<E>() 
		{
			/**
			 * The current index of the Iterator.
			 */
			int currentIndex = -1;
			
			/**
			 * Keeps track of whether or not the next method has been called.
			 */
			boolean nextIsCalled = false;
			
			/**
			 * Keeps track of whether or not the remove method has been called.
			 */
			boolean removeIsCalled = false;

			@Override
			public boolean hasNext()
			{
				if (size == 0)
				{
					return false;
				}
				
				return currentIndex < size;
			}

			@Override
			public E next()
			{
				currentIndex++;
				
				if (hasNext() == false)
				{
					throw new NoSuchElementException();
				}
				
				nextIsCalled = true;
				removeIsCalled = false;
				
				return getNodeAt(currentIndex).item;
			}

			@Override
			public void remove() 
			{
				if (nextIsCalled == false || removeIsCalled == true)
				{
					throw new IllegalStateException();
				}
				
				DoublyLinkedList.this.remove(currentIndex);
				
				currentIndex--;
				nextIsCalled = !nextIsCalled;
				removeIsCalled = !removeIsCalled;
			}
		};
	}

}
