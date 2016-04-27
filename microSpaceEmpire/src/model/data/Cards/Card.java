
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

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" + "cardType=" + cardType + ", name=" + name + '}';
    }
 
}
