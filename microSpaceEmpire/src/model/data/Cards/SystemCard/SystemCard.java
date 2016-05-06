
package model.data.Cards.SystemCard;

import java.io.Serializable;
import model.data.Cards.Card;
import model.data.Cards.CardType;
import model.data.DataGame;

public abstract class SystemCard extends Card implements Serializable{
    
    static final long serialVersionUID = 1l;
    
    private int resistance;
    private int metalProdution;
    private int wealthProdution;
    private int points;
    
    private SystemType systemType = null;

    public SystemCard(SystemType type, DataGame d) {
        super(CardType.SYSTEM, d);
        this.systemType = type;
    }
    
     public SystemCard(DataGame d, SystemType type, String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(CardType.SYSTEM, cardName, d);
        this.systemType = type;
        this.resistance = resistance;
        this.metalProdution = metalProdution;
        this.wealthProdution = wealthProduction;
        this.points = points;
    }
     
     /**
      * 
      * Gets and Sets 
      */

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getMetalProdution() {
        return metalProdution;
    }

    public void setMetalProdution(int metalProdution) {
        this.metalProdution = metalProdution;
    }

    public int getWealthProdution() {
        return wealthProdution;
    }

    public void setWealthProdution(int wealthProdution) {
        this.wealthProdution = wealthProdution;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }    

    public SystemType getSystemType() {
        return systemType;
    }

    public void setSystemType(SystemType systemType) {
        this.systemType = systemType;
    }
    
    /**
     * 
     * ToString 
     */

//    @Override
//    public String toString() {
//        String s;
//        
//        
//        s = "\nNear system: " + super.getName();
//        s += "\n\t\tResistance: " + this.getResistance();
//        s += "\n\t\tMetal prodution: " + this.getMetalProdution();
//        s += "\n\t\tWealth prodution: " + this.getWealthProdution();
//        s += "\n\t\tPoints: " + this.getPoints();
//        
//        return s;
//    }
    
    
    
}
