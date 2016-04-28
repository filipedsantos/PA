
package model.data.Cards.SystemCard;

public class DistantSystem extends SystemCard{

    public DistantSystem() {
        super(SystemType.DISTANT_SYSTEM);
    }

    public DistantSystem(String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(SystemType.DISTANT_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }
    
}
