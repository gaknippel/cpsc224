

/*
 * JPanel: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JPanel.html
 * JFrame: https://docs.oracle.com/en/java/javase/21/docs/api/java.desktop/javax/swing/JFrame.html
 */

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;


public class JFrameBlueJPanelExample {

  public static void main(String[] args) {
    // create a frame
    JFrame frame = new JFrame("Frame with Blue Pane");
    frame.setPreferredSize(new Dimension(500, 300));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // add an empty panel
    JPanel panel = new JPanel();
    // set to Gonzaga Navy Blue
    panel.setBackground(new Color(4, 30, 66));
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
  
}
