
package model.states;

public class StateAdapter implements IStates{
  
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
