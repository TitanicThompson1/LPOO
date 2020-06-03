package com.g77.gui;

import com.g77.data.objects.Timer;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TimerViewTester {

    private Timer timer;
    private TimerView timerView;
    private TerminalScreen screen;
    private TextGraphics textGraphics;

    @Before
    public void setup(){

        this.timerView = new TimerView();
        this.timer = new Timer(new Position(1,2),1,9);
        this.timer.setVisible(true);

        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.screen =  Mockito.mock(TerminalScreen.class);
        Mockito.when(this.screen.newTextGraphics()).thenReturn(this.textGraphics);

    }


    @Test
    public void drawTimer1() {

        this.timerView.draw(this.screen, this.timer);

        Mockito.verify(this.textGraphics).setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#0F0F0F"));
        Mockito.verify(this.textGraphics).putString(1,2,"1:09");

    }


    @Test
    public void drawTimer2() {

        this.timer.setMinutes(2);
        this.timer.setSeconds(11);
        this.timerView.draw(this.screen, this.timer);

        Mockito.verify(this.textGraphics).setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#0F0F0F"));
        Mockito.verify(this.textGraphics).putString(1,2,"2:11");

    }

    @Test
    public void drawTimer3() {

        this.timer.setVisible(false);

        this.timerView.draw(this.screen, this.timer);
        Mockito.verifyZeroInteractions(this.textGraphics);
    }
}
