package com.g77.data;

import static org.junit.Assert.*;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.stats.Life;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class MovementTester {
    private Floor defaultFloor;

    @Before
    public void setupFloor(){
        List<Room> rooms = new ArrayList<>();

        rooms.add(new Room(1,20,20, new Position(0, 0)));


        this.defaultFloor = new Floor(rooms, new Position(1, 1));
    }

    //Movement test without collisions
    @Test
    public void moveMainCharacter1(){


        //Test that MC can move down
        this.defaultFloor.moveMainCharacter(Move.DOWN);
        assertEquals("Down movement failed!", new Position(1, 2), defaultFloor.getMainCharacterPosition());
        assertEquals(Move.DOWN, this.defaultFloor.getMainCharacter().getLastMovement());

        //Test that MC can move up
        this.defaultFloor.moveMainCharacter(Move.UP);
        assertEquals("Up movement failed!", new Position(1, 1), defaultFloor.getMainCharacterPosition());

        //Test that MC can move right
        this.defaultFloor.moveMainCharacter(Move.RIGHT);
        assertEquals("Right movement failed!", new Position(2, 1), defaultFloor.getMainCharacterPosition());

        //Test that MC can move left
        this.defaultFloor.moveMainCharacter(Move.LEFT);
        assertEquals("Left movement failed!", new Position(1, 1), defaultFloor.getMainCharacterPosition());


        //Test combination of movements
        this.defaultFloor.moveMainCharacter(Move.DOWN);
        this.defaultFloor.moveMainCharacter(Move.RIGHT);
        assertEquals("Mixed movement failed!", new Position(2, 2), defaultFloor.getMainCharacterPosition());
    }

    //Movement test with collisions
    @Test
    public void moveMainCharacter2(){

        //Test that MC can move up
        assertEquals(-1, defaultFloor.moveMainCharacter(Move.UP));
        assertEquals("Up movement failed!", new Position(1, 1), defaultFloor.getMainCharacterPosition());

        //Test that MC can move left
        assertEquals(-1, defaultFloor.moveMainCharacter(Move.LEFT));
        assertEquals("Up movement failed!", new Position(1, 1), defaultFloor.getMainCharacterPosition());


        for(int i = 0; i < 17; i++){
            assertEquals(-1, defaultFloor.moveMainCharacter(Move.DOWN));
        }
        assertEquals("Translation failed!", new Position(1, 18), defaultFloor.getMainCharacterPosition());

        assertEquals(-1, defaultFloor.moveMainCharacter(Move.DOWN));
        assertEquals("Down movement failed!", new Position(1, 18), defaultFloor.getMainCharacterPosition());

        for(int i = 0; i < 18; i++){
            assertEquals(-1, defaultFloor.moveMainCharacter(Move.RIGHT));
        }
        assertEquals("Translation failed!", new Position(18, 18), defaultFloor.getMainCharacterPosition());
        assertEquals("Right movement failed!", new Position(18, 18), defaultFloor.getMainCharacterPosition());

    }

    @Test
    public void checkCollisionsMonster1(){
        Room room = this.defaultFloor.getCurrentRoom();
        room.addMonster(new Position(1,2));

        this.defaultFloor.moveMainCharacter(Move.DOWN);
        assertEquals(new Position(1,1), this.defaultFloor.getMainCharacterPosition());
        assertEquals(new Life(2),this.defaultFloor.getMainCharacter().getLife());
        assertFalse(this.defaultFloor.isMainCharacterDead());

        this.defaultFloor.moveMainCharacter(Move.DOWN);
        assertEquals(new Position(1,1), this.defaultFloor.getMainCharacterPosition());
        assertEquals(new Life(1),this.defaultFloor.getMainCharacter().getLife());
        assertFalse(this.defaultFloor.isMainCharacterDead());

        this.defaultFloor.moveMainCharacter(Move.DOWN);
        assertEquals(new Position(1,1), this.defaultFloor.getMainCharacterPosition());
        assertEquals(new Life(0),this.defaultFloor.getMainCharacter().getLife());
        assertTrue(this.defaultFloor.isMainCharacterDead());

    }

    @Test
    public void checkMovementWithoutWallsInRoom(){
        Room room = new Room(1,1);
        Floor floor = new Floor(new Position(0,0));
        floor.addRoom(room);

        floor.moveMainCharacter(Move.RIGHT);
        assertEquals(new Position(0,0), floor.getMainCharacterPosition());

        floor.moveMainCharacter(Move.LEFT);
        assertEquals(new Position(0,0), floor.getMainCharacterPosition());

        floor.moveMainCharacter(Move.UP);
        assertEquals(new Position(0,0), floor.getMainCharacterPosition());

        floor.moveMainCharacter(Move.DOWN);
        assertEquals(new Position(0,0), floor.getMainCharacterPosition());

    }
}
