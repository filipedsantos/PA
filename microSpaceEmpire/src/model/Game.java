
package model;

import java.io.IOException;
import model.data.Cards.SystemCard.SystemCard;
import model.data.DataGame;
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
   
   public void conquer() {
       setState(getState().conquer());
   }
   
   public void exploreAttack(SystemCard s) {
       setState(getState().exploreAttack(s));
   }
  
   public void change() {
       setState(getState().change());
   }
   
   public void buildMilitary() {
       setState(getState().buildMilitary());
   }
   
   public void discoverTechnology() {
       setState(getState().discoverTechnology());
   }
   
   public void newTurn() {
       setState(getState().newTurn());
   }
   
   public void gameOver() {
       setState(getState().gameOver());
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
   
   
}
