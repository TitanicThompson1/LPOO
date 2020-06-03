package com.g77.gui;

import com.g77.data.GameModel;
import com.g77.data.state.MenuState;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaGUI implements GUI{

    private TerminalScreen screen;

    private FloorView floorView;
    private ItemView itemView;
    private TimerView timerView;
    private MenuView menuView;

    public LanternaGUI(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        this.screen =  new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary

        this.floorView = new FloorView();
        this.itemView = new ItemView();
        this.timerView = new TimerView();
        this.menuView = new MenuView();
    }

    public LanternaGUI(FloorView floorView, ItemView itemView, TimerView timerView, MenuView menuView) {
        this.floorView = floorView;
        this.itemView = itemView;
        this.timerView = timerView;
        this.menuView = menuView;
    }

    public LanternaGUI(TerminalScreen screen) throws IOException{
        this.screen = screen;

        this.screen.setCursorPosition(null);   // we don't need a cursor
        this.screen.startScreen();             // screens must be started
        this.screen.doResizeIfNecessary();     // resize screen if necessary
        this.floorView = new FloorView();
        this.itemView = new ItemView();
        this.timerView = new TimerView();
        this.menuView = new MenuView();
    }

    @Override
    public Move getMovement() throws IOException{
        while(true) {
            KeyStroke input = screen.readInput();

            switch (input.getKeyType()) {
                case Escape:
                    return Move.EXIT;
                case ArrowLeft:
                    return Move.LEFT;
                case ArrowRight:
                    return Move.RIGHT;
                case ArrowUp:
                    return Move.UP;
                case ArrowDown:
                    return Move.DOWN;
                case Character:
                    if(input.getCharacter() == 'a') return Move.ATTACK;
                    if(input.getCharacter() == ' ') return Move.SPACE;
                case Enter:
                    return Move.ENTER;
            }
        }
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }



    @Override
    public void changed(GameModel subject) {

        try {
            draw(subject);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void draw(GameModel gameModel) throws IOException {
        screen.clear();

        if(gameModel.getCurrentState() instanceof MenuState)
            drawMenuState(gameModel);
        else
            drawGameplayState(gameModel);

        screen.refresh();
    }

    private void drawGameplayState(GameModel gameModel) {

        if(gameModel.getFloors().isEmpty())
            return;

        this.floorView.draw(this.screen, gameModel.getCurrentFloor());
        this.itemView.draw(this.screen, gameModel.getCurrentFloor().getMainCharacter());
        this.timerView.draw(this.screen, gameModel.getTimer());
    }



    private void drawMenuState(GameModel gameModel) {
        if(gameModel.getMenus().isEmpty())
            return;

        this.menuView.draw(this.screen, gameModel.getCurrentMenu());
    }



}
