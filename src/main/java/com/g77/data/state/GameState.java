package com.g77.data.state;

import com.g77.data.GameModel;
import com.g77.gui.Move;

public abstract class GameState {
    protected GameModel gameModel;

    public GameState(GameModel gameModel){
        this.gameModel = gameModel;
    }

    abstract public void doMovement(Move move);

    abstract public void changeStates();
}
