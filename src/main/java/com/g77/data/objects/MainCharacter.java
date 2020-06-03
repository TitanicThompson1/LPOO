package com.g77.data.objects;

import com.g77.data.stats.Life;
import com.g77.data.stats.Position;
import com.g77.gui.Move;

import java.util.ArrayList;

public class MainCharacter extends Movable {
    private Life life;
    private Move lastMovement;
    Key key;
    private boolean canAttack, wasAttacked;

    public MainCharacter(Position position) {
        super(position);
        life = new Life();
        this.canAttack = false;
    }

    public Life getLife() {
        return life;
    }
    public boolean isDead() {
        return life.isDead();
    }

    public boolean isWasAttacked() {
        return wasAttacked;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public void setWasAttacked(boolean wasAttacked) {
        this.wasAttacked = wasAttacked;
    }

    public Move getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(Move lastMovement) {
        this.lastMovement = lastMovement;
    }

    public void canNowAttack(){this.canAttack = true;}

    public boolean isCanAttack(){return this.canAttack;}

    public void setKey(Key key){this.key = key;}

    public Key getKey() {
        return key;
    }

    public boolean hasKey(){ return this.key != null;}

    public void updateMC(MainCharacter mainCharacter){
        this.life = mainCharacter.life;
        this.lastMovement = mainCharacter.lastMovement;
        this.key = mainCharacter.key;
        this.canAttack = mainCharacter.canAttack;
        this.wasAttacked = mainCharacter.wasAttacked;
        this.position = mainCharacter.position;
    }

}
