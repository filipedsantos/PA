package model.states;


public class AwaitOption extends StateAdapter{

    public AwaitOption() {
         
    }
    
    @Override
    public IStates pass(){
        return new Collecting();
    }
    
    @Override
    public IStates conquer(){
        return new Collecting();
    }
    
    @Override
    public IStates exploreAttack(){
        return new Collecting();
    }
    
    @Override
    public IStates gameOver(){
        return new Ending();
    }
    
}
