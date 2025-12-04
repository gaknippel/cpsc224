package cpsc224;

public class CustomStrategy implements ComputerStrategy{
   /**
   * Determines whether the computer player should hold or not (based on custom risky/safe strategy).
   * @param player the own cpu player
   * @param opponent the current player's opponent
   * @param turnTotal the total rolled so far in the turn
   * @return true or false if holding or rolling
   */    
    public boolean hold(Player player, Player opponent, int turnTotal){
        if(player.gameScore() + turnTotal >= player.winningScore()) //checks if turntotal + actual score
        {                                                           //can win them the game or not.
            return true;
        }
        if(player.gameScore() > opponent.gameScore()) //safe strategy: if computer is ahead, player safer
        {                                             //than 23, play at 16.
            if(turnTotal >= 16)
            {
                return true;
            }
        }
        else if(opponent.gameScore() - player.gameScore() >= 20 || opponent.gameScore() - player.gameScore() == 0) //COMEBACK STRATEGY: gamble harder if the
        {                                                       //opponent is up by 20 or more points
            if(turnTotal >= 30)                                 // this is also the default/tied strategy
            {                                                   //since I think you should always go big or go home when
                return true;                                    //you aren't ahead.
            }
        }
        return false;
    }

}
