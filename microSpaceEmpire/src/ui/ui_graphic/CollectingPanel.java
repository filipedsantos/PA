package ui.ui_graphic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;
import model.states.Collecting;

public class CollectingPanel extends JPanel implements Observer {

    ObservableGame game;
    JButton collect;
    
    public CollectingPanel(ObservableGame game) {
        this.game = game;

        setupComponents();
        setupLayout();
        this.game.addObserver(this);
        
        setVisible(game.getState() instanceof Collecting);
        if (!game.getGameData().isTechnologyPurchased("Interspecies Commerce"))
        game.pass();
        
    }

    private void setupLayout() {
        add(collect);
    }

    private void setupComponents() {
        collect = new JButton("Collect");
        
        collect.addActionListener(new ActionListener()
         {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.pass();
            }
        });
    }

    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof Collecting);
        repaint();
    }

}
