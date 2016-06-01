
package ui.ui_graphic;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import model.ObservableGame;

class MicroSpaceEmpireGamePanel extends JPanel{
    ObservableGame game;
    
    // Panels of the game to be created

    public MicroSpaceEmpireGamePanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
    }

    private void setupLayout() {
        this.setBorder(BorderFactory.createLineBorder(Color.red));
    }
   
    
    
}
