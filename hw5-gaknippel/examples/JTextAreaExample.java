

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class JTextAreaExample {

  public static void main(String[] args) {
    // create a frame to hold the text area
    JFrame frame = new JFrame("Text Area Example");
    frame.setPreferredSize(new Dimension(300, 300));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create the text area with 20 columns and 40 rows
    JTextArea textArea = new JTextArea(20, 40);

    // add it to the frame and show the frame
    frame.add(textArea);
    frame.pack();
    frame.setVisible(true);
  }

} 
