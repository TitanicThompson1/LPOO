package com.g77.data.objects;

import com.g77.data.stats.Position;
import com.g77.gui.Move;

import java.util.Random;

public class Monster extends Movable
{
    public Monster(Position position)
    {
        super(position);
    }

    public Position getPosition()
    {
        return position;
    }

}
