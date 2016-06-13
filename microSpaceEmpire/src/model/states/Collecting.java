package model.states;

import model.data.DataGame;

public class Collecting extends StateAdapter {

    boolean change;

    public Collecting(DataGame gameData) {
        super(gameData);
        getDataGame().refreshLog();
        collect();
        change = false;
    }

    public boolean getChange() {
        return this.change;
    }

    public void setChange(boolean q) {
        this.change = q;
    }

//    @Override
//    public IStates saveGame(){
//        getDataGame().saveThisGame();
//        return this;
//    }
    
    @Override
    public IStates collect() {
        int mLimit, wLimit;

        if (getDataGame().getEventStrike()==true) {
            if (!getDataGame().getTechnologyByName("Robot Workers").isBought()) {
                getDataGame().setLog("Strike Event is happening so you dont have resources this turn");
                getDataGame().setStrikeEvent(false);
            } else {
                if (getDataGame().isTechnologyPurchased("Interstellar Banking")) {
                    mLimit = getDataGame().METAL_STOCk_UPGRADED_LIMIT;
                    wLimit = getDataGame().WEALTH_STOCK_UPGRADED_LIMIT;
                } else {
                    mLimit = getDataGame().METAL_STOCK_LIMIT;
                    wLimit = getDataGame().WEALTH_STOCK_LIMIT;
                }

                int mStorage = getDataGame().getMetalStorage() + (getDataGame().getMetalProduction()/2);
                int wStorage = getDataGame().getWealthStorage() + (getDataGame().getWealthProduction()/2);

                if (mStorage < mLimit) {
                    getDataGame().setMetalStorage(mStorage);
                    getDataGame().setLog("\nStrike event is happen, this turn you only recive\n"
                        + "\nMetal: "+ getDataGame().getMetalProduction()/2);
                } else {
                    getDataGame().setLog("\nStrike event is happen, this turn you only recive\n"
                        + "\nMetal: "+ 0 +" you're storages are full!");
                    getDataGame().setMetalStorage(mLimit);
                }

                if (wStorage < wLimit) {
                    getDataGame().setWealthStorage(wStorage);
                    getDataGame().setLog("\nMetal: " + wStorage);
                } else {
                    getDataGame().setWealthStorage(wLimit);
                    getDataGame().setLog("\nMetal: "+ 0 +" you're wealth storages are full!");
                }
                
                getDataGame().setStrikeEvent(false);
            }
        } else {

            if (getDataGame().isTechnologyPurchased("Interstellar Banking")) {
                mLimit = getDataGame().METAL_STOCk_UPGRADED_LIMIT;
                wLimit = getDataGame().WEALTH_STOCK_UPGRADED_LIMIT;
            } else {
                mLimit = getDataGame().METAL_STOCK_LIMIT;
                wLimit = getDataGame().WEALTH_STOCK_LIMIT;
            }

            int mStorage = getDataGame().getMetalStorage() + getDataGame().getMetalProduction();
            int wStorage = getDataGame().getWealthStorage() + getDataGame().getWealthProduction();

            if (mStorage < mLimit) {
                getDataGame().setMetalStorage(mStorage);
            } else {
                getDataGame().setMetalStorage(mLimit);
            }

            if (wStorage < wLimit) {
                getDataGame().setWealthStorage(wStorage);
            } else {
                getDataGame().setWealthStorage(wLimit);
            }
        }
        
        return this;
    }

    @Override
    public IStates pass() {
        return new Upgrading(getDataGame());
    }

    @Override
    public IStates change(int o) {

        if (o == 3) {
            return new Upgrading(getDataGame());
        } else if (o == 1) {
            getDataGame().swapResources(1);
        } else if (o == 2) {
            getDataGame().swapResources(2);
        }

        return new Upgrading(getDataGame());

    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

}
