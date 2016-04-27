
package model.data;

public class DataGame {
    static int turn;
    int metalStorage;
    int walthStorage;
    int militaryStrenght;
    
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
