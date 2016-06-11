
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
        add(info);
    
        validate();
    }
    
    private void setupComponents() {
        l = new JLabel("Side panel");
    
        
        info = new UserInfo(game);
    }

    
    
    
}
