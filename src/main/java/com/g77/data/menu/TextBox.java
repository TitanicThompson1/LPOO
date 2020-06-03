package com.g77.data.menu;

import com.g77.data.objects.Object;
import com.g77.data.stats.Position;

public class TextBox  extends Object {
    private String text;

    public TextBox(Position position, String text) {
        super(position);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
