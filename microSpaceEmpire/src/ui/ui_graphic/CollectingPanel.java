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
    JButton tradeMW;
    JButton tradeWM;
    
    public CollectingPanel(ObservableGame game) {
        this.game = game;

        setupComponents();
        setupLayout();
        this.game.addObserver(this);

        setVisible(game.getState() instanceof Collecting);
    }

    private void setupLayout() {
        add(collect);
        add(tradeWM);
        add(tradeMW);
    }

    private void setupComponents() {
        collect = new JButton("Collect");
        tradeWM = new JButton("Trade 2 Wealth for Metal");
        tradeMW = new JButton("Trade 2 Metal for Wealth");

        collect.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.pass();
            }
        });

        tradeWM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.change(0);
                game.pass();
            }
        });

        tradeMW.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.change(1);
                game.pass();
            }
        });

    }

    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof Collecting);

        if (game.getGameData().isTechnologyPurchased("Interspecies Commerce")) {
            tradeMW.setVisible(true);
            tradeWM.setVisible(true);
            collect.setVisible(true);
            collect.setText("Pass");
        } else {
            collect.setVisible(true);
            tradeMW.setVisible(false);
            tradeWM.setVisible(false);
        }
        repaint();
    }

}
