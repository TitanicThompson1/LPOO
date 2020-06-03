package com.g77.data.objects;

import com.g77.data.stats.Position;
import com.g77.gui.Move;

import java.util.Random;

public abstract class Movable extends Object {

    public Movable(Position position) {
        super(position);
    }

    public void move(Move move){
        switch (move) {
            case UP:
                this.moveUp();
                break;
            case DOWN:
                this.moveDown();
                break;
            case LEFT:
                this.moveLeft();
                break;
            case RIGHT:
                this.moveRight();
                break;
        }
    }

    public Position randomMove(Position position)
    {
        Random random = new Random();
        int direction = random.nextInt(4);

        switch (direction)
        {
            case 0:
                return new Position(position.getX() + 1, position.getY()); // right
            case 1:
                return new Position(position.getX() , position.getY()+ 1); // down
            case 2:
                return new Position(position.getX() - 1, position.getY()); // left
            case 3:
                return new Position(position.getX(), position.getY() - 1); // up
        }

        return position;
    }

    public void moveTo(Position position) {
        this.position = position.copy();
    }

    private void moveUp(){
        this.position.setY(position.getY() - 1);
    }

    private void moveDown(){
        this.position.setY(position.getY() + 1);
    }

    private void moveLeft(){
        this.position.setX(position.getX() - 1);
    }

    private void moveRight(){
        this.position.setX(position.getX() + 1);
    }

}
