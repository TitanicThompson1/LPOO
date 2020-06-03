package com.g77.gui;

import com.g77.data.GameModel;
import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.g77.data.objects.*;
import com.g77.data.state.GameplayState;
import com.g77.data.state.MenuState;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.input.KeyStroke;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mockito;

import java.io.IOException;


public class GUITester {

    @Test
    public void LanternaGetMovementAttackTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke('a',false,false));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got attack movement", Move.ATTACK, move);
        } catch (IOException e) {
            fail();
        }

    }
    @Test
    public void LanternaGetMovementExitTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.Escape));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got exit movement", Move.EXIT, move);
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void LanternaGetMovementEnterTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.Enter));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got enter movement", Move.ENTER, move);
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void LanternaGetMovementUpTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowUp));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got up movement", Move.UP, move);
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void LanternaGetMovementDownTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowDown));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got down movement", Move.DOWN, move);
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void LanternaGetMovementLeftTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);

        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowLeft));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got left movement", Move.LEFT, move);
        } catch (IOException e) {
            fail();
        }

    }

    @Test
    public void LanternaGetMovementRightTester(){


        TerminalScreen terminalScreen = Mockito.mock(TerminalScreen.class);


        try {
            Mockito.when(terminalScreen.readInput()).thenReturn(new KeyStroke(KeyType.ArrowRight));
            GUI gui = new LanternaGUI(terminalScreen);
            Move move = gui.getMovement();
            assertEquals("Didnt got right movement", Move.RIGHT, move);
        } catch (IOException e) {
            fail();
        }

    }


    @Test
    public void LanternaConstructors(){


        try {

            TerminalScreen screenMock = Mockito.mock(TerminalScreen.class);
            LanternaGUI lanternaGUI1 = new LanternaGUI(screenMock);

            Mockito.verify(screenMock).setCursorPosition(null);
            Mockito.verify(screenMock).startScreen();

            lanternaGUI1.close();

        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void LanternaCloseTester(){


        try {

            TerminalScreen screenMock = Mockito.mock(TerminalScreen.class);
            LanternaGUI lanternaGUI1 = new LanternaGUI(screenMock);

            lanternaGUI1.close();

            Mockito.verify(screenMock).close();

        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void LanternaChangedTester(){


        try {

            TerminalScreen screenMock = Mockito.mock(TerminalScreen.class);
            LanternaGUI lanternaGUI = new LanternaGUI(screenMock);

            LanternaGUI lanternaGUISpy = Mockito.spy(lanternaGUI);

            GameModel gameModel = new GameModel();
            gameModel.changeStates(new GameplayState(gameModel));

            lanternaGUISpy.changed(gameModel);

            Mockito.verify(lanternaGUISpy).draw(gameModel);


        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void drawGameplay(){
        try {

            TerminalScreen screenMock = Mockito.mock(TerminalScreen.class);
            TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
            Mockito.when(screenMock.newTextGraphics()).thenReturn(textGraphics);

            LanternaGUI lanternaGUI = new LanternaGUI(screenMock);

            LanternaGUI lanternaGUISpy = Mockito.spy(lanternaGUI);

            GameModel gameModel = new GameModel();
            Timer timer = new Timer(new Position(2,2),1,1);
            timer.setVisible(true);
            gameModel.setTimer(timer);

            Floor floor = new Floor(new Position(1,2));
            Room room = new Room();
            room.addItem(new Sword(new Position(3,2)));
            room.setVisible(true);
            floor.addRoom(room);
            gameModel.addFloor(floor);

            gameModel.changeStates(new GameplayState(gameModel));
            GameModel gameModelSpy = Mockito.spy(gameModel);

            lanternaGUISpy.draw(gameModelSpy);

            Mockito.verify(gameModelSpy).getFloors();
            Mockito.verify(textGraphics).putString(1,2,"T");
            Mockito.verify(textGraphics).putString(3,2,String.valueOf(Symbols.SPADES));
            Mockito.verify(textGraphics).putString(2,2,"1:01");


        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void drawMenu(){
        try {

            TerminalScreen screenMock = Mockito.mock(TerminalScreen.class);
            TextGraphics textGraphics = Mockito.mock(TextGraphics.class);
            Mockito.when(screenMock.newTextGraphics()).thenReturn(textGraphics);

            LanternaGUI lanternaGUI = new LanternaGUI(screenMock);

            LanternaGUI lanternaGUISpy = Mockito.spy(lanternaGUI);

            GameModel gameModel = new GameModel();
            gameModel.changeStates(new MenuState(gameModel));
            Menu menu = new Menu();
            menu.addTextBox(new TextBox(new Position(1,1),"TESTE"));
            gameModel.addMenu(menu);

            GameModel gameModelSpy = Mockito.spy(gameModel);

            lanternaGUISpy.draw(gameModelSpy);

            Mockito.verify(gameModelSpy).getCurrentMenu();
            Mockito.verify(textGraphics).putString(1, 1, "TESTE");




        } catch (IOException e) {
            fail();
        }
    }




}
