package model.states;

import model.data.Cards.SystemCard.DistantSystem;
import model.data.Cards.SystemCard.NearSystem;
import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;
import model.data.DataGame;
import model.data.EmptyException;

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
    public IStates conquer(int opt) {

        attacking(getDataGame().getUnalignedSystemsCard(opt), true);
        return new Collecting(getDataGame());
    }

    @Override
    public IStates exploreAttack(SystemType s) {

        try {
            NearSystem card = getDataGame().getNearSystems(0);
            attacking(card, false);
        } catch (EmptyException e) {
            System.err.println("Near System");
        }

        return new Collecting(getDataGame());
    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

    private void attacking(SystemCard s, boolean conquer) {

        int militaryForce = getDataGame().getDiceNumber() + getDataGame().getMilitaryStrenght();

        if (s instanceof NearSystem) {
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and wealth production with planet s
                getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire
                getDataGame().setLog("");
                if (conquer) {
                    getDataGame().getUnalignedSystems().remove(s);
                }
            } else {
                getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
                if (!conquer) {
                    getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                    getDataGame().addUnalignedSystems(s);
                }
            }
        } else if (s instanceof DistantSystem) {
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and welth production with planet s
                getDataGame().getDistantSystems().remove(0); // Remove DistantSystem from arraylist because was added to empire
                if (conquer) {
                    getDataGame().getUnalignedSystems().remove(s);
                }
            } else {
                getDataGame().addUnalignedSystems(s);
                if (!conquer) {
                    getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                    getDataGame().addUnalignedSystems(s);
                }
            }
        } else {
            return; // Never should happens.
        }
    }
}
