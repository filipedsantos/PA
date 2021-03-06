package model.data.Cards.EventCard;

import java.io.Serializable;
import model.data.DataGame;
import model.states.AwaitOption;
import model.states.IStates;

public class PeaceAndQuiet extends EventCard implements Serializable {

    static final long serialVersionUID = 1l;
    private static final String name = "Peace & Quiet";

    public PeaceAndQuiet(DataGame d) {
        super(d);
    }

    @Override
    public IStates makeEventActionYear1() {
        getDataGame().setLog("'Peace & Quiet', Nothing Happens");
        getDataGame().setCurrentEvent("Peace And Quiet");
        return new AwaitOption(getDataGame());
    }

    @Override
    public IStates makeEventActionYear2() {
        getDataGame().setLog("'Peace & Quiet', Nothing Happens");
        getDataGame().setCurrentEvent("Peace And Quiet");
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
