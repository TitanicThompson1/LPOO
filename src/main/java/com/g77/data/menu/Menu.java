package com.g77.data.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Button> buttons;
    private List<TextBox> textBoxes;
    int currentButton;

    public Menu(List<Button> buttons, List<TextBox> textBoxes) {
        this.buttons = buttons;
        this.textBoxes = textBoxes;
        this.currentButton = 0;
    }

    public Menu() {
        this.buttons = new ArrayList<>();
        this.textBoxes = new ArrayList<>();
        this.currentButton = 0;
    }

    /*********Getters and Setters*********/


    public List<TextBox> getTextBoxes() {
        return textBoxes;
    }

    public int getCurrentButton() {
        return currentButton;
    }

    public int selectButton(){
        return buttons.get(currentButton).getNextMenu();
    }

    public void addButton(Button button){ this.buttons.add(button);}

    public void addTextBox(TextBox textBox) {this.textBoxes.add(textBox);}

    public List<Button> getButtons() {
        return buttons;
    }

    /*******************Specific*******************/

    public void goDown(){
        if(buttons.isEmpty()) return;

        if(currentButton != buttons.size() - 1) {
            this.buttons.get(currentButton).setSelect(false);
            this.currentButton++;
            this.buttons.get(currentButton).setSelect(true);
        }

    }

    public void goUp(){
        if(buttons.isEmpty()) return;

        if(currentButton > 0) {
            this.buttons.get(currentButton).setSelect(false);
            this.currentButton--;
            this.buttons.get(currentButton).setSelect(true);

        }

    }

}
