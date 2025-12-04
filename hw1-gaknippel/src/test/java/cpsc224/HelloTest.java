/**
 * CPSC 224, Fall 2025
 * Simple JUnit test file. 
 */

package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class HelloTest {

  @Test
  void basicHelloMessageCall() {
    String s = Hello.getHelloMessage();
    assertEquals("Hello World!", s);
  }
  
}
