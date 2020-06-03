package com.g77.data.state;

import com.g77.data.GameModel;
import com.g77.gui.Move;

public class GameplayState extends GameState {
    public GameplayState(GameModel gameModel) {
        super(gameModel);
    }

    @Override
    public void doMovement(Move move) {
        if(move == Move.ATTACK){
            this.gameModel.attack();
        }else if(move != Move.ENTER){
            this.gameModel.moveMainCharacter(move);
        }
    }

    @Override
    public void changeStates() {
        this.gameModel.setCurrentState(new MenuState(this.gameModel));
    }
}
