package com.g77;

import com.g77.data.GameModel;
import com.g77.gui.GUI;
import com.g77.gui.LanternaGUI;
import com.g77.logic.GameLogic;
import com.g77.utils.Generator;

import java.io.IOException;

public class Game {
    public static void main(String[] args) {
        Generator generator = new Generator();
        GameModel levels = new GameModel(generator);
        GUI gui;

        try {
            gui = new LanternaGUI(65, 30);
        }catch (IOException e){
            e.printStackTrace();
            return;
        }
        levels.addObserver(gui);


        GameLogic gameLogic = new GameLogic(levels, gui);

        try {
            gui.draw(levels);
            gameLogic.run();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
