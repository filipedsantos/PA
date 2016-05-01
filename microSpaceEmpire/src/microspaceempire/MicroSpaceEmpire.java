
package microspaceempire;

import java.io.IOException;
import ui.UI;

public class MicroSpaceEmpire {

    public static void main(String[] args) throws IOException {
        UI ui = new UI(args);
        ui.verifyArgsFromCommandLine();
    }

}
