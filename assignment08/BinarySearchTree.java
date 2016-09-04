package assignment08;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T>
{
	BinarySearchNode<T> root;
	int size;

	public BinarySearchTree()
	{
		root = null;
		size = 0;
	}

	/**
	 * Ensures that this set contains the specified item.
	 * 
	 * @param item
	 *            - the item whose presence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually inserted); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean add(T item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		}
		if (root == null)
		{
			root = new BinarySearchNode<T>(item);
			size++;
			return true;
		} else
		{
			return insertRecursive(item, root);
		}

	}

	private boolean insertRecursive(T item, BinarySearchNode<T> node)
	{
		if (item.equals(node.getData()))
		{
			return false;
		}

		else if (item.compareTo(node.getData()) < 0)
		{
			if (node.getLeft() == null)
			{
				node.setLeft(new BinarySearchNode<T>(item));
				size++;
				return true;
			} else
			{
				return insertRecursive(item, node.getLeft());
			}
		} else
		{
			if (node.getRight() == null)
			{
				node.setRight(new BinarySearchNode<T>(item));
				size++;
				return true;
			} else
			{
				return insertRecursive(item, node.getRight());
			}

		}

	}

	/**
	 * Ensures that this set contains all items in the specified collection.
	 * 
	 * @param items
	 *            - the collection of items whose presence is ensured in this
	 *            set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually inserted);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean addAll(Collection<? extends T> items)
	{
		for (T item : items)
		{
			if (add(item) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes all items from this set. The set will be empty after this method
	 * call.
	 */
	@Override
	public void clear()
	{
		root = null;
		size = 0;

	}

	/**
	 * Determines if there is an item in this set that is equal to the specified
	 * item.
	 * 
	 * @param item
	 *            - the item sought in this set
	 * @return true if there is an item in this set that is equal to the input
	 *         item; otherwise, returns false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean contains(T item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		} else
		{
			BinarySearchNode<T> nodeContainsItem = containsRecursive(item, root);
			return (nodeContainsItem != null) ? true : false;
		}

	}

	private BinarySearchNode<T> containsRecursive(T item, BinarySearchNode<T> node)
	{
		// only for the root node with no parent
		if (node == null)
		{
			return null;
		} else if (item.equals(node.getData()))
		{
			return node;
		} else if (item.compareTo(node.getData()) < 0)
		{

			return containsRecursive(item, node.getLeft());
		} else
		{
			return containsRecursive(item, node.getRight());
		}

	}

	/**
	 * Determines if for each item in the specified collection, there is an item
	 * in this set that is equal to it.
	 * 
	 * @param items
	 *            - the collection of items sought in this set
	 * @return true if for each item in the specified collection, there is an
	 *         item in this set that is equal to it; otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean containsAll(Collection<? extends T> items)
	{
		for (T item : items)
		{
			if (contains(item) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the first (i.e., smallest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public T first() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		} else
		{
			return root.getLeftmostNode().getData();
		}

	}

	/**
	 * Returns true if this set contains no items.
	 */
	@Override
	public boolean isEmpty()
	{
		return (size == 0);
	}

	/**
	 * Returns the last (i.e., largest) item in this set.
	 * 
	 * @throws NoSuchElementException
	 *             if the set is empty
	 */
	@Override
	public T last() throws NoSuchElementException
	{
		if (size == 0)
		{
			throw new NoSuchElementException();
		} else
		{
			return root.getRightmostNode().getData();
		}

	}

	/**
	 * Ensures that this set does not contain the specified item.
	 * 
	 * @param item
	 *            - the item whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if the input item was actually removed); otherwise, returns
	 *         false
	 * @throws NullPointerException
	 *             if the item is null
	 */
	@Override
	public boolean remove(T item)
	{
		if (item == null)
		{
			throw new NullPointerException();
		} else if (root == null)
		{
			return false;
		} else if (contains(item) == false)
		{
			return false;
		} else if (item.equals(root.getData()))
		{
			deleteRoot();
			return true;
		} else
		{
			return removeRecursive(item, root);
		}

	}

	private void deleteRoot()
	{
		size--;
		int numChild = root.numChildren();
		if (numChild == 0)
		{
			root = null;

		} else if (numChild == 1)
		{
			if (root.getLeft() == null)
			{
				root = root.getRight();
			} else
			{
				root = root.getLeft();
			}

		} else
		{
			T replaceData = root.getSuccessor().getData();
			remove(root.getSuccessor().getData());
			root.setData(replaceData);
		}
	}

	private boolean removeRecursive(T item, BinarySearchNode<T> node)
	{
		if (item.compareTo(node.getData()) < 0)
		{
			if (item.equals(node.getLeft().getData()))
			{
				deleteNode(node, node.getLeft());
				size--;
				return true;

			} else
			{
				return removeRecursive(item, node.getLeft());
			}

		} else if ((item.compareTo(node.getData()) > 0))
		{
			if (item.equals(node.getRight().getData()))
			{
				deleteNode(node, node.getRight());
				size--;
				return true;

			} else
			{
				return removeRecursive(item, node.getRight());
			}

		}
		return false;
	}

	private void deleteNode(BinarySearchNode<T> parent, BinarySearchNode<T> deletedNode)
	{

		if (deletedNode.numChildren() == 0)
		{
			deleteLeaf(parent, deletedNode);
		} else if (deletedNode.numChildren() == 1)
		{
			deleteNodeOneChild(parent, deletedNode);
		} else
		{
			deleteNodeTwoChildren(parent, deletedNode);
		}

	}

	private void deleteNodeTwoChildren(BinarySearchNode<T> parent, BinarySearchNode<T> deletedNode)
	{
		T replacedData = deletedNode.getSuccessor().getData();
		remove(replacedData);
		if (deletedNode == parent.getLeft())
		{
			parent.getLeft().setData(replacedData);
		} else
		{
			parent.getRight().setData(replacedData);
		}

	}

	private void deleteNodeOneChild(BinarySearchNode<T> parent, BinarySearchNode<T> deletedNode)
	{
		if (parent.getLeft() == deletedNode)
		{
			if (parent.getLeft().getLeft() == null)
			{
				parent.setLeft(parent.getLeft().getRight());
			}
			parent.setLeft(parent.getLeft().getLeft());
		} else
		{
			if (parent.getRight().getLeft() == null)
			{
				parent.setRight(parent.getRight().getRight());
			} else
			{
				parent.setLeft(parent.getRight().getLeft());
			}

		}

	}

	private void deleteLeaf(BinarySearchNode<T> parent, BinarySearchNode<T> deletedNode)
	{
		if (parent.getLeft() == deletedNode)
		{
			parent.setLeft(null);
		} else
		{
			parent.setRight(null);
		}

	}

	/**
	 * Ensures that this set does not contain any of the items in the specified
	 * collection.
	 * 
	 * @param items
	 *            - the collection of items whose absence is ensured in this set
	 * @return true if this set changed as a result of this method call (that
	 *         is, if any item in the input collection was actually removed);
	 *         otherwise, returns false
	 * @throws NullPointerException
	 *             if any of the items is null
	 */
	@Override
	public boolean removeAll(Collection<? extends T> items)
	{
		for (T item : items)
		{
			if (remove(item) == false)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns the number of items in this set.
	 */
	@Override
	public int size()
	{
		return size;
	}

	/**
	 * Returns an ArrayList containing all of the items in this set, in sorted
	 * order.
	 */
	@Override
	public ArrayList<T> toArrayList()
	{

		ArrayList<T> arr = new ArrayList<>();
		addedToArrayList(this.root, arr);
		arr.sort(null);

		return arr;
	}

	private void addedToArrayList(BinarySearchNode<T> node, ArrayList<T> array)
	{
		if (node == null)
		{
			return;
		}
		array.add(node.getData());
		addedToArrayList(node.getLeft(), array);
		addedToArrayList(node.getRight(), array);

	}

	// Driver for writing this tree to a dot file
	public void writeDot(String filename)
	{
		try
		{
			// PrintWriter(FileWriter) will write output to a file
			PrintWriter output = new PrintWriter(new FileWriter(filename));

			// Set up the dot graph and properties
			output.println("digraph BST {");
			output.println("node [shape=record]");

			if (root != null)
				writeDotRecursive(root, output);
			// Close the graph
			output.println("}");
			output.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Recursive method for writing the tree to a dot file
	private void writeDotRecursive(BinarySearchNode<T> n, PrintWriter output) throws Exception
	{
		output.println(n.getData() + "[label=\"<L> |<D> " + n.getData() + "|<R> \"]");
		if (n.getLeft() != null)
		{
			// write the left subtree
			writeDotRecursive(n.getLeft(), output);

			// then make a link between n and the left subtree
			output.println(n.getData() + ":L -> " + n.getLeft().getData() + ":D");
		}
		if (n.getRight() != null)
		{
			// write the left subtree
			writeDotRecursive(n.getRight(), output);

			// then make a link between n and the right subtree
			output.println(n.getData() + ":R -> " + n.getRight().getData() + ":D");
		}

	}

}
