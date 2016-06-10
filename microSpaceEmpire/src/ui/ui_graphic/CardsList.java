package ui.ui_graphic;

import java.awt.GridLayout;
import javax.swing.JPanel;
import model.ObservableGame;

class CardsList extends JPanel implements Constants {

    ObservableGame game;
    String systemType;

    public CardsList(ObservableGame game, String sType) {
        this.game = game;
        this.systemType = sType;
        
        setupLayout();
    }

    private void setupLayout() {
        JPanel p = new JPanel();

        p.setLayout(new GridLayout(1, DIM_SYSTEM_CARDS, 10, 0));

        for (int i = 0; i < DIM_SYSTEM_CARDS; i++) {
            CardCell cell = new CardCell(game, i, systemType);
            p.add(cell);
        }
        add(p);
    }

}
