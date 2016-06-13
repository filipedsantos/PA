package ui.ui_graphic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.ObservableGame;
import model.states.Upgrading;

public class BuildMilitaryDiscoverTechnologyPanel extends JPanel implements Observer {

    ObservableGame game;
    JButton buildMilitary;
    JButton discoverTechnology;
    JButton pass;
    TechnologyPanel tPanel;
    boolean militaryUsed;
    boolean technologyUsed;

    public BuildMilitaryDiscoverTechnologyPanel(ObservableGame game) {
        this.game = game;
        this.militaryUsed = true;
        this.technologyUsed = true;
        
        setupComponents();
        setupLayout();

        this.game.addObserver(this);
        setVisible(game.getState() instanceof Upgrading);
    }

    private void setupComponents() {
        tPanel = new TechnologyPanel(game);
        tPanel.setVisible(false);

        buildMilitary = new JButton("Build Military");
      
        buildMilitary.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.buildMilitary();
            }
        });

        discoverTechnology = new JButton("Discover Technology");
      
        discoverTechnology.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                tPanel.setVisible(true);
            }
        });

        pass = new JButton("Pass");
 
        pass.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.newTurn();
            }
        });
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        add(pass);
        add(Box.createVerticalStrut(10));
        add(buildMilitary);
        add(Box.createVerticalStrut(10));
        add(discoverTechnology);
        
        
        add(Box.createVerticalStrut(10));
        add(tPanel);

    }

    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof Upgrading);
        tPanel.setVisible(false);
    }

}
