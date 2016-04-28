
package model.data.Cards.SystemCard;

import model.data.DataGame;

public class NearSystem extends SystemCard{

    public NearSystem(DataGame d) {
        super(SystemType.NEAR_SYSTEM, d);
    }
    
    public NearSystem(DataGame d, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(d, SystemType.NEAR_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }
    
}
