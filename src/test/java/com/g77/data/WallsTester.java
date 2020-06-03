package com.g77.data;

import com.g77.data.levels.Room;
import com.g77.data.stats.Position;
import org.junit.Test;
import static org.junit.Assert.*;

public class WallsTester {

    @Test
    public void generateNumberWalls(){

        Room room1 = new Room(0, 1,1,new Position(0,0));
        assertEquals(1, room1.getWalls().size());

        Room room2 = new Room(0, 2,2,new Position(0,0));
        assertEquals(4, room2.getWalls().size());

        Room room3 = new Room(0, 3,3,new Position(0,0));
        assertEquals(8, room3.getWalls().size());

        Room room4 = new Room(0, 4,4,new Position(0,0));
        assertEquals(12, room4.getWalls().size());

        Room room5 = new Room(0, 5,5,new Position(0,0));
        assertEquals(16, room5.getWalls().size());

        Room room6  = new Room(0, 2,3,new Position(0,0));
        assertEquals(6, room6.getWalls().size());

        Room room7  = new Room(0, 3,5,new Position(0,0));
        assertEquals(12, room7.getWalls().size());

        Room room8 =  new Room(0, 1,3,new Position(0,0));
        assertEquals(3, room8.getWalls().size());

    }
}
