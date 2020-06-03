package com.g77.logic;

import com.g77.data.GameModel;
import com.g77.gui.GUI;
import com.g77.gui.LanternaGUI;
import com.g77.gui.Move;

import org.junit.Test;

import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

public class LogicTest {

    @Test
    public void exitTester(){
        GUI gui = Mockito.mock(LanternaGUI.class);

        try {
            Mockito.when(gui.getMovement()).thenReturn(Move.EXIT);
        } catch (IOException e) {
            fail();
        }
        GameModel gameModel = Mockito.mock(GameModel.class);

        GameLogic gameLogic = new GameLogic(gameModel,gui);

        try {
            gameLogic.run();
            Mockito.verify(gui).close();
        } catch (IOException e) {
            fail();
        }


    }

    //TODO: Comentado pq nao funcionava

    @Test
    public void proccesMovementTester(){
        GUI gui = Mockito.mock(LanternaGUI.class);

        try {
            Mockito.when(gui.getMovement()).thenReturn(Move.DOWN).thenReturn(Move.EXIT);
        } catch (IOException e) {
            fail();
        }
        GameModel gameModel = Mockito.mock(GameModel.class);

        GameLogic gameLogic = new GameLogic(gameModel, gui);

        try {
            gameLogic.run();
            Mockito.verify(gameModel).processMovement(Move.DOWN);
            Mockito.verify(gameModel).isGameOver();
            Mockito.verify(gameModel).isWonGame();

            Mockito.verify(gui).close();
        } catch (IOException e) {
            fail();
        }


    }

    @Test
    public void gameOverTester(){
        GUI gui = Mockito.mock(LanternaGUI.class);

        try {
            Mockito.when(gui.getMovement()).thenReturn(Move.DOWN).thenReturn(Move.EXIT);
        } catch (IOException e) {
            fail();
        }
        GameModel gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.isGameOver()).thenReturn(true);

        GameLogic gameLogic = new GameLogic(gameModel, gui);

        try {
            gameLogic.run();
            Mockito.verify(gameModel).processMovement(Move.DOWN);
            Mockito.verify(gameModel).isGameOver();
            Mockito.verify(gameModel).setCurrentMenu(3);
            Mockito.verify(gameModel).changeStates(any());

            Mockito.verify(gui).close();
        } catch (IOException e) {
            fail();
        }


    }

    @Test
    public void wonGameTester(){
        GUI gui = Mockito.mock(LanternaGUI.class);

        try {
            Mockito.when(gui.getMovement()).thenReturn(Move.DOWN).thenReturn(Move.EXIT);
        } catch (IOException e) {
            fail();
        }
        GameModel gameModel = Mockito.mock(GameModel.class);
        Mockito.when(gameModel.isWonGame()).thenReturn(true);

        GameLogic gameLogic = new GameLogic(gameModel, gui);

        try {
            gameLogic.run();
            Mockito.verify(gameModel).processMovement(Move.DOWN);
            Mockito.verify(gameModel).setCurrentMenu(2);
            Mockito.verify(gameModel).changeStates(any());

            Mockito.verify(gui).close();
        } catch (IOException e) {
            fail();
        }


    }


}
