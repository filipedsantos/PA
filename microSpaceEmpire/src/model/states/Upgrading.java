package model.states;

import model.data.DataGame;

public class Upgrading extends StateAdapter {

    private boolean military;
    private boolean technology;

    public Upgrading(DataGame gameData) {
        super(gameData);

        military = true;
        technology = true;
    }

    public boolean getMilitary() {
        return this.military;
    }

    public boolean getTechnology() {
        return this.technology;
    }

    //Estados Seguintes
    @Override
    public IStates buildMilitary() {

        getDataGame().setMetalStorage(getDataGame().getMetalStorage() - 1);
        getDataGame().setWealthStorage(getDataGame().getWealthStorage() - 1);

        int limit;

        if (getDataGame().isTechnologyPurchased("Capital ships")) {
            limit = getDataGame().MILITARY_STRENGTH_UPGRADED_LIMIT;
        } else {
            limit = getDataGame().MILITARY_STRENGTH_LIMIT;
        }

        if (getDataGame().getMilitaryStrenght() < limit) {
            getDataGame().setMilitaryStrenght(getDataGame().getMilitaryStrenght() + 1);
        }

        this.military = false;
        return this;
    }

    @Override
    public IStates discoverTechnology(String TecName) {

        if (!getDataGame().validateTecName(TecName)) 
            return this;
        else if(getDataGame().getTechnologyByName(TecName).getCost() > getDataGame().getWealthStorage()){
            return this;
            //LOG
        } else {
        
            getDataGame().getTechnologyByName(TecName).setBought(true);
            int newWealth = getDataGame().getWealthStorage() - getDataGame().getTechnologyByName(TecName).getCost();
            
            getDataGame().setWealthStorage(newWealth);
            
            this.technology = false;
            return this;
        }
    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

    @Override
    public IStates newTurn() {
        return new AwaitOption(getDataGame());
    }
}
