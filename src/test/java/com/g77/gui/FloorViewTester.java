package com.g77.gui;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.objects.*;
import com.g77.data.stats.Position;
import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;




public class FloorViewTester {

    private FloorView floorView;
    private Room room;
    private Floor floor;
    private TerminalScreen screen;
    private TextGraphics textGraphics;

    @Before
    public void setup(){

        this.floorView = new FloorView();

        this.floor = new Floor();

        this.room = new Room();

        this.room.setVisible(true);

        floor.addRoom(room);

        this.textGraphics = Mockito.mock(TextGraphics.class);
        this.screen =  Mockito.mock(TerminalScreen.class);
        Mockito.when(this.screen.newTextGraphics()).thenReturn(this.textGraphics);

    }

    @Test
    public void drawMonster(){

        this.room.addMonster(new Position(1,2));
        this.room.addMonster(new Position(2,2));


        this.floorView.draw(this.screen,this.floor);


        Mockito.verify(this.textGraphics).putString(1,2,String.valueOf(Symbols.FACE_BLACK));
        Mockito.verify(this.textGraphics).putString(2,2,String.valueOf(Symbols.FACE_BLACK));

    }

    @Test
    public void drawMainCharacter(){

        this.floor.setMainCharacter(new MainCharacter(new Position(3,3)));
        this.floorView.draw(this.screen,this.floor);


        Mockito.verify(this.textGraphics).putString(3,3,"T");

    }

    @Test
    public void drawWalls(){

        this.room.addWall(new Wall(new Position(0,0)));
        this.room.addWall(new Wall(new Position(2,2)));

        this.floorView.draw(this.screen,this.floor);


        Mockito.verify(this.textGraphics).setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        Mockito.verify(this.textGraphics).putString(0,0, String.valueOf(Symbols.BLOCK_SOLID));
        Mockito.verify(this.textGraphics).putString(2,2, String.valueOf(Symbols.BLOCK_SOLID));
    }

    @Test
    public void drawItem(){
        this.room.addItem(new Sword(new Position(5,5)));
        this.room.addItem(new ExtraLife(new Position(5,6)));
        this.room.addItem(new Key(new Position(5,7), new Door(new Position(1,1))));

        this.floorView.draw(this.screen,this.floor);

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(this.textGraphics).putString(5,5, String.valueOf(Symbols.SPADES));

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#FF007F"));
        Mockito.verify(this.textGraphics).putString(5,6, String.valueOf(Symbols.HEART));

        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#DAA520"));
        Mockito.verify(this.textGraphics).putString(5,7, "q");
    }

    @Test
    public void drawStair(){
        this.room.addStair(new Stair(new Position(1,1)));
        this.room.addStair(new Stair(new Position(1,2)));

        this.floorView.draw(this.screen,this.floor);

        Mockito.verify(this.textGraphics).putString(1,1, "H");
        Mockito.verify(this.textGraphics).putString(1,2, "H");

    }

    @Test
    public void drawDoor(){
        this.room.addDoor(new Door(new Position(2,1)));
        this.room.addDoor(new Door(new Position(2,4)));


        this.floorView.draw(this.screen,this.floor);

        Mockito.verify(this.textGraphics).setBackgroundColor(TextColor.Factory.fromString("#663300"));
        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#663300"));
        Mockito.verify(this.textGraphics).putString(2,1, String.valueOf(Symbols.BLOCK_SOLID));


        Mockito.verify(this.textGraphics).setBackgroundColor(TextColor.Factory.fromString("#663300"));
        Mockito.verify(this.textGraphics).setForegroundColor(TextColor.Factory.fromString("#663300"));
        Mockito.verify(this.textGraphics).putString(2,4, String.valueOf(Symbols.BLOCK_SOLID));

    }

    @Test
    public void drawRoom(){
        this.room.setVisible(false);

        this.room.addMonster(new Position(1,2));

        this.floorView.draw(this.screen,this.floor);

        Mockito.verify(this.textGraphics,Mockito.times(0)).putString(1,2,String.valueOf(Symbols.FACE_BLACK));

        this.room.setVisible(true);

        this.floorView.draw(this.screen,this.floor);

        Mockito.verify(this.textGraphics).putString(1,2,String.valueOf(Symbols.FACE_BLACK));
    }
}
