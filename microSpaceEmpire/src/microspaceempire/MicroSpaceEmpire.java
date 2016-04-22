
package microspaceempire;

import model.Game;
import ui_text.TextUserInterface;

public class MicroSpaceEmpire {

    public static void main(String[] args) {
        TextUserInterface ui = new TextUserInterface(new Game());
        ui.run();
    }

}
