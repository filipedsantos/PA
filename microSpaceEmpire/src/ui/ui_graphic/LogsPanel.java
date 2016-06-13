package ui.ui_graphic;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class LogsPanel extends JPanel implements Observer {

    ObservableGame game;
    JLabel log;

    LogsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);
        
        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(log);
        validate();
    }

    private void setupComponents() {
        log = new JLabel("log: "+ game.getLog());
    }

    @Override
    public void update(Observable o, Object o1) {
        log.setText("log: "+ game.getGameData().getLog());
        repaint();
    }

}
