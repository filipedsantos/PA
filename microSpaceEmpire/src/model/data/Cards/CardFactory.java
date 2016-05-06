
package model.data.Cards;

import java.io.Serializable;
import model.data.Cards.EventCard.EventCard;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.StartingSystem;
import model.data.Cards.SystemCard.SystemType;
import static model.data.Cards.SystemCard.SystemType.DISTANT_SYSTEM;
import static model.data.Cards.SystemCard.SystemType.NEAR_SYSTEM;
import static model.data.Cards.SystemCard.SystemType.STARTING_SYSTEM;
import model.data.DataGame;

public class CardFactory implements Serializable{
    
    static final long serialVersionUID = 1l;
        
//    public static EventCard buildCardEvent() {
//        return new EventCard();
//    }

    public static Card buildCardSystem(DataGame d, SystemType systemType, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        Card card = null;

        switch (systemType) {
            case STARTING_SYSTEM:
                card = new StartingSystem(d, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            case NEAR_SYSTEM:
                card = new NearSystem(d, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            case DISTANT_SYSTEM:
                card = new DistantSystem(d, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            default:
                break;
        }

        return card;
    }
    
}
