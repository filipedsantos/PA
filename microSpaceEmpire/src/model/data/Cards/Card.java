
package model.data.Cards;

import java.io.Serializable;
import model.data.DataGame;

public abstract class Card implements Serializable{
    
    private DataGame dataGame;
    private CardType cardType;
    private String name;

    public Card(CardType cardType, DataGame d) {
        this.cardType = cardType;
        this.dataGame = d;
    }
    
    public Card(CardType cardType, String cardName, DataGame d) {
        this.cardType = cardType;
        this.name = cardName;
        this.dataGame = d;
    }
    
    /**
     * 
     * Gets and Sets 
     */
    
    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }

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

//    @Override
//    public String toString() {
//        String s;
//        
////        s = "Card: " + this.getName();
////        s+= "\n\tType card: " + this.getCardType();
//        
////        s = "" + this.getCardType() + ": ";
////        s += this.getName();
//        
//        return s;
//    }
 
}
