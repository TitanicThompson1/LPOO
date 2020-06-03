package com.g77.data;

import com.g77.data.levels.Room;
import com.g77.data.objects.Door;
import com.g77.data.objects.NullStair;
import com.g77.data.objects.Stair;
import com.g77.data.stats.Position;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class RoomTester {

    private Room room;
    @Before
    public void setupRoom(){


        this.room = new Room(1,10,10,new Position(10,10));
    }

    @Test
    public void basicFunctionTester(){


        assertEquals(new Position(10,10), this.room.getPosition());
        assertEquals(10, this.room.getHeight());
        assertEquals(10, this.room.getWidth());

        room.setPosition(new Position(11,11));

        assertEquals(new Position(11,11), this.room.getPosition());

    }


    @Test
    public void StairFinderTester(){

        assertEquals(NullStair.class,this.room.findStairByPosition(new Position(1,1)).getClass());

        Stair stub = Mockito.mock(Stair.class);
        Mockito.when(stub.getPosition()).thenReturn(new Position(1,1));

        this.room.addStair(stub);
        assertEquals(stub, this.room.findStairByPosition(new Position(1,1)));

    }
}
