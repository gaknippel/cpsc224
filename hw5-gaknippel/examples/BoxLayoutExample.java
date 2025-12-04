
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class BoxLayoutExample {
  public static void main(String[] args) {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
    panel.add(Box.createRigidArea(new Dimension(20, 20)));
    panel.add(new JButton("First Button"));
    panel.add(Box.createHorizontalGlue());
    panel.add(new JButton("Second Button"));
    panel.add(Box.createRigidArea(new Dimension(20, 20)));
    panel.add(new JButton("Third Button"));
    panel.add(Box.createRigidArea(new Dimension(20, 20)));    
    JFrame frame = new JFrame("BoxLayout Example");
    // frame.setPreferredSize(new Dimension(420, 100));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
