/*
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 */

package cpsc224;


/**
 * A basic class for representing human players in the game.
 */
public class HumanPlayer extends Player {

  /**
   * Creates a human player with a given name and the default target
   * winning score.
   * @param name the name of the player (e.g., "Player 1")
   */
  public HumanPlayer(String name) {
    super(name);
  }

  /** 
   * Creates a human player with a given name and target winning score.
   * @param name the name of the player (e.g., "Player 1")
   * @param winningScore the target score for a win
   */
  public HumanPlayer(String name, int winningScore) {
    super(name, winningScore);
  }
 
}
