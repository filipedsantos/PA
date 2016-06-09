package ui.ui_graphic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import model.ObservableGame;
import model.states.AwaitBeginning;


public class Starting extends JPanel implements Observer {

    ObservableGame game;
    JButton start;
    JLabel Welcome;

    public Starting(ObservableGame game) {
        this.game = game;
        
        game.addObserver(this);
        setupComponents();
        setupLayout();
        
        setVisible(game.getState() instanceof AwaitBeginning);
        
    
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof AwaitBeginning);
    }
       

    private void setupComponents() {
        Welcome = new JLabel("MICROSPACE EMPIRE");
        start = new JButton("START");
        
        start.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                game.start();
            }
        } );
    }
   

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(Welcome);
        add(Box.createVerticalStrut(20));
        add(start);
        validate();
    }

}
