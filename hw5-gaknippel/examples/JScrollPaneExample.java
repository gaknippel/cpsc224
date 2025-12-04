

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class JScrollPaneExample {

  public static void main(String[] args) {
    // create the text area with 20 columns and 40 rows
    JTextArea textArea = new JTextArea(20, 40);
    JScrollPane scrollPane = new JScrollPane(textArea);

    // create a frame to hold the text area
    JFrame frame = new JFrame("Text Area Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add scroll pane to frame (which contains the text area)
    frame.add(scrollPane);
    frame.pack();
    frame.setVisible(true);
  }

} 
