package ui.ui_graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

class StatesOptionPanel extends JPanel implements Observer {

    ObservableGame game;
    JLabel state;
    AwaitOptionPanel awaitPanel;
    BuildMilitaryDiscoverTechnologyPanel upgradePanel;
    CollectingPanel collectPanel;

    public StatesOptionPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    @Override
    public void update(Observable o, Object o1) {

    }

    private void setupComponents() {
        
        awaitPanel = new AwaitOptionPanel(game);
        collectPanel = new CollectingPanel(game);
        upgradePanel = new BuildMilitaryDiscoverTechnologyPanel(game);
        
    }

    private void setupLayout() {    
        add(awaitPanel);
        add(upgradePanel);
        add(collectPanel);
    }

}
