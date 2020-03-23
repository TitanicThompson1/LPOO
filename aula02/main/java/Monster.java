import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {

    public Monster(int x, int y){
        super(x, y);
    }

    @Override
    public void draw(TextGraphics graphics) {
        graphics.putString(position.getX(),position.getY(),"M");
    }

    public Position move(){
        Random random = new Random();
        int dir = random.nextInt(4) + 1;
        Position nextPosition = new Position(0,0);
        switch (dir)
        {
            case 1:     //Moves right
                nextPosition.setPos(position.getX() + 1, position.getY());
                break;
            case 2:     //Moves left
                nextPosition.setPos(position.getX() - 1, position.getY());
                break;
            case 3:     //Moves up
                nextPosition.setPos(position.getX(), position.getY() - 1);
                break;
            case 4:     //Moves down
                nextPosition.setPos(position.getX(), position.getY() + 1);
                break;
        }
        return nextPosition;
    }


}
