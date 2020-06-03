package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.Door;
import com.g77.data.objects.Key;
import com.g77.data.objects.MainCharacter;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DoorTester {


    private Floor defaultFloor;
    private Door doorTester1;
    private Door doorTester2;
    private Door doorTester3;
    private Door doorTester4;

    @Before
    public void setupFloor(){
        List<Room> rooms = new ArrayList<>();
        List<Door> doors = new ArrayList<>();

        Door door1 = new Door(new Position(40,25),new Position(38,25),0,1, false);
        Door door2 = new Door(new Position(39,25),new Position(41,25),1,0, false);
        Door door3 = new Door(new Position(40,23),new Position(38,23),0,1, true);
        Door door4 = new Door(new Position(40,25),new Position(38,25),0,1, false);
        this.doorTester1 = door1;
        this.doorTester2 = door2;
        this.doorTester3 = door3;
        this.doorTester4 = door4;

        Room room0 = new Room(0,20,10, new Position(40, 20));
        Room room1 = new Room(1,20,30, new Position(20, 0));

        room0.addDoor(door1);
        room0.addDoor(door3);
        room1.addDoor(door2);

        rooms.add(room0);
        rooms.add(room1);


        this.defaultFloor = new Floor(rooms, new Position(41, 25));

    }

    @Test
    public void testDoorConstructor()
    {
        Door door = new Door(new Position(40,25),new Position(38,25),0,2, false);

        assertEquals(40, door.getPosition().getX());
        assertEquals(25, door.getPosition().getY());
        assertEquals(38, door.getNextRoomPosition().getX());
        assertEquals(25, door.getNextRoomPosition().getY());
        assertEquals(25, door.getNextRoomPosition().getY());
        assertEquals(0,door.getRoomId1());
        assertEquals(2,door.getRoomId2());
    }


    @Test
    public void testSetPosition()
    {
    Door door = new Door(new Position(40,25),new Position(38,25),0,2, false);

    door.setPosition(new Position(15, 20));

    assertEquals(15, door.getPosition().getX());
    assertEquals(20, door.getPosition().getY());
    }

    @Test
    public void testOpenDoor()
    {
        Door door1 = new Door(new Position(40,25),new Position(38,25),0,2, false);
        Door door2 = new Door(new Position(39,25),new Position(41,25),2,0, false);

        int openDoor1 = door1.openDoor();
        int openDoor2 = door2.openDoor();

        assertEquals(2,openDoor1);
        assertEquals(0,openDoor2);

    }

    @Test
    public void testChangeRooms()
    {
        MainCharacter mc = this.defaultFloor.getMainCharacter();

        assertEquals(new Position(41,25), mc.getPosition());
        assertEquals(0,this.defaultFloor.getCurrentRoom().getId());


        defaultFloor.moveMainCharacter(Move.LEFT);

        this.defaultFloor.changeRooms(doorTester1);

        assertTrue(this.defaultFloor.getCurrentRoom().isVisible());

        assertEquals(new Position(38,25),mc.getPosition());
        assertEquals(1,this.defaultFloor.getCurrentRoom().getId());

        defaultFloor.moveMainCharacter(Move.RIGHT);

        this.defaultFloor.changeRooms(doorTester2);
        assertEquals(0,this.defaultFloor.getCurrentRoom().getId());


        assertEquals(new Position(41,25),mc.getPosition());
    }


    @Test
    public void openLockedDoor(){
        this.defaultFloor.getMainCharacterPosition().setY(23);
        assertEquals(new Position(41,23), this.defaultFloor.getMainCharacterPosition());

        this.defaultFloor.moveMainCharacter(Move.LEFT);

        assertTrue(this.doorTester3.isLocked());
        assertEquals(new Position(41,23), this.defaultFloor.getMainCharacterPosition());

        this.defaultFloor.getMainCharacter().setKey(new Key(new Position(1,1),this.doorTester3));

        this.defaultFloor.moveMainCharacter(Move.LEFT);
        assertEquals(new Position(38,23),this.defaultFloor.getMainCharacterPosition());
    }

    @Test
    public void doorWasSteppedOn()
    {
        MainCharacter mc = this.defaultFloor.getMainCharacter();

        mc.move(Move.LEFT);
        assertEquals(new Position(40,25), this.defaultFloor.getMainCharacterPosition());
        this.doorTester1.wasSteppedOn(this.defaultFloor.getMainCharacter());
        assertEquals(new Position(38,25), this.defaultFloor.getMainCharacterPosition());
    }

    @Test
    public void doorEquals()
    {
        assertEquals(this.doorTester1.hashCode(), this.doorTester4.hashCode());
        assertNotEquals(this.doorTester1.hashCode(), this.doorTester2.hashCode());
        assertTrue(this.doorTester1.equals(this.doorTester4));
        assertTrue(this.doorTester1.equals(this.doorTester1));
        assertFalse(this.doorTester1.equals(this.doorTester2));
        assertFalse(this.doorTester1.equals(null));
    }

}
