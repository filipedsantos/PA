/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui_graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.*;
import model.ObservableGame;
import model.data.Cards.SystemCard.SystemType;
import static ui.ui_graphic.CardCell.images;

/**
 *
 * This class contains the Systems card that will be used to explore.
 * -> Near Systems and Distant Systems.
 */
public class DeckPanel extends JPanel implements Observer {

    ObservableGame game;
    JLabel titleNear;
    JLabel titleDistant;
    JPanel nearDeck;
    JPanel distantDeck;

    public DeckPanel(ObservableGame game) {
        this.game = game;
        this.game.addObserver(this);

        setupComponents();
        setupLayout();
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        
        
        //add(titleNear);
        add(nearDeck);
        add(Box.createHorizontalStrut(10));
        //add(titleDistant);
        add(distantDeck);
        
    }

    private void setupComponents() {
        titleDistant = new JLabel("Distant System");
        titleNear = new JLabel("Near System");

        nearDeck = new JPanel() {
           
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
             
                setPreferredSize(new Dimension(100,150));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(game.getGameData().getNearSystemsSize() > 0)
                    try {
                        g.drawImage(ImageIO.read(Resources.getResourceFile("images/Systems/systemBack.jpg")), 0, 0, getWidth()-1, getHeight()-1, null);
                } catch (IOException ex) {
                }
 
            }
            
        };
        nearDeck.addMouseListener(new MouseAdapter() {
        @Override
            public void mousePressed(MouseEvent e) {
                game.exploreAttack(SystemType.NEAR_SYSTEM);
                /*
                *
                */
                game.pass();
                game.newTurn();
                /**/
            } 
        });
        
        distantDeck = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                setPreferredSize(new Dimension(100,150));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if(game.getGameData().getDistantSystemsSize() > 0)
                    try {
                        g.drawImage(ImageIO.read(Resources.getResourceFile("images/Systems/systemBack.jpg")), 0, 0, getWidth()-1, getHeight()-1, null);
                } catch (IOException ex) {
                }
            }
        };
        distantDeck.addMouseListener(new MouseAdapter() {
        @Override
            public void mousePressed(MouseEvent e) {

            } 
        });

    }

    @Override
    public void update(Observable o, Object o1) {
        repaint();
    }

}
