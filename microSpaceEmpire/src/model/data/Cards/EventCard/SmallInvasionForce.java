package model.data.Cards.EventCard;

import model.data.DataGame;

public class SmallInvasionForce extends EventCard{
private static final String name = "Small Invasion Force";

    public SmallInvasionForce(DataGame d) {
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
