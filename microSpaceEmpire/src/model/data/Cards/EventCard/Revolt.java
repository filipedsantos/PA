package model.data.Cards.EventCard;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.DataGame;
import model.data.EmptyException;
import model.states.AwaitOption;
import model.states.Ending;
import model.states.IStates;

public class Revolt extends EventCard{
private static final String name = "Revolt";

    public Revolt(DataGame d) {
        super(d);
    }
    
    // 1st Parameter: type of system to attack - (0) Least Resistance, (1) Last Empire added
    // 2nd parameter: Force
    // 3rd parameter: String techology used

    @Override
    public IStates makeEventActionYear1() {
        
        if(getDataGame().getEmpireSize() == 1)
            return new AwaitOption(getDataGame());
      
    try {
        getDataGame().fightAgainstSystem(0, 1, "Hyper Televison");
    } catch (EmptyException ex) {
    }
        
        return new AwaitOption(getDataGame());
    }
    
    @Override
    public IStates makeEventActionYear2() {
        
        if(getDataGame().getEmpireSize() == 1)
            return new Ending(getDataGame());
        
    try {
        getDataGame().fightAgainstSystem(0, 3, "Hyper Televison");
    } catch (EmptyException ex) {
    }
        
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
