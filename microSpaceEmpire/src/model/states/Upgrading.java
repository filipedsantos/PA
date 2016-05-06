package model.states;

import java.util.Collections;
import model.data.Cards.EventCard.EventCard;
import model.data.DataGame;
import model.data.EmptyException;

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

//    @Override
//    public IStates saveGame(){
//        getDataGame().saveThisGame();
//        return this;
//    }
    
    @Override
    public IStates discoverTechnology(String TecName) {

        if (!getDataGame().validateTecName(TecName)) {  // if tecName not recogniced, return this
            getDataGame().setLog("\n\nThat technology does not exist!\n");
            return this;
        } else if (getDataGame().getTechnologyByName(TecName).getCost() > getDataGame().getWealthStorage()) { // if not enought wealth to buy tec, return this
            getDataGame().setLog("\n\nYou haven't enough wealth to buy this technology\n");
            return this;
        } else if (!getDataGame().getTechnologyByName(TecName).isBought()) {

            getDataGame().getTechnologyByName(TecName).setBought(true);
            int newWealth = getDataGame().getWealthStorage() - getDataGame().getTechnologyByName(TecName).getCost();

            getDataGame().setWealthStorage(newWealth);

            this.technology = false;

            getDataGame().setLog("you purchase " + TecName + " with success!");
            return this;
        }
        return this;

    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

    @Override
    public IStates newTurn() {
        EventCard event = null;
        String log = "";

        try {
            event = getDataGame().getEvent(0);                  // Get top card of events deck
            //makeEventAction(event, getDataGame().getYear());    // Make event action
            getDataGame().getEvents().remove(0);
        } catch (EmptyException ex) {
            System.err.println("Events");
        }

        // If the last event card was used during 1s year
        // shuffle all event cards
        // Remove 2 event cards to side
        // If the last event card was used during 1s year
        // Inc game year
        // Next phase: explore/attack
        if (getDataGame().getEvents().isEmpty() && getDataGame().getYear() == 1) {
            getDataGame().createEventCards(getDataGame());
            Collections.shuffle(getDataGame().getEvents());
            getDataGame().getEvents().remove(0);
            getDataGame().getEvents().remove(0);
            getDataGame().setYear(2);
            getDataGame().setLog("\n\nHappy 2nd Year!");
            return new AwaitOption(getDataGame());
        }

        if (getDataGame().getEvents().isEmpty() && getDataGame().getYear() == 2) {
            return new Ending(getDataGame());
        }

        return makeEventAction(event, getDataGame().getYear());
    }

    private IStates makeEventAction(EventCard event, int year) {
        if (year == 1) {
            return event.makeEventActionYear1();
        } else {
            return event.makeEventActionYear2();
        }
    }
}
