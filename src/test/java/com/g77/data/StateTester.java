package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.menu.Button;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.g77.data.state.GameplayState;
import com.g77.data.state.MenuState;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class StateTester {

    private GameModel gameModel;


    @Test
    public void menuDoMovent(){
        GameModel gameModel = Mockito.mock(GameModel.class);
        Menu menu = Mockito.mock(Menu.class);
        Mockito.when(gameModel.getCurrentMenu()).thenReturn(menu);

        MenuState menuState = new MenuState(gameModel);

        menuState.doMovement(Move.UP);

        Mockito.verify(menu).goUp();

        Mockito.verify(gameModel).notifyObservers(gameModel);
    }

    @Test
    public void menuDoMovent2(){
        GameModel gameModel = Mockito.mock(GameModel.class);
        Menu menu = Mockito.mock(Menu.class);
        Mockito.when(menu.selectButton()).thenReturn(1);
        Mockito.when(gameModel.getCurrentMenu()).thenReturn(menu);

        MenuState menuState = new MenuState(gameModel);

        menuState.doMovement(Move.ENTER);

        Mockito.verify(gameModel).setCurrentMenu(1);
        Mockito.verify(gameModel).notifyObservers(gameModel);
    }

    @Test
    public void changeState1(){
        GameModel gameModel = Mockito.mock(GameModel.class);

        MenuState menuState = new MenuState(gameModel);

        menuState.changeStates();

        Mockito.verify(gameModel).moveMonsters();
    }


    @Before
    public void setUp(){
        Menu menu = new Menu(new ArrayList<Button>(), new ArrayList<TextBox>());
        menu.addButton(new Button( new Position(1, 1),"Start", -1));
        menu.addButton(new Button( new Position(1, 1),"dummy", 0));

        this.gameModel = new GameModel();
        Floor floor = new Floor(new Position(1,1));
        floor.addRoom(new Room(20, 20));
        this.gameModel.addFloor(floor);
        this.gameModel.addMenu(menu);


    }

    @Test
    public void changeState()
    {
        this.gameModel.processMovement(Move.ENTER);
        assertEquals(GameplayState.class, this.gameModel.getCurrentState().getClass());

    }


    @Test
    public void gamePlayState()
    {

        this.gameModel.getCurrentState().changeStates();

        assertEquals(GameplayState.class, this.gameModel.getCurrentState().getClass());
        this.gameModel.processMovement(Move.UP);
        this.gameModel.processMovement(Move.ATTACK);
    }

    @Test
    public void menuState()
    {

        this.gameModel.processMovement(Move.UP);
        assertEquals(0,this.gameModel.getCurrentMenu().getCurrentButton());
        this.gameModel.processMovement(Move.DOWN);
        assertEquals(1,this.gameModel.getCurrentMenu().getCurrentButton());

    }

}

