package com.g77.data.levels;

import com.g77.data.objects.*;
import com.g77.data.stats.Position;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int width, height;
    private int id;
    private boolean visible;
    private Position position;
    private List<Wall> walls;
    private List<Door> doors;
    private List<Monster> monsters;
    private List<Item> items;
    private List<Stair> stairs;

    /************Constructors****************/

    public Room(int id, int width, int height, Position position) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.visible = false;
        this.position = position;
        this.walls = generateWalls(width,height,position);
        this.doors = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        this.stairs = new ArrayList<>();
    }

    public Room(int width, int height){
        this.width = width;
        this.height = height;
        this.doors = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        this.stairs = new ArrayList<>();
        this.walls = new ArrayList<>();
    }

    public Room(){
        this.visible = false;
        this.doors = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.items = new ArrayList<>();
        this.stairs = new ArrayList<>();
        this.walls = new ArrayList<>();

    }

    /************Getters and Setters****************/

    public int getId() {
        return id;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void addWall(Wall wall){ this.walls.add(wall);}

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Wall> getWalls() {
        return this.walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Door> getDoors() {
        return this.doors;
    }

    public void setDoors(List<Door> doors) {
        this.doors = doors;
    }

    public List<Monster> getMonsters() {
        return this.monsters;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addMonster(Position position) {this.monsters.add(new Monster(position));}

    public void addDoor(Door door){this.doors.add(door);}

    public void addWalls(List<Wall> walls){this.walls.addAll(walls);}

    public void addStair(Stair stair){this.stairs.add(stair);}

    public void addMonster(Monster monster) {this.monsters.add(monster);}

    public void addItem (Item item) {this.items.add(item);}

    public List<Stair> getStairs() {
        return stairs;
    }

    public void setStairs(List<Stair> stairs) {
        this.stairs = stairs;
    }

    /************Room specific****************/


    public List<Wall> generateWalls(int width, int height, Position position)
    {
        List<Wall> walls = new ArrayList<>();

        for (int c = position.getX(); c < width+position.getX(); c++) {
            walls.add(new Wall(new Position(c, position.getY())));
            if(width != 1)
                walls.add(new Wall(new Position(c,height+position.getY() - 1)));
        }

        for (int a = position.getY() + 1; a < height+position.getY() - 1; a++) {
            walls.add(new Wall(new Position(position.getX(),a)));
            if(height != 1)
                walls.add(new Wall(new Position(width+position.getX() - 1, a)));
        }

        return walls;
    }

    public Stair findStairByPosition (Position position)
    {
        for(Stair stair : stairs)
            if (stair.getPosition().equals(position))
                return stair;

        return new NullStair();
    }

}
