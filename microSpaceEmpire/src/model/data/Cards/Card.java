
package model.data.Cards;

public abstract class Card {
    
    private CardType cardType;
    private String name;

    public Card(CardType cardType) {
        this.cardType = cardType;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    @Override
    public String toString() {
        return "Card{" + "cardType=" + cardType + '}';
    }
 
}
