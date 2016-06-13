package ui.ui_graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;
import static ui.ui_graphic.Constants.BACKGROUND_LOGO;

class StatesOptionPanel extends JPanel implements Observer {

    ObservableGame game;
    JLabel state;
    AwaitOptionPanel awaitPanel;
    BuildMilitaryDiscoverTechnologyPanel upgradePanel;
    CollectingPanel collectPanel;
    ScorePanel scorePanel;

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
        scorePanel = new ScorePanel(game);
    }

    private void setupLayout() {    
        add(awaitPanel);
        add(upgradePanel);
        add(collectPanel);
        add(scorePanel);
    }
}
