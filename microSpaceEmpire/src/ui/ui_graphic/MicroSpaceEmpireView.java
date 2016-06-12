
package ui.ui_graphic;

import model.ObservableGame;
import java.awt.*;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import model.states.AwaitBeginning;
import model.states.AwaitOption;

public class MicroSpaceEmpireView extends JFrame implements Observer{
    
    ObservableGame game;
    MicroSpaceEmpireGamePanel panel;
    Starting start;
    ScorePanel scorePanel;
    
    public MicroSpaceEmpireView() throws IOException{
        super("Micro Space Empire");
        this.game = new ObservableGame();
        game.addObserver(this);
        
        start = new Starting(game);
        scorePanel = new ScorePanel(game);
        panel = new MicroSpaceEmpireGamePanel(game);
       
        addComponents();
        menus();

        
        setVisible(true);
        //this.setSize(1200, 600);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }
    
    private void addComponents() throws IOException{
        Container cp = getContentPane();
        
        cp.setLayout(new BorderLayout());
        cp.setBackground(Color.DARK_GRAY);
        cp.add(panel, BorderLayout.CENTER);
        cp.add(start, BorderLayout.SOUTH);

    }
    

    @Override
    public void update(Observable o, Object o1) {
        //panel.setVisible(game.getState() instanceof AwaitOption);
        //start.setVisible(game.getState() instanceof AwaitBeginning);
        repaint();
    }

    private void menus() {
    }
    
}
