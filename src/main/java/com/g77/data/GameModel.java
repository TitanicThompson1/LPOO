package com.g77.data;

import com.g77.data.levels.Floor;
import com.g77.data.menu.Menu;
import com.g77.data.objects.MainCharacter;
import com.g77.data.objects.Timer;
import com.g77.data.state.GameState;
import com.g77.data.state.MenuState;
import com.g77.data.stats.Position;
import com.g77.gui.Move;
import com.g77.utils.Generator;
import com.g77.observer.Observable;
import java.util.ArrayList;
import java.util.List;

public class GameModel extends Observable<GameModel> {
    private List<Floor> floors;
    private List<Menu> menus;
    private GameState currentState;
    private int currentFloor, currentMenu;
    private boolean gameOver, mCDead, wonGame, timerStarted;
    private Timer timer;

    /************Constructors****************/

    public GameModel() {
        this.currentFloor = 0;
        this.currentMenu = 0;
        this.floors = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.currentState = new MenuState(this);
        this.mCDead = false;
        this.wonGame = false;
        this.gameOver = false;
        this.timerStarted = false;
        this.timer = new Timer(new Position(61,29),1,0);
    }

    public GameModel(Generator generator){
        this.currentFloor = 0;
        this.currentMenu = 0;
        this.floors = new ArrayList<>();
        this.menus = new ArrayList<>();
        this.currentState = new MenuState(this);
        this.mCDead = false;
        this.wonGame = false;
        this.gameOver = false;
        timerStarted = false;
        this.timer = new Timer(new Position(61,29),1,0);
        //this.timer.start(this);
        generator.generateGame(floors);
        generator.generateMenus(menus);
    }


    /************Getters and Setters****************/

    public List<Floor> getFloors() {
        return floors;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void setCurrentMenu(int currentMenu) {
        this.currentMenu = currentMenu;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isWonGame() {
        return wonGame;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public boolean isGameOver() {
        return this.gameOver || this.mCDead;
    }

    public Floor getCurrentFloor() { return floors.isEmpty() ? null : floors.get(currentFloor);}

    public int getCurrentFloorId() { return currentFloor;}

    public void setCurrentFloorId(int currentFloor) {
        this.currentFloor = currentFloor;
    }
    public void addFloor (Floor floor){this.floors.add(floor);}

    public Position getMainCharacterPosition(){return this.floors.get(this.currentFloor).getMainCharacterPosition();}

    public Menu getCurrentMenu(){ return this.menus.get(this.currentMenu);}

    public void setCurrentState(GameState currentState) {
        this.currentState = currentState;
    }

    public void addMenu(Menu menu) {this.menus.add(menu);}

    public GameState getCurrentState() {
        return this.currentState;
    }

    /************GameModel specific****************/

    /**
     * Main character attack
     */
    public void attack() {
        floors.get(this.currentFloor).attack();
        notifyObservers(this);

    }

    /**
     * Move main character
     * @param move is a input
     */
    public void moveMainCharacter(Move move)
    {
        int i = this.floors.get(this.currentFloor).moveMainCharacter(move);

        if (verifyAndSetWonGame()) return;

        if (updateMainCharacter(i)) return;

        setTimer();

        notifyObservers(this);
    }

    /**
     * Setting timer
     */
    private void setTimer() {
        if(!this.timerStarted && this.getCurrentFloor().getMainCharacter().getKey() != null){
            this.timer.start(this);
            this.timerStarted = true;
        }
    }

    /**
     * Check if main character won the game
     * @return if the the next room has the id -1, main character pass by the last door and won game, return true. Otherwise return false.
     */
    private boolean verifyAndSetWonGame() {
        if(this.getCurrentFloor().getCurrentRoomID() == -1) {
            this.wonGame = true;
            return true;
        }
        return false;
    }

    /**
     * Update the main character with the information he was on the previous floor
     * @param i id of the floor
     * @return true if main character is dead. Otherwise return false.
     */
    private boolean updateMainCharacter(int i) {
        MainCharacter mc =  this.getCurrentFloor().getMainCharacter();
        if(mc.isDead()) {
            this.mCDead = true;
            return true;
        }

        if(i != -1)
        {
            easterEgg(i);
            this.currentFloor = i;
            this.getCurrentFloor().getMainCharacter().updateMC(mc);
        }
        return false;
    }

    /**
     * A fun Easter Egg
     * @param i if of the floor
     */
    private void easterEgg(int i)
    {
        if(this.getCurrentFloor().isEasterEgg())
        {
            this.currentFloor = i;
            this.getCurrentFloor().setCurrentRoom(3);
            this.getCurrentFloor().getRooms().get(3).setVisible(true);
        }
    }

    /**
     * Move Monsters
     */
    public void moveMonsters()
    {
        GameModel f = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(isGameOver()) break;
                    floors.get(currentFloor).moveMonsters();
                    notifyObservers(f);
                }
            }
        }).start();
    }

    /**
     * process the input of the player
     * @param move input of the player
     */
    public void processMovement(Move move){
        this.currentState.doMovement(move);
    }

    /**
     * Change the states between menus and game play
     * @param state is the state to change to
     */
    public void changeStates(GameState state) {this.currentState = state;}

}
