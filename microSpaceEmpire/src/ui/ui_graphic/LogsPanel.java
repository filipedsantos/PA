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
    JLabel l;
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

        add(l);
        add(log);
        validate();
    }

    private void setupComponents() {
        l = new JLabel("South Panel");
        log = new JLabel("log: "+ game.getLog());
    }

    @Override
    public void update(Observable o, Object o1) {
        log.setText("log: "+ game.getGameData().getLog());
        repaint();
    }

}
