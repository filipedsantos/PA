package ui.ui_graphic;

import files.FileUtility;
import model.ObservableGame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Game;
import model.states.AwaitBeginning;


public class MicroSpaceEmpireView extends JFrame implements Observer {


    ObservableGame game;
    MicroSpaceEmpireGamePanel panel;
    Starting start;
    ScorePanel scorePanel;
    
    public MicroSpaceEmpireView() throws IOException{
        super("Micro Space Empire");
        this.game = new ObservableGame();
        game.addObserver(this);
        
        start = new Starting(game);
        scorePanel = new ScorePanel(game);
        panel = new MicroSpaceEmpireGamePanel(game);

        addComponents();
        menus();

        setVisible(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }

    private void addComponents() throws IOException {
        Container cp = getContentPane();

        cp.setLayout(new BorderLayout());
  
        cp.add(panel, BorderLayout.CENTER);
        cp.add(start, BorderLayout.SOUTH);
        

    }

    @Override
    public void update(Observable o, Object o1) {
        
        repaint();
    }

    private void menus() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);

        JMenuItem newGameJMI = new JMenuItem("New Game");
        newGameJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem loadObjJMI = new JMenuItem("Load");
        loadObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));

        JMenuItem saveObjJMI = new JMenuItem("Save");
        saveObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem exitObjJMI = new JMenuItem("Exit");
        exitObjJMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        gameMenu.add(newGameJMI);
        gameMenu.add(loadObjJMI);
        gameMenu.add(saveObjJMI);
        gameMenu.addSeparator();
        gameMenu.add(exitObjJMI);

        menuBar.add(gameMenu);

        newGameJMI.addActionListener(new NewGameMenuBarListener());
        loadObjJMI.addActionListener(new LoadObjMenuBarListener());
        saveObjJMI.addActionListener(new SaveObjMenuBarListener());
        exitObjJMI.addActionListener(new ExitObjMenuBarListener());
    }

    private class NewGameMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            game.gameOver();
        }
    }

    private class LoadObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showOpenDialog(MicroSpaceEmpireView.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    game.setGame((Game) FileUtility.retrieveGameFromFile(file));
                } catch (IOException | ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(MicroSpaceEmpireView.this, "Operation failed: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    private class SaveObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            JFileChooser fc = new JFileChooser("./data");
            int returnVal = fc.showSaveDialog(MicroSpaceEmpireView.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    FileUtility.saveGameToFile(file, game.getGame());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(MicroSpaceEmpireView.this, "Operation failed: \r\n\r\n" + e);
                }
            } else {
                System.out.println("Operation canceled ");
            }
        }
    }

    private class ExitObjMenuBarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }

}
