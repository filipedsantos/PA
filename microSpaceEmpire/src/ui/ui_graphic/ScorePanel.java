package ui.ui_graphic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import model.ObservableGame;
import model.states.Ending;


public class ScorePanel extends JPanel implements Observer{
    ObservableGame game;
    JLabel score;
    JButton newGame;

    public ScorePanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);
        
        setupComponents();
        setupLayout();
    }
    
    private void setupComponents() {
        score = new JLabel();
        newGame = new JButton("New Game");
        
        newGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.start();
            }

        });
    }
    
    private void setupLayout() {
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        score.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add(score);
        add(Box.createVerticalStrut(10));
        
        add(newGame);
    }
    
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof Ending);
        score.setText(game.getGameData().getLog());
    }
        
    
}
