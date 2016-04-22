
package ui_text;

import java.util.Scanner;
import model.Game;

public class TextUserInterface {
    
    private Game game;
    private Scanner s;

    public TextUserInterface(Game game) {
        this.game = game;
        s = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Running..");
    }
    
}
