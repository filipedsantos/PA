
package ui.ui_graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class SidePanel extends JPanel {
    
    ObservableGame game;
    JLabel l;
    
    EventsPanel eventPanel;
    UserInfo info;

    public SidePanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
    }
    
    private void setupLayout() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(150, 160));
        
        add(l);
        add(Box.createVerticalStrut(10));
        add(info);
        add(Box.createVerticalStrut(300));
        add(eventPanel);
        validate();
    }
    
    private void setupComponents() {
        l = new JLabel("User Data");
    
        eventPanel = new EventsPanel(game);
        info = new UserInfo(game);
    }

    
    
    
}
