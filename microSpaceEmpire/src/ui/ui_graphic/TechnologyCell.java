
package ui.ui_graphic;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.ObservableGame;
import model.states.Upgrading;

public class TechnologyCell extends JPanel {

    int row, col;
    ObservableGame game;
    JLabel name;
    JLabel cost;
    JLabel description;

    public TechnologyCell(final ObservableGame game, int r, int c, final String name, String cost, String des) {
        row = r;
        col = c;
        this.game = game;

        setPreferredSize(new Dimension(300, 50));

        setupComponents(name, cost, des);
        setupLayout();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                if(game.getState() instanceof Upgrading){
                    game.discoverTechnology(name);
                }
            }
        });
    }

    private void setupComponents(String name, String cost, String des) {
        this.name = new JLabel(" " + name);
        this.cost = new JLabel(" Cost: " + cost);
        this.description = new JLabel(des);
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        add(name);
        add(description);
        add(cost);
    }

    @Override
    public void paintBorder(Graphics g) {
        super.paintBorder(g);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
