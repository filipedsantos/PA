
package model.data.Cards.SystemCard;

public class StartingSystem extends SystemCard{

    public StartingSystem() {
        super(SystemType.STARTING_SYSTEM);
    }

    public StartingSystem(String cardName, int cardType, int sType, int resistance, int metalProdution, int wealthProduction, int points) {
        super(SystemType.STARTING_SYSTEM, cardName, cardType, sType, resistance, metalProdution, wealthProduction, points);
    }

    
    @Override
    public String toString() {
        return  super.toString() + "H";
    }
    
}
