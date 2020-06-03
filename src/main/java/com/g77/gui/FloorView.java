package com.g77.gui;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.*;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.List;

public class FloorView {
    private TerminalScreen screen;

    public void draw(TerminalScreen screen, Floor floor){
        this.screen = screen;
        drawFloor(floor);
    }
    private void drawFloor(Floor floor)
    {
        List<Room> rooms = floor.getRooms();

        for (Room room : rooms)
            drawRoom(room);
        drawMainCharacter(floor.getMainCharacter());
    }

    private void drawRoom(Room room)
    {
        if(room.isVisible()){
            drawWalls(room.getWalls());
            drawItems(room.getItems());
            drawDoors(room.getDoors());
            drawMonsters(room.getMonsters());
            drawStairs(room.getStairs());
        }
    }

    private void drawDoors(List<Door> doors)
    {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#663300"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#663300"));
        for( Door door : doors)
            graphics.putString(door.getPosition().getX(), door.getPosition().getY(), String.valueOf(Symbols.BLOCK_SOLID));

    }

    private void drawStairs(List<Stair> stairs)
    {
        TextGraphics graphics = screen.newTextGraphics();
        for( Stair stair : stairs)
            graphics.putString(stair.getPosition().getX(), stair.getPosition().getY(), "H");

    }
    private void drawItems(List<Item> items)
    {
        for( Item item : items)
        {
            if (item instanceof ExtraLife) drawItem(item.getPosition(), String.valueOf(Symbols.HEART), "#FF007F");
            if (item instanceof Sword) drawItem(item.getPosition(), String.valueOf(Symbols.SPADES),"#FFFFFF");
            if (item instanceof Key) drawItem(item.getPosition(),"q","#DAA520");
        }
    }

    private void drawItem(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();

        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.putString(position.getX(), position.getY(), character);

    }

    private void drawMainCharacter(MainCharacter mainCharacter) {

        if(mainCharacter == null) return;

        Position pos = mainCharacter.getPosition();

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));

        graphics.putString(pos.getX(), pos.getY(), "T");

    }

    private void drawWalls(List<Wall> walls) {

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        for( Wall wall : walls)
            graphics.putString(wall.getPosition().getX(), wall.getPosition().getY(), String.valueOf(Symbols.BLOCK_SOLID));


    }

    private void drawMonsters(List<Monster> monsters)
    {
        TextGraphics graphics = screen.newTextGraphics();
        for( Monster monster : monsters)
            graphics.putString(monster.getPosition().getX(), monster.getPosition().getY(), String.valueOf(Symbols.FACE_BLACK));

    }
}
