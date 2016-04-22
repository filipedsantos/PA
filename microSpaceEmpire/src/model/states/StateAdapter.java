
package model.states;
import model.data.DataGame;

public class StateAdapter implements IStates{
  
   //dados de jogo 
   private DataGame dataGame;

    public StateAdapter(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    } 
   
   //acções que disparam os eventos
   @Override
   public IStates start() {
       return this;
   }
   
   @Override
   public IStates end() {
       return this;
   }
   
   @Override
   public IStates pass() {
       return this;
   }
   
   @Override
   public IStates conquer() {
       return this;
   }
   
   @Override
   public IStates exploreAttack() {
       return this;
   }
   
   @Override
   public IStates change() {
       return this;
   }
   
   @Override
   public IStates buildMilitary() {
       return this;
   }
   
   @Override
   public IStates discoverTechnology() {
       return this;
   }
   
   @Override
   public IStates newTurn() {
       return this;
   }
   
   @Override
   public IStates gameOver() {
       return this;
   }   
    
}
