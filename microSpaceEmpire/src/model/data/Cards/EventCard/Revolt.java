package model.data.Cards.EventCard;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.DataGame;
import model.data.EmptyException;
import model.states.AwaitOption;
import model.states.Ending;
import model.states.IStates;

public class Revolt extends EventCard implements Serializable{
    static final long serialVersionUID = 1l;
    private static final String name = "Revolt";

    public Revolt(DataGame d) {
        super(d);
    }

    // 1st Parameter: type of system to attack - (0) Least Resistance, (1) Last Empire added
    // 2nd parameter: Force
    // 3rd parameter: String techology used
    @Override
    public IStates makeEventActionYear1() {
        getDataGame().setLog("'Revolt'");
        if (getDataGame().getEmpireSize() == 1) {
            getDataGame().setLog("You only have Home World, nothing to do!");
            return new AwaitOption(getDataGame());
        }
        try {
            getDataGame().fightAgainstSystem(1, 1, "Hyper Televison");
        } catch (EmptyException ex) {
        }

        return new AwaitOption(getDataGame());
    }

    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Revolt'");
        if (getDataGame().getEmpireSize() == 1) {
            getDataGame().setLog("You only have Home World, GAME OVER!");
            return new Ending(getDataGame());    
        }
        try {
            getDataGame().fightAgainstSystem(1, 3, "Hyper Televison");
            return new AwaitOption(getDataGame());
        } catch (EmptyException ex) {
             return new AwaitOption(getDataGame());
        }
    }

    @Override
    public String toString() {
        return name;
    }

    public String getNameEvent() {
        return this.name;
    }
}
