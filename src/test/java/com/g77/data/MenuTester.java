package com.g77.data;

import com.g77.data.menu.Button;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.g77.data.stats.Position;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MenuTester {

    @Test
    public void Menu1(){

        Menu menu = new Menu(new ArrayList<>(), new ArrayList<>());
        Button dummy1 = new Button(new Position(1, 1),"Dummy1", -1);
        menu.addButton(dummy1);
        assertEquals(-1, menu.selectButton());

        Button dummy2 = new Button(new Position(1, 1),"Dummy2",-1);
        menu.addButton(dummy2);

        assertEquals(0,menu.getCurrentButton());

        TextBox textBox = new TextBox(new Position(1, 1),"text");
        menu.addTextBox(textBox);
        assertEquals("text",menu.getTextBoxes().get(0).getText());

        menu.getButtons().get(0).setSelect(true);
        assertTrue(menu.getButtons().get(0).isSelect());

        menu.goDown();
        assertEquals(1,menu.getCurrentButton());
        assertEquals("Dummy2", menu.getButtons().get(1).getText());
        assertEquals(-1, menu.selectButton());

        assertTrue(menu.getButtons().get(1).isSelect());
        assertFalse(menu.getButtons().get(0).isSelect());

        menu.goDown();
        assertEquals(1,menu.getCurrentButton());

        menu.goUp();
        assertEquals(0,menu.getCurrentButton());

        assertTrue(menu.getButtons().get(0).isSelect());
        assertFalse(menu.getButtons().get(1).isSelect());

        menu.goUp();
        assertEquals(0,menu.getCurrentButton());

    }
}
