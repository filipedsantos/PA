
package model.data.Cards;

import model.data.Cards.EventCard.EventCard;
import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.StartingSystem;
import model.data.Cards.SystemCard.SystemType;
import static model.data.Cards.SystemCard.SystemType.DISTANT_SYSTEM;
import static model.data.Cards.SystemCard.SystemType.NEAR_SYSTEM;
import static model.data.Cards.SystemCard.SystemType.STARTING_SYSTEM;

public class CardFactory {
    
//    public static EventCard buildCardEvent() {
//        return new EventCard();
//    }

    public static Card buildCardSystem(SystemType systemType) {
        Card card = null;

        switch (systemType) {
            case STARTING_SYSTEM:
                card = new StartingSystem();
                break;
            case NEAR_SYSTEM:
                card = new NearSystem();
                break;
            case DISTANT_SYSTEM:
                card = new DistantSystem();
                break;
            default:
                break;
        }

        return card;
    }

    public static Card buildCardSystem(SystemType systemType, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        Card card = null;

        switch (systemType) {
            case STARTING_SYSTEM:
                card = new StartingSystem(cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            case NEAR_SYSTEM:
                card = new NearSystem(cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            case DISTANT_SYSTEM:
                card = new DistantSystem(cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
                break;
            default:
                break;
        }

        return card;
    }
    
}
