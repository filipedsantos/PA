
package model.data.Cards.EventCard;

import model.data.Cards.Card;
import model.data.Cards.CardType;
import model.data.DataGame;

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
    public abstract void makeEventAction();

    @Override
    public String toString() {
        return "E";
    }
    
}
