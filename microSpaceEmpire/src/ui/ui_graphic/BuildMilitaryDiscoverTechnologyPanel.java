
package ui.ui_graphic;

import java.awt.Component;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.ObservableGame;
import model.states.Upgrading;

public class BuildMilitaryDiscoverTechnologyPanel extends JPanel implements Observer{
    
    ObservableGame game;
    JButton buildMilitary;
    JButton discoverTechnology;
    JButton pass;

    public BuildMilitaryDiscoverTechnologyPanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
        
        setVisible(game.getState() instanceof Upgrading);
    }

    private void setupComponents() {
        buildMilitary = new JButton("Build Military");
        discoverTechnology = new JButton("Discover Technology");
        pass = new JButton("Pass");
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));

        //pass.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        add(Box.createVerticalStrut(10));
        add(buildMilitary);
        add(Box.createVerticalStrut(10));
        add(discoverTechnology);
        add(Box.createVerticalStrut(10));
        add(pass);
    }

    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof Upgrading);
    }
    
    
    
}
