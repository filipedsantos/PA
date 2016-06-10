/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui_graphic;

import java.util.Observable;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.ObservableGame;

/**
 *
 * This class contains the Systems card that will be used to explore.
 * -> Near Systems and Distant Systems.
 */
public class DeckPanel extends JPanel implements Observer{
    
    ObservableGame game;
    UserInfo info;
   

    public DeckPanel(ObservableGame game) {
        this.game = game;
        this.game.addObserver(this);
        
        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        add(info);
    }

    private void setupComponents() {
        info = new UserInfo(game);
    }
    
    @Override
    public void update(Observable o, Object o1) {
        repaint();
    }
    
}
