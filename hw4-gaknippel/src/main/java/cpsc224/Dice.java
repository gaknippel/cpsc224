/*
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 */

package cpsc224;

import java.util.Random;


/**
 * A simple class for handling rolls of the dice.
 */
public class Dice {

  private Random numberGenerator;     // the random number generator

  /**
   * Creates a single die based on a random-number generator seed.
   * @param seed the random number generator seed
   */
  public Dice(int seed) {
    numberGenerator = new Random(seed);
  }

  /**
   * Creates a single die without a seeded random number generator.
   */
  public Dice() {
    numberGenerator = new Random();
  }

  /**
   * Returns a random int between 1 and the number of given sides
   * (inclusive).
   * @param sides the number of sides (should be 2 or larger)
   * @return 1 to the number of sides (inclusive), or 0 if invalid number sides
   */
  public int roll(int sides) {
    if (sides < 2)
      return 0;
    return numberGenerator.nextInt(sides) + 1;
  }

  /**
   * Returns a random int between 1 and 6 (inclusive).
   * @return 1 to 6 (inclusive)
   */
  public int roll() {
    return numberGenerator.nextInt(6) + 1;
  }
  
}
