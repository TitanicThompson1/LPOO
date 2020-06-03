package com.g77.data.objects;

import com.g77.data.stats.Position;

public class Sword extends Item {

    public Sword(Position position) {
        super(position);
    }


    @Override
    public void wasSteppedOn(MainCharacter mainCharacter) {
        mainCharacter.canNowAttack();
    }
}
