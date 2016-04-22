package model.states;

import model.data.DataGame;


public class Upgrading extends StateAdapter{
    boolean military;
    boolean technology;
    
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
