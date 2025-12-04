/*
 * NAME: Greyson Knippel
 * CLASS: CPSC 224, Fall 2025
 * ASSIGNMENT: HW-2
 *
 * DESCRIPTION: TwoDicePig is a game that can be played with two players, or one player against a CPU.
 * 
 * on each player's turn, they roll two dice. the sum of the dice rolls are added to the players "bank"
 * and they have a choice to store the points or keep rolling. 
 * 
 * But if the player rolled a 1 on one die, they
 * lose all the points gained on that turn, and their turn ends. 
 * But if the player rolls two ones on each die,
 * they lose all their points as well as their turn. the first player to reach 100 points or more wins!
 */

package cpsc224;

import java.util.Random;
import java.util.Scanner;

/**
 * Basic implementation of a simple console-based two-dice pig game
 * for CPSC 224, HW-2.
 */
public class TwoDicePig {

  
  /**
   * Simulates a roll of a single six-sided die.
   * @return a value between 1 and 6
   */
  public static int rollDie() {
    Random random = new Random(); //creates new random obj
    int x = random.nextInt(6); //dice  roll from #'s - 1-6
    x+=1; //offset from starting on 0
    return x;
  }

  
  /**
   * Displays a simple menu of choices and prompts the user for their
   * menu selection.
   * @param title the title string of the menu
   * @param choices a list of n menu choices
   * @return user selection as a number from 1 to n
   */
  public static int displayChoiceMenu(String title, String[] choices) {
    Scanner in = new Scanner(System.in);

    System.out.println("\n" + title + "\n"); //prints out title message

    for(int i = 0; i <= choices.length - 1; i++) //loop print the array of options.
    {
      System.out.println(choices[i]);
    }

    while(true) //only breaks if break; or return.
    {
      System.out.print("Your choice (1-" + choices.length + "): ");
      while(!in.hasNextInt()) //if the next imput is NOT an integer.
      {
        in.next(); //throw away input token
        System.out.println("invalid input. choose something that is an integer.");
        System.out.print("Your choice (1-" + choices.length + "): ");

      }
      int input = in.nextInt(); 
      if(input <= choices.length && input > 0) //if input in range (most of the time 1-2 or 1-3)
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
   * @param prompt the prompt to display
   * @return true if user wants to continue, false otherwise
   */ 
  public static boolean displayContinuePrompt(String text) {
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
   * Display the score of the game.
   * @param p1Score player 1's score
   * @param p2Score player 2's score
   */
  public static void displayScore(int p1Score, int p2Score) {
    System.out.println("-------------------\n" + "Player 1's score: " + p1Score + "\nPlayer 2's Score: " + p2Score + "\n-------------------\n"); 
  }

  
  /**
   * Display a message that it is a player's turn.
   * @param player the number of the player whose turn it is
   */
  public static void displayTurn(int player) {
        System.out.println("\n---------------\n" + "Player " + player + "'s turn\n" + "---------------\n");
  }

  
  /**
   * Play a single human player's turn.
   * @param player the player's number (either 1 or 2)
   * @param score the player's current score in the game
   * @return player's new score or -1 if player quits
   */
  public static int playHumanTurn(int player, int score) {
    displayTurn(player);
    String[] game_choices = {"1. Roll the dice", "2. Hold", "3. Quit Game"};
    int turnTotal = 0;

    while(true)
    {
      int choice = displayChoiceMenu("Player " + player + "'s turn total is " + turnTotal + ":" , game_choices);

      if(choice == 3) //quit
      {
        return -1;
      }
      if (choice == 2) //hold
      {
         System.out.println("\nPlayer " + player + " holds.\n");
         return score + turnTotal;
      }


      int die1 = rollDie();
      int die2 = rollDie();

      System.out.println("\nYou rolled a " + die1 + " and a " + die2 + ".");


      if (die1 == 1 && die2 == 1) //snakme eyes, both die rolled are 1's
      {
        System.out.println("\nsnake eyes! you lose all points & lose your turn!\n");
        return 0;
      }
      if (die1 == 1 || die2 == 1) //only return their previous score (one die was 1)
      { 
        System.out.println("\nyou rolled a 1 on one of your dice rolls! you get no points on this turn.\n");
        return score;
      }

      turnTotal += die1 + die2;
    }
  }

  
  /**
   * Play a single computer player's turn implementing the "Hold at 23"
   * (non-optimal) strategy.
   * @param player the player's number (either 1 or 2)
   * @param score the player's current score in the game
   * @return player's new score or -1 if player quits
   */
  public static int playComputerTurn(int player, int score) {
    displayTurn(player);
    int turnTotal = 0;

    while(turnTotal <= 23) //23 or more is the max the computer can have until they hold.
    {
      int die1 = rollDie();
      int die2 = rollDie();

      System.out.println("\nPlayer 2 rolled a " + die1 + " and a " + die2 + ".\n");

      if (die1 == 1 && die2 == 1)
      {
        System.out.println("\nPlayer 2 rolled a snake eyes!\n");
        return 0;
      }
      if (die1 == 1 || die2 == 1)
      {
        System.out.println("\nPlayer 2 rolled a 1 on one of their dice rolls! no points this turn for them!\n");
        return score;
      }

      turnTotal += die1 + die2;

    }

    System.out.println("\nPlayer 2 decides to hold.\n");
    return score + turnTotal;
  }

  
  /**
   * Main driver for the game that (repeatedly) plays rounds of
   * two-dice pig, which includings prompting for play mode (human or
   * computer) and then repeatedly allowing each player to take a turn
   * until a player wins (or quits). 
   * @param args the command line arguments (not used)
   */
  public static void main(String[] args) {
    
    System.out.println( """
                       --------------------------------------
                       Welcome to the Two Dice Pig Game! (v1)
                       --------------------------------------""");  //main message
                       
    String[] start_choices = {"1. Human vs. Human", "2. Human vs. Computer"};

    displayContinuePrompt("Play a new game? (y/n)");
    

    int gameMode = displayChoiceMenu("Player mode: ", start_choices); 
    int p1Score = 0;
    int p2Score = 0;
    int currentPlayer = 1; //variable to check who to give points to

    while(true)
    {
      int currentScore;
      if(currentPlayer == 1)
      {
        currentScore = p1Score;
      }
      else
      {
        currentScore = p2Score;
      }
      
      int newScore = 0;

        if(gameMode == 1) //vs human
        {
          newScore = playHumanTurn(currentPlayer, currentScore);
        }
        else //vs computer
        {
          if(currentPlayer == 1)
          {
            newScore = playHumanTurn(currentPlayer, currentScore);
          }
          if(currentPlayer == 2)
          {
          System.out.println("It's the computer's turn!");
          newScore = playComputerTurn(currentPlayer, currentScore);
          }
        }

      if (newScore == -1) //if chose option 3 (quit)
      {
        System.out.println("\nthanks for playing!\n");
        System.exit(0);
        break;
      }

      //update score
      if(currentPlayer == 1)
      {
        p1Score = newScore;
      }
      if(currentPlayer == 2)
      {
        p2Score = newScore;
      }

      displayScore(p1Score, p2Score);

      //check for winner
      if (p1Score >= 100 || p2Score >= 100)
      {
        System.out.print("\nPlayer " + currentPlayer + " Wins!\n");
        if(!displayContinuePrompt("Play a new game? (y/n): "))
        {
          break;
        }
        else
        {
          //reset game
          p1Score = 0;
          p2Score = 0;
          currentPlayer = 1;
          gameMode = displayChoiceMenu("Player Mode: ", start_choices);
        }
      }
      else //switch turns
      {
        if(currentPlayer == 1)
        {
          currentPlayer = 2;
        }
        else
        {
          currentPlayer = 1;
        }
      }
    }
//hi
  }

}
