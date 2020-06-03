package com.g77.data.state;

import com.g77.data.GameModel;
import com.g77.data.menu.Menu;
import com.g77.gui.Move;

public class MenuState extends GameState{

    public MenuState(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void doMovement(Move move) {
        Menu currentMenu = this.gameModel.getCurrentMenu();
        if(move == Move.UP)
            currentMenu.goUp();
        else if(move == Move.DOWN)
            currentMenu.goDown();
        else if(move == Move.ENTER) {
            int nextMenu = currentMenu.selectButton();
            if(nextMenu == -1)
                changeStates();
            else
                this.gameModel.setCurrentMenu(nextMenu);
        }
        this.gameModel.notifyObservers(this.gameModel);
    }

    @Override
    public void changeStates(){
        this.gameModel.setCurrentState(new GameplayState(this.gameModel));
        this.gameModel.moveMonsters();
    }
}
