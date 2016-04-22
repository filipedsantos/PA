
package model.data;

public abstract class Card {
    
    private DataGame dataGame;

    public Card(DataGame dataGame) {
        this.dataGame = dataGame;
    }

    public DataGame getDataGame() {
        return dataGame;
    }

    public void setDataGame(DataGame dataGame) {
        this.dataGame = dataGame;
    }
    
}
