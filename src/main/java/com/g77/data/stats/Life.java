package com.g77.data.stats;

import java.util.Objects;

public class Life {
    private static final int MAX_HEARTS = 3;
    private int heartQuantity;


    public Life() {
        this.heartQuantity = 3;
    }
    public Life(int lives){
        this.heartQuantity = lives;
    }


    public int getHeartQuantity() {
        return heartQuantity;
    }

    public void increaseLife() {
        if(!isFullLife())
            heartQuantity++;
    }

    public void decreaseLife() {
        if(!isDead())
            heartQuantity--;
    }

    public boolean isDead() { return heartQuantity == 0;}

    private boolean isFullLife() {return heartQuantity == MAX_HEARTS;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Life life = (Life) o;
        return heartQuantity == life.heartQuantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heartQuantity);
    }
}
