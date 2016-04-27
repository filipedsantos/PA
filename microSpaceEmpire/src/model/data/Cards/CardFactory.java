
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
    
}
