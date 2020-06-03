package com.g77.data.objects;

import com.g77.data.GameModel;
import com.g77.data.stats.Position;


public class Timer extends Object{
    private int minutes, seconds;
    private boolean visible;

    public Timer(Position position, int minutes, int seconds) {
        super(position);
        this.minutes = minutes;
        this.seconds = seconds;
        this.visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void start(GameModel gameModel){
        this.visible = true;

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }

                    if(decreaseTime()) {
                        gameModel.setGameOver(true);
                        break;
                    }

                    gameModel.notifyObservers(gameModel);

                    if(gameModel.isGameOver()) break;
                }
            }
        }).start();

    }

    private boolean decreaseTime() {
        if(seconds - 1 < 0){
            if(minutes == 0) return true;
            seconds = 59;
            minutes--;
        }else{
            seconds--;
        }
        return false;
    }


}
