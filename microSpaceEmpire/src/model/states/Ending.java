package model.states;

import model.Game;
import model.ObservableGame;
import model.data.DataGame;


public class Ending extends StateAdapter{

    public Ending(DataGame dataGame){
        super(dataGame);
        
        getDataGame().generateScore();
    }
    
    @Override
    public IStates start(){
        return new AwaitBeginning(getDataGame());
    }
}
