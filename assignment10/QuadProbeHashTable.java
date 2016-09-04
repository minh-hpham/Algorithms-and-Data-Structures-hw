package assignment10;

import java.util.Collection;

/**
 * Class representation of a quadratic probing hash table.
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class QuadProbeHashTable implements Set<String>
{
    private HashFunctor hashFunction;
    private String[] hashTable;
    private int size;
    private int initialCapacity;
    private long collisions;
    private boolean rehashing;

    /**
     * Constructs a hash table of the given capacity that uses the hashing function specified by the given functor.
     */
    public QuadProbeHashTable (int capacity, HashFunctor functor)
    {
        // table = this;
        initialCapacity = capacity;
        hashFunction = functor;
        int primeCapacity = nextPrime(capacity);
        hashTable = new String[primeCapacity];
        size = 0;
    }

    /**
     * Method to find the next largest prime number. If n is prime, it returns the next largest prime above n.
     */
    private int nextPrime (int n)
    {
        // if n is prime, return the next largest prime number
        n++;
        if (n % 2 == 0)
        {
            n++;
        }
        for (; !isPrime(n); n += 2)
            ;
        return n;
    }

    /**
     * Method to check if a number is prime
     */
    private boolean isPrime (int n)
    {
        if (n <= 1)
        {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(n); i++)
        {
            if (n % i == 0)
            {
                return false;
            }
        }
        return true;
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
        if (loadFactor() > 0.5)
        {
            rehash();
        }

        int addIndex = findIndex(item);
        // item hasn't been added to the table
        if (addIndex != -1)
        {
            hashTable[addIndex] = item;
            size++;
            return true;
        }
        // table has already contained item
        else
        {
            return false;
        }
    }

    /**
     * Returns the index of given item using quadratic probing. If the item already exists in the list, or it cannot be
     * added(it is null), returns -1;
     */
    private int findIndex (String item)
    {
        if (item == null)
        {
            return -1;
        }
        else
        {
            int index = hashFunction.hash(item) % hashTable.length;
            int start = index;
            int x = 1;

            // We are trying to find the next vacant cell. searching at indexes [(start + x*x) % hashTable.length] where
            // x
            // starts at one and increases.
            while (hashTable[index] != null && hashTable[index].equals(item) == false)
            {
                index = (start + x * x) % hashTable.length;
                x++;
                if (!rehashing)
                {
                    collisions++;
                }
            }

            if (hashTable[index] != null && hashTable[index].equals(item))
            {
                // the item already exists in the list.
                return -1;
            }
            return index;
        }
    }

    /**
     * Resize and relocate item in Hash Table
     */
    private void rehash ()
    {
        // increase the length of the hashTable to the next largest prime number.
        String[] temp = hashTable;
        size = 0;
        hashTable = new String[nextPrime(hashTable.length) * 2]; // Double the array size and take its next prime to be
                                                                 // the capacity.
        rehashing = true;
        // rehash all of the elements in temp. and add them into the hashTable.
        for (int i = 0; i < temp.length; i++)
        {
            if (temp[i] != null)
            {
                this.add(temp[i]);
            }
        }
        rehashing = false;
    }

    /**
     * @returns the portion of the hashTable that is filled.
     */
    private double loadFactor ()
    {
        return (double) size / hashTable.length;
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
        int primeCapacity = nextPrime(initialCapacity);
        hashTable = new String[primeCapacity];
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
        int containIndex = findIndex(item);
        // if we find an index where the item can be inserted, the item is not contained in the list.
        if (containIndex == -1)
        {
            return true;
        }
        else
        {

            return false;
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
        // if the collection has more elements than hashTable, the hashTable cannot possibly contain all of the elements
        // in the collection
        if (items.size() > size)
        {
            return false;
        }

        if (items.isEmpty())
        {
            return true;
        }
        for (String item : items)
        {
            if (contains(item) == false)
            {
                return false;
            }
        }
        return true;
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
     * Returns the number of collisions that occurred when making this HashTable
     * 
     */
    public long collisions ()
    {
        return collisions;
    }
}