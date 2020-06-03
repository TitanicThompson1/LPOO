package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.Monster;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FloorTester {

    @Test
    public void FloorBasics(){
        //Constructors
        Floor floor = new Floor(new Position(1,2));
        assertEquals(60, floor.getWidth());
        assertEquals(30, floor.getHeight());
        assertEquals(new Position(1,2), floor.getMainCharacterPosition());
        assertTrue(floor.getRooms().isEmpty());

        List<Room> rooms = new ArrayList<>();
        Room room = Mockito.mock(Room.class);
        rooms.add(room);
        Floor floor1 = new Floor(rooms, new Position(1,2));

        assertEquals(60, floor1.getWidth());
        assertEquals(30, floor1.getHeight());
        assertEquals(new Position(1,2), floor1.getMainCharacterPosition());
        assertEquals(1, floor1.getRooms().size());

        //Getters and Setters
        assertNotNull(floor.getMainCharacterPosition());
        assertNotNull(floor.getRooms());
    }

    @Test
    public void EasterEgg(){

        List<Room> rooms = new ArrayList<>();

        for(int i = 0;  i <= 8 ; i++)
        {
            rooms.add(Mockito.mock(Room.class));
        }

        Floor floor = new Floor(rooms, new Position(1,2));

        floor.setCurrentRoom(8);
        Move move = Move.SPACE;

        assertFalse(floor.isEasterEgg());

        floor.moveMainCharacter(move);

        assertTrue(floor.isEasterEgg());
    }

    @Test
    public void MoveMonsters(){


        List<Room> rooms = new ArrayList<>();
        Room room = Mockito.mock(Room.class);
        rooms.add(room);

        Floor floor = new Floor(rooms, new Position(1,2));


        List<Monster> monsters = new ArrayList<>();
        Monster monster = Mockito.mock(Monster.class);
        monsters.add(monster);

        floor.getCurrentRoom().setMonsters(monsters);

        floor.moveMonsters();

    }

}
