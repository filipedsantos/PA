package model.data.Cards.EventCard;

import model.data.DataGame;
import model.states.AwaitOption;
import model.states.IStates;

public class Strike extends EventCard{
private static final String name = "Strike";

    public Strike(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() { 
        getDataGame().setLog("'Strike'");
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Strike'");
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getNameEvent(){
        return this.name;
    }
}
