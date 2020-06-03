package com.g77.gui;

import com.g77.data.menu.Button;
import com.g77.data.menu.Menu;
import com.g77.data.menu.TextBox;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.util.List;

public class MenuView {
    private TerminalScreen screen;

    public void draw(TerminalScreen screen, Menu currentMenu){
        this.screen = screen;
        drawButtons(currentMenu.getButtons());

        drawTextBoxes(currentMenu.getTextBoxes());
    }


    private void drawButtons(List<Button> buttons) {

        for(Button button : buttons){
            drawButton(button);
        }
    }

    private void drawTextBoxes(List<TextBox> textBoxes) {

        for(TextBox textBox : textBoxes)
            drawTextBox(textBox);

    }

    private void drawTextBox(TextBox textBox) {
        TextGraphics graphics = this.screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        //graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));

        graphics.putString(textBox.getPosition().getX(), textBox.getPosition().getY(), textBox.getText());
    }

    private void drawButton(Button button) {
        TextGraphics graphics = this.screen.newTextGraphics();
        if(button.isSelect())
        {
            graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFF00"));
            graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        }

        graphics.putString(button.getPosition().getX(), button.getPosition().getY(), button.getText());
    }
}
