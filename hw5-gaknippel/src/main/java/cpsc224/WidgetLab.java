/*
* Name : Greyson Knippel 
* Date : Fall 2025
* Class : CPSC 224
* Assignment : HW-5
*
* Description : WidgetLab class!
*/
package cpsc224 ;

import java . awt .*;
import java . awt . event .*;
import javax . swing .*;
import javax . swing . border .*;

public class WidgetLab extends JPanel{


    private JTextField textField1; //a bunch of swing components that are used 

    private JLabel label2;

    private JList<String> list4;

    private JComboBox<String> comboBox4;

    private JToggleButton toggle1;

    private JToggleButton toggle2;

    private JLabel label6;

    private JSlider slider7;

    private JSpinner spinner7;
    
    private JProgressBar progressBar7;

    public WidgetLab()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); //THIS SINGLE LINE CAUSED ME SO MUCH TROUBLE I HAD TO LOOK IT UP
        add(addTitle(createPanel1(), "Panel 1"));
        add(addTitle(createPanel2(), "Panel 2"));
        add(addTitle(createPanel4(), "Panel 3"));
        add(addTitle(createPanel5(), "Panel 4"));
        add(addTitle(createPanel6(), "Panel 6"));
        add(addTitle(createPanel7(), "Panel 7"));

    }

    private JPanel createPanel1()
    {
            JPanel panel = new JPanel();
            

            textField1 = new JTextField(20);
            JButton button = new JButton("Display Text");

            button.addActionListener(new ActionListener() {
                
                public void actionPerformed(ActionEvent e)
                {
                    Window parent = SwingUtilities.getWindowAncestor(textField1);
                    JOptionPane.showMessageDialog(parent,textField1.getText());
                }
            });

            panel.add(textField1);
            panel.add(button);

            return panel;
    }

    private JPanel createPanel2()
    {
        JPanel panel = new JPanel();

        label2 = new JLabel("Click button");

        JButton button = new JButton("Prompt");

        button.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                Window parent = SwingUtilities.getWindowAncestor(WidgetLab.this);
                String text = JOptionPane.showInputDialog(parent, "Enter text:");
                label2.setText(text);
            }
        });


        panel.add(label2);
        panel.add(button);

        return panel;
    }

    private JPanel createPanel4()
    {

        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        String[] data = {"first item","second item","third item","fourth item"};

        list4 = new JList<>(data);

        list4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list4);

        scrollPane.setPreferredSize(new Dimension(150, 100));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton button = new JButton("Display Selection");

        button.addActionListener(e -> {
            Window parent = SwingUtilities.getWindowAncestor(this);
            String selected = list4.getSelectedValue();
            JOptionPane.showMessageDialog(parent, selected);
        });

        panel.add(scrollPane);
        panel.add(button);
        return panel;
    }

    private JPanel createPanel5()
    {
        JPanel panel = new JPanel();
        String[] data = {"first item","second item","third item","fourth item"};


        comboBox4 = new JComboBox<>(data); //combo box!

        JButton button = new JButton("Display Selection");

        button.addActionListener(e -> {
            Window parent = SwingUtilities.getWindowAncestor(this);
            String selected = (String)comboBox4.getSelectedItem();
            JOptionPane.showMessageDialog(parent, selected);
        });

        panel.add(comboBox4);
        panel.add(button);

        return panel;

    }

    private JPanel createPanel6()
    {
        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        JPanel buttonPanel = new JPanel();

        toggle1 = new JToggleButton("Key 1");
        toggle2 = new JToggleButton("Key 2");

        label6 = new JLabel("Waiting for Key 1 & 2");

        label6.setAlignmentX(Component.CENTER_ALIGNMENT);

        ActionListener toggleListener = e -> {
            boolean k1 = toggle1.isSelected();
            boolean k2 = toggle2.isSelected();


            if(k1 && k2)  //if statements for toggle keys
            {
                label6.setText("prepare for launch");
            }
            else if(k1)
            {
                label6.setText("waiting for key 2");
            }
            else if (k2)
            {
                label6.setText("waiting for key 1");
            }
            else
            {
                label6.setText("waiting");
            }
        };

        toggle1.addActionListener(toggleListener);
        toggle2.addActionListener(toggleListener);

        buttonPanel.add(toggle1);
        buttonPanel.add(toggle2);

        mainPanel.add(buttonPanel);
        mainPanel.add(label6);

        return mainPanel;
    }

    private JPanel createPanel7() //custom panel
    {
        JPanel panel = new JPanel();

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        slider7 = new JSlider(0,100,50); //slider from 0 to 100

        spinner7 = new JSpinner(new SpinnerNumberModel(50, 0, 100, 1)); //spinner from 0 to 100, inrcrement by 1

        progressBar7 = new JProgressBar(0, 100); //progress bar from 0 to 100

        progressBar7.setValue(50); //starting value 50

        slider7.addChangeListener(e ->{ //add change listener changes everytime change occurs
            int value = slider7.getValue(); 
            if((Integer)spinner7.getValue() != value) //so bars can sync up
            {
                spinner7.setValue(value);
            }
            progressBar7.setValue(value);


        });


        spinner7.addChangeListener(e -> {
            int value = (Integer) spinner7.getValue();
            if(slider7.getValue() != value) //so bars can sync up
            {
                slider7.setValue(value);
            }

            progressBar7.setValue(value);
        });


        panel.add(new JLabel("control progress"));

        panel.add(slider7);
        panel.add(spinner7);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); //to separate a little
        panel.add(progressBar7);
        
        return panel;

    }

    private JPanel addTitle( JPanel panel, String title) 
    {
    Border empty = BorderFactory.createEmptyBorder(5,5,5,5);
    Border line = BorderFactory.createLineBorder(Color.black);
    Border compound = BorderFactory.createCompoundBorder(line,empty);
    panel.setBorder(BorderFactory.createTitledBorder(compound,title));
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
        frame.add(new WidgetLab());
        frame.pack();
        frame.setVisible(true);
    }
}