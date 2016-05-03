
package model;

import java.io.IOException;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;
import model.data.DataGame;
import model.data.EmptyException;
import model.data.Technology;
import model.states.AwaitBeginning;
import model.states.IStates;

public class Game {
    private DataGame dataGame;
    private IStates state;
    
    public Game() throws IOException {
        dataGame = new DataGame();
        state = new AwaitBeginning(dataGame);
    }
    
    //uso geral dos dados de jogo
    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }
    
    //Uso geral dos estados
    public IStates getState() {
        return state;
    }

    public void setState(IStates state) {
        this.state = state;
    }
    
    //----------------------------------------------------
    //Metodos que disparam as ações na maquina de estados|
    //----------------------------------------------------
    public void start() {
        setState(getState().start());
    }
    
    public void end() {
       setState(getState().end());
    }
   
    public void pass() {
        setState(getState().pass());
    }
   
   public void conquer(int i) {
       setState(getState().conquer(i));
   }
   
   public void exploreAttack(SystemType s) {
       setState(getState().exploreAttack(s));
   }
  
   public void change(int o) {
       setState(getState().change(o));
   }
   
   public void buildMilitary() {
       setState(getState().buildMilitary());
   }
   
   public void discoverTechnology(String tecName) {
       setState(getState().discoverTechnology(tecName));
   }
   
   public void newTurn() {
       setState(getState().newTurn());
   }
   
   public void gameOver() {
       setState(getState().gameOver());
   }
   
   public void collect(){
       setState(getState().collect());
   }
    
   //-------------------------------------
   //Metodos de acesso aos dados de jogo |
   //-------------------------------------
   
   /*
        ...
   */
   
   /**
    * ToString
    */
   
   @Override
    public String toString() {
        return dataGame.toString();
    }

    public NearSystem getNearSystem() throws EmptyException{
        return getDataGame().getNearSystems(0);
    }
    
    public SystemCard getUnaligedNearSystemCard(int i) throws ArrayIndexOutOfBoundsException{
        return getDataGame().getUnalignedSystemsCard(i);
    }
    
    public DistantSystem getDistantSystem() throws EmptyException {
        return getDataGame().getDistantSystems(0);
    }

    public int getActualForce() {
        return getDataGame().getMilitaryStrenght();
    }

    public boolean getTechnologyBought(String forwardStarbases) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean verifyNearSystemsOnUnalignedSystems() {
        return getDataGame().verifyNearSystemsOnUnalignedSystems();
    }

    public Technology getTechnology(int i, int j) {
        return getDataGame().getTechnology()[i][j];
    }
    
    public boolean isTechnologyPurchased(String name) {
        return getDataGame().isTechnologyPurchased(name);
    }

    public void upgradeStocklimits() {
        getDataGame().upgradeStocklimits();
    }

    public void addProductionToStock() {
        getDataGame().addProductionToStock();
    }

    public void collectResources() {
        getDataGame().collectResources();
    }

    public SystemType getUnalignedSystemCardType(int i) {
        return getDataGame().getUnalignedSystems().get(i).getSystemType();
    }

    public String getLog() {
        return getDataGame().getLog();
    }

    public void refreshlog() {
        getDataGame().refreshLog();
    }
}
