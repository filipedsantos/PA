package model.states;

import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemType;
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
    public IStates exploreAttack(SystemCard s){
        
        // Verify type of System card
       
        
        
        return new Collecting(getDataGame());
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
}
