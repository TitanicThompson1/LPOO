package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.objects.MainCharacter;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class GameModelTester {
    private GameModel defaultGameModel;

    @Before
    public void setupFloor(){
        this.defaultGameModel = new GameModel();
    }

    @Test
    public void moveMC1(){
        Floor floor = Mockito.mock(Floor.class);
        MainCharacter mc = Mockito.mock(MainCharacter.class);
        Mockito.when(floor.moveMainCharacter(Move.DOWN)).thenReturn(-1);
        Mockito.when(mc.isDead()).thenReturn(false);
        Mockito.when(floor.getMainCharacter()).thenReturn(mc);

        this.defaultGameModel.addFloor(floor);

        this.defaultGameModel.moveMainCharacter(Move.DOWN);

        Mockito.verify(floor).moveMainCharacter(Move.DOWN);

    }
/*
    @Test
    public void moveMC2(){

        GameModel floorModelSpy = Mockito.spy(this.defaultGameModel);

        Floor floor = Mockito.mock(Floor.class);
        Mockito.when(floor.moveMainCharacter(Move.DOWN)).thenReturn(1);
        Mockito.when(floor.(Move.DOWN)).thenReturn(1);


        this.defaultGameModel.addFloor(floor);

        this.defaultGameModel.moveMainCharacter(Move.DOWN);

        assertEquals(1,this.defaultGameModel.getCurrentFloorId());

    }*/


    //Verifies if notifyObservers is call
    @Test
    public void moveMC3(){

        GameModel spyGameModel = Mockito.spy(this.defaultGameModel);

        Floor floor = Mockito.mock(Floor.class);
        MainCharacter mc = Mockito.mock(MainCharacter.class);
        Mockito.when(floor.moveMainCharacter(Move.DOWN)).thenReturn(-1);
        Mockito.when(mc.isDead()).thenReturn(false);
        Mockito.when(floor.getMainCharacter()).thenReturn(mc);

        spyGameModel.addFloor(floor);

        spyGameModel.moveMainCharacter(Move.DOWN);

        Mockito.verify(spyGameModel).notifyObservers(spyGameModel);
    }
    @Test
    public void attack1(){
        Floor floor = Mockito.mock(Floor.class);

        this.defaultGameModel.addFloor(floor);

        this.defaultGameModel.attack();

        Mockito.verify(floor).attack();

    }


    @Test
    public void getMCPosition(){
        Floor floor = Mockito.mock(Floor.class);
        Mockito.when(floor.getMainCharacterPosition()).thenReturn(new Position(1,1));

        this.defaultGameModel.addFloor(floor);

        assertEquals(new Position(1,1),this.defaultGameModel.getMainCharacterPosition());
    }

    @Test
    public void easterEggTester()
    {
        Floor floor = Mockito.mock(Floor.class);
        Mockito.when(floor.getMainCharacterPosition()).thenReturn(new Position(1,1));

        this.defaultGameModel.addFloor(floor);

        assertEquals(new Position(1,1),this.defaultGameModel.getMainCharacterPosition());
    }

}
