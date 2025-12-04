/**
 * CPSC 224, Fall 2025
 * Simple Hello World class.
 */

package cpsc224;

/**
 * A simple Hello class for testing.
 */
public class Hello {

  /**
   * Get the hello message.
   * @return The hello message
   */
  public static String getHelloMessage() {
    return "Hello World!";
  }

  /**
   * Prints the hello message.
   */
  public static void main(String[] args) {
    System.out.println(getHelloMessage());
  }
  
}
