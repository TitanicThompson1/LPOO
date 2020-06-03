package com.g77.gui;

import com.g77.data.objects.Timer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
public class TimerView {


    public void draw(TerminalScreen screen, Timer timer) {
        if(!timer.isVisible()) return;

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#0F0F0F"));

        if(timer.getSeconds() < 10)
            graphics.putString(timer.getPosition().getX(), timer.getPosition().getY(), Integer.toString(timer.getMinutes())
                    + ":" + "0" +Integer.toString(timer.getSeconds()));
        else
            graphics.putString(timer.getPosition().getX(), timer.getPosition().getY(), Integer.toString(timer.getMinutes())
                    + ":" + Integer.toString(timer.getSeconds()));


    }
}
