package cpsc224;



/**
 * A class for the computer player (vs cpu).
 */
public class ComputerPlayer extends Player {
    
    private ComputerStrategy strategy;

  /**
   * creates a ComputerPlayer with the given name and selected strategy.
   * @param name the cpu's name
   * @param strategy the strategy type
   */
    public ComputerPlayer(String name, ComputerStrategy strategy)
    {
        super(name);
        this.strategy = strategy;
    }

  /**
   * creates a ComputerPlayer with the given name and selected strategy and winning score.
   * @param name the cpu's name
   * @param strategy the strategy type
   * @param winningScore the score required to win
   */
    public ComputerPlayer(String name, ComputerStrategy strategy, int winningScore)
    {
        super(name,winningScore);
        this.strategy = strategy;
    }

  /**
   * Determines whether the computer player should hold or not.
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true or false if holding or rolling
   */ 
    public boolean hold(Player opponent, int turnTotal) 
    {
        // cpu passes itself with "this"

        return this.strategy.hold(this, opponent, turnTotal);
    }

}
