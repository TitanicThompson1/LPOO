package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.Door;
import com.g77.data.objects.ExtraLife;
import com.g77.data.objects.Key;
import com.g77.data.objects.Sword;
import com.g77.data.stats.Life;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class ItemTester {

    private Floor defaultFloor;

    @Before
    public void setupFloor(){
        List<Room> rooms = new ArrayList<>();
        Room room = new Room(0,20,20, new Position(0, 0));

        rooms.add(room);

        this.defaultFloor = new Floor(rooms, new Position(1, 1));

    }

    @Test
    public void pickExtraLifeUp(){

        this.defaultFloor.getMainCharacter().getLife().decreaseLife();
        this.defaultFloor.getCurrentRoom().addItem(new ExtraLife(new Position(2,1)));
        int nItems = this.defaultFloor.getCurrentRoom().getItems().size();

        this.defaultFloor.moveMainCharacter(Move.RIGHT);

        assertEquals("Didn't increased life!", new Life(), this.defaultFloor.getMainCharacter().getLife());
        assertEquals("Didnt removed the item!", nItems - 1, this.defaultFloor.getCurrentRoom().getItems().size());
    }

    @Test
    public void pickSwordUp(){
        this.defaultFloor.getCurrentRoom().addItem(new Sword(new Position(2,1)));
        int nItems = this.defaultFloor.getCurrentRoom().getItems().size();

        this.defaultFloor.moveMainCharacter(Move.RIGHT);
        assertTrue("Didn't make hero attack!", this.defaultFloor.getMainCharacter().isCanAttack());
        assertEquals("Didnt removed the item!", nItems - 1, this.defaultFloor.getCurrentRoom().getItems().size());

    }

    @Test
    public void pickKeyUp()
    {
        Door door = new Door(new Position(3,3),new Position(3,3),0,0,true);
        this.defaultFloor.getCurrentRoom().addItem(new Key(new Position(2,1),door));
        int nItems = this.defaultFloor.getCurrentRoom().getItems().size();

        this.defaultFloor.moveMainCharacter(Move.RIGHT);
        assertTrue("Didn't give hero a key!", this.defaultFloor.getMainCharacter().hasKey());
        assertEquals("Didnt removed the item!", nItems - 1, this.defaultFloor.getCurrentRoom().getItems().size());

    }

}
