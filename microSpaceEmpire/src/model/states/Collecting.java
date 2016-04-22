package model.states;
import model.data.DataGame;

public class Collecting extends StateAdapter{

    public Collecting(DataGame gameData){
        super(gameData);
    }
    
    @Override
    public IStates pass(){
        return new Upgrading(getDataGame());
    }
    
    @Override
    public IStates change(){
        return new Upgrading(getDataGame());
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
}
