package com.g77.data.objects;

import com.g77.data.stats.Position;

public class Stair extends Object implements SteppedOn
{
    private int floor1,floor2;

    public Stair(Position position, int floor1, int floor2) {
        super(position);
        this.floor1 = floor1;
        this.floor2 = floor2;
    }

    public Stair(Position position) {super(position);}

    public Stair(){}

    public int getFloor1() {
        return floor1;
    }

    public int getFloor2() {
        return floor2;
    }

    public int useStair()
    {
        return floor2;
    }

    @Override
    public void wasSteppedOn(MainCharacter mainCharacter) {
        mainCharacter.moveTo(position);
    }
}
