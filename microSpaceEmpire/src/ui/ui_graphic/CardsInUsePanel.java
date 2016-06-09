package ui.ui_graphic;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;

public class CardsInUsePanel extends JPanel {

    ObservableGame game;
    JLabel l;
    JLabel lEmpire;
    JLabel lUnalignedSystems;
    
    CardsList empire;
    CardsList unalignedSystems;

    CardsInUsePanel(ObservableGame game) {
        this.game = game;

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createLineBorder(Color.black));

        add(l);
        add(lEmpire);
        add(empire);
        add(lUnalignedSystems);
        add(unalignedSystems);

        validate();
    }

    private void setupComponents() {
        l = new JLabel("North panel");
        lEmpire = new JLabel("Your Empire:");
        empire = new CardsList(game);
        lUnalignedSystems = new JLabel("Unaligned Systems:");
        unalignedSystems = new CardsList(game);
    }

}
