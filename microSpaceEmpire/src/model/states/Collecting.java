package model.states;


public class Collecting extends StateAdapter{

    public Collecting(){
    
    }
    
    @Override
    public IStates pass(){
        return new Upgrading();
    }
    
    @Override
    public IStates change(){
        return new Upgrading();
    }
    
    @Override
    public IStates gameOver(){
        return new Ending();
    }
    
}
