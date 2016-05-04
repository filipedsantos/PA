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

        if (getDataGame().getTechnologyByName("Interstellar Diplomacy").isBought()) {
            conquerForFree(getDataGame().getUnalignedSystems().get(opt));

        } else 
            conquering(opt);
            
        return new Collecting(getDataGame());

    }

    @Override
    public IStates exploreAttack(SystemType s) {
        NearSystem card = null;

        if (!getDataGame().canAttackPlanets(s)) // Verify if game can attack planets of certain type (if array is not empty)
        {
            return this;
        }

        try {
            card = getDataGame().getNearSystems(0);

            if (getDataGame().getTechnologyByName("Interstellar Diplomacy").isBought()) {
                attackForFree(card);
            } else {
                attacking(card);
            }
        } catch (EmptyException e) {
            System.err.println("Near System");
        }

        return new Collecting(getDataGame());
    }

    @Override
    public IStates gameOver() {
        return new Ending(getDataGame());
    }

    private void attackForFree(SystemCard s) {
        getDataGame().addEmpire(s);
        getDataGame().adjustResources(s); // Update metal and wealth production with planet s

        if (s instanceof NearSystem) {
            getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire
        } else {
            getDataGame().getDistantSystems().remove(0);
        }

        //iNFOS to Log
        getDataGame().setLog("\n\nDue to Interstellar Diplomacy technology, you win this fight for free! this planet now belongs to your empire!\n");

    }

    private void conquerForFree(SystemCard s) {
        getDataGame().addEmpire(s);
        getDataGame().adjustResources(s); // Update metal and wealth production with planet s

        getDataGame().getUnalignedSystems().remove(s);

        getDataGame().getTechnologyByName("Interstellar Diplomacy").setBought(false);
    }

    private void attacking(SystemCard s) {
        String log = "\n";

        int militaryForce = getDataGame().getDiceNumber() + getDataGame().getMilitaryStrenght();

        log = "Attacking planet: " + s.getName() + "\nPlanet resistance: " + s.getResistance();
        log += "\nActual Military force: " + militaryForce;

        if (s instanceof NearSystem) {
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and wealth production with planet s
                getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire // ERRO
                getDataGame().setLog(log + "\n\nYou win the fight! this planet belongs to your empire system!\n\n");
            } else {
                getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
                getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                getDataGame().addUnalignedSystems(s);
                getDataGame().setLog(log + "\n\nYou lost the fight! this planet is now unaligned system!\n\n");

            }
        } else if (s instanceof DistantSystem) {
            if (militaryForce >= s.getResistance()) {
                getDataGame().addEmpire(s);
                getDataGame().adjustResources(s); // Update metal and welth production with planet s
                getDataGame().getDistantSystems().remove(0); // Remove DistantSystem from arraylist because was added to empire
                //iNFOS to Log
                getDataGame().setLog(log + "\n\nYou win this fight! this planet now belongs to your empire!\n\n");
            } else {
                getDataGame().addUnalignedSystems(s);
                getDataGame().getDistantSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                getDataGame().addUnalignedSystems(s);
                getDataGame().setLog(log + "\n\nYou lost the fight! this planet is now unaligned system!\n\n");
            }
        } else {
            return; // Never should happens.
        }
    }

    private void conquering(int index) {
        String log = "\n";
        
        System.out.println("this is my index "+ index);
        SystemCard card = getDataGame().getUnalignedSystems().get(index);

        int militaryForce = getDataGame().getDiceNumber() + getDataGame().getMilitaryStrenght();

        log = "Attacking planet: " + card.getName() + "\nPlanet resistance: " + card.getResistance();
        log += "\nActual Military force: " + militaryForce;

        if (militaryForce >= card.getResistance()) {
            getDataGame().addEmpire(card);
            getDataGame().adjustResources(card); // Update metal and wealth production with planet
            getDataGame().getUnalignedSystems().remove(index);
            getDataGame().setLog(log + "\n\nYou win the fight! this planet belongs to your empire system!\n\n");
        } else {
            getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
            getDataGame().setLog(log + "\n\nYou lost the fight! this planet will remain on unaligned system!\n\n");

        }
    }
}
