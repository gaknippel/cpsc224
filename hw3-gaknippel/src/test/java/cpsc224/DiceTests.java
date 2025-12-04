/*
 * CPSC 224, Fall 2025
 * Unit tests for the Die Roller class. 
 */

package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;


public class DiceTests {

  @Test
  void tooFewSidesGivesZero() {
    Dice d1 = new Dice();
    assertEquals(0, d1.roll(-1));    
    assertEquals(0, d1.roll(0));
    assertEquals(0, d1.roll(1));
    Dice d2 = new Dice(10);
    assertEquals(0, d2.roll(-1));
    assertEquals(0, d2.roll(0));
    assertEquals(0, d2.roll(1));
  }

  @Test
  void seededRollerGivesCorrectSixSidedValues() {
    Dice d = new Dice(0);
    // seeded six-sided values
    var vals = Arrays.asList(1, 5, 2, 6, 6, 6, 6, 4, 4, 3);
    // test six-sided values
    for (int v : vals)
      assertEquals(v, d.roll());
  }

  @Test
  void seededRollerGivesCorrectTwoSidedValues() {
    Dice d = new Dice(0);
    // seeded six-sided values
    var vals = Arrays.asList(2, 2, 1, 2, 2, 1, 2, 1, 2, 2);
    // test six-sided values
    for (int v : vals)
      assertEquals(v, d.roll(2));
  }

  @Test
  void seededRollerGivesCorrectNineSidedValues() {
    Dice d = new Dice(1);
    // seeded nine-sided values
    var vals = Arrays.asList(7, 2, 2, 7, 9, 5, 6, 2, 2, 2, 8, 8, 2, 1, 1);
    // test six-sided values
    for (int v : vals)
      assertEquals(v, d.roll(9));
  }

  @Test
  void unseededRollerGivesFirstHundredValuesBetweenOneAndSix() {
    Dice d = new Dice();
    // test six-sided values
    for (int i = 0; i < 100; ++i) {
      int v = d.roll();
      assertTrue(1 <= v && v <= 6);
    }
  }

}
