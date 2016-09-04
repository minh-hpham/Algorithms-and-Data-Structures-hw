package assignment10;

/**
 * Class representation of a bad hash functor.
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class BadHashFunctor implements HashFunctor
{
    int hashValue =0;
    @Override
    public int hash (String item)
    {
        int hashValue =0;
        hashValue = item.length()%10;
        if (hashValue < 0)
        {
            hashValue *= -1;
        }
        return hashValue;
    }
    

}
