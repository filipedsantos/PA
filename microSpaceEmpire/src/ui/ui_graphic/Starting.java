package ui.ui_graphic;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import javax.imageio.ImageIO;
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
        });
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        start.setAlignmentY(Component.CENTER_ALIGNMENT);
        start.setAlignmentX(Component.CENTER_ALIGNMENT);

        if (game.getState() instanceof AwaitBeginning) {
            try {
                start.add(new JLabel(new ImageIcon(ImageIO.read(Resources.getResourceFile(Constants.BACKGROUND_LOGO)))));
            } catch (IOException ex) {
            }
        }
        
        add(Box.createVerticalStrut(20));
        add(start);
        add(Box.createVerticalStrut(20));
        
        validate();
    }

}
