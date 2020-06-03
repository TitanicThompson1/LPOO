package com.g77.data;


import com.g77.data.levels.Room;
import com.g77.data.objects.MainCharacter;
import com.g77.data.stats.Life;
import com.g77.data.stats.Position;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MainCharacterTester
{
    @Test
    public void updateMcTester()
    {
        MainCharacter mc1 = new MainCharacter(new Position(1,1));
        MainCharacter mc2 = new MainCharacter(new Position(2,2));
        assertFalse(mc1.isWasAttacked());

        Life life = Mockito.mock(Life.class);
        mc1.setLife(life);

        assertNotEquals(mc1, mc2);
        assertNotEquals(mc1.getLife(), mc2.getLife());
        assertNotEquals(mc1.getPosition(), mc2.getPosition());


        mc1.updateMC(mc2);

        assertEquals(mc1.getLife(), mc2.getLife());
        assertEquals(mc1.getKey(), mc2.getKey());
        assertEquals(mc1.getLastMovement(), mc2.getLastMovement());
        assertEquals(mc1.getPosition(), mc2.getPosition());
        assertEquals(mc1.isCanAttack(), mc2.isCanAttack());
        assertEquals(mc1.isWasAttacked(), mc2.isWasAttacked());

    }
}
