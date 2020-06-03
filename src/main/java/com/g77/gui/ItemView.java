package com.g77.gui;

import com.g77.data.objects.MainCharacter;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

public class ItemView {

    private TerminalScreen screen;

    public void draw(TerminalScreen screen, MainCharacter mainCharacter)
    {
        this.screen = screen;
        drawText(new Position(60,2));

        drawLife(mainCharacter.getLife().getHeartQuantity());

        if(mainCharacter.isCanAttack())
            drawSword(new Position(62, 14));


        if(mainCharacter.hasKey())
            drawKey(new Position(62,24));

    }

    private void drawLife(int hearts) {
        for(int i= 1; i< hearts+1; i++){
            drawSingleLife(new Position(62,2 + (i*2)));
        }
    }

    private void drawSword(Position position) {
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(position.getX(), position.getY(), String.valueOf(Symbols.SPADES));
    }

    private void drawKey(Position position) {
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setForegroundColor(TextColor.Factory.fromString("#DAA520"));

        graphics.putString(position.getX(), position.getY(), "q");
    }

    private void drawSingleLife(Position position) {
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));

        graphics.putString(position.getX(), position.getY(), String.valueOf(Symbols.HEART));
    }

    private void drawText(Position position) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(position.getX(), position.getY(), "LIVES");
        graphics.putString(position.getX(), position.getY()+ 10, "SWORD");
        graphics.putString(position.getX()+1, position.getY()+ 20, "KEY");
    }
}
