package model.states;

import model.data.DataGame;


public class Upgrading extends StateAdapter{
    
    private boolean military;
    private boolean technology;
    
    public Upgrading(DataGame gameData){
        super(gameData);
        
        military = true;
        technology = true;
    }
    
    public boolean getMilitary(){
        return this.military;
    }
    
    public boolean getTechnology(){
        return this.technology;
    }
    
    public void setMilitary(boolean q){
        this.military = q;
    }
    
    public void setTechnology(boolean q){
        this.technology = q;
    }
    
    //Estados Seguintes
    @Override
    public IStates buildMilitary(){
        getDataGame().setMetalStorage(getDataGame().getMetalStorage() - 1);
        getDataGame().setWealthStorage(getDataGame().getWealthStorage() - 1);
        
        int limit;
        
        if(getDataGame().isTechnologyPurchased("Capital ships"))
            limit = getDataGame().MILITARY_STRENGTH_UPGRADED_LIMIT;
        else
            limit = getDataGame().MILITARY_STRENGTH_LIMIT;
        
        System.out.println("Limit : " + limit);
        System.out.println("MS: " + getDataGame().getMilitaryStrenght());
        
        if(getDataGame().getMilitaryStrenght() < limit ){
            getDataGame().setMilitaryStrenght(getDataGame().getMilitaryStrenght() + 1);
        }
        
        return this;
    }
    
    @Override
    public IStates discoverTechnology(){
        return this;
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
    @Override
    public IStates newTurn(){
        return new AwaitOption(getDataGame());
    }
}
