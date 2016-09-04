package assignment10;

/**
 * Class representation of a mediocre hash functor.
 * 
 * @author Sara Adamosn, Minh Pham
 */
public class MediocreHashFunctor implements HashFunctor
{

	@Override
	public int hash(String item)
	{
		int hashValue = 0;
        for (int i = 0; i < item.length(); i++)
        {
        	hashValue = (int)item.charAt(i);
        }
        if (hashValue < 0)
        {
            hashValue *= -1;
        }
        return hashValue;
	}

}
