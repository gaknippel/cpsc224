

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JWindow;


public class JWindowExample {

  public static void main(String[] args) {
    // create a window (e.g., for a splash screen)
    JWindow window = new JWindow();
    window.setPreferredSize(new Dimension(500, 300));
    // create a pane
    JPanel panel = new JPanel();
    panel.setBackground(Color.CYAN);
    // add panel
    window.add(panel);
    // display in center
    window.pack();
    window.setLocationRelativeTo(null); 
    window.setVisible(true);
  }
  
}
