package lab04;

/**
 * Implementation of a generator that produces a very non-random sequence of
 * numbers.
 */
public class PoorRandomNumberGenerator implements RandomNumberGenerator
{

	private long seed;
	private long mMultiplier;
	private long increment;

	public int nextInt(int max)
	{
		
		return 1;
	}

	public void setSeed(long seed)
	{
		this.seed = seed;
	}

	public void setConstants(long multipler, long increment)
	{
		this.mMultiplier = multipler;
		this.increment = increment;
	}

}
