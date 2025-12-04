/*
 * Name: ... your name here ...
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 * Unit tests for the Player class.  
 */

package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class PlayerTests {

  @Test
  void playerCreatedGivesCorrectName() {
    Player p = new Player("Player 1");
    assertEquals("Player 1", p.name());
  }
  
  @Test
  void playerCreatedGivesCorrectNameAndWinningScore() {
    // the default case:
    Player p1 = new Player("Player 1");
    assertEquals("Player 1", p1.name());
    assertEquals(100, p1.winningScore());
    // given winning score case:
    Player p2 = new Player("Player 2", 50);
    assertEquals("Player 2", p2.name());
    assertEquals(50, p2.winningScore());
  }

  @Test
  void givesCorrectDiceRolls() {
    Player p = new Player("Player 1");
    // initial value:
    assertEquals(0, p.diceRolls());
    // first roll:
    p.addDiceRoll();
    assertEquals(1, p.diceRolls());
    // second roll:
    p.addDiceRoll();
    assertEquals(2, p.diceRolls());    
  }

  @Test
  void gameScoreReturnsCorrectScore() {
    Player p = new Player("Player 1");
    // initial value:
    assertEquals(0, p.gameScore());
    // first time called:
    p.addTurnScore(10);
    assertEquals(10, p.gameScore());
    // not the max score:
    p.addTurnScore(8);
    assertEquals(18, p.gameScore());
    // a max score:
    p.addTurnScore(12);
    assertEquals(30, p.gameScore());
    p.resetGameScore();
    assertEquals(0, p.gameScore());
  }
  
  @Test
  void isWinnerWorks()
  {
    Player p = new Player("Player 1" , 1);
    p.addTurnScore(2);
    assertEquals(true, p.isWinner());
  }
  

  

  // TODO: Finish remaining tests for each method. Note that testing
  //       isWinning() should result in multiple unit tests.

  
}
