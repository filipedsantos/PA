
package model.data.Cards;

public abstract class Card {
    
    private CardType cardType;
    private String name;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }
    
    public Card(CardType cardType, String cardName) {
        this.cardType = cardType;
        this.name = cardName;
    }
    
    /**
     * 
     * Gets and Sets 
     */

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 
     * To String 
     */

    @Override
    public String toString() {
        String s;
        
        s = "Card: " + this.getName();
        s+= "\n\tType card: " + this.getCardType();
        
        return s;
    }
 
}
