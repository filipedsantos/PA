package ui.ui_graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.ObservableGame;
import model.data.EmptyException;

public class EventsPanel extends JPanel implements Observer, Constants {

    ObservableGame game;
    JLabel titleEvent;
    JPanel eventCard;
    static Map<String, Image> images;

    static {
        images = new HashMap<>();

        try {
            images.put(ASTEROID, ImageIO.read(Resources.getResourceFile(PATH_IMG_ASTEROID)));
            images.put(DERELICTSHIP, ImageIO.read(Resources.getResourceFile(PATH_IMG_DERELICTSHIP)));
            images.put(PEACEANDQUIET, ImageIO.read(Resources.getResourceFile(PATH_IMG_PEACEANDQUIET)));
            images.put(SMALINVASIONFORCE, ImageIO.read(Resources.getResourceFile(PATH_IMG_SMALINVASIONFORCE)));
            images.put(LARGEINVASIONFORCE, ImageIO.read(Resources.getResourceFile(PATH_IMG_LARGEINVASIONFORCE)));
            images.put(REVOLT, ImageIO.read(Resources.getResourceFile(PATH_IMG_REVOLT)));
            images.put(REVOLT2, ImageIO.read(Resources.getResourceFile(PATH_IMG_REVOLT2)));
            images.put(STRIKE, ImageIO.read(Resources.getResourceFile(PATH_IMG_STRIKE)));

        } catch (IOException e) {
        }
    }

    public EventsPanel(ObservableGame game) {
        this.game = game;
        game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupComponents() {

        eventCard = new JPanel() {
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
                    g.drawImage(images.get(getNameCard()), 0, 0, getWidth() - 1, getHeight() - 1, null);
                } catch (IndexOutOfBoundsException e) {
                } catch (EmptyException ex) {
                    Logger.getLogger(EventsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
    }

    private void setupLayout() {
        eventCard.setPreferredSize(new Dimension(100, 150));
        add(eventCard);
    }

    public String getNameCard() throws EmptyException {
        return game.getGameData().getEvent(0).getName();
    }

    @Override
    public void update(Observable o, Object o1) {
        repaint();
    }
}
