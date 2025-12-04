
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FlowLayoutExample {
  public static void main(String[] args) {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    panel.add(new JButton("First Button"));
    panel.add(new JButton("Second Button"));    
    panel.add(new JButton("Third Button"));
    JFrame frame = new JFrame("FlowLayout Example");
    // frame.setPreferredSize(new Dimension(400, 100));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
