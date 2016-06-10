
package ui.ui_graphic;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class SidePanel extends JPanel {
    
    ObservableGame game;
    JLabel l;
    
    DeckPanel panel;
    UserInfo info;

    public SidePanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
    }
    
    private void setupLayout() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        add(l);
        
        panel.setAlignmentY(CENTER_ALIGNMENT);
        add(panel);
        add(Box.createVerticalStrut(50));
        
        add(info);
    
        validate();
    }
    
    private void setupComponents() {
        l = new JLabel("Side panel");
        panel = new DeckPanel(game);
        
        info = new UserInfo(game);
    }

    
    
    
}
