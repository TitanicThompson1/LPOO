import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;
    private boolean gameover = false;

    public Game() throws IOException {

        Terminal terminal = new DefaultTerminalFactory().createTerminal();
        this.screen = new TerminalScreen(terminal);

        this.screen.setCursorPosition(null);   // we don't need a cursor
        this.screen.startScreen();             // screens must be started
        this.screen.doResizeIfNecessary();     // resize screen if necessary

        this.arena = new Arena(30,15);

    }
    private void draw() throws IOException {

        this.screen.clear();
        this.arena.draw(screen.newTextGraphics());
        this.screen.refresh();

    }

    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = this.screen.readInput();
            processKey(key);
            if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
                this.screen.close();
            }else if (key.getKeyType() == KeyType.EOF){
                break;
            }else if (gameover){
                this.screen.close();
                System.out.println("You died!");
                break;
            }
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        gameover = !this.arena.processKey(key);
    }

}
