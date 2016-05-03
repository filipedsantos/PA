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

    public void setMilitary(boolean q) {
        this.military = q;
    }

    public void setTechnology(boolean q) {
        this.technology = q;
    }

    //Estados Seguintes
    @Override
    public IStates buildMilitary() {
        int limit;

        if (getDataGame().isTechnologyPurchased("Capital Ships")) {
            limit = getDataGame().MILITARY_STRENGTH_UPGRADED_LIMIT;
        } else {
            limit = getDataGame().MILITARY_STRENGTH_LIMIT;
        }

        this.military = false;
        
        System.out.println("Limit : " + limit);
        System.out.println("MS: " + getDataGame().getMilitaryStrenght());

        if (getDataGame().getMilitaryStrenght() < limit) {
            getDataGame().setMetalStorage(getDataGame().getMetalStorage() - 1);
            getDataGame().setWealthStorage(getDataGame().getWealthStorage() - 1);
            getDataGame().setMilitaryStrenght(getDataGame().getMilitaryStrenght() + 1);
        }

        return this;
    }

    @Override
    public IStates discoverTechnology(String TecName) {

        if (!getDataGame().validateTecName(TecName))  // if tecName not recogniced, return this
            return this;
        else if(getDataGame().getTechnologyByName(TecName).getCost() > getDataGame().getWealthStorage()){ // if not enought wealth to buy tec, return this
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
