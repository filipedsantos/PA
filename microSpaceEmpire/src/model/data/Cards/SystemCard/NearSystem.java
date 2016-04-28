
package model.data.Cards.SystemCard;

public class NearSystem extends SystemCard{

    public NearSystem() {
        super(SystemType.NEAR_SYSTEM);
    }
    
    public NearSystem(String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(SystemType.NEAR_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }
    
}
