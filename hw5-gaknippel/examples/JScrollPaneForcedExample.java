

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class JScrollPaneForcedExample {

  public static void main(String[] args) {
    // create the text area with 20 columns and 40 rows
    JTextArea textArea = new JTextArea(20, 40);
    JScrollPane scrollPane = new JScrollPane(textArea);

    // always shows scroll bars
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

    // create a frame
    JFrame frame = new JFrame("Text Area Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // add scroll pane to frame
    frame.add(scrollPane);
    frame.pack();
    frame.setVisible(true);
  }

} 
