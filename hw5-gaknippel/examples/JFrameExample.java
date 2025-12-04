

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Dimension;


public class JFrameExample {

  public static void main(String[] args) {
    // create a frame
    JFrame frame = new JFrame("Frame Title");
    frame.setPreferredSize(new Dimension(300, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // add an empty panel
    frame.add(new JPanel());
    frame.pack();
    frame.setVisible(true);
  }
  
}
