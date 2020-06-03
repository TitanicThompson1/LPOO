package com.g77.data.menu;

import com.g77.data.objects.Object;
import com.g77.data.stats.Position;

public class Button extends Object {
    private boolean select;
    private String text;
    private int nextMenu;       //id of next menu; -1 means that goes to gameplay


    public Button(Position position, String text, int nextMenu) {
        super(position);
        this.text = text;
        this.nextMenu = nextMenu;
    }

    public String getText() {
        return text;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public int getNextMenu() {
        return this.nextMenu;
    }
}
