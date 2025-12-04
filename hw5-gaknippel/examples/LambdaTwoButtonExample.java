
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class LambdaTwoButtonExample {

  public static void main(String[] args) {
    JButton panicButton = new JButton("Panic");
    JButton quitButton = new JButton("Quit");
    JLabel statusLabel = new JLabel("Waiting");

    JPanel panel = new JPanel();
    panel.add(panicButton);
    panicButton.addActionListener(e -> {statusLabel.setText("Panic button pressed");});
    panel.add(quitButton);
    quitButton.addActionListener(e -> {System.exit(0);});
    panel.add(statusLabel);

    // create a frame
    JFrame frame = new JFrame("Simple Button Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

} 
