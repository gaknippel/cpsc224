/*
 * NAME: Greyson Knippel
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-3
 *
 * DESCRIPTION: This assignment is an extention on hw-2's TwoDicePig game. This time
 * we are implementing classes and using inheritance to practice Java OOP. 
 * We are also supposed to make a javadoc out of all this documentation as well. 
 */

package cpsc224;  

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Basic implementation, with OOP helper classes, for a simple console-based
 * two-dice pig game for CPSC 224, HW-2.
 */
public class TwoDicePig {

  private static Dice dice = new Dice();     // class variable for rolling dice

  /**
   * Displays a simple menu of choices and prompts the user for their
   * menu selection.
   * @param title the title string of the menu
   * @param choices a list of n menu choices
   * @return user selection as a number from 1 to n
   */
  
  public static int displayChoiceMenu(String title, List<String> choices) {
    // TODO: use your previous implementation but update to use the
    //       choices list (vs the old version using an array).

    Scanner in = new Scanner(System.in);

    System.out.println("\n" + title + "\n"); //prints out title message

    for(int i = 0; i <= choices.size() - 1; i++) //loop print the array of options.
    {
      System.out.println(choices.get(i));
    }

    while(true) //only breaks if break; or return.
    {
      System.out.print("Your choice (1-" + choices.size() + "): ");
      while(!in.hasNextInt()) //if the next imput is NOT an integer.
      {
        in.next(); //throw away input token
        System.out.println("invalid input. choose something that is an integer.");
        System.out.print("Your choice (1-" + choices.size() + "): ");

      }
      int input = in.nextInt(); 
      if(input <= choices.size() && input > 0) //if input in range (most of the time 1-2 or 1-3)
      {
        return input;
      }
      else
      { 
        System.out.println("invalid input. choose in integer in the range of options.");
      }
    }
  }  
  
  /**
   * Displays a simple prompt to continue (yes or no answer).
   * @param text the prompt to display
   * @return true if user wants to continue, false otherwise
   */ 
  public static boolean displayContinuePrompt(String text) {
    // TODO: use your previous implementation here
    Scanner in = new Scanner(System.in);
    
    while(true)
    {
      System.out.print(text + " ");
      String input = in.nextLine().toUpperCase(); //uppercases the input to avoid case sensitive input

      if(input.equals("Y"))
      {
        return true;
      }
      else if(input.equals("N"))
      {
        System.out.println("\nGoodbye!");
        System.exit(0); //exits program
        return false;
      }
      else
      {
        System.out.println("invalid input! try entering only y or n");
      }

    }
    
  }
  
  /**
   * Display the scores of the game.
   * @param game the current game being played
   */
  public static void displayScore(Game game) {
    // TODO: Update the display of the scoreboard as per the homework
    //       instructions.

    Player player1 = game.player1();
    Player player2 = game.player2();

    System.out.println("=====================================================\n");
    
    System.out.println("Player 1");

    System.out.println("Total Score...: " + player1.gameScore());
    System.out.println("Max Turn Score: " + player1.maxTurnScore());
    System.out.println("Dice Rolls....: " + player1.diceRolls());

    System.out.println("Player 2");

    System.out.println("Total Score...: " + player2.gameScore());
    System.out.println("Max Turn Score: " + player2.maxTurnScore());
    System.out.println("Dice Rolls....: " + player2.diceRolls());

    System.out.println("\n=====================================================");

  }
  
  /**
   * Display a message that it is a given player's turn.
   * @param player the player whose turn it is
   */
  public static void displayTurn(Player player) {
    // TODO: Update this method to use the player's name. 
    System.out.println("\n---------------\n" + player.name() + "'s turn\n" + "---------------\n");

  }
  
  /**
   * Plays a single human player's turn, prompting for player input.
   * @param player the human player
   * @param opponent the current player's opponent
   * @return true if the player wants to continue playing, false otherwise
   */
  public static boolean playTurn(HumanPlayer player, Player opponent) {
    // TODO: Re-implement your playHumanTurn method from HW-2 here
    //       using the modified (Player) parameters.  Your
    //       implementation must use the dice instance variable (see
    //       above).
    displayTurn(player);
    List<String> game_choices = new ArrayList<>();

    game_choices.add("1. Roll the Dice");
    game_choices.add("2. Hold");
    game_choices.add("3. Quit Game");

    int turnTotal = 0;

    while(true)
    {
      int choice = displayChoiceMenu(player.name() + "'s turn total is " + turnTotal + ":" , game_choices);

      if(choice == 3) //quit
      {
        return false;
      }
      if (choice == 2) //hold
      {
         System.out.println("\n" + player.name() + " holds.\n");
         player.addTurnScore(turnTotal);
         break;
      }


      int die1 = dice.roll();
      int die2 = dice.roll();
      player.addDiceRoll();

      System.out.println("\nYou rolled a " + die1 + " and a " + die2 + ".");


      if (die1 == 1 && die2 == 1) //snakme eyes, both die rolled are 1's
      {
        System.out.println("\nsnake eyes! you lose all points & lose your turn!\n");
        player.resetGameScore();
        break;
      }
      if (die1 == 1 || die2 == 1) //only return their previous score (one die was 1)
      { 
        System.out.println("\nyou rolled a 1 on one of your dice rolls! you get no points on this turn.\n");
        break;
      }

      turnTotal += die1 + die2;
    }

    return true;
  }
  
  /**
   * Play a single computer player's turn.
   * @param player the current computer player
   * @param opponent the current players opponent
   * @return true to play again
   */
  public static boolean playTurn(ComputerPlayer player, Player opponent) {
    // TODO: Re-implement your playComputerTurn method from HW-2 here
    //       using the modified (Player) parameters.  Your
    //       implementation must use the dice instance variable (see
    //       above).

        displayTurn(player);
        System.out.println(player.name() + " is thinking...");
        int turnTotal = 0;

        while (true) 
        {
            if (player.hold(opponent, turnTotal)) 
            {
                System.out.println(player.name() + " decides to hold with a turn total of " + turnTotal + ".");
                player.addTurnScore(turnTotal);
                break; 
            }

            System.out.println(player.name() + " decides to roll.");
            int die1 = dice.roll();
            int die2 = dice.roll();
            player.addDiceRoll();
            System.out.println(player.name() + " rolled a " + die1 + " and a " + die2 + ".");

            if (die1 == 1 && die2 == 1) 
            {
                System.out.println("\nSnake eyes! All points are lost!\n");
                player.resetGameScore();
                break;
            }
            if (die1 == 1 || die2 == 1) 
            {
                System.out.println("\nA 1 was rolled! Turn ends with no points.\n");
                break;
            }
            turnTotal += die1 + die2;
        }
        return true;
    }
  

  /**
   * Plays a turn of the game based on the player whose turn it is by
   * calling the appropriate playTurn method.
   * @param game the current game being played
   * @return true to continue playing, false otherwise
   */
  public static boolean playTurn(Game game) 
  {
    Player player = game.currentPlayer(); 
    if (player instanceof HumanPlayer p)
      return playTurn(p, game.currentOpponent());
    else if (player instanceof ComputerPlayer p)
      return playTurn(p, game.currentOpponent());
    return false;
  }

  /**
   * Prompts for and then sets up a new game based on user selections.
   * @return the new game to be played
   */
  public static Game gameSetup() {
    // TODO: Implement the initial game setup prompts here. These
    //       inlcude the "Human vs Human and Human vs Computer" prompt
    //       and the "Hold-At-23 vs Custom Strategy" prompt. 

    List<String> modeChoices = new ArrayList<>();
        modeChoices.add("1. Human vs. Human");
        modeChoices.add("2. Human vs. Computer");
        int gameMode = displayChoiceMenu("Select player mode:", modeChoices);

        Player player1 = new HumanPlayer("Player 1");
        Player player2;

        if (gameMode == 1) 
        {
          player2 = new HumanPlayer("Player 2");
        } 
        else 
        {
          List<String> strategyChoices = new ArrayList<>();
          strategyChoices.add("1. hold at 23");
          strategyChoices.add("2. custom (go big or go home)");

          int strategyChoice = displayChoiceMenu("Select computer strategy:", strategyChoices);
          
          ComputerStrategy strategy;

          if (strategyChoice == 1)
          {
            strategy = new HoldAtNStrategy(23);
          } 
          else 
          {
            strategy = new CustomStrategy();
          }

          player2 = new ComputerPlayer("computer", strategy);
        }
        
        return new Game(player1, player2);
  }
  
  /**
   * Main driver for the game that (repeatedly) plays rounds of
   * two-dice pig.
   * @param args the command line arguments (not used)
   */
  public static void main(String[] args) {
    // TODO: Fill out the rest of the method calling the appropriate
    //       helper methods. The helper classes and methods should
    //       generally simplify your previous implementation.

  System.out.println("""
                      --------------------------------------
                      Welcome to the Two Dice Pig Game! (v2)
                      --------------------------------------""");

    
  while (displayContinuePrompt("Play a new game? (y/n): ")) {
      Game game = gameSetup(); 
      boolean gameIsRunning = true;

      
      while (gameIsRunning && !game.hasWinner()) 
      {
        displayScore(game);
        gameIsRunning = playTurn(game); 
        
        if (!gameIsRunning) 
        {
          break;
        }

        if (game.hasWinner()) 
        {
          break; 
        }

            
        game.switchPlayers();
      }


      if (game.hasWinner()) 
      {
          displayScore(game);
          System.out.println("\n" + game.currentPlayer().name() + " Wins!\n");
      } 
      else 
      {
          System.out.println("\nThanks for playing!");
      }
  }

}

}
