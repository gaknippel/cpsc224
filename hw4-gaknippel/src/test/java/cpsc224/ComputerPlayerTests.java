package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ComputerPlayerTests {


    @Test
    void createdComputerPlayerGivesCorrectName()
    {
        ComputerStrategy strategy = new HoldAtNStrategy(23);
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);  
        
        assertEquals("Computer", cpu.name());
    }

    @Test
    void testHoldforHoldAtN()
    {
        ComputerStrategy strategy = new HoldAtNStrategy(23);
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);
        Player opponent = new HumanPlayer("dummy");

        boolean resultWhenBelow23 = cpu.hold(opponent, 19);

        assertEquals(false, resultWhenBelow23);


        boolean resultWhenAbove23 = cpu.hold(opponent, 24);

        assertEquals(true, resultWhenAbove23);
    }

    @Test
    void testHoldforCustomStrat()
    {
        ComputerStrategy strategy = new CustomStrategy();
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);
        Player opponent = new HumanPlayer("dummy");       


        boolean resultWhenBelow30 = cpu.hold(opponent, 29);

        assertEquals(false, resultWhenBelow30);


        boolean resultWhenAbove30 = cpu.hold(opponent, 31);

        assertEquals(true, resultWhenAbove30);
    }
    
}
