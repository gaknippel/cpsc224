/*
 * Name: ... your name here ...
 * Date: Fall 2025
 * Class: CPSC 224
 * Assignment: HW-2
 */

package cpsc224;


/**
 * A pig player class for tracking information about the player
 * including their current score in a game and basic play statistics.
 */ 
public class Player {

  private String name;             // the name of the player (e.g., "Player 1")
  private int maxTurnScore;        // the most points they have scored in a turn 
  private int diceRolls;    // the number of dice rolls they've made in the game
  private int snakeEyes;    //the number of snake eyes they've made in the game
  private int gameScore;           // their total game score
  private int winningScore = 100;  // the number of points needed for a winning score
  private int doubleTroubles;     //amount of double troubles player has
  private int tournamentWins = 0; //amount of tournament wins a player has

  /**
   * Create a player with the given name (using the default winning
   * score).
   * @param name the player's name
   */
  public Player(String name) {
    this.name = name;
  }

  /**
   * Create a player with the given name and target winning score.
   * @param name the player's name
   * @param winningScore the target winning score
   */
  public Player(String name, int winningScore) {
    this.name = name;
    this.winningScore = winningScore;
  }

  /**
   * The name of the player.
   * @return the player's name
   */
  public String name() {
    return name;
  }

  /**
   * The target winning score.
   * @return the player's target score
   */
  public int winningScore() {
    return winningScore;
  }

  /**
   * The current game score.
   * @return the player's current score in the game
   */
  public int gameScore() {
    return gameScore;
  }

  /**
  * A simple method that increments tournament wins by 1.
  */    
  public void addTournamentWin()
  {
    this.tournamentWins++;
  }

    /**
    * A simple method that returns the tournament wins
    * @return tournament wins amount
    */    
  public int getTournamentWin()
  {
    return this.tournamentWins;
  }
    /**
    * A simple method that resets tournament wins
    */    
  public void resetTournamentWins()
  {
    this.tournamentWins = 0;
  }
  /**
   * Sets the current game score to zero (e.g., if they roll two 1's).
   */
  public void resetGameScore() {
    gameScore = 0;
  }

  /**
   * Checks if the player has won the game (i.e., their game score is
   * at least as large as the target winning score).
   * @return true if the player has won and false otherwise
   */ 
  public boolean isWinner() {
    if(gameScore >= winningScore)
    {
      return true;
    }
    
    return false;
  }

    /**
    * A simple method that resets a bunch of stats for a new game
    */    
  public void resetForNewGame()
  {
    this.gameScore = 0;
    this.maxTurnScore = 0;
    this.diceRolls = 0;
    this.doubleTroubles = 0;
    this.snakeEyes = 0;
  }

  /**
   * The number of dice rolls the player has made in the game.
   * @return the total number of dice rolls made by the player
   */ 
  public int diceRolls() {
    return diceRolls;
  }

  /**
   * Adds a dice roll to the total number of rolls
   */
  public void addDiceRoll() {
    diceRolls++;
  }
    /**
    * A simple method that returns amount of snake eyes
    * @return amount of snake eyes
    */    
  public int SnakeEyes()
  {
    return snakeEyes;
  }
    /**
    * A simple method that increments snake eyes by 1
    */    
  public void addSnakeEyes()
  {
    snakeEyes++;
  }  
    /**
    * A simple method that returns amount of double troubles
    * @return amount of double troubles
    */    
  public int doubleTroubles()
  {
    return doubleTroubles;
  }
    /**
    * A simple method that increments double troubles by 1
    */    
  public void addDoubleTroubles()
  {
    doubleTroubles++;
  }
  /**
   * The highest turn score the player has received in the game.
   * @return the player's highest turn score so far
   */ 
  public int maxTurnScore() {
    return maxTurnScore;
  }

  /**
   * Updates the players score after their turn is complete.
   * @param turnScore the turn score to be added to the total score
   */
  public void addTurnScore(int turnScore) {
     if (turnScore > this.maxTurnScore) {
        this.maxTurnScore = turnScore;
    }
    this.gameScore += turnScore;
  }

}
