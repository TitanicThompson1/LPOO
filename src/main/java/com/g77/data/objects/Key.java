package com.g77.data.objects;

import com.g77.data.stats.Position;

public class Key extends Item {
    private Door door;

    public Key(Position position, Door door) {
        super(position);
        this.door = door;
    }
    public Key(Position position){
        super(position);
    }

    @Override
    public void wasSteppedOn(MainCharacter mainCharacter) {
        mainCharacter.setKey(this);
    }

}
