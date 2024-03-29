package lab04;

/**
 * RandomGenerator - A simple RNG interface
 */

public interface RandomNumberGenerator {

  /**
   * This function returns a random integer between [0,max)
   * 
   * @param max
   *          the upper bound for the range of the random number, non-inclusive.
   * @return an integer between [0, max).
   */
  public int nextInt(int max);

  /**
   * This function sets a seed for the random number generator. A random number
   * generator should return the same sequence of numbers for the same seed.
   * 
   * @param seed
   *          the seed parameter used to initialize the random number generator.
   */
  public void setSeed(long seed);

  /**
   * This function sets the two constants for use with the random generator (see
   * your textbook for more information). If your generator does not use such
   * constants then you are free to ignore the input from this function.
   */
  public void setConstants(long multiplier, long increment);

}
