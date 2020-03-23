import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;
    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
        this.monsters = createMonsters();
        this.coins = createCoins();

    }

    public boolean canHeroMove(Position position){
        return (position.getX() < this.width - 1) && (position.getY() < this.height - 1) && (position.getX() > 0) && (position.getY() > 0);
    }

    public boolean processKey(KeyStroke key){
        moveMonsters();

        if(verifyMonsterCollisions()) return false;

        switch (key.getKeyType()) {
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;

        }

        if(verifyMonsterCollisions()) return false;
        return true;
    }

    private void moveHero(Position position){
        if (canHeroMove(position)){
            this.hero.setPosition(position);
            retrieveCoins();
        }
    }

    public void draw(TextGraphics graphics){
        //Draw background
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0),new TerminalSize(width,height), ' ');

        //Draw hero
        this.hero.draw(graphics);

        //Draw the Walls
        for (Wall wall : this.walls){
            wall.draw(graphics);
        }

        //Draw the coins
        for (Coin coin : this.coins) {
            coin.draw(graphics);
        }

        //Draw the monster
        for (Monster m : this.monsters){
            m.draw(graphics);
        }
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        Coin temp;
        for (int i = 0; i < 5; i++) {
            temp = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (canPutCoin(temp))
                coins.add(temp);
            else
                i--;
        }
        return coins;
    }

    //Function that verifies if the coin can be drawn
    private boolean canPutCoin(Coin coin){
        return !hasHero(coin.position) && !hasCoin(coin.position);
    }

    //Function that verifies if the hero landed on a coin
    public void retrieveCoins(){
        boolean landedInCoin = false;
        Coin temp = null;
        for (Coin coin : coins){
            if(coin.getPosition().equals(this.hero.getPosition())){
                landedInCoin = true;
                temp = coin;
                break;
            }
        }
        if (landedInCoin) coins.remove(temp);
    }

    private boolean hasHero(Position position){
        return this.hero.getPosition().equals(position);
    }

    private boolean hasCoin(Position position){
        if(this.coins == null) return false;

        for (Coin Lcoin : this.coins){
            if (Lcoin.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    private boolean hasMonster(Position position){
        if(this.monsters == null) return false;

        for (Monster monster: this.monsters){
            if (monster.getPosition().equals(position)){
                return true;
            }
        }
        return false;
    }

    private boolean hasWall(Position position){
        if(position.getY() <= 1 || position.getX() <= 1 || position.getX() >= this.width - 1 || position.getY() >= this.height - 1) return true;
        return false;
    }

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> tmonsters = new ArrayList<>();
        Monster temp;
        for (int i = 0; i < 3; i++) {
            temp = new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            if (!hasHero(temp.position))
                tmonsters.add(temp);
            else
                i--;
        }
        return tmonsters;
    }

    private boolean canMonsterMove(Position position){
        return !hasCoin(position) && !hasWall(position) && !hasMonster(position);
    }

    private void moveMonsters(){
        Position pos;
        for(Monster m : monsters) {
            while (true){
                pos = m.move();
                if (canMonsterMove(pos)) {
                    m.setPosition(pos);
                    break;
                }
            }
        }
    }

    public boolean verifyMonsterCollisions(){
        for (Monster m : this.monsters){
            if (m.position.equals(this.hero.position)){
                return true;
            }
        }
        return false;
    }
}
