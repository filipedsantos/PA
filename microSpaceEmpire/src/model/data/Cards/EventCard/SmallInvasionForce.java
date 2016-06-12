package model.data.Cards.EventCard;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.DataGame;
import model.data.EmptyException;
import model.states.AwaitOption;
import model.states.IStates;

public class SmallInvasionForce extends EventCard implements Serializable{
    static final long serialVersionUID = 1l;
    private static final String name = "Small Invasion Force";

    public SmallInvasionForce(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() {
        getDataGame().setLog("'Small Invasion Force'");
        getDataGame().setCurrentEvent("Small Invasion Force");
        try {
            getDataGame().fightAgainstSystem(0, 1, "Planetary Defenses");
        } catch (EmptyException ex) {
            return new AwaitOption(getDataGame());
        }

        return new AwaitOption(getDataGame());
    }

    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Small Invasion Force'");
        getDataGame().setCurrentEvent("Small Invasion Force");
        try {
            getDataGame().fightAgainstSystem(0, 2, "Planetary Defenses");
        } catch (EmptyException ex) {
            return new AwaitOption(getDataGame());
        }

        return new AwaitOption(getDataGame());
    }

    @Override
    public String toString() {
        return name;
    }

    public String getNameEvent() {
        return this.name;
    }

}
