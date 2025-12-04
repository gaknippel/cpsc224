
import javax.swing.UIManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MyThreeButtonJPanel extends JPanel implements ActionListener {

  private JButton panicButton = new JButton("Panic");
  private JButton quitButton = new JButton("Quit");
  private JLabel statusLabel = new JLabel("Waiting");
  
  public MyThreeButtonJPanel() {
    panicButton.addActionListener(this);
    quitButton.addActionListener(this);
    add(panicButton);
    add(quitButton);
    add(statusLabel);    
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == panicButton)
      statusLabel.setText("Panic button pressed");
    else
      System.exit(0);
  }
  
  public static void main(String[] args) {
    // short detour into look and feel: 
    try {
      UIManager.LookAndFeelInfo[] lfs = UIManager.getInstalledLookAndFeels();
      for (var lf : lfs)
        System.out.println(lf);
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
    } catch(Exception e) {
      e.printStackTrace();
    }
    // create a frame
    JFrame frame = new JFrame("Simple Two Button Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    MyThreeButtonJPanel panel = new MyThreeButtonJPanel();
    frame.add(panel);
    // show the frame
    frame.pack();
    frame.setVisible(true);
  }

} 
