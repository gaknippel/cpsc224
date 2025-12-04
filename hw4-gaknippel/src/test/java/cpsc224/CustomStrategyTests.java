package cpsc224;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CustomStrategyTests {
    

    @Test
    void testIfComputerCanWin()
    {
        ComputerStrategy strategy = new CustomStrategy();
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);
        Player opponent = new HumanPlayer("dummy");

        cpu.addTurnScore(99);
        
        assertEquals(false, cpu.hold(opponent, 0));

        cpu.addTurnScore(1);

        assertEquals(true, cpu.hold(opponent, 0));
    }

    @Test
    void testSafeStrategy()
    {
        ComputerStrategy strategy = new CustomStrategy();
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);
        Player opponent = new HumanPlayer("dummy");

        cpu.addTurnScore(50);

        opponent.addTurnScore(20);

        boolean shouldRoll = strategy.hold(cpu, opponent, 15);
        assertEquals(false, shouldRoll);

        boolean shouldHold = strategy.hold(cpu, opponent, 16);
        assertEquals(true, shouldHold);
    }

    @Test
    void testRiskyStrategy()
    {
        ComputerStrategy strategy = new CustomStrategy();
        ComputerPlayer cpu = new ComputerPlayer("Computer", strategy);
        Player opponent = new HumanPlayer("dummy");

        cpu.addTurnScore(20);

        opponent.addTurnScore(60);

        boolean shouldRoll = strategy.hold(cpu, opponent, 29);

        assertEquals(false, shouldRoll);

        boolean shouldHold = strategy.hold(cpu, opponent, 30);

        assertEquals(true, shouldHold);
    }
    
}
