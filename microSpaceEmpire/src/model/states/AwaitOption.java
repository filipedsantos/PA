package model.states;

import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.DataGame;

public class AwaitOption extends StateAdapter {

    public AwaitOption(DataGame dataGame) {
        super(dataGame);
    }

    //Estados Seguintes
    @Override
    public IStates pass() {
        return new Collecting(getDataGame());
    }

    @Override
    public IStates conquer() {
        return new Collecting(getDataGame());
    }

    @Override
    public IStates exploreAttack(SystemCard s, int militaryForce) {

        if (s instanceof NearSystem) {
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and welth production with planet s
                getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire
            } else {
                getDataGame().addUnalignedSystems(s);
                getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
                getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
            }
        } else if(s instanceof DistantSystem){
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and welth production with planet s
                getDataGame().getDistantSystems().remove(0); // Remove DistantSystem from arraylist because was added to empire
            } else {
                getDataGame().addUnalignedSystems(s);
                getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
                getDataGame().getDistantSystems().remove(0); // Remove DistantSystem from arraylist because was added to unalignedSystems
            }
        }else{
            return this; // Never should happens.
        }

        return new Collecting(getDataGame());
    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

}
