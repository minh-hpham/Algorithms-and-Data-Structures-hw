package assignment11;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Represents a priority queue of generically-typed items. The queue is implemented as a min heap. The min heap is
 * implemented implicitly as an array.
 * 
 * @author Sara Adamson Minh Pham
 */
@SuppressWarnings("unchecked")
public class PriorityQueue<AnyType>
{

    private int currentSize;

    private AnyType[] array;

    private Comparator<? super AnyType> cmp;

    /**
     * Constructs an empty priority queue. Orders elements according to their natural ordering (i.e., AnyType is
     * expected to be Comparable) AnyType is not forced to be Comparable.
     */
    public PriorityQueue ()
    {
        currentSize = 0;
        cmp = null;
        array = (AnyType[]) new Object[10]; // safe to ignore warning
    }

    /**
     * Construct an empty priority queue with a specified comparator. Orders elements according to the input Comparator
     * (i.e., AnyType need not be Comparable).
     */
    public PriorityQueue (Comparator<? super AnyType> c)
    {
        currentSize = 0;
        cmp = c;
        array = (AnyType[]) new Object[10]; // safe to ignore warning
    }

    /**
     * @return the number of items in this priority queue.
     */
    public int size ()
    {
        return currentSize;
    }

    /**
     * Makes this priority queue empty.
     */
    public void clear ()
    {
        currentSize = 0;
    }

    /**
     * @return the minimum item in this priority queue.
     * @throws NoSuchElementException if this priority queue is empty.
     * 
     *             (Runs in constant time.)
     */
    public AnyType findMin () throws NoSuchElementException
    {
        if (currentSize == 0)
        {
            throw new NoSuchElementException();
        }
        return array[0]; // root is the smallest item
    }

    /**
     * Removes and returns the minimum item in this priority queue.
     * 
     * @throws NoSuchElementException if this priority queue is empty.
     * 
     *             (Runs in logarithmic time.)
     */
    public AnyType deleteMin () throws NoSuchElementException
    {

        // if the heap is empty, throw a NoSuchElementException
        if (currentSize == 0)
        {
            throw new NoSuchElementException();
        }
        // store the minimum item so that it may be returned at the end
        AnyType deletedMin = array[0];
        // replace the item at minIndex with the last item in the tree
        array[0] = array[currentSize - 1];
        array[currentSize - 1] = null;
        // update size
        currentSize--;
        // percolate the item at minIndex down the tree until heap order is
        // restored
        if (currentSize > 1)
        {
            percolateDown(0);
        }
        // return the minimum item that was stored
        return deletedMin;
    }

    /**
     * percolates the given item down the tree until heap order is restored.
     * 
     * @param anyType
     */
    private void percolateDown (int parentIndex)
    {
        AnyType parent = array[parentIndex];
        int minChildIndex = findIndexOfSmallerChild(parentIndex);
        AnyType child = array[minChildIndex];

        while (compare(parent, child) > 0 && leftChildIndex(parentIndex) <= currentSize - 1)
        {
            swap(parentIndex, minChildIndex);
            parentIndex = minChildIndex;

            // if the parent still has children, find the smallest child.
            if (leftChildIndex(parentIndex) <= currentSize - 1)
            {
                minChildIndex = findIndexOfSmallerChild(minChildIndex);
                child = array[minChildIndex];
            }
        }
    }

    /**
     * returns the index of the smaller of the two children belonging to the parentIndex.
     */
    private int findIndexOfSmallerChild (int parentIndex)
    {

        int left = leftChildIndex(parentIndex);
        int right = rightChildIndex(parentIndex);
        // if a right child exists
        if (right <= currentSize - 1)
        {
            return compare(array[left], array[right]) < 0 ? left : right;
        }
        return left;

    }

    /**
     * Adds an item to this priority queue.
     * 
     * (Runs in logarithmic time.) Can sometimes terminate early.
     * 
     * @param x -- the item to be inserted
     */
    public void add (AnyType x)
    {
        // if the array is full, double its capacity
        if (array[array.length - 1] != null)
        {
            array = Arrays.copyOf(array, currentSize * 2);
        }
        // add the new item to the next available node in the tree, so that
        // complete tree structure is maintained
        array[currentSize] = x;
        // update size
        currentSize++;
        // percolate the new item up the levels of the tree until heap order is
        // restored
        percolateUp(x);
    }

    /**
     * percolate's the given item up the levels of the tree until the heap order is restored.
     */
    private void percolateUp (AnyType item)
    {
        AnyType child = item;
        // percolateUp is used for ADD method. the 1st childIndex
        // is the currentSize
        int childIdx = currentSize - 1;
        // finding parent index
        int parentIdx = parentIndex(childIdx);
        AnyType parent = array[parentIdx];
        // compare and swap child and parent until child > parent
        while (compare(child, parent) < 0)
        {
            swap(childIdx, parentIdx);
            // update child index and child value
            childIdx = parentIdx;
            // update parent index and parent value
            parentIdx = parentIndex(parentIdx);
            parent = array[parentIdx];
        }
    }

    /**
     * Swaps the element at childIndex in array with the element at parentIndex.
     */
    private void swap (int childIndex, int parentIndex)
    {
        AnyType temp = array[childIndex];
        array[childIndex] = array[parentIndex];
        array[parentIndex] = temp;

    }

    /**
     * returns the index of Left Child of the given parentIndex. Does not determine if the child actually exists.
     */
    private int leftChildIndex (int parentIndex)
    {
        return (parentIndex * 2) + 1;
    }

    /**
     * returns the index of right Child of the given parentIndex.Does not determine if the child actually exists.
     */
    private int rightChildIndex (int parentIndex)
    {
        return (parentIndex * 2) + 2;
    }

    /**
     * returns the index of the parent of the given childIndex.
     */
    private int parentIndex (int childIndex)
    {
        return (childIndex - 1) / 2;
    }

    /**
     * Generates a DOT file for visualizing the binary heap.
     */
    public void generateDotFile (String filename)
    {
        try
        {
            PrintWriter out = new PrintWriter(filename);
            out.println("digraph Heap {\n\tnode [shape=record]\n");

            for (int i = 0; i < currentSize; i++)
            {
                out.println("\tnode" + i + " [label = \"<f0> |<f1> " + array[i] + "|<f2> \"]");
                if (((i * 2) + 1) < currentSize)
                    out.println("\tnode" + i + ":f0 -> node" + ((i * 2) + 1) + ":f1");
                if (((i * 2) + 2) < currentSize)
                    out.println("\tnode" + i + ":f2 -> node" + ((i * 2) + 2) + ":f1");
            }

            out.println("}");
            out.close();
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    /**
     * Internal method for comparing lhs and rhs using Comparator if provided by the user at construction time, or
     * Comparable, if no Comparator was provided.
     */
    private int compare (AnyType lhs, AnyType rhs)
    {
        if (cmp == null)
            return ((Comparable<? super AnyType>) lhs).compareTo(rhs); // safe
                                                                       // to
                                                                       // ignore
                                                                       // warning
        // We won't test your code on non-Comparable types if we didn't supply a
        // Comparator

        return cmp.compare(lhs, rhs);
    }

    /**
     * @return an array representation of a priorityQueue.
     */
    public Object[] toArray ()
    {
        Object[] ret = new Object[currentSize];
        for (int i = 0; i < currentSize; i++)
            ret[i] = array[i];
        return ret;
    }
}
