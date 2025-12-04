
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;


public class JOptionPaneConfirmExample {

  public static void main(String[] args) {
    // create a frame to hold the text area
    JFrame frame = new JFrame("Confirmation Option Pane Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new JPanel());
    frame.pack();
    frame.setVisible(true);

    // open a confirm dialog
    String title = "Make a choice";
    String msg = "Do you want to continue?";
    int ans = JOptionPane.showConfirmDialog(frame, msg, title, JOptionPane.YES_NO_OPTION);
    if (ans == JOptionPane.NO_OPTION)
      System.exit(0);
  }

} 
