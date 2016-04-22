
package ui.ui_graphic;

import java.util.Scanner;
import model.Game;

public class GraphicUserInterface {
    
    private Game game;
    private Scanner s;

    public GraphicUserInterface() {
        this.game = new Game();
        s = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Running..");
    }
    
}
