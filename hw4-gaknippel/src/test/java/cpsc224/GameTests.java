package cpsc224;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GameTests {
    
    @Test
    void testReturnPlayers()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);

        assertEquals(players, g.getPlayers());
    }

    @Test
    void testCurrentPlayer()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);

        assertEquals(player1, g.currentPlayer());
    }

    @Test
    void testResetForNewGame()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);

        g.switchPlayers();

        g.resetForNewGame();

        assertEquals(player1, g.getPlayers().get(0));
    }

    @Test
    void testDoubleTrouble()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);

        g.setDoubleTrouble(true);

        assertEquals(true, g.getDoubleTroble());
    }   

    @Test
    void testSwitchPlayers()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");

        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);

        g.switchPlayers();

        assertEquals(g.currentPlayer(), g.currentPlayer());
    }

    @Test
    void testHasWinner()
    {
        Player player1 = new Player ("player 1");
        Player player2 = new Player ("player 2");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        Game g = new Game(players);   

        player1.addTurnScore(100);

        assertEquals(true, g.hasWinner());
    }



}
