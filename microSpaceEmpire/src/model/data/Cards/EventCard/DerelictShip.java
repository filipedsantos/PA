package model.data.Cards.EventCard;

import java.io.Serializable;
import model.data.DataGame;
import model.states.AwaitOption;
import model.states.IStates;

public class DerelictShip extends EventCard implements Serializable{
    static final long serialVersionUID = 1l;
    private static final String name = "Derelict Ship";
    
    public DerelictShip(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() {
        getDataGame().setLog("'Derelict Ship'");
        getDataGame().addMetalFromEvent(1);
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Derelict Ship'");
        getDataGame().addMetalFromEvent(1);
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
