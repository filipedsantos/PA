package ui.ui_graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import model.ObservableGame;
import model.data.Cards.SystemCard.SystemType;

class CardCell extends JPanel implements Constants {

    ObservableGame game = null;
    int col;

    CardCell(final ObservableGame game, int c) {
        this.col = c;
        this.game = game;
        setPreferredSize(new Dimension(100, 150));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent ev) {
                game.exploreAttack(SystemType.NEAR_SYSTEM);
            }
        });
    }

    public String r() {
        return game.getGameData().getEmpire().get(col).getName();
    }

    @Override
    public void paintBorder(Graphics g) {
        super.paintBorder(g);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.LIGHT_GRAY);

        try {
            g.drawImage(ImageIO.read(Resources.getResourceFile(PATH_IMG_BACK_CARD)), 0, 0, getWidth() - 1, getHeight() - 1, null);
            g.setColor(Color.black);
        } catch (IOException ex) {
            Logger.getLogger(CardCell.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

}
