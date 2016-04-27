
package model.data.Cards.SystemCard;

import model.data.Cards.Card;
import model.data.Cards.CardType;

public abstract class SystemCard extends Card{
    
    private int resistance;
    private int metalProdution;
    private int wealthProdution;
    private int points;
    
    private SystemType systemType = null;

    public SystemCard(SystemType type) {
        super(CardType.SYSTEM);
        this.systemType = type;
    }
    
     public SystemCard(SystemType type, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(CardType.SYSTEM);
        this.systemType = type;
        this.resistance = resistance;
        this.metalProdution = metalProdution;
        this.wealthProdution = wealthProduction;
        this.points = points;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }
    
}
