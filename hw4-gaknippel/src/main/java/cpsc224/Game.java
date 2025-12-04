package cpsc224;
import java.util.ArrayList;
/**
 * A class for the main game that is running.
 */
public class Game {

    private ArrayList<Player> players; //new array list of players for game

    private int currPlayerIndex; //current player index for the new arraylist of players

    private boolean isDoubleTrouble; //flag for double trouble

  /**
   * Create a game with the determined player types in setup.
   * @param players an array list of players in the game
   */
    public Game(ArrayList<Player> players) 
    {
        this.players = players;
        this.currPlayerIndex = 0;
        this.isDoubleTrouble = false;
    }

    public Game(ArrayList<Player> players, boolean isDoubleTrouble) //game with double trouble
    {
        this.players = players;
        this.currPlayerIndex = 0;
        this.isDoubleTrouble = isDoubleTrouble;
    }
    
    /**
    * A simple method that returns player1.
    * @return the array list of players in this game
    */    
    public ArrayList<Player> getPlayers()
    {
        return this.players;
    }

    /**
    * A simple method that resets player index for new game.
    */    
    public void resetForNewGame() //resets player index for array list to 0
    {
        this.currPlayerIndex = 0;
    }

    /**
    * A simple method that returns the double trouble boolean
    * @return boolean for isDoubleTrouble
    */    

    public boolean getDoubleTroble()
    {   
        return this.isDoubleTrouble;
    }

    /**
    * A simple method that sets double trouble
    */    

    public void setDoubleTrouble(boolean bool)
    {
        this.isDoubleTrouble = bool;
    }

   /**
    * A simple method that returns current player.
    * @return current player
    */  
    public Player currentPlayer() //the current player is the current player index
    {
        return this.players.get(this.currPlayerIndex);
    }

   /**
    * A simple method that switches the players by changing the currPlayer variable.
    */  
    public void switchPlayers() //switching players is just the next player in the array list
    {
        this.currPlayerIndex = (this.currPlayerIndex + 1) % this.players.size(); //mod so it wraps back around (2 % 2 is 0)
    }
   /**
    * A simple method that checks if the game has a winner or not.
    * @return true or false if the game has a winner or not
    */  
    public boolean hasWinner()
    {
        for (int i = 0; i < this.players.size(); i++) //checks through player list to see if any player is a winner
        {
            if(this.players.get(i).isWinner())
            {
                return true;
            }
        }

        return false;
    }
}