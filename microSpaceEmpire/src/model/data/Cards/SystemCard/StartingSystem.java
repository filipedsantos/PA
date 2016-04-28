
package model.data.Cards.SystemCard;

import model.data.DataGame;

public class StartingSystem extends SystemCard{

    public StartingSystem(DataGame d) {
        super(SystemType.STARTING_SYSTEM, d);
    }

    public StartingSystem(DataGame d, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(d, SystemType.STARTING_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }
    
}
