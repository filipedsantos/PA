
package model.data.Cards.EventCard;

import model.data.Cards.Card;
import model.data.Cards.CardType;

public abstract class EventCard extends Card{

    public EventCard() {
        super(CardType.EVENT);
    }
    
    public abstract void makeEventAction();

    @Override
    public String toString() {
        return "E";
    }
    
}
