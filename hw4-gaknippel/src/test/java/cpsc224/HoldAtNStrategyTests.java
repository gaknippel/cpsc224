/*
 * CPSC 224, Fall 2025
 * Unit tests for the HoldAtNStrategy class. 
 */

package cpsc224;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;


public class HoldAtNStrategyTests {

  @Test
  void strategyCorrectlyHoldsAt23() {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    HoldAtNStrategy s = new HoldAtNStrategy(23);
    assertTrue(s.hold(p1, p2, 23));
  }
  
  @Test
  void strategyCorrectlyHoldsAbove23() {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    HoldAtNStrategy s = new HoldAtNStrategy(23);
    assertTrue(s.hold(p1, p2, 24));
  }

  @Test
  void strategyDoesNotHoldsBelow23() {
    Player p1 = new Player("Player 1");
    Player p2 = new Player("Player 2");
    HoldAtNStrategy s = new HoldAtNStrategy(23);
    assertFalse(s.hold(p1, p2, 22));
  }
  
}
