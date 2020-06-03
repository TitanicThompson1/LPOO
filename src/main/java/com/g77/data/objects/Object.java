package com.g77.data.objects;

import com.g77.data.stats.Position;

public abstract class Object {
    protected Position position;

    public Object(Position position) {
        this.position = position;
    }

    protected Object() {}

    public Position getPosition() {
        return position;
    }
    public void setPosition(Position position) {
        this.position = position;
    }
}
