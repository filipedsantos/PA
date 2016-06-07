
package ui.ui_graphic;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class EventsPanel extends JPanel{
    
    ObservableGame game;
    JLabel l;

    EventsPanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(l);

        validate();
    }

    private void setupComponents() {
        l = new JLabel("South panel");
    }
    
}
