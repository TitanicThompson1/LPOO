package com.g77.utils;

import com.g77.data.levels.Floor;
import com.g77.data.levels.Room;
import com.g77.data.menu.Button;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.g77.data.objects.*;
import com.g77.data.stats.Position;


import java.util.ArrayList;
import java.util.List;

public class Generator {

    public void generateGame(List<Floor> floors)
    {

        Door finalDoor = new Door(new Position(30,0), new Position(30,0) ,2,-1, true);

        floors.add(generateFloor0(new Position(55,25),finalDoor));
        floors.add(generateFloor1(new Position(2,10)));
        floors.add(generateFloor2(new Position(50,7)));
        floors.add(generateFloor3(new Position(30,27)));
        floors.add(generateFloor4(new Position(30,15),finalDoor));
    }

    public Floor generateFloor0(Position mainCharacter, Door finalDoor)
    {
        List<Room> rooms = new ArrayList<>();



        rooms.add(generateRoom0(0,20,7, new Position(40,23)));  // Quarto - Starting room
        rooms.add(generateRoom1(1,20,7,  new Position(0,0)));    // WC
        rooms.add(generateRoom2(2,20,30, new Position(20,0),finalDoor));   // Corredor central
        rooms.add(generateRoom3(3,20,23, new Position(40,0)));   // Secret Room
        rooms.add(generateRoom4(4,20,10, new Position(0,20)));   // Dining Room
        rooms.add(generateRoom5(5,10,7,  new Position(0,7)));    // Stairs
        rooms.add(generateRoom6(6,10,6,  new Position(0,14)));   // kitchen
        rooms.add(generateRoom7(7,10,13,  new Position(10,7)));  // living room


        return new Floor(rooms, mainCharacter);
    }

    public Floor generateFloor1(Position mainCharacter)
    {
        List<Room> rooms = new ArrayList<>();

        rooms.add(generateRoom10(0,20,13, new Position(0,0)));       // Stairs
        rooms.add(generateRoom11(1,20,13,  new Position(20,0)));     // Monster Block
        rooms.add(generateRoom12(2,30,10, new Position(0,13)));      // Corredor central
        rooms.add(generateRoom13(3,20,7, new Position(0,23)));       // Canto Inferior Esquerdo
        rooms.add(generateRoom14(4,20,7, new Position(20,23)));      // Corredor
        rooms.add(generateRoom15(5,20,7,  new Position(40,23)));     // Sword Room
        rooms.add(generateRoom16(6,20,13,  new Position(40,0)));     // Stairs
        rooms.add(generateRoom17(7,10,10,  new Position(30,13)));    // Monster
        rooms.add(generateRoom18(8,20,10,  new Position(40,13)));    // Ester Egg

        return new Floor(rooms, mainCharacter);
    }

    public Floor generateFloor2(Position mainCharacter)
    {
        List<Room> rooms = new ArrayList<>();

        rooms.add(generateRoom20(0,20,13, new Position(40,0)));       // Stairs
        rooms.add(generateRoom21(1,40,13, new Position(0,0)));
        rooms.add(generateRoom22(2,40,7, new Position(0,13)));
        rooms.add(generateRoom23(3,40,5, new Position(0,20)));
        rooms.add(generateRoom24(4,40,5, new Position(0,25)));
        rooms.add(generateRoom25(5,20,17, new Position(40,13)));

        return new Floor(rooms, mainCharacter);
    }

    public Floor generateFloor3(Position mainCharacter)
    {
        List<Room> rooms = new ArrayList<>();

        rooms.add(generateRoom30(0,20,10, new Position(20,20)));        // Stairs
        rooms.add(generateRoom31(1,20,10, new Position(0,20)));         // Canto inferior esquerdo
        rooms.add(generateRoom32(2,20,20, new Position(0,0)));          // Canto superior esquerdo
        rooms.add(generateRoom33(3,20,7, new Position(20,0)));          // Topo central
        rooms.add(generateRoom34(4,20,13, new Position(20,7)));         // Stairs
        rooms.add(generateRoom35(5,20,20, new Position(40,0)));         // Canto superior direito
        rooms.add(generateRoom36(6,20,10, new Position(40,20)));        // Canto inferior direito

        return new Floor(rooms, mainCharacter);
    }

    public Floor generateFloor4(Position mainCharacter, Door finalDoor)
    {
        List<Room> rooms = new ArrayList<>();

        rooms.add(generateRoom40(0,20,10, new Position(20,10)));                  // Stairs
        rooms.add(generateRoom41(1,60,30, new Position(0,0),finalDoor));          // Last floor

        return new Floor(rooms, mainCharacter);
    }


//----------------------------------------------------------------------------------------------------------------------

    public Room generateRoom0(int id, int width, int height, Position position)  // Quarto - Starting room
    {
        Room room = new Room(id,width, height, position);



        List<Door> doors = new ArrayList<>();
        Door door = new Door(new Position(40,25),new Position(38,25),id,2, false);
        doors.add(door);
        room.setDoors(doors);

        room.setVisible(true);
        return room;
    }

    public Room generateRoom1(int id, int width, int height, Position position)  // WC
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(15,6), new Position(15,8) ,id,7, false));
        room.setDoors(doors);

        List<Item> items = new ArrayList<>();
        items.add(new ExtraLife(new Position(3,3)));
        room.setItems(items);

        return room;
    }

    public Room generateRoom2(int id,int width, int height, Position position, Door door)   // Corredor central
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();

        doors.add(new Door(new Position(39,25), new Position(41,25) ,id,0, false));
        doors.add(new Door(new Position(20,17), new Position(18,17) ,id,7, false));

        doors.add(door);

        room.setDoors(doors);
        return room;
    }

    public Room generateRoom3(int id, int width, int height, Position position)  // Secret Room
    {
        Room room = new Room(id,width, height, position);

        Door door = new Door(new Position(50,0), new Position(50,0) ,3,-1, true);

        List<Item> items = new ArrayList<>();
        items.add(new Key(new Position(50,10), door));
        room.setItems(items);

        room.addDoor(door);
        return room;
    }

    public Room generateRoom4(int id, int width, int height, Position position)  // Dining Room
    {
        Room room = new Room(id, width, height, position);



        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(15,20), new Position(15,18) ,id,7, false));
        doors.add(new Door(new Position(3,20), new Position(3,18) ,id,6, false));

        room.setDoors(doors);

        return room;
    }

    public Room generateRoom5(int id, int width, int height, Position position)  // Stairs
    {
        Room room = new Room(id, width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(9,10), new Position(11,10) ,id,7, false));

        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(2,10), 0,1);
        stairs.add(stair);
        room.setStairs(stairs);

        return room;
    }

    public Room generateRoom6(int id, int width, int height, Position position)  // kitchen
    {
        Room room = new Room(id, width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(3,19), new Position(3,21) ,id,4, false));

        room.setDoors(doors);

        return room;
    }

    public Room generateRoom7(int id, int width, int height, Position position)  // living room
    {
        Room room = new Room(id, width, height, position);

        List<Door> doors = new ArrayList<>();

        doors.add(new Door(new Position(15,7), new Position(15,5) ,id,1, false));
        doors.add(new Door(new Position(19,17), new Position(21,17) ,id,2, false));
        doors.add(new Door(new Position(15,19), new Position(15,21) ,id,4, false));
        doors.add(new Door(new Position(10,10), new Position(8,10) ,id,5, false));

        room.setDoors(doors);

        return room;
    }

//----------------------------------------------------------------------------------------------------------------------


    public Room generateRoom10(int id, int width, int height, Position position) // Stairs
    {
        Room room = new Room(id,width, height, position);
        room.setVisible(true);

        List<Door> doors = new ArrayList<>();
        Door door = new Door(new Position(19,10),new Position(21,10),id,1, false);
        doors.add(door);
        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(2,10), 1,0);
        stairs.add(stair);
        room.setStairs(stairs);

        return room;
    }

    public Room generateRoom11(int id, int width, int height, Position position) // Monster Block
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(20,10), new Position(18,10) ,id,0,false));
        doors.add(new Door(new Position(25,12), new Position(25,14) ,id,2,false));
        doors.add(new Door(new Position(39,7), new Position(41,7) ,id,6,false));
        room.setDoors(doors);


        Monster monster = new Monster(new Position(37,7));
        room.addMonster(monster);

        return room;
    }

    public Room generateRoom12(int id,int width, int height, Position position)  // Corredor central
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(25,13), new Position(25,11) ,id,1,false));
        doors.add(new Door(new Position(10,22), new Position(10,24) ,id,3,false));
        doors.add(new Door(new Position(29,18), new Position(31,18) ,id,7,false));

        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(4,15)));
        monsters.add(new Monster(new Position(25,20)));


        room.setDoors(doors);
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom13(int id, int width, int height, Position position) // Canto Inferior Esquerdo
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(10,23), new Position(10,21) ,id,2,false));
        doors.add(new Door(new Position(19,26), new Position(21,26) ,id,4,false));

        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(4,27)));


        room.setDoors(doors);
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom14(int id, int width, int height, Position position) // Corredor
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(20,26), new Position(18,26) ,id,3,false));
        doors.add(new Door(new Position(39,26), new Position(41,26) ,id,5,false));

        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(36,25)));


        room.setDoors(doors);
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom15(int id, int width, int height, Position position) // Sword Room
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(40,26), new Position(38,26) ,id,4,false));

        room.setDoors(doors);

        List<Item> items = new ArrayList<>();
        items.add(new Sword(new Position(55,26)));
        room.setItems(items);

        return room;
    }

    public Room generateRoom16(int id, int width, int height, Position position) // Stairs
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(40,7), new Position(38,7) ,id,1,false));
        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(50,7), 1,2);
        stairs.add(stair);
        room.setStairs(stairs);

        return room;
    }

    public Room generateRoom17(int id, int width, int height, Position position) // Monster
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(30,18), new Position(28,18) ,id,2,false));
        doors.add(new Door(new Position(39,18), new Position(41,18) ,id,8,false));

        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(35,18)));


        room.setDoors(doors);
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom18(int id, int width, int height, Position position) // Ester Egg
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(40,18), new Position(38,18) ,id,7,false));



        room.setDoors(doors);
        return room;
    }


//----------------------------------------------------------------------------------------------------------------------

    public Room generateRoom20(int id, int width, int height, Position position)  // Stairs
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(40,7), new Position(38,7) ,id,1, false));
        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(50,7), 2,1);
        stairs.add(stair);
        room.setStairs(stairs);

        room.setVisible(true);
        return room;
    }

    public Room generateRoom21(int id, int width, int height, Position position)
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(39,7), new Position(41,7) ,id,0, false));
        doors.add(new Door(new Position(5,12), new Position(5,14) ,id,2, false));
        room.setDoors(doors);


        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(20,7)));
        monsters.add(new Monster(new Position(5,3)));
        monsters.add(new Monster(new Position(35,3)));
        monsters.add(new Monster(new Position(5,10)));
        monsters.add(new Monster(new Position(35,10)));
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom22(int id, int width, int height, Position position)
    {
        Room room = new Room(id,width, height, position);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(5,13), new Position(5,11) ,id,1, false));
        doors.add(new Door(new Position(35,19), new Position(35,21) ,id,3, false));
        room.setDoors(doors);

        Monster monster = new Monster(new Position(20,16));
        room.addMonster(monster);


        return room;
    }

    public Room generateRoom23(int id, int width, int height, Position position)
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(35,20), new Position(35,18) ,id,2, false));
        doors.add(new Door(new Position(5,24), new Position(5,26) ,id,4, false));
        room.setDoors(doors);


        Monster monster = new Monster(new Position(20,22));
        room.addMonster(monster);

        return room;
    }

    public Room generateRoom24(int id, int width, int height, Position position)
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(5,25), new Position(5,23) ,id,3, false));
        room.setDoors(doors);

        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(30,27), 2,3);
        stairs.add(stair);
        room.setStairs(stairs);

        return room;
    }

    public Room generateRoom25(int id, int width, int height, Position position)
    {
        return new Room(id,width, height, position);
    }
//----------------------------------------------------------------------------------------------------------------------


    public Room generateRoom30(int id, int width, int height, Position position)  // Stairs
    {
        Room room = new Room(id,width, height, position);
        room.setVisible(true);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(20,27), new Position(18,27) ,id,1, false));
        doors.add(new Door(new Position(39,27), new Position(41,27) ,id,6, false));
        room.setDoors(doors);

        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(30,27), 3,2);
        stairs.add(stair);
        room.setStairs(stairs);

        room.setVisible(true);
        return room;
    }

    public Room generateRoom31(int id, int width, int height, Position position)  // Canto inferior esquerdo
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(19,27), new Position(21,27) ,id,0, false));
        doors.add(new Door(new Position(10,20), new Position(10,18) ,id,2, false));
        room.setDoors(doors);


        Monster monster = new Monster(new Position(10,25));
        room.addMonster(monster);



        return room;
    }

    public Room generateRoom32(int id, int width, int height, Position position)  // Canto superior esquerdo
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(10,19), new Position(10,21) ,id,1, false));
        doors.add(new Door(new Position(19,3), new Position(21,3) ,id,3, false));
        room.setDoors(doors);


        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(10,10)));
        monsters.add(new Monster(new Position(10,17)));
        monsters.add(new Monster(new Position(17,3)));
        room.setMonsters(monsters);

        return room;
    }

    public Room generateRoom33(int id, int width, int height, Position position)  // Topo central
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(20,3), new Position(18,3) ,id,2, false));
        doors.add(new Door(new Position(30,6), new Position(30,8) ,id,4, false));
        doors.add(new Door(new Position(39,3), new Position(41,3) ,id,5, false));
        room.setDoors(doors);


        return room;
    }

    public Room generateRoom34(int id, int width, int height, Position position)  // Stairs
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(30,7), new Position(30,5) ,id,3, false));
        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(30,15), 3,4);
        stairs.add(stair);
        room.setStairs(stairs);


        return room;
    }

    public Room generateRoom35(int id, int width, int height, Position position)  // Canto superior direito
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(50,19), new Position(50,21) ,id,6, false));
        doors.add(new Door(new Position(40,3), new Position(38,3) ,id,3, false));
        room.setDoors(doors);

        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(new Position(50,10)));
        monsters.add(new Monster(new Position(50,17)));
        monsters.add(new Monster(new Position(42,3)));
        room.setMonsters(monsters);


        return room;
    }

    public Room generateRoom36(int id, int width, int height, Position position)  // Canto inferior direito
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(40,27), new Position(38,27) ,id,0, false));
        doors.add(new Door(new Position(50,20), new Position(50,18) ,id,5, false));
        room.setDoors(doors);


        Monster monster = new Monster(new Position(50,25));
        room.addMonster(monster);

        return room;
    }


//----------------------------------------------------------------------------------------------------------------------

    public Room generateRoom40(int id, int width, int height, Position position)  // Stairs
    {
        Room room = new Room(id,width, height, position);


        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(30,10), new Position(30,9) ,id,1, false));
        room.setDoors(doors);


        List<Stair> stairs = new ArrayList<>();
        Stair stair = new Stair(new Position(30,15), 4,3);
        stairs.add(stair);
        room.setStairs(stairs);


        room.setVisible(true);
        return room;
    }

    public Room generateRoom41(int id, int width, int height, Position position,Door finalDoor)  // Last floor
    {
        Room room = new Room(id,width, height, position);


        List<Item> items = new ArrayList<>();
        items.add(new Key(new Position(30,25), finalDoor));
        room.setItems(items);


        //Wall
        List<Wall> walls = room.generateWalls(20,10, new Position(20,10));
        room.addWalls(walls);

        List<Door> doors = new ArrayList<>();
        doors.add(new Door(new Position(30,10), new Position(30,11) ,id,0, false));
        room.setDoors(doors);


        return room;
    }



//----------------------------------------------------------------------------------------------------------------------






//----------------------------------------------------------------------------------------------------------------------


    public boolean floorHaveSpace(Floor floor)
    {
        int widthTemp = 0;
        int heightTemp = 0;

        List<Room> rooms = floor.getRooms();
        for (Room room : rooms)
        {
            widthTemp += room.getWidth();
            heightTemp += room.getHeight();
        }

        return (widthTemp <= floor.getWidth() && heightTemp <= floor.getHeight());

    }

    public void generateMenus(List<Menu> menus){
        menus.add(generateMainMenu());
        menus.add(generateCreditMenu());
        menus.add(generateWonMenu());
        menus.add(generateGameOver());
    }

    private Menu generateWonMenu() {
        List<Button> buttons = new ArrayList<>();
        List<TextBox> textBoxes = new ArrayList<>();
        textBoxes.add(new TextBox(new Position(25,15),"You Won!!!"));

        return new Menu(buttons,textBoxes);
    }

    private Menu generateGameOver() {
        List<Button> buttons = new ArrayList<>();
        List<TextBox> textBoxes = new ArrayList<>();
        textBoxes.add(new TextBox(new Position(25,15),"GAME OVER"));

        return new Menu(buttons,textBoxes);
    }

    private Menu generateCreditMenu() {
        List<Button> buttons = new ArrayList<>();
        List<TextBox> textBoxes = new ArrayList<>();

        textBoxes.add(new TextBox(new Position(15,10),"Developed by:"));
        textBoxes.add(new TextBox(new Position(20,14),"Ricardo Amaral Nunes"));
        textBoxes.add(new TextBox(new Position(20,16),"Jose Antonio Dantas Macedo"));

        buttons.add(new Button(new Position(50,25),"Main Menu", 0));
        buttons.get(0).setSelect(true);

        return new Menu(buttons,textBoxes);
    }

    private Menu generateMainMenu(){
        List<Button> buttons = new ArrayList<>();
        List<TextBox> textBoxes = new ArrayList<>();

        buttons.add(new Button(new Position(27,15),"Play", -1));
        buttons.get(0).setSelect(true);
        buttons.add(new Button(new Position(27,20),"Credits", 1));

        textBoxes.add(new TextBox(new Position(22,10),"Escape the House!"));

        return new Menu(buttons, textBoxes);

    }

}
