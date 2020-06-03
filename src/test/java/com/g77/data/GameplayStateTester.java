package com.g77.data;

import com.g77.data.state.GameplayState;
import com.g77.gui.Move;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.ArgumentMatchers.any;


public class GameplayStateTester {

    @Test
    public void doMovementTest1(){

        GameModel gameModel = Mockito.mock(GameModel.class);
        GameplayState gameplayState = new GameplayState(gameModel);

        gameplayState.doMovement(Move.ATTACK);

        Mockito.verify(gameModel).attack();
        Mockito.verify(gameModel, Mockito.times(0)).moveMainCharacter(any());
    }


    @Test
    public void doMovementTest2(){

        GameModel gameModel = Mockito.mock(GameModel.class);
        GameplayState gameplayState = new GameplayState(gameModel);

        gameplayState.doMovement(Move.DOWN);

        Mockito.verify(gameModel, Mockito.times(0)).attack();
        Mockito.verify(gameModel).moveMainCharacter(Move.DOWN);


    }

    @Test
    public void doMovementTest3(){

        GameModel gameModel = Mockito.mock(GameModel.class);
        GameplayState gameplayState = new GameplayState(gameModel);

        gameplayState.doMovement(Move.ENTER);

        Mockito.verify(gameModel, Mockito.times(0)).attack();
        Mockito.verify(gameModel, Mockito.times(0)).moveMainCharacter(Move.ENTER);
    }

    @Test
    public void changeStateTest(){

        GameModel gameModel = Mockito.mock(GameModel.class);
        GameplayState gameplayState = new GameplayState(gameModel);

       gameplayState.changeStates();

       Mockito.verify(gameModel).setCurrentState(any());
    }
}
