package com.g77.gui;

import com.g77.data.GameModel;
import com.g77.observer.Observer;

import java.io.IOException;


public interface GUI extends Observer<GameModel>
{
    void draw(GameModel gameModel) throws IOException;

    Move getMovement() throws IOException;

    void close() throws IOException;
}
