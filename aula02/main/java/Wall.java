import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Wall extends Element {

    public Wall(int x, int y){
        super(x,y);
    }

    @Override
    public void draw(TextGraphics graphics){
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "*");
    }
}
