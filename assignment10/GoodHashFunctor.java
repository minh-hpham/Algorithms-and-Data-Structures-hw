package assignment10;

/**
 * Class representation of a good hash functor.
 * 
 * @author Sara Adamosn, Minh Pham
 */

public class GoodHashFunctor implements HashFunctor
{
    private static int multiplier = 43;

    @Override
    public int hash (String item)
    {
        int hashValue = 0;
        for (int i = 0; i < item.length(); i++)
        {
            hashValue = hashValue * multiplier + (int) item.charAt(i);
        }
        if (hashValue < 0)
        {
            hashValue *= -1;
        }
        return hashValue;
    }

    public static void setMultiplier (int multilplier)
    {
        multiplier = multilplier;
    }
    // public static void main(String[] args)
    // {
    // GoodHashFunctor ghf = new GoodHashFunctor();
    // System.out.println(ghf.hash("cow")%2);
    // System.out.println(ghf.hash("dog")%2);
    // }

}
