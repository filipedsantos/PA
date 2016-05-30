
package ui.ui_graphic;

import model.ObservableGame;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MicroSpaceEmpireView extends JFrame implements Observer{
    
    ObservableGame game;
    
    public MicroSpaceEmpireView() throws IOException{
        super("Micro Space Empire");
        this.game = new ObservableGame();
        
        
        game.addObserver(this);
        addComponents();
        
        setVisible(true);
        this.setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }
    
    private void addComponents() throws IOException{
        Container cp = getContentPane();
        
        cp.setLayout(new BorderLayout());
        
    }
    

    @Override
    public void update(Observable o, Object o1) {
        repaint();
    }
    
}
