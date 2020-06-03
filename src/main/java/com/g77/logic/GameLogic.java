package com.g77.logic;

import com.g77.data.GameModel;
import com.g77.data.state.MenuState;
import com.g77.gui.GUI;
import com.g77.gui.Move;

import java.io.IOException;

public class GameLogic {
    private GameModel levels;
    private GUI gui;

    public GameLogic(GameModel levels, GUI gui) {
        this.levels = levels;
        this.gui = gui;
    }

    public void run() throws IOException {
        while(true){
            Move nextMove = gui.getMovement();

            if (verifyExit(nextMove)) break;

            this.levels.processMovement(nextMove);

            verifyGameOver();
            verifyWonGame();

        }
    }

    private boolean verifyExit(Move nextMove) throws IOException {
        if(nextMove == Move.EXIT) {
            this.gui.close();

            //For threads to finish
            this.levels.setGameOver(true);
            return true;
        }
        return false;
    }

    private void verifyWonGame() {
        if(this.levels.isWonGame()) {
            this.levels.setCurrentMenu(2);
            this.levels.changeStates(new MenuState(this.levels));
        }
    }

    private void verifyGameOver() {
        if(this.levels.isGameOver()){
            this.levels.setCurrentMenu(3);
            this.levels.changeStates(new MenuState(this.levels));
        }
    }

}
