/*
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 */

package cpsc224;


/**
 * A class representing the "Hold at n" strategy. 
 */
public class HoldAtNStrategy implements ComputerStrategy {

  private int n;                // the value n to hold at

  /**
   * Creates a hold-at-n strategy for a given n value.
   * @param n the value to hold at
   */
  public HoldAtNStrategy(int n) {
    this.n = n;
  }

  /**
   * Determines whether the computer player should hold or not.
   * @param player the current player (taking the turn)
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true if player holds and false to continue rolling
   */ 
  public boolean hold(Player player, Player opponent, int turnTotal) {
    if (turnTotal >= n)
      return true;
    return false;
  }

}

