
package model.data.Cards.EventCard;

import model.data.Cards.Card;
import model.data.Cards.CardType;
import model.data.DataGame;
import model.states.IStates;

public abstract class EventCard extends Card{

    public EventCard(DataGame d) {
        super(CardType.EVENT, d);
    }
    
    
    /**
     * Gets and Sets
     */
    
    
    
    /**
     * Functions
     */
    
    public abstract IStates makeEventActionYear1();

    public abstract IStates makeEventActionYear2();
    
    @Override
    public String toString() {
        return "E";
    }
    
}
