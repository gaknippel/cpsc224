

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;


public class MyButtonJPanel extends JPanel implements ActionListener {

  private JButton button = new JButton("Panic");

  public MyButtonJPanel() {
    // add the custom panel to the listener list of the button
    button.addActionListener(this);
    // add the button to the current panel (add inherited from JPanel)
    add(button);
  }

  // called when the button is selected
  public void actionPerformed(ActionEvent e) {
    // this condition overkill (used if listing to multiple widgets)
    if (e.getSource() == button)
      System.out.println("Panic button pressed");
  }
  
  public static void main(String[] args) {
    // create a frame
    JFrame frame = new JFrame("Simple Button Example");
    frame.setPreferredSize(new Dimension(500, 500));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // create a custom panel object
    MyButtonJPanel panel = new MyButtonJPanel();
    frame.add(panel);
    // show the frame
    frame.pack();
    frame.setVisible(true);
  }

} 
