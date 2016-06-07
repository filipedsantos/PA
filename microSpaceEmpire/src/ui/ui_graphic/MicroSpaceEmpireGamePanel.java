
package ui.ui_graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.ObservableGame;

class MicroSpaceEmpireGamePanel extends JPanel{
    ObservableGame game;
    
    // Panels of the game to be created
    SidePanel sidePanel;
    CardsInUsePanel cardsInUsePanel;
    EventsPanel eventsPanel;

    public MicroSpaceEmpireGamePanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
        setupLayout();
    }

    private void setupComponents() {
        sidePanel = new SidePanel(game);
        cardsInUsePanel = new CardsInUsePanel(game);
        eventsPanel = new EventsPanel(game);
    }

    private void setupLayout() {
        JPanel pWest, pCenter;
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        
        setLayout(new BorderLayout());

        pWest=new JPanel();
        pWest.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(sidePanel, BorderLayout.WEST);
        
        pCenter = new JPanel();
        pCenter.setLayout(new BorderLayout());
        pCenter.add(cardsInUsePanel, BorderLayout.NORTH);
        pCenter.add(eventsPanel, BorderLayout.SOUTH);
        
        add(pCenter,BorderLayout.CENTER);
        
        
        
        validate();
    }
   
    
    
}
