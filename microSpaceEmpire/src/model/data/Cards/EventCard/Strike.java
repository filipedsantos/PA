package model.data.Cards.EventCard;

import java.io.Serializable;
import model.data.DataGame;
import model.states.AwaitOption;
import model.states.IStates;

public class Strike extends EventCard implements Serializable{
    static final long serialVersionUID = 1l;
    private static final String name = "Strike";

    public Strike(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() { 
        getDataGame().setLog("'Strike'");
        getDataGame().setCurrentEvent("Strike");
        getDataGame().setStrikeEvent(true);
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Strike'");
        getDataGame().setCurrentEvent("Strike");
        getDataGame().setStrikeEvent(true);
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
