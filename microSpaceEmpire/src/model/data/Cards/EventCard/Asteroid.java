package model.data.Cards.EventCard;

import java.io.Serializable;
import model.data.DataGame;
import model.states.AwaitOption;
import model.states.IStates;

public class Asteroid extends EventCard implements Serializable{
    static final long serialVersionUID = 1l;
   private static final String name = "Asteroid";
           
   public Asteroid(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() {
        getDataGame().setLog("'Asteroid'");
        getDataGame().setCurrentEvent("Asteroid");
        getDataGame().addWealthFromEvent(1);
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Asteroid'");
        getDataGame().setCurrentEvent("Asteroid");
        getDataGame().addWealthFromEvent(1);
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
