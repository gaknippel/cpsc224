package cpsc224;

/**
 * A class for the main game that is running.
 */
public class Game {

    private static Player player1 = new Player("player1");
    private static Player player2 = new Player("player2");

    private static int currPlayer = 1;

  /**
   * Create a game with the determined player types in setup.
   * @param player1 always you
   * @param player2 player 2 (either human or cpu)
   */
    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currPlayer = 1;
    }
    
    /**
    * A simple method that returns player1.
    * @return player1
    */    
    public Player player1()
    {
        return player1;
    }
    /**
    * A simple method that returns player2.
    * @return playe2
    */    
    public Player player2()
    {
        return player2;
    }

   /**
    * A simple method that returns current player.
    * @return current player
    */  
    public Player currentPlayer()
    {
        if(this.currPlayer == 1)
        {
            return this.player1;
        }
        return this.player2;
    }
   /**
    * A simple method that returns current opponent.
    * @return current opponent
    */  
    public Player currentOpponent()
    {
         if(this.currPlayer == 1)
        {
            return this.player2;
        }
        return this.player1;
    }
   /**
    * A simple method that switches the players by changing the currPlayer variable.
    */  
    public void switchPlayers()
    {
        if(this.currPlayer == 1)
        {
            this.currPlayer = 2;
        }
        else
        {
            this.currPlayer = 1;
        }
    }
   /**
    * A simple method that checks if the game has a winner or not.
    * @return true or false if the game has a winner or not
    */  
    public static boolean hasWinner()
    {
        if(player1.isWinner() || player2.isWinner())
        {
            return true;
        }

        return false;
    }
}