package com.g77.data.levels;

import com.g77.data.objects.*;
import com.g77.data.stats.Position;

import com.g77.gui.Move;

import java.util.ArrayList;
import java.util.List;


public class Floor {

    private List<Room> rooms;
    private MainCharacter mainCharacter;
    private int width, height;
    private int currentRoom;
    private boolean easterEgg;

    /************Constructors****************/

    public Floor(List<Room> rooms, Position mainCharPos){
        this.width = 60; this.height = 30;; this.currentRoom = 0;
        this.rooms = rooms;
        this.mainCharacter = new MainCharacter(mainCharPos);
    }

    public Floor(Position mainCharPos){
        this.width = 60; this.height = 30;; this.currentRoom = 0;
        this.mainCharacter = new MainCharacter(mainCharPos);
        this.rooms = new ArrayList<>();
        this.easterEgg = false;
    }

    public Floor() {
        this.rooms = new ArrayList<>();
    }

    /************Getters and Setters****************/

    public boolean isEasterEgg() {
        return easterEgg;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Position getMainCharacterPosition(){return mainCharacter.getPosition();}

    public void setMainCharacter(MainCharacter mainCharacter) {
        this.mainCharacter = mainCharacter;
    }

    public List<Room> getRooms() {
        return rooms;
    }


    public void addRoom(Room room){rooms.add(room);}

    public Room getCurrentRoom() { return rooms.get(currentRoom);}

    public void setCurrentRoom(int currentRoom) { this.currentRoom = currentRoom;}

    public boolean isMainCharacterDead() {return mainCharacter.isDead();}

    public int getCurrentRoomID(){return this.currentRoom;}

    public MainCharacter getMainCharacter() {
        return mainCharacter;
    }

    /************Floor specific****************/

    /**
     * Moves the character, checking for all types of collision
     * @param move move to do
     * @return if floor has changed, returns next floor. Otherwise, returns -1
     */
    public int moveMainCharacter(Move move){

        Position position = getNewPosition(move);



        if(checkDoorsCollision(position)  || checkValidPos(position) || checkMonsterCollision(position))
            return -1;

        if (checkStairsCollision(position))
            return getOut(position);

        checkItemInPos(position);

        this.mainCharacter.move(move);
        this.mainCharacter.setLastMovement(move);

        if(easterEgg(move))
        {
            return 0;
        }

        return -1;
    }

    /**
     * Easter egg, go to a secret room
     * @param move need to be the space bar
     */
    private boolean easterEgg(Move move)
    {
        if(currentRoom == 8)
        {
            if (move == Move.SPACE)
            {
                this.easterEgg = true;
                return true;
            }
        }
        return this.easterEgg;
    }

    /**
     * Moves the monsters, checking for all types of collision
     */
    public void moveMonsters()
    {
        List<Monster> monsters = this.rooms.get(currentRoom).getMonsters();

        for(Monster monster : monsters ) {
            Position check = monster.randomMove(monster.getPosition());

            while (checkValidPos(check))
                check = monster.randomMove(monster.getPosition());

            if (verifyMainCharacterCollision(check)) continue;

            monster.moveTo(check);
        }
        this.mainCharacter.setWasAttacked(false);
    }

    /**
     * Checks if the position given is the position of main character, if it is, attack the main character
     * @param check position to check is if the position of main character
     * @return if the position given is the position of main character, returns true. Otherwise, returns false
     */
    private boolean verifyMainCharacterCollision(Position check) {
        if(check.equals(getMainCharacterPosition())) {
            if(!this.mainCharacter.isWasAttacked()){
                this.mainCharacter.getLife().decreaseLife();
                this.mainCharacter.setWasAttacked(true);
            }
            return true;
        }
        return false;
    }

    /**
     * Checks if in the position given has a item, if it has pick up the item
     * @param position position to look fot the item
     */
    private void checkItemInPos(Position position){
        List<Item> items = getCurrentRoom().getItems();
        for (Item item: items){
            if(item.getPosition().equals(position)){
                item.wasSteppedOn(this.mainCharacter);
                items.remove(item);
                break;
            }
        }
    }

    /**
     * Checks if the position is valid
     */
    private boolean checkValidPos(Position newPosition){
        return !checkInBorder(newPosition) || checkWallsCollision(newPosition);
    }

    /**
     * Checks if the position given is inside the border
     */
    private boolean checkInBorder(Position position){
        return position.getY() > 0 && position.getY() < this.height && position.getX() > 0 && position.getX() < this.width;
    }


    /**
     * Checks if the position given is the position of a wall
     */
    private boolean checkWallsCollision(Position position){

        List<Wall> walls = rooms.get(currentRoom).getWalls();
        if(walls.isEmpty()) return false;

        for (Wall wall : walls){
            if( wall.getPosition().equals(position))
                return true;
        }
        return false;

    }

    /**
     * Checks if the position given is the position of a monster
     */
    private boolean checkMonsterCollision(Position position){

        List<Monster> monsters = rooms.get(currentRoom).getMonsters();
        if(monsters.isEmpty()) return false;

        for (Monster monster : monsters){

            if(monster.getPosition().equals(position)){
                mainCharacter.getLife().decreaseLife();
                mainCharacter.setWasAttacked(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the position given is the position of a door
     */
    private boolean checkDoorsCollision(Position position){

        List<Door> doors = this.rooms.get(currentRoom).getDoors();

        for (Door door : doors){
            if(door.getPosition().equals(position)) {
                if(canOpenDoor(door)){
                    door.wasSteppedOn(this.mainCharacter);
                    changeRooms(door);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the main character can open a door
     * @param door door to check is can be open
     * @return if the door is unlock or if the main character has a key, return true. Otherwise return false
     */
    private boolean canOpenDoor(Door door){
        if(!door.isLocked()) return true;

        return this.mainCharacter.hasKey();
    }

    /**
     * Change to other room
     * @param door door that know the next room
     */
    public void changeRooms(Door door) {

        this.currentRoom = door.openDoor(); //changeRoom
        if(this.currentRoom == -1) return;
        this.rooms.get(currentRoom).setVisible(true);
    }


    /**
     * Go to other floor
     * @param position position of a stair
     * @return the floor that main character will move to
     */
    public int getOut(Position position) {
        return this.rooms.get(currentRoom).findStairByPosition(position).useStair();
    }


    /**
     * Checks if the position given is the position of a stair
     * @param position position to check is if the position of a stair
     * @return if the position given is the position of a stair, returns true. Otherwise, returns false
     */
    private boolean checkStairsCollision(Position position){

        List<Stair> stairs = this.rooms.get(currentRoom).getStairs();
        for(Stair stair : stairs) {
            if(stair.getPosition().equals(position)) {
                stair.wasSteppedOn(mainCharacter);
                return true;
            }
        }
        return false;
    }


    /**
     * Calculate a new position based on the move
     * @param move the new input
     * @return a new position
     */
    private Position getNewPosition(Move move){

        Position newPosition = this.mainCharacter.getPosition().copy();

        switch (move){
            case UP:
                newPosition.setY(newPosition.getY() - 1 );
                break;
            case DOWN:
                newPosition.setY(newPosition.getY() + 1);
                break;
            case LEFT:
                newPosition.setX(newPosition.getX() - 1);
                break;
            case RIGHT:
                newPosition.setX(newPosition.getX() + 1);
                break;
        }
        return newPosition;
    }


    /**
     * Checks if main character can attack. If it can, attacks
     */
    public void attack() {
        if(!mainCharacter.isCanAttack()) return;
        Position position = getNewPosition(mainCharacter.getLastMovement());
        killMonster(position);
    }

    /**
     * Chekcs if is any monster in the position given. If it is, eliminates him from the list.
     * @param position position to look for the monster
     */
    private void killMonster(Position position) {
        List<Monster> monsters = rooms.get(currentRoom).getMonsters();
        for (Monster monster : monsters){
            if (monster.getPosition().equals(position)){
                monsters.remove(monster);
                break;
            }
        }
    }

}
