package ui.ui_graphic;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.ObservableGame;
import model.data.Cards.SystemCard.SystemType;
import model.states.AwaitOption;

class CardCell extends JPanel implements Constants {

    ObservableGame game = null;
    int col;
    String systemType;
    
    static Map<String, Image>images; 
    static{    
        images = new HashMap<>();
        
        try{
            images.put(HOME_WORLD, ImageIO.read(Resources.getResourceFile(PATH_IMG_HOME_WORLD)));
            images.put(CANOPUS, ImageIO.read(Resources.getResourceFile(PATH_IMG_CANOPUS)));
            images.put(GALAXYSEDGE, ImageIO.read(Resources.getResourceFile(PATH_IMG_GALAXYSEDGE)));
            images.put(POLARIS, ImageIO.read(Resources.getResourceFile(PATH_IMG_POLARIS)));
            images.put(SIRIUS, ImageIO.read(Resources.getResourceFile(PATH_IMG_SIRIUS)));
            images.put(CYGNUS, ImageIO.read(Resources.getResourceFile(PATH_IMG_CYGNUS)));
            images.put(PROCYGON, ImageIO.read(Resources.getResourceFile(PATH_IMG_PROCYGON)));
            images.put(PROXIMA, ImageIO.read(Resources.getResourceFile(PATH_IMG_PROXIMA)));
            images.put(TAUCETI, ImageIO.read(Resources.getResourceFile(PATH_IMG_TAUCETI)));
            images.put(WOLF359, ImageIO.read(Resources.getResourceFile(PATH_IMG_WOLF359)));
            images.put(EPSILONERIDANI, ImageIO.read(Resources.getResourceFile(PATH_IMG_EPSILONERIDANI)));
        }catch(IOException e){}
    }
    
    
    CardCell(final ObservableGame game, int c, String sT) {
        this.col = c;
        this.game = game;
        this.systemType = sT;
        
        setPreferredSize(new Dimension(100, 150));
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent ev){
                if(game.getState() instanceof AwaitOption){
                    if(systemType == "unaligned")
                        game.conquer(col);
                }
            }
        });
    }

    public String getNameCard() {
        
        if(systemType == "empire")
            return game.getGameData().getEmpire().get(col).getName();
        else if(systemType == "unaligned")
            return game.getGameData().getUnalignedSystems().get(col).getName();
        else
            return"";
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
            g.drawImage(images.get(getNameCard()), 0, 0, getWidth() - 1, getHeight() - 1, null);
        } catch (IndexOutOfBoundsException e) {
        }
        

    }


}
