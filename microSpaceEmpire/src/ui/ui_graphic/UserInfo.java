
package ui.ui_graphic;

import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class UserInfo extends JPanel implements Observer{
    
    ObservableGame game;
    
    JLabel lMetalStorage;
    JLabel lMetalProduction;
    JLabel lWealthStorage;
    JLabel lWealthProduction;
    JLabel lMilitaryStrength;
   

    public UserInfo(ObservableGame game) {
        this.game = game;
        this.game.addObserver(this);
        
        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        lMetalProduction = new JLabel("Metal Production: ");
        lMetalStorage = new JLabel("Metal production: " );
        lWealthProduction = new JLabel("Wealth Production: ");
        lWealthStorage = new JLabel("Wealth Storage: ");
        lMilitaryStrength = new JLabel("Military Strength: ");
        
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(lMetalProduction);
        add(lWealthProduction);
        add(Box.createVerticalStrut(10));
        add(lMetalStorage);
        add(lWealthStorage);
        add(Box.createVerticalStrut(10));
        add(lMilitaryStrength);
        
        validate();
    }
    
    @Override
    public void update(Observable o, Object o1) {
        lMetalProduction.setText("Metal production: +" + game.getGameData().getMetalProduction() );
        lMetalStorage.setText("Metal storage: " + game.getGameData().getMetalStorage());
        lWealthProduction.setText("Wealth Production: +" + game.getGameData().getWealthProduction());
        lWealthStorage.setText("Wealth Storage: " + game.getGameData().getWealthStorage() );
        lMilitaryStrength.setText("Military Strength: " + game.getGameData().getMilitaryStrenght());
        repaint();
    }
    
}
