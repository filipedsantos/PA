package model.states;
import model.data.DataGame;

public class Collecting extends StateAdapter{
    boolean change;

    public Collecting(DataGame gameData){
        super(gameData);
        
        change = false;
    }
    
    public boolean getChange(){
        return this.change;
    }
    
    public void setChange(boolean q){
        this.change = q;
    }
    
    @Override
    public IStates pass(){
                        
        return new Upgrading(getDataGame());
    }
    
    @Override
    public IStates change(int o){        
        if(o == 1){
            getDataGame().swapResources(1);
        }
        else{
            getDataGame().swapResources(2);
        }
        
        return new Upgrading(getDataGame());
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
}
