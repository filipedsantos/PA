
package ui.ui_text;

import java.util.Scanner;
import model.Game;

public class TextUserInterface {
    
    private Game game;
    private Scanner s;

    public TextUserInterface() {
        this.game = new Game();
        s = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Running..");
    }
    
}
