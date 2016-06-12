
package ui.ui_graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.ObservableGame;
import model.states.AwaitBeginning;
import model.states.AwaitOption;

class MicroSpaceEmpireGamePanel extends JPanel implements Observer{
    ObservableGame game;
    
    // Panels of the game to be created
    SidePanel sidePanel;
    CardsInUsePanel cardsInUsePanel;
    LogsPanel eventsPanel;
    StatesOptionPanel middlePanel;

    public MicroSpaceEmpireGamePanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);
        
        setupComponents();
        setupLayout();
        setVisible(!(game.getState() instanceof AwaitBeginning));
    }

    private void setupComponents() {
        sidePanel = new SidePanel(game);
        cardsInUsePanel = new CardsInUsePanel(game);
        eventsPanel = new LogsPanel(game);
        middlePanel = new StatesOptionPanel(game);
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
        pCenter.add(middlePanel, BorderLayout.CENTER);
        
        add(pCenter,BorderLayout.CENTER);
        
        validate();
    }

    @Override
    public void update(Observable o, Object o1) {
        setVisible(!(game.getState() instanceof AwaitBeginning));
    }
   
    
    
}
