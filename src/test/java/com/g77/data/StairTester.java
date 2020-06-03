package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.MainCharacter;
import com.g77.data.objects.NullStair;
import com.g77.data.objects.Stair;
import com.g77.data.stats.Life;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StairTester
{

    private GameModel gameModel;

    @Before
    public void setupFloors(){
        Floor floor1, floor2;
        this.gameModel = new GameModel();
        List<Room> rooms = new ArrayList<>();

        Room room0 = new Room(0,20,10, new Position(40, 20));
        Room room1 = new Room(0,20,10, new Position(40, 20));
        Stair stair0 = new Stair(new Position(45,25),0,1);
        Stair stair1 = new Stair(new Position(45,25),1,0);

        room0.addStair(stair0);
        room1.addStair(stair1);

        rooms.add(room0);
        rooms.add(room1);

        floor1 = new Floor(rooms, new Position(44, 25));
        floor2 = new Floor(rooms, new Position(44, 25));

        this.gameModel.addFloor(floor1);
        this.gameModel.addFloor(floor2);
    }

    @Test
    public void testStairConstructor()
    {
        Stair stair = new Stair(new Position(45,25),0,1);

        assertEquals(45, stair.getPosition().getX());
        assertEquals(25, stair.getPosition().getY());
        assertEquals(0, stair.getFloor1());
        assertEquals(1, stair.getFloor2());
    }


    @Test
    public void testSetPosition()
    {
        Stair stair = new Stair(new Position(45,25),0,1);

        stair.setPosition(new Position(15, 20));

        assertEquals(15, stair.getPosition().getX());
        assertEquals(20, stair.getPosition().getY());
    }

    @Test
    public void testUseStair()
    {
        Stair stair = new Stair(new Position(45,25),0,1);
        assertEquals(1,stair.useStair());
    }

    @Test
    public void testChangeFloor()
    {
        MainCharacter mc = this.gameModel.getCurrentFloor().getMainCharacter();

        assertEquals(new Position(44,25), mc.getPosition());
        assertEquals(0,this.gameModel.getCurrentFloorId());

        mc.move(Move.RIGHT);

        this.gameModel.setCurrentFloorId(this.gameModel.getCurrentFloor().getOut(mc.getPosition()));

        assertEquals(1,this.gameModel.getCurrentFloorId());

        mc.move(Move.RIGHT);
        mc.move(Move.LEFT);

        this.gameModel.setCurrentFloorId(this.gameModel.getCurrentFloor().getOut(mc.getPosition()));
        assertEquals(1,this.gameModel.getCurrentFloorId());
    }

    @Test
    public void testChangeFloor1(){
        assertEquals(1 , gameModel.getCurrentFloor().moveMainCharacter(Move.RIGHT));

    }

    @Test
    public void stairWasSteppedOn()
    {
        MainCharacter mc = this.gameModel.getCurrentFloor().getMainCharacter();

        mc.move(Move.RIGHT);
        assertEquals(new Position(45,25), mc.getPosition());
        this.gameModel.getCurrentFloor().getCurrentRoom().findStairByPosition(mc.getPosition()).wasSteppedOn(mc);
        assertEquals(new Position(45,25), mc.getPosition());
    }

    @Test
    public void stairNull(){
        NullStair stair = new NullStair();

        assertTrue(stair.isNull());
    }
}
