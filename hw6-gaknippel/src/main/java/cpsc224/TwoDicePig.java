/*
 * Name: Greyson Knippel
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-6
 *
 * Description: a GUI implementation using java swing of the TwoDicePig game we've made so far
 * 
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
   * gets user input for integers.
   * @param title the title string of the menu
   * @return user input (an int)
   */

  public static int getIntInput(String title)
  {
    Scanner in = new Scanner(System.in);

    System.out.println("\n" + title + "\n"); //prints out title message

    while(true) //only breaks if break; or return.
    {
      System.out.print("Your choice: ");
      while(!in.hasNextInt()) //if the next imput is NOT an integer.
      {
        in.next(); //throw away input token
        System.out.println("invalid input. choose something that is an integer.");
        System.out.print("Your choice: ");

      }
      int input = in.nextInt(); 
      if(input > 0) //if input in range (most of the time 1-2 or 1-3)
      {
        return input;
      }
      else
      { 
        System.out.println("invalid input. choose in integer that is positive.");
      }
    }
  }

  /**
   * gets user input for strings.
   * @param title the title string of the menu
   * @return user input (a string)
   */

  public static String getStringInput(String title)
  {
    {
      Scanner in = new Scanner(System.in);

      System.out.println("\n" + title + "\n"); //prints out title message

      while(true) //only breaks if break; or return.
      {
        System.out.print("Your choice: ");
    
        String input = in.nextLine(); 
        
        return input;
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
   * @param isTournament a boolean if the game being played is in tournament mode
   */
  public static void displayScore(Game game, boolean isTournament) {
    // TODO: Update the display of the scoreboard as per the homework
    //       instructions.
    if(isTournament) //if in tournament mode
    {
      System.out.println("\n----------------------------------------");
      System.out.println("------------TOURNAMENT SCORE------------");
      System.out.println(game.getPlayers().get(0).name() + " has " + game.getPlayers().get(0).getTournamentWin() + " tournament wins.");
      System.out.println(game.getPlayers().get(1).name() + " has " + game.getPlayers().get(1).getTournamentWin() + " tournament wins.");
      System.out.println("----------------------------------------");
      System.out.println("----------------------------------------");
    }


    System.out.println("\n=====================================================\n");

    if(game.getDoubleTroble() == true) //if in double trouble mode
    {
      System.out.println("DOUBLE TROUBLE MODE!!!\n");
    }
    for(int i = 0; i < game.getPlayers().size(); i++)
    {
      System.out.println(game.getPlayers().get(i).name() + "\n");
      System.out.println("Total Score...: " + game.getPlayers().get(i).gameScore());
      System.out.println("Max Turn Score: " + game.getPlayers().get(i).maxTurnScore());
      System.out.println("Dice Rolls....: " + game.getPlayers().get(i).diceRolls());
      System.out.println("Snake Eyes....: " + game.getPlayers().get(i).SnakeEyes());
      if(game.getDoubleTroble() == true) //only displays if in double trouble mode
      {
      System.out.println("Doubles Rolled: " + game.getPlayers().get(i).doubleTroubles());
      }
      System.out.println("\n---------------------------------------------------");
    }

    System.out.println("\n=====================================================\n");

  }

  /**
   * gets the lead opponent of the game (the player with the highest score)
   * @param game the current game being played
   * @return the lead opponent (a player)
   */

  public static Player getLeadOpponent(Game game)
  {
    Player lead = null;

    int maxScore = -1; //max score is -1 so any other score than be the max 

    Player currentPlayer = game.currentPlayer();

    for(int i = 0; i < game.getPlayers().size(); i++)
    {
      Player p = game.getPlayers().get(i);

      if(p != currentPlayer) //if the player in this loop is not the current player
      {
        if(p.gameScore() > maxScore) //if that player's game score is greater than the current max score
        {
          maxScore = p.gameScore();
          lead = p; //make the lead player that player
        }
      }
    }

    if(lead == null && game.getPlayers().size() > 1) //if nobody is in the lead
    {
      ArrayList<Player> players = game.getPlayers();
      int currentIndex = -1;

      for(int i = 0; i < players.size(); i++)
      {
        if(players.get(i) == currentPlayer)
        {
          currentIndex = i; //finds the current player's index, will use this later
          break;
        }
      }

      int nextIndex = (currentIndex + 1) % players.size(); //simply the next player

      lead = players.get(nextIndex);
    }
    if(lead == null) //edge case: if only 1 computer player in game
    {
      Player opponent = new Player("dummy"); //makes a dummy opponent
      lead = opponent;
    }

    return lead;
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
   * @param game the current game being played
   * @return true if the player wants to continue playing, false otherwise
   */
  public static boolean playTurn(HumanPlayer player, Game game) {
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
    boolean mustRollAgain = false; //for the double trouble gamemode

    while(true)
    {
      if(mustRollAgain) //if gotten double trouble from last turn
      {
        System.out.println("DOUBLE TROUBLE!! you have to roll again!\n");
        mustRollAgain = false; //reset double trouble flag
      }
      else //else is used here to ignore the hold (option 2) if double trouble is activated
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
      if(die1 == die2 && game.getDoubleTroble() == true) //double trouble!
      {
        System.out.println("\nyou rolled doubles! you must roll again.\n");
        player.addDoubleTroubles();
        turnTotal += die1 + die2;
        mustRollAgain = true;
      }

      turnTotal += die1 + die2;
    }

    return true;
  }
  
  /**
   * Play a single computer player's turn.
   * @param player the current computer player
   * @param game the current game being played
   * @return true to play again
   */
  public static boolean playTurn(ComputerPlayer player, Game game) {
    // TODO: Re-implement your playComputerTurn method from HW-2 here
    //       using the modified (Player) parameters.  Your
    //       implementation must use the dice instance variable (see
    //       above).

        displayTurn(player);
        System.out.println(player.name() + " is thinking...");
        int turnTotal = 0;
        boolean mustRollAgain = false; 

        Player opponent = getLeadOpponent(game); //gets the lead opponent

        while (true) 
        {
          if(mustRollAgain) //double trouble stuff (again)
          {
            System.out.println(player.name() + " rolled doubles and must roll again!");
            mustRollAgain = false;
          }
          else
          {
            if (player.hold(opponent, turnTotal)) //hold decision
            {
                System.out.println(player.name() + " decides to hold with a turn total of " + turnTotal + "."); 
                player.addTurnScore(turnTotal);
                break; 
            } 
          }
            System.out.println(player.name() + " decides to roll.");
            int die1 = dice.roll();
            int die2 = dice.roll();
            player.addDiceRoll();
            System.out.println(player.name() + " rolled a " + die1 + " and a " + die2 + ".");

            if (die1 == 1 && die2 == 1) 
            {
                System.out.println("\nSnake eyes! All points are lost!\n");
                player.addSnakeEyes();
                player.resetGameScore();
                break;
            }
            if (die1 == 1 || die2 == 1) 
            {
                System.out.println("\nA 1 was rolled! Turn ends with no points.\n");
                break;
            }
            if (die1 == die2 && game.getDoubleTroble() == true)
            {
              System.out.println("\n" + player.name() + " rolled doubles! you must roll again.\n"); //double trouble
              player.addDoubleTroubles();
              turnTotal += die1 + die2;
              mustRollAgain = true;
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
    {
      return playTurn(p, game);
    }
    else if (player instanceof ComputerPlayer p)
    {
      return playTurn(p, game);
    }
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

    Scanner in = new Scanner(System.in);

    ArrayList<Player> newPlayers = new ArrayList<>();  

    //adds a bunch of new arguments/setup stuff for new options

    int playerCount = getIntInput("How many players do you want for this game?");

    int winningScore = getIntInput("what is the winning score for this game?");

    boolean doubleTroubleChoice = displayContinuePrompt("\ndo you want to enable double trouble mode? (y/n)");

    for(int i = 0; i < playerCount; i++)
    {
      String playerName;

      String defaultName = "Player " + (i + 1); //default player name (just Player 1, 2, 3, etc.)

      List<String> nameChoices = new ArrayList<>();
      nameChoices.add("1. use default player name?" + " (" + defaultName + ")");
      nameChoices.add("2. custom player name");

      int choice = displayChoiceMenu("choose a player name! (for " + defaultName + ")", nameChoices);

      if(choice == 1)
      {
        playerName = defaultName;
      }
      else
      {
        String customName = getStringInput("enter custon name: ");
        playerName = customName;
      }
    

      List<String> playerTypes = new ArrayList<>();
      playerTypes.add("1. Human Player");
      playerTypes.add("2. Computer Player");


      int typeChoice = displayChoiceMenu("what type of player do you want " + playerName + " to be?", playerTypes); //asks for type of player. can do only computers!!

      if(typeChoice == 1)
      {
        newPlayers.add(new HumanPlayer(playerName, winningScore));
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

          newPlayers.add(new ComputerPlayer(playerName, strategy, winningScore));
      }

     } 

     return new Game(newPlayers, doubleTroubleChoice); //returns the game w/ players and game modifications

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
                      Welcome to the Two Dice Pig Game! (v3)
                      --------------------------------------""");

    
  while (displayContinuePrompt("Play a new game? (y/n): ")) {
    


      Game game = gameSetup(); 

      boolean isTournament = false; 

      int winsNeeded = 1; //wins needed for tournament win

      if(game.getPlayers().size() == 2) //tournament can only be played with 2 players. (couldnt figure out multiple players)
      {
        if(displayContinuePrompt("play tournament mode? (y/n)"))
        {
          isTournament = true;
          int totalGames = getIntInput("best of how many games? (positive number)");
          winsNeeded = (totalGames/2) + 1; //player has to win n/2 + 1 games to win the tournament

          game.getPlayers().get(0).resetTournamentWins(); //resets players tournament wins to 0
          game.getPlayers().get(1).resetTournamentWins();
        }
      }
      else if(game.getPlayers().size() != 1) //if players is not 1, display this message
      {
        System.out.println("tournament mode is only for 2 players.");
      }

      boolean tournamentRunning = true;
      int gameNumber = 1; //tracker for amount of games ran

      while(tournamentRunning)
      {
        if(isTournament)
        {
          System.out.println("\n game number " + gameNumber + "! first to " + winsNeeded + " wins!");
        }

        boolean gameIsRunning = true;
        while(gameIsRunning && !game.hasWinner()) //mostly code from last time
        {
            displayScore(game, isTournament);

            gameIsRunning = playTurn(game);

            if(gameIsRunning && !game.hasWinner()) //switch players once turn is done
            {
              game.switchPlayers();
            }
        }

        if(game.hasWinner()) //once the game has a winner
        {
          Player winner = game.currentPlayer();
          System.out.println("\n" + winner.name() + " wins game #" + gameNumber + "!");

          if(isTournament)
          {
            winner.addTournamentWin(); //add a tournament win to the player

            if(winner.getTournamentWin() >= winsNeeded) //if tournament wins needed requirement is met
            {
              System.out.println("TOURNAMENT OVER!");
              System.out.println(winner.name() + " wins the tournament!");
              displayScore(game, isTournament); //display final score
              tournamentRunning = false;
            }
            else
            {
              for(int i = 0; i < game.getPlayers().size(); i++)
              {
                game.getPlayers().get(i).resetForNewGame(); //reset players stats for new game
              }
              game.resetForNewGame(); //resets current player index
              gameNumber++; //increase game number
            }
          }
          else
          {
            tournamentRunning = false;
          }
        }
        else
        {
          System.out.println("\n Game " + gameNumber + " was quit.");
          tournamentRunning = false;
        }
      }
  }
  System.out.println("\nThanks for playing!");

}

}

