
package model.data;

public class DataGame {
    static int turn;
    
    public DataGame(){
        turn=1;
    }
    
    public void countTurn(){
        this.turn++;
    }
    
    public int getTurn(){
        return this.turn;
    }
}
