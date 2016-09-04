package lab06;

public class BinarySearchNode<T extends Comparable<? super T>> {

	private T data;
	private BinarySearchNode<T> left;
	private BinarySearchNode<T> right;

	/**
	 * Construct a new node with known children
	 * 
	 */
	public BinarySearchNode(T data, BinarySearchNode<T> left,
			BinarySearchNode<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}

	/**
	 * Construct a new node with no children
	 * 
	 */
	public BinarySearchNode(T data) {
		this(data, null, null);
	}

	/**
	 * Getter method.
	 * 
	 * @return the node data.
	 */
	public T getData() {
		return data;
	}

	/**
	 * Setter method.
	 * 
	 * @param data
	 *            - the node data to be set.
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * Getter method.
	 * 
	 * @return the left child node.
	 */
	public BinarySearchNode<T> getLeft() {
		return left;
	}

	/**
	 * Setter method.
	 * 
	 * @param left
	 *            - the left child node to be set.
	 */
	public void setLeft(BinarySearchNode<T> left) {
		this.left = left;
	}

	/**
	 * Getter method.
	 * 
	 * @return the right child node.
	 */
	public BinarySearchNode<T> getRight() {
		return right;
	}

	/**
	 * Setter method.
	 * 
	 * @param right
	 *            - the right child node to be set.
	 */
	public void setRight(BinarySearchNode<T> right) {
		this.right = right;
	}


	/**
	 * Number of children Use this to help figure out which BST deletion case to
	 * perform
	 * 
	 * @return The number of children of this node
	 */
	public int numChildren() {
		// done for you. 
		int numChildren = 0;
		if (getLeft() != null) {
			numChildren++;
		}
		if (getRight() != null) {
			numChildren++;
		}
		
		return numChildren;
	}

	/**
	 * @return The leftmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getLeftmostNode() {
		// Base case, done for you
		if (getLeft() == null) {
			return this; // returns "this" node
		}
		
		// The rest is up to you!
		return getLeft().getLeftmostNode();
	}

	/**
	 * @return The rightmost node in the binary tree rooted at this node.
	 */
	public BinarySearchNode<T> getRightmostNode() {
		// Base case, done for you
		if (getRight() == null) {
			return this; // returns "this" node
		}
		
		// The rest is up to you!
		return getRight().getRightmostNode(); 
	}

	/**
	 * @return The height of the binary tree rooted at this node. The height of
	 *         a tree is the length of the longest path to a leaf node. Consider
	 *         a tree with a single node to have a height of zero.
	 * 
	 *         The height of a tree with more than one node is the greater of
	 *         its two subtrees' heights, plus 1
	 */
	public int height() {
		if (numChildren() == 0)
		{
			return 0;
		}
		return getLeft().height() + getRight().height()+1; // implement, do not return -1
	}
	/**
	 * 
	 * This method applies to binary search trees only (not general binary
	 * trees).
	 * 
	 * @return The successor of this node.
	 * 
	 *         The successor is a node which can replace this node in a case-3
	 *         BST deletion. It is either the smallest node in the right
	 *         subtree, or the largest node in the left subtree.
	 */
	public BinarySearchNode<T> getSuccessor() {

		if(getRight() == null)
		{
			return getLeft().getRightmostNode(); 
		}
		return getRight().getLeftmostNode();
	}
}
