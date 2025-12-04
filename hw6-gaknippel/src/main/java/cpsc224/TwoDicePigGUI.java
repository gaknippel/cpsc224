package cpsc224;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatDarkLaf;

public class TwoDicePigGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private GamePanel gamePanel; 

    private static String SCREEN_SPLASH = "SPLASH";
    private static String SCREEN_SETUP = "SETUP";
    private static String SCREEN_GAME = "GAME";

    public TwoDicePigGUI() {
        setTitle("TwoDicePig (extended version 1.0)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); 
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        gamePanel = new GamePanel(); 

        mainPanel.add(createSplashPanel(), SCREEN_SPLASH);
        mainPanel.add(createSetupPanel(), SCREEN_SETUP);
        mainPanel.add(gamePanel, SCREEN_GAME);

        add(mainPanel);
        cardLayout.show(mainPanel, SCREEN_SPLASH);
    }

    private JPanel createSplashPanel() //SPLASH SCREEN
    { 
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.DARK_GRAY);

        JLabel titleLabel = new JLabel("welcome to twodicepig!");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel creditLabel = new JLabel("by greyson knippel");
        creditLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        creditLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    

        URL imgUrl = getClass().getResource("/images/splash.gif");
        imageLabel.setIcon(new ImageIcon(imgUrl));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("start game");
        JButton quitButton = new JButton("quit :(");

        startButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        quitButton.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        
        startButton.addActionListener(e -> cardLayout.show(mainPanel, SCREEN_SETUP));
        quitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);

        panel.add(Box.createVerticalGlue());
        panel.add(titleLabel);
        panel.add(creditLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30))); 
        panel.add(imageLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 40))); 
        panel.add(buttonPanel);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel createSetupPanel() //GAME SETUP SCREEN
    {
        JPanel panel = new JPanel(new BorderLayout());
        
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        settingsPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        settingsPanel.add(new JLabel("winning score:"));
        JSpinner scoreSpinner = new JSpinner(new SpinnerNumberModel(100, 10, 1000, 10));
        settingsPanel.add(scoreSpinner);

        JCheckBox doubleTroubleCheck = new JCheckBox("double trouble mode (HAVE TO ROLL IF GET SAME DICE)");
        settingsPanel.add(doubleTroubleCheck);

        settingsPanel.add(new JLabel("players:"));
        JSpinner playerCountSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 10, 1));
        settingsPanel.add(playerCountSpinner);

        panel.add(settingsPanel, BorderLayout.NORTH);

        JPanel playersContainer = new JPanel(); //players list in setup
        playersContainer.setLayout(new BoxLayout(playersContainer, BoxLayout.Y_AXIS));
        
        JScrollPane scroll = new JScrollPane(playersContainer);
        panel.add(scroll, BorderLayout.CENTER);

        List<PlayerConfigRow> playerRows = new ArrayList<>();
        updatePlayerRows(playersContainer, playerRows, (Integer) playerCountSpinner.getValue()); //updating players if adding more

        playerCountSpinner.addChangeListener(e -> 
        {
            updatePlayerRows(playersContainer, playerRows, (Integer) playerCountSpinner.getValue());
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton backButton = new JButton("back");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, SCREEN_SPLASH));

        JButton startGameButton = new JButton("start game");
        startGameButton.setFont(new Font("Segoe UI", Font.BOLD, 12));

        startGameButton.addActionListener(e ->  //get a bunch of settings
        {
            int winningScore = (Integer) scoreSpinner.getValue();
            boolean isDoubleTrouble = doubleTroubleCheck.isSelected();

            ArrayList<Player> players = new ArrayList<>();
            for (PlayerConfigRow row : playerRows) 
            {
                players.add(row.createPlayer(winningScore));
            }

            Game newGame = new Game(players, isDoubleTrouble); //new game
            gamePanel.startNewGame(newGame); //startNewGame helper func
            cardLayout.show(mainPanel, SCREEN_GAME); //shows game screen!
        });

        buttonPanel.add(backButton);
        buttonPanel.add(startGameButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private void updatePlayerRows(JPanel container, List<PlayerConfigRow> rows, int count) 
    {
        container.removeAll(); //clears data so things dont mess up
        rows.clear();           //^
        for (int i = 1; i <= count; i++) {
            PlayerConfigRow row = new PlayerConfigRow(i);
            rows.add(row);
            container.add(row);
            container.add(Box.createRigidArea(new Dimension(0, 10))); 
        }
        container.revalidate();  //refresh UI :D
        container.repaint();
    }

    private class GamePanel extends JPanel {
        private Game game;
        private Dice dice = new Dice();
        private int turnTotal = 0;
        
        private JLabel die1Label, die2Label;
        private JLabel currentTurnLabel;
        private JTextArea gameLog;
        private JButton rollButton, holdButton, quitButton, settingsButton; 
        private JTable statsTable; 
        private DefaultTableModel statsModel;
        
        public GamePanel() 
        {
            setLayout(new BorderLayout());

            JPanel leftPanel = new JPanel(new BorderLayout());
            leftPanel.setBorder(new TitledBorder("scoreboard"));
            leftPanel.setPreferredSize(new Dimension(450, 0)); 
            
            String[] columnNames = {"name", "score", "maxturn", "rolls", "1s", "2x"};
            statsModel = new DefaultTableModel(columnNames, 0); //columns
            statsTable = new JTable(statsModel);
            statsTable.setEnabled(false); //read only!!
            statsTable.setRowHeight(25);
            statsTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            
            leftPanel.add(new JScrollPane(statsTable), BorderLayout.CENTER);
            add(leftPanel, BorderLayout.WEST);

            JPanel centerPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            currentTurnLabel = new JLabel("waiting to start...");
            currentTurnLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
            gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; //aligns dice jpgs
            centerPanel.add(currentTurnLabel, gbc);

            die1Label = new JLabel();
            die1Label.setPreferredSize(new Dimension(100, 100));
            
            die2Label = new JLabel();
            die2Label.setPreferredSize(new Dimension(100, 100));

            gbc.gridwidth = 1;
            gbc.gridy = 1; 
            gbc.gridx = 0; 
            centerPanel.add(die1Label, gbc);
            gbc.gridx = 1; 
            centerPanel.add(die2Label, gbc);

            add(centerPanel, BorderLayout.CENTER);

            JPanel southPanel = new JPanel(new BorderLayout());
            
            JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
            rollButton = new JButton("roll");
            holdButton = new JButton("hold");
            settingsButton = new JButton("settings"); 
            quitButton = new JButton("quit game :(");
            
            rollButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            holdButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
            
            controls.add(rollButton);
            controls.add(holdButton);
            controls.add(settingsButton);
            controls.add(quitButton);
            southPanel.add(controls, BorderLayout.NORTH);

            gameLog = new JTextArea(6, 50);
            gameLog.setEditable(false); // not changeable 
            gameLog.setFont(new Font("Segoe UI", Font.ITALIC, 12));
            southPanel.add(new JScrollPane(gameLog), BorderLayout.CENTER); 

            add(southPanel, BorderLayout.SOUTH); //put it at bottom

            rollButton.addActionListener(e -> performRoll());
            holdButton.addActionListener(e -> performHold());
            settingsButton.addActionListener(e -> showSettingsDialog()); 
            quitButton.addActionListener(e -> 
            {
                int confirm = JOptionPane.showConfirmDialog(this, "end current game?", "quit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION)
                {
                   cardLayout.show(mainPanel, SCREEN_SPLASH);
                }
            });
        }

        public void startNewGame(Game game) 
        {
            this.game = game;
            this.turnTotal = 0;
            gameLog.setText("--- new game started!!! ---\ntarget score: " + game.getPlayers().get(0).winningScore() + "\n");
            
            updateDiceIcons(1, 1); //helper funcs to updatre scoreboard
            updateStatsTable();
            checkTurnState();
        }


        private void checkTurnState() //check whos turn it is
        {
            Player current = game.currentPlayer();
            updateStatsTable();
            currentTurnLabel.setText(current.name() + "'s turn (total: " + turnTotal + ")");
            
            if (current instanceof ComputerPlayer) //if computer, dont let player do anytihng
            {
                rollButton.setEnabled(false);
                holdButton.setEnabled(false);
                playComputerTurn();
            } else 
            {
                rollButton.setEnabled(true);
                holdButton.setEnabled(true);
            }
        }

        private void playComputerTurn() 
        {
            ComputerPlayer cpu = (ComputerPlayer) game.currentPlayer();
            Player opponent = TwoDicePig.getLeadOpponent(game); 

            while (true) 
            {

                
                if (cpu.hold(opponent, turnTotal)) 
                {
                    performHold();
                    break; 
                } 
                else 
                {
                    boolean continueTurn = performRoll();
                    if (!continueTurn) 
                    {
                        break; //got 1 or snake eyes
                    }
                    // if we rolled successfully, loop continues (CPU thinks again)
                }
            }
        }

        private boolean performRoll() //false if get 1 or snakeeyes, true if continues
        { 
            int d1 = dice.roll();
            int d2 = dice.roll();
            game.currentPlayer().addDiceRoll();
            updateDiceIcons(d1, d2);
            log(game.currentPlayer().name() + " rolled " + d1 + " + " + d2); //log message

            if (d1 == 1 && d2 == 1) 
            {
                log("SNAKE EYES!!! score reset.");
                game.currentPlayer().addSnakeEyes();
                game.currentPlayer().resetGameScore();
                turnTotal = 0;
                
                if (game.currentPlayer() instanceof HumanPlayer) 
                {
                    JOptionPane.showMessageDialog(this, "snake eyes!\nscore reset to 0.", "turn over", JOptionPane.WARNING_MESSAGE);
                }
                
                endTurn();
                return false;
            } 
            else if (d1 == 1 || d2 == 1) 
            {
                log("rolled a single 1. turn over.");
                turnTotal = 0;
                
                if (game.currentPlayer() instanceof HumanPlayer) 
                {
                    JOptionPane.showMessageDialog(this, "you rolled a 1!\nno points this turn.", "turn over", JOptionPane.WARNING_MESSAGE);
                }
                
                endTurn();
                return false;
            } 
            else 
            {
                turnTotal += (d1 + d2);
                boolean doubleTrouble = (d1 == d2) && game.getDoubleTroble();
                
                if (doubleTrouble) 
                {
                    log("DOUBLE TROUBLE!!! must roll again!");
                    game.currentPlayer().addDoubleTroubles();
                    if (game.currentPlayer() instanceof HumanPlayer) //disable hold if human
                    {
                         holdButton.setEnabled(false);
                    }
                } 
                else 
                {
                    if (game.currentPlayer() instanceof HumanPlayer) 
                    {
                        holdButton.setEnabled(true);
                    }
                }
                
                currentTurnLabel.setText(game.currentPlayer().name() + "'s turn (total: " + turnTotal + ")");
                return true;
            }
        }

        private void performHold() 
        {
            Player current = game.currentPlayer();
            log(current.name() + " holds with " + turnTotal);
            current.addTurnScore(turnTotal);
            
            if (current.isWinner()) 
            {
                updateStatsTable();
                int choice = JOptionPane.showConfirmDialog(this, current.name() + " WINS!\n\nplay another game?", 
                    "game over", JOptionPane.YES_NO_OPTION);
                    
                if (choice == JOptionPane.YES_OPTION) 
                {
                    cardLayout.show(mainPanel, SCREEN_SPLASH);
                }
                else 
                {
                    System.exit(0);
                }
            } 
            else
            {
                turnTotal = 0;
                endTurn();
            }
        }

        private void endTurn() 
        {
            game.switchPlayers();
            turnTotal = 0;
            checkTurnState();
        }

        //helper methods

        private void showSettingsDialog() 
        {
            JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "game settings", true); 
            dialog.setLayout(new BorderLayout());
            dialog.setSize(450, 500);
            dialog.setLocationRelativeTo(this); //sets location relative to main window

            JPanel content = new JPanel();
            content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
            content.setBorder(new EmptyBorder(10, 10, 10, 10));

            JPanel generalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            generalPanel.setBorder(new TitledBorder("general"));
            JCheckBox dtCheck = new JCheckBox("double trouble mode");
            dtCheck.setSelected(game.getDoubleTroble());
            JSpinner winScoreSpin = new JSpinner(new SpinnerNumberModel(game.getPlayers().get(0).winningScore(), 10, 1000, 10));
            generalPanel.add(dtCheck);
            generalPanel.add(new JLabel("goal:"));
            generalPanel.add(winScoreSpin);
            content.add(generalPanel);

            JPanel playersPanel = new JPanel();
            playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.Y_AXIS));
            playersPanel.setBorder(new TitledBorder("players (name & strategy)"));

            List<JTextField> nameFields = new ArrayList<>();
            List<JComboBox<String>> stratCombos = new ArrayList<>();

            for (Player p : game.getPlayers()) 
            {
                JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
                row.add(new JLabel("name:"));
                JTextField nameField = new JTextField(p.name(), 10);
                nameFields.add(nameField);
                row.add(nameField);

                if (p instanceof ComputerPlayer) 
                {
                    JComboBox<String> stratBox = new JComboBox<>(new String[]{"hold at 23", "go big or go home"});
                    stratBox.setSelectedIndex(0); 
                    stratCombos.add(stratBox);
                    row.add(stratBox);
                } 
                else
                {
                    stratCombos.add(null);
                }
                playersPanel.add(row);
            }
            content.add(playersPanel);
            dialog.add(content, BorderLayout.CENTER);

            JPanel btnPanel = new JPanel();
            JButton saveBtn = new JButton("save & continue");
            JButton cancelBtn = new JButton("cancel");

            saveBtn.addActionListener(e -> 
            {
                game.setDoubleTrouble(dtCheck.isSelected());
                int newScore = (Integer) winScoreSpin.getValue();


                for (int i = 0; i < game.getPlayers().size(); i++) 
                {
                    Player p = game.getPlayers().get(i);
                    p.setName(nameFields.get(i).getText());
                    p.setWinningScore(newScore);

                    if (p instanceof ComputerPlayer && stratCombos.get(i) != null) {
                        ComputerPlayer cpu = (ComputerPlayer) p;

                        int selectedIndex = stratCombos.get(i).getSelectedIndex();

                        if (selectedIndex == 0)
                        {
                            cpu.setStrategy(new HoldAtNStrategy(23));
                        } 
                        else
                        {
                            cpu.setStrategy(new CustomStrategy());
                        }
                    }
                }
                updateStatsTable();
                currentTurnLabel.setText(game.currentPlayer().name() + "'s turn (total: " + turnTotal + ")");
                log("settings updated.");
            });

            cancelBtn.addActionListener(e -> dialog.dispose());

            btnPanel.add(saveBtn);
            btnPanel.add(cancelBtn);
            dialog.add(btnPanel, BorderLayout.SOUTH);

            dialog.setVisible(true);
        }

        private void updateStatsTable() 
        {
            statsModel.setRowCount(0); //clear table
                    
            for (Player p : game.getPlayers()) 
            {
                //add the player data directly to the row
                statsModel.addRow(new Object[]
                {
                    p.name(), 
                    p.gameScore(), 
                    p.maxTurnScore(), 
                    p.diceRolls(), 
                    p.SnakeEyes(), 
                    p.doubleTroubles()
                });
            }
        }

        private void updateDiceIcons(int d1, int d2) 
        {
            die1Label.setIcon(loadDiceIcon(d1));
            die2Label.setIcon(loadDiceIcon(d2));
        }

        private ImageIcon loadDiceIcon(int num) //i have no clue why the border is so wierd for the dice jpegs. i gave up
        {
            URL url = getClass().getResource("/images/die_" + num + ".jpg"); 
            return new ImageIcon(url);

        }

        private void log(String msg) 
        {
            gameLog.append(msg + "\n");
        }
    }

    private class PlayerConfigRow extends JPanel //helper for setup
    {
        private JTextField nameField;
        private JComboBox<String> typeCombo;
        private JComboBox<String> strategyCombo;

        public PlayerConfigRow(int playerNum) 
        {
            setLayout(new FlowLayout(FlowLayout.LEFT, 15, 5));

            add(new JLabel("Player " + playerNum + " Name:"));
            nameField = new JTextField("Player " + playerNum, 10);
            add(nameField);

            add(new JLabel("Type:"));
            typeCombo = new JComboBox<>(new String[]{"Human", "Computer"});
            add(typeCombo);

            add(new JLabel("Strategy:"));
            strategyCombo = new JComboBox<>(new String[]{"Hold at 23", "Custom"});
            strategyCombo.setEnabled(false); 
            add(strategyCombo);

            typeCombo.addActionListener(e -> 
            {
                boolean isComputer = "Computer".equals(typeCombo.getSelectedItem());
                strategyCombo.setEnabled(isComputer);
            });
        }

        public Player createPlayer(int winningScore) 
        {
            String name = nameField.getText();
            String type = (String) typeCombo.getSelectedItem();

            if ("Human".equals(type))
            {
                return new HumanPlayer(name, winningScore);
            }
            else
            {
                ComputerStrategy strategy;
                if (strategyCombo.getSelectedIndex() == 0)
                {
                    strategy = new HoldAtNStrategy(23);
                } 
                else
                {
                    strategy = new CustomStrategy();
                }
                return new ComputerPlayer(name, strategy, winningScore);
            }
        }
    }

    public static void main(String[] args) 
    {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

            TwoDicePigGUI gui = new TwoDicePigGUI(); //when playing against 2 CPU's it shows winning screen first. click "yes" to see game results
            gui.setVisible(true);
    }
}