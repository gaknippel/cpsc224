package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTests {
    
    @Test
    void testReturnPlayers()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        Game g = new Game(player1, player2);

        assertEquals(player1, g.player1());
        assertEquals(player2, g.player2());
    }

    @Test
    void testCurrentPlayerAndOpponent()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        Game g = new Game(player1, player2);

        assertEquals(player1, g.currentPlayer());
        assertEquals(player2, g.currentOpponent());
    }

    @Test
    void testSwitchPlayers()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        Game g = new Game(player1, player2);
        g.switchPlayers();

        assertEquals(player2, g.currentPlayer());
    }

    @Test
    void testHasWinner()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        Game g = new Game(player1, player2);     

        player1.addTurnScore(100);

        assertEquals(true, g.hasWinner());
    }



}
