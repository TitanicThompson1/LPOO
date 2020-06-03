package com.g77.gui;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.Key;
import com.g77.data.objects.MainCharacter;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ItemViewTester {

    private MainCharacter mainCharacter;
    private TerminalScreen screen;
    private TextGraphics textGraphics;
    private ItemView itemView;

    @Before
    public void setup(){
        this.itemView = new ItemView();

        this.mainCharacter = new MainCharacter(new Position(3,3));

        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.screen =  Mockito.mock(TerminalScreen.class);
        Mockito.when(this.screen.newTextGraphics()).thenReturn(this.textGraphics);

    }

    @Test
    public void drawLife3() {

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics, Mockito.times(3)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(this.textGraphics).putString(62,4, String.valueOf(Symbols.HEART));
        Mockito.verify(this.textGraphics).putString(62,6, String.valueOf(Symbols.HEART));
        Mockito.verify(this.textGraphics).putString(62,8, String.valueOf(Symbols.HEART));
    }

    @Test
    public void drawLife2() {

        this.mainCharacter.getLife().decreaseLife();

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(this.textGraphics).putString(62,4, String.valueOf(Symbols.HEART));
        Mockito.verify(this.textGraphics).putString(62,6, String.valueOf(Symbols.HEART));
    }

    @Test
    public void drawLife1() {

        this.mainCharacter.getLife().decreaseLife();
        this.mainCharacter.getLife().decreaseLife();

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(this.textGraphics).putString(62,4, String.valueOf(Symbols.HEART));
    }

    @Test
    public void drawSword(){

        this.mainCharacter.canNowAttack();

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics, Mockito.times(2)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(this.textGraphics).putString(62,14, String.valueOf(Symbols.SPADES));
    }

    @Test
    public void drawKey(){

        this.mainCharacter.setKey(new Key(new Position(0,0)));

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        Mockito.verify(this.textGraphics).putString(62,24, "q");
    }

    @Test
    public void drawText(){

        this.itemView.draw(this.screen, this.mainCharacter);

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(this.textGraphics).putString(60,2, "LIVES");
        Mockito.verify(this.textGraphics).putString(60,12, "SWORD");
        Mockito.verify(this.textGraphics).putString(61,22, "KEY");
    }
}
