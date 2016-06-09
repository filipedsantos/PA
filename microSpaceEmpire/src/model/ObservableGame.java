package model;

import java.io.IOException;
import java.util.Observable;
import model.Game;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemType;
import model.data.DataGame;
import model.data.EmptyException;
import model.data.Technology;
import model.states.IStates;

public class ObservableGame extends Observable {

    Game game;

    public ObservableGame() throws IOException {
        this.game = new Game();
    }

    public Game getGame() {
        return game;
    }

    public DataGame getGameData() {
        return game.getDataGame();
    }

    public IStates getState() {
        return game.getState();
    }

    /*
     *
     */
    public void setGame(Game game) {
        this.game = game;

//        setChanged();
//        notifyObservers();
    }

    public void start() {
        game.start();

        setChanged();
        notifyObservers();
    }

    public void end() {
        game.end();

        setChanged();
        notifyObservers();
    }

    public void pass() {
        game.pass();

        setChanged();
        notifyObservers();
    }

    public void conquer(int i) {
        game.conquer(i);

        setChanged();
        notifyObservers();
    }

    public void exploreAttack(SystemType s) {
        game.exploreAttack(s);

        setChanged();
        notifyObservers();
    }

    public void change(int o) {
        game.change(o);

        setChanged();
        notifyObservers();
    }

    public void buildMilitary() {
        game.buildMilitary();

        setChanged();
        notifyObservers();
    }

    public void discoverTechnology(String tecName) {
        game.discoverTechnology(tecName);
        setChanged();
        notifyObservers();
    }

    public void newTurn() {
        game.newTurn();
        setChanged();
        notifyObservers();
    }

    public void gameOver() {
        game.gameOver();

        setChanged();
        notifyObservers();

    }

    public void collect() {
        game.collect();
    }

    /**
     *
     */
    @Override
    public String toString() {
        return game.toString();
    }

    public NearSystem getNearSystem() throws EmptyException {
        return game.getNearSystem();
    }

    public DistantSystem getDistantSystem() throws EmptyException {
        return game.getDistantSystem();
    }

    public int getActualForce() {
        return game.getActualForce();
    }

    public boolean getTechnologyBought(String forwardStarbases) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean verifyNearSystemsOnUnalignedSystems() {
        return game.verifyNearSystemsOnUnalignedSystems();
    }

    public Technology getTechnology(int i, int j) {
        return game.getTechnology(i, j);
    }

    public boolean isTechnologyPurchased(String name) {
        return game.isTechnologyPurchased(name);
    }

    public SystemType getUnalignedSystemCardType(int i) {
        return game.getUnalignedSystemCardType(i);
    }

    public String getLog() {
        return game.getLog();
    }

    public void refreshlog() {
        game.refreshlog();
    }

    public int getNearSystemsSize() {
        return game.getDataGame().getNearSystemsSize();
    }

    /**
     * Functions to save and load game on file
     */
//    public static Game loadGame(String name) throws FileNotFoundException, IOException, ClassNotFoundException {
//        ObjectInputStream oin = null;
//        Game g;
//        
//        try {
//            oin = new ObjectInputStream(new FileInputStream(name));
//            g = (Game) oin.readObject();
//            return g;
//        } finally{
//            if(oin != null)
//                oin.close();
//        }
//    }
//
//    public void saveGame(String name) throws FileNotFoundException, IOException {
//        ObjectOutputStream oout = null;
//        
//        try {
//            oout = new ObjectOutputStream(new FileOutputStream(name));
//            oout.writeObject(this);
//        } finally{
//          if(oout != null)
//              oout.close();
//        }
//    }
}
