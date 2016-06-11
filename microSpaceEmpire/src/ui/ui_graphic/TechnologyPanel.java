package ui.ui_graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Observer;
import javafx.beans.Observable;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class TechnologyPanel extends JPanel {

    ObservableGame game;

    public TechnologyPanel(ObservableGame game) {
        this.game = game;
        
        setupComponents();
    }

    private void setupComponents() {
        JPanel p = new JPanel();

        p.setLayout(new GridLayout(6, 2, 10, 10));

        p.add(new TechnologyCell(game, 0, 0, "Capital Ships", "3", " Advance beyond military strength 3."));
        p.add(new TechnologyCell(game, 0, 1, "Forward Starbases", "4", " Required to explore distant systems."));
        p.add(new TechnologyCell(game, 1, 0, "Robot Workers", "2", " Recive 1/2 production during strike."));
        p.add(new TechnologyCell(game, 1, 1, "Planetary Defenses", "4", " +1 to resistance during invasion."));
        p.add(new TechnologyCell(game, 2, 0, "Hyper Television", "3", " +1 resistence during revolt."));
        p.add(new TechnologyCell(game, 2, 1, "Interstellar Diplomacy", "5", " Next planet is conquered for free."));
        p.add(new TechnologyCell(game, 3, 0, "Interspecies Commerce", "2", " Exchange 2 of one resource for 1 of the other."));
        p.add(new TechnologyCell(game, 3, 1, "Interstellar Banking", "3", " Advance beyond storage value 3."));

        add(p);
    }
        
}
