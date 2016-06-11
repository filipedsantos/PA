/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ui_graphic;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.states.AwaitOption;
import model.states.Upgrading;
import static ui.ui_graphic.CardCell.images;

/**
 *
 * This class contains the Systems card that will be used to explore. -> Near
 * Systems and Distant Systems.
 */
public class AwaitOptionPanel extends JPanel implements Observer {
    
    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;
    
    ObservableGame game;
    JLabel titleNear;
    JLabel titleDistant;
    JPanel nearDeck;
    JPanel distantDeck;
    JButton pass;
    
    public AwaitOptionPanel(ObservableGame game) {
        this.game = game;
        this.game.addObserver(this);
        
        setupComponents();
        setupLayout();
        
        setVisible(game.getState() instanceof AwaitOption);
    }
    
    private void setupLayout() {
        
        if (RIGHT_TO_LEFT) {
            this.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }
        this.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            c.fill = GridBagConstraints.HORIZONTAL;
        }
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 0, 0, 0);  //left padding
        add(titleNear, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.insets = new Insets(20, 0, 0, 0);  //left padding
        add(titleDistant, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);  //left padding
        add(nearDeck, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        c.insets = new Insets(20, 0, 0, 0);  //left padding
        add(distantDeck, c);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(20, 0, 0, 0);  //left padding
        add(pass, c);
        
    }
    
    private void setupComponents() {
        titleDistant = new JLabel("Distant System");
        titleNear = new JLabel("Near System");
        
        nearDeck = new JPanel() {
            
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                setPreferredSize(new Dimension(100, 150));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (game.getGameData().getNearSystemsSize() > 0) {
                    try {
                        g.drawImage(ImageIO.read(Resources.getResourceFile("images/Systems/systemBack.jpg")), 0, 0, getWidth() - 1, getHeight() - 1, null);
                    } catch (IOException ex) {
                    }
                }
                
            }
            
        };
        
        nearDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                game.exploreAttack(SystemType.NEAR_SYSTEM);
            }
        });
        
        distantDeck = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                
                setPreferredSize(new Dimension(100, 150));
                this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                if (game.getGameData().getDistantSystemsSize() > 0) {
                    try {
                        g.drawImage(ImageIO.read(Resources.getResourceFile("images/Systems/systemBack.jpg")), 0, 0, getWidth() - 1, getHeight() - 1, null);
                    } catch (IOException ex) {
                    }
                }
            }
        };
        distantDeck.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    game.exploreAttack(SystemType.DISTANT_SYSTEM);
            }
        });
        
        pass = new JButton("Pass");
        
        pass.addActionListener(new ActionListener() {            
            @Override
            public void actionPerformed(ActionEvent ev) {
                game.pass();
                
            }
        });
    }
    
    @Override
    public void update(Observable o, Object o1) {
        setVisible(game.getState() instanceof AwaitOption);
    }
    
}
