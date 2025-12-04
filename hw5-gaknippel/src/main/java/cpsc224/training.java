package cpsc224;

import java . awt .Color;
import java . awt .Dimension;
import java . awt .Window;

import javax . swing .BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;

public class training extends JPanel{


    private JTextField textField1;


    public training()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //THIS SINGLE LINE CAUSED ME SO MUCH TROUBLE I HAD TO LOOK IT UP
        add(addTitle(createPanel1(), "Panel 1"));
    }
    private JPanel addTitle( JPanel panel, String title) 
    {
    Border empty = BorderFactory.createEmptyBorder(5,5,5,5);
    Border line = BorderFactory.createLineBorder(Color.black);
    Border compound = BorderFactory.createCompoundBorder(line,empty);
    panel.setBorder(BorderFactory.createTitledBorder(compound,title));
    return panel;
    }


    

    private JPanel createPanel1()
    {
        JPanel panel = new JPanel();

        textField1 = new JTextField();

        JButton button = new JButton("click me");

        button.addActionListener(e -> {
            Window parent = SwingUtilities.getWindowAncestor(this);
            JOptionPane.showMessageDialog(parent,textField1.getText());
        });

        panel.add(textField1);
        panel.add(button);

        return panel;
    }



        public static void main (String[] args)
    {
        try { //look and feel imports
            System.out.println("Available Look and Feels:");
            for (UIManager.LookAndFeelInfo lf : UIManager.getInstalledLookAndFeels()) {
                System.out.println(lf);
            }
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }       

        //new widget lab

        JFrame frame = new JFrame("WidgetLab");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(400,700)); //starting resolution
        frame.add(new training());
        frame.pack();
        frame.setVisible(true);
    }
    
}
