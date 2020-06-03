package com.g77.data;

import com.g77.data.objects.Timer;
import com.g77.data.stats.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;


public class TimerTester
{
    private Timer timer;
    private GameModel gameModel;

    @Before
    public void setupTimer()
    {
        this.timer = new Timer(new Position(1,1),0,30);
        this.gameModel = new GameModel();
    }

    @Test
    public void testStartTimer()
    {
        assertEquals(0,timer.getMinutes());
        assertEquals(30,timer.getSeconds());
        assertFalse(timer.isVisible());

        timer.start(gameModel);

        assertTrue(timer.isVisible());

        gameModel.setGameOver(true);
    }

    @Test
    public void startTest(){

        Timer timer = new Timer(new Position(1,1),0,1);
        GameModel gameModel = Mockito.mock(GameModel.class);

        timer.start(gameModel);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            fail();
        }
        Mockito.verify(gameModel).setGameOver(true);

    }

}
