package com.g77.gui;

import com.g77.data.menu.Button;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MenuViewTester {
    private MenuView menuView;
    private Menu menu;
    private TerminalScreen screen;
    private TextGraphics textGraphics;

    @Before
    public void setup(){

        this.menuView = new MenuView();

        this.menu = new Menu();

        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.screen =  Mockito.mock(TerminalScreen.class);
        Mockito.when(this.screen.newTextGraphics()).thenReturn(this.textGraphics);

    }

    @Test
    public void drawButton(){

        this.menu.addButton(new Button(new Position(1,1),"TESTE",-1));
        this.menu.addButton(new Button(new Position(1,2),"TESTE1",-1));

        this.menuView.draw(this.screen,this.menu);

        Mockito.verify(this.textGraphics).putString(1,1,"TESTE");
        Mockito.verify(this.textGraphics).putString(1,2,"TESTE1");

    }

    @Test
    public void drawTextBox(){

        this.menu.addTextBox(new TextBox(new Position(1,2),"LABEL1"));
        this.menu.addTextBox(new TextBox(new Position(3,2),"LABEL2"));

        this.menuView.draw(this.screen,this.menu);


        Mockito.verify(this.textGraphics).putString(1,2,"LABEL1");
        Mockito.verify(this.textGraphics).putString(3,2,"LABEL2");

    }

}
