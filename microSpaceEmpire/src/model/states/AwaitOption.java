package model.states;

import model.data.DataGame;


public class AwaitOption extends StateAdapter{

    public AwaitOption(DataGame dataGame) {
         super(dataGame);
    }
    
    
    //Estados Seguintes
    @Override
    public IStates pass(){
        return new Collecting(getDataGame());
    }
    
    @Override
    public IStates conquer(){
        return new Collecting(getDataGame());
    }
    
    @Override
    public IStates exploreAttack(){
        return new Collecting(getDataGame());
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
}
