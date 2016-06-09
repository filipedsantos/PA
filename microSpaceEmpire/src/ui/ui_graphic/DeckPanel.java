/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui_graphic;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.ObservableGame;
import model.data.Cards.CardType;
import model.data.Cards.SystemCard.SystemType;

/**
 *
 * This class contains the Systems card that will be used to explore.
 * -> Near Systems and Distant Systems.
 */
public class DeckPanel extends JPanel implements Observer{
    
    ObservableGame game;
    JPanel nearSystemsInDeck;
    JPanel distantSystemsInDeck;

    public DeckPanel(ObservableGame game) {
        this.game = game;
        this.game.addObserver(this);
        
        
    }
    
    

    @Override
    public void update(Observable o, Object o1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
