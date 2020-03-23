import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Element {
    protected Position position;

    public Element(int x, int y){
        this.position = new Position(x, y);

    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position.setPos(position.getX(),position.getY());
    }
    public abstract void draw(TextGraphics graphics);
}
