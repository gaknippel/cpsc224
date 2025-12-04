

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class JOptionPaneExample {

  public static void main(String[] args) {
    // create a frame to hold the text area
    JFrame frame = new JFrame("Dialog Box Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JPanel());
    frame.pack();
    frame.setVisible(true);

    // info dialog (default)
    JOptionPane.showMessageDialog(frame, "Welcome!");

    // plain message (adds title)
    JOptionPane.showMessageDialog(frame, "Hi!", "Title", JOptionPane.PLAIN_MESSAGE);

    // warning message
    JOptionPane.showMessageDialog(frame, "Oops!", "Title", JOptionPane.WARNING_MESSAGE);    
  }

} 
