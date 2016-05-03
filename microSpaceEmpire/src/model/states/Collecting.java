package model.states;
import model.data.DataGame;

public class Collecting extends StateAdapter{
    boolean change;

    public Collecting(DataGame gameData){
        super(gameData);
        
        change = false;
    }
    
    public boolean getChange(){
        return this.change;
    }
    
    public void setChange(boolean q){
        this.change = q;
    }
    
    @Override
    public IStates collect(){
        int mLimit, wLimit;
        
        if (getDataGame().isTechnologyPurchased("Interstellar Banking")) {
            mLimit = getDataGame().METAL_STOCk_UPGRADED_LIMIT;
            wLimit = getDataGame().WEALTH_STOCK_UPGRADED_LIMIT;
        } else {
            mLimit = getDataGame().METAL_STOCK_LIMIT;
            wLimit =getDataGame().WEALTH_STOCK_LIMIT;
        }
        
        int mStorage = getDataGame().getMetalStorage() + getDataGame().getMetalProduction();
        int wStorage = getDataGame().getWealthStorage() + getDataGame().getWealthProduction();
        
        if(mStorage < mLimit){
            getDataGame().setMetalStorage(mStorage);
        }
        else{
            getDataGame().setMetalStorage(mLimit);
        }
        
        if(wStorage < wLimit){
            getDataGame().setWealthStorage(wStorage);
        }
        else{
            getDataGame().setWealthStorage(wLimit);
        }
        
        return this;
    }
    
    @Override
    public IStates pass(){
        return new Upgrading(getDataGame());
    }
    
    @Override
    public IStates change(int o){  
        
        
        if(o == 3){
            return new Upgrading(getDataGame());
        }
        else if(o == 1){
            getDataGame().swapResources(1);
        }else if(o == 2){
            getDataGame().swapResources(2);
        }
        
        return new Upgrading(getDataGame());
        
    }
    
    @Override
    public IStates gameOver(){
        return new Ending(getDataGame());
    }
    
}
