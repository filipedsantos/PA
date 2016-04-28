package model.data.Cards.EventCard;

import model.data.DataGame;

public class Revolt extends EventCard{
private static final String name = "Revolt";

    public Revolt(DataGame d) {
        super(d);
    }

    @Override
    public void makeEventActionYear1() {
        
    }
    
    @Override
    public void makeEventActionYear2() {
        
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getNameEvent(){
        return this.name;
    }
}
