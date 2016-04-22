package model.states;


public class Upgrading extends StateAdapter{
    boolean military = true;
    boolean technology = true;
    
    public Upgrading(){
        
    }
    
    @Override
    public IStates buildMilitary(){
        return this;
    }
    
    @Override
    public IStates discoverTechnology(){
        return this;
    }
    
    @Override
    public IStates gameOver(){
        return new Ending();
    }
    
    @Override
    public IStates newTurn(){
        return new AwaitOption();
    }
}
