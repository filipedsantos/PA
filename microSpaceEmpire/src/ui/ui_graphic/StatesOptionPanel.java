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
    DeckPanel panel;

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
        
        state = new JLabel("teste");
        panel = new DeckPanel(game);
    }

    private void setupLayout() {    
        add(state);
        add(panel);
    }

}
