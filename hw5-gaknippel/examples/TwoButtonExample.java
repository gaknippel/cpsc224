
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class TwoButtonExample {

  static JButton panicButton = new JButton("Panic");
  static JButton quitButton = new JButton("Quit");
  static JLabel statusLabel = new JLabel("Waiting");

  static class PanicButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      statusLabel.setText("Panic button pressed");
    }
  }
  
  static class QuitButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  public static void main(String[] args) {
    JPanel panel = new JPanel();
    panel.add(panicButton);
    panicButton.addActionListener(new PanicButtonListener());
    panel.add(quitButton);
    quitButton.addActionListener(new QuitButtonListener());
    panel.add(statusLabel);

    // create a frame
    JFrame frame = new JFrame("Simple Button Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

} 
