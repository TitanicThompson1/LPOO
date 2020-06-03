package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class MCAttack {

    private Floor defaultFloor;
    @Before
    public void setupFloor(){
        List<Room> rooms = new ArrayList<>();
        Room room = new Room(0,20,20, new Position(0, 0));
        room.addMonster(new Position(2, 1));
        rooms.add(room);

        this.defaultFloor = new Floor(rooms, new Position(1, 1));

    }

    @Test
    public void attack1(){

        defaultFloor.getMainCharacter().canNowAttack();
        defaultFloor.getMainCharacter().setLastMovement(Move.RIGHT);
        defaultFloor.attack();

        assertEquals("Didnt killed monster!", true, defaultFloor.getCurrentRoom().getMonsters().isEmpty());

    }

    @Test
    public void attack2(){

        defaultFloor.getMainCharacter().setLastMovement(Move.RIGHT);
        defaultFloor.attack();

        assertEquals("It killed monster!", false, defaultFloor.getCurrentRoom().getMonsters().isEmpty());

    }
}
