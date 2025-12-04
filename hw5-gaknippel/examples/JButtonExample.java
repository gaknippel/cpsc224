

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class JButtonExample {

  public static void main(String[] args) {
    // create a button and add it to a panel
    JButton button = new JButton("Panic");
    JPanel panel = new JPanel();
    panel.add(button);

    // create a frame
    JFrame frame = new JFrame("Simple Button Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

} 
