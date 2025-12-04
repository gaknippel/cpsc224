
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class TwoButtonAltExample {

  public JButton panicButton = new JButton("Panic");
  public JButton quitButton = new JButton("Quit");
  public JLabel statusLabel = new JLabel("Waiting");

  private class PanicButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      statusLabel.setText("Panic button pressed");
    }
  }
  
  private class QuitButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      System.exit(0);
    }
  }

  public TwoButtonAltExample() {
    panicButton.addActionListener(new PanicButtonListener());
    quitButton.addActionListener(new QuitButtonListener());
  }
  
  public static void main(String[] args) {
    TwoButtonAltExample tbe = new TwoButtonAltExample();
    JPanel panel = new JPanel();
    panel.add(tbe.panicButton);
    panel.add(tbe.quitButton);
    panel.add(tbe.statusLabel);

    // create a frame
    JFrame frame = new JFrame("Simple Button Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

} 
