/*
 * CPSC 224, Fall 2025
 * Unit tests for the HumanPlayer class. 
 */

package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class HumanPlayerTests {

  @Test
  void humanPlayerCreatedGivesCorrectName() {
    HumanPlayer p = new HumanPlayer("Player 1");
    assertEquals("Player 1", p.name());
  }
  
  @Test
  void humanPlayerCreatedGivesCorrectNameAndWinningScore() {
    // the default case:
    HumanPlayer p1 = new HumanPlayer("Player 1");
    assertEquals("Player 1", p1.name());
    assertEquals(100, p1.winningScore());
    // given winning score case:
    HumanPlayer p2 = new HumanPlayer("Player 2", 50);
    assertEquals("Player 2", p2.name());
    assertEquals(50, p2.winningScore());
  }
  
}
