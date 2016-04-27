
package model.data.Cards.SystemCard;

import model.data.Cards.Card;
import model.data.Cards.CardType;

public abstract class SystemCard extends Card{
    
    private int resistance;
    private int metalProdution;
    private int wealthProdution;
    
    private SystemType systemType = null;

    public SystemCard(SystemType type) {
        super(CardType.SYSTEM);
        this.systemType = type;
    }

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }
    
}
