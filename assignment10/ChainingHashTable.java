package assignment10;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Class representation of a chaining hash table.
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class ChainingHashTable implements Set<String>
{
    private HashFunctor hashFunction;
    /** number of items in the hashTable */
    private int size;
    private int initialCapacity;
    private LinkedList<String>[] storage;
    private int collisions;
    private boolean rehashing;

    @SuppressWarnings("unchecked")
    public ChainingHashTable (int capacity, HashFunctor functor)
    {
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        initialCapacity = capacity;
        for (int i = 0; i < initialCapacity; i++)
        {
            storage[i] = new LinkedList<String>();
        }
        size = 0;
        hashFunction = functor;

    }

    /**
     * Ensures that this set contains the specified item, by adding it to the set if it does not already exist.
     * 
     * @param item the item whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if the input item was actually
     *         inserted); otherwise, returns false
     */
    @Override
    public boolean add (String item)
    {
        if (item == null)
        {
            return false;
        }
        else
        {
            if (loadFactor() > 3.0)
            {
                rehash();
            }

            int index = hashFunction.hash(item) % storage.length;

            ListIterator<String> iterator = storage[index].listIterator();
            while (iterator.hasNext())
            {
                if (item.equals(iterator.next()))
                {
                    collisions++;
                    return false;
                }
            }
            size++;
            iterator.add(item);
            return true;
        }
    }

    /**
     * Ensures that this set contains all items in the specified collection, by adding them to the set if they do not
     * already exist.
     * 
     * @param items the collection of items whose presence is ensured in this set
     * @return true if this set changed as a result of this method call (that is, if any item in the input collection
     *         was actually inserted); otherwise, returns false
     */
    @Override
    public boolean addAll (Collection<? extends String> items)
    {
        boolean addAll = true;
        for (String item : items)
        {
            if (add(item) == false)
            {
                addAll = false;
            }
        }
        return addAll;
    }

    /**
     * Removes all items from this set. The set will be empty after this method call.
     */
    @Override
    public void clear ()
    {
        storage = new ChainingHashTable(initialCapacity, hashFunction).getStorage();
        size = 0;
    }

    /**
     * Determines if there is an item in this set that is equal to the specified item.
     * 
     * @param item the item sought in this set
     * @return true if there is an item in this set that is equal to the input item; otherwise, returns false
     */
    @Override
    public boolean contains (String item)
    {
        if (item == null)
        {
            return false;
        }

        else
        {
            int index = hashFunction.hash(item) % storage.length;
            if (index < 0)
            {
                index += storage.length;
            }
            return storage[index].contains(item);
        }
    }

    /**
     * Determines if for each item in the specified collection, there is an item in this set that is equal to it.
     * 
     * @param items the collection of items sought in this set
     * @return true if for every item in the specified collection, there is an item in this set that is equal to it;
     *         otherwise, returns false
     */
    @Override
    public boolean containsAll (Collection<? extends String> items)
    {
        boolean contains = true;
        for (String item : items)
        {
            if (contains(item) == false)
            {
                contains = false;
            }
        }
        return contains;
    }

    /**
     * Returns true if this set contains no items.
     */
    @Override
    public boolean isEmpty ()
    {
        return size == 0;
    }

    /**
     * Returns the number of items in this set.
     */
    @Override
    public int size ()
    {
        return size;
    }

    /**
     * Resize and relocate item in Hash Table
     */
    private void rehash ()
    {
        // increase the length of the hashTable to the next largest prime number.
        LinkedList<String>[] temp = storage;

        // Double the array size and take its next prime to be the capacity.
        int capacity = storage.length * 2;
        storage = new ChainingHashTable(capacity, hashFunction).getStorage();
        size = 0;
        rehashing = true;
        // rehash all of the elements in temp. and add them into the hashTable.
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] != null)
            {
                addAll(temp[i]);
            }
        }
        rehashing = false;
    }

    /**
     * @returns the average length of each linked list in the table. 
     */
    private double loadFactor ()
    {
        return (double) size / storage.length;
    }

    /**
     * @return the number of collisions in the hashTable
     */
    public int collisions ()
    {
        return collisions;
    }

    /**
     * @return the storage linked list array for a hashTable
     */
    private LinkedList<String>[] getStorage ()
    {
        return storage;
    }
}
