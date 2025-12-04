/*
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 */

package cpsc224;


/**
 * A basic interface for computer hold strategies.
 */  
public interface ComputerStrategy {

  /**
   * Determines whether the computer player should hold or not.
   * @param player the current player (taking the turn)
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true or false if holding or rolling
   */ 
    public boolean hold(Player player, Player opponent, int turnTotal);
}
