
package model.data.Cards.SystemCard;

import model.data.DataGame;

public class NearSystem extends SystemCard{

    public NearSystem(DataGame d) {
        super(SystemType.NEAR_SYSTEM, d);
    }
    
    public NearSystem(DataGame d, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(d, SystemType.NEAR_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }

    @Override
    public String toString() {
        String s;
        
        
        s = "\nNear system: " + super.getName();
        s += "\n\t\tResistance: " + this.getResistance();
        s += "\n\t\tMetal prodution: " + this.getMetalProdution();
        s += "\n\t\tWealth prodution: " + this.getWealthProdution();
        s += "\n\t\tPoints: " + this.getPoints();
        
        return s;
    }
    
    
    
}
