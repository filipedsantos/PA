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

        if (opt == 0) {
            return new Collecting(getDataGame());
        }

        attacking(getDataGame().getUnalignedSystemsCard(opt), true);
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
        String log = "";

        int militaryForce = getDataGame().getDiceNumber() + getDataGame().getMilitaryStrenght();

        log = "Attacking planet: " + s.getName() + "\nPlanet resistance: " + s.getResistance();
        log += "\nActual Military force: " + militaryForce;
        
        
        if (getDataGame().getTechnologyByName("Interstellar Diplomacy").isBought()) {
            
                    getDataGame().addEmpire(s);
                    getDataGame().adjustResources(s); // Update metal and wealth production with planet s
                    
                    if(s instanceof NearSystem)
                        getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire
                    else
                        getDataGame().getDistantSystems().remove(0); 
                    
                    getDataGame().setLog("");
                    if (conquer) {
                        getDataGame().getUnalignedSystems().remove(s);
                    }
                    //iNFOS to Log
                    getDataGame().setLog(log + "\n\nDue to Interstellar Diplomacy technology, you win this fight for free! this planet now belongs to your empire!\n");
            
                    
                    getDataGame().getTechnologyByName("Interstellar Diplomacy").setBought(false);
        }else{
            if (s instanceof NearSystem) {
                if (militaryForce >= s.getResistance()) {
                    getDataGame().addEmpire(s);
                    getDataGame().adjustResources(s); // Update metal and wealth production with planet s
                    getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to empire
                    getDataGame().setLog("");
                    if (conquer) {
                        getDataGame().getUnalignedSystems().remove(s);
                    }
                    //iNFOS to Log
                    getDataGame().setLog(log + "\n\nYou win this fight! this planet now belongs to your empire!\n");
                } else {
                    getDataGame().reduceMilitaryForceOneunit(); // Reduce MilitaryForce 1 unit because achievement attempt failed
                    if (!conquer) {
                        getDataGame().getNearSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                        getDataGame().addUnalignedSystems(s);
                        //iNFOS to Log
                        getDataGame().setLog(log + "\n\nYou lost the fight! this planet is now unaligned system!\n");
                    } else {
                        getDataGame().setLog(log + "\n\nYou lost the fight! this planet will remain on unaligned system!\n");
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
                    //iNFOS to Log
                    getDataGame().setLog(log + "\n\nYou win this fight! this planet now belongs to your empire!\n");
                } else {
                    getDataGame().addUnalignedSystems(s);
                    if (!conquer) {
                        getDataGame().getDistantSystems().remove(0); // Remove NearSystem from arraylist because was added to unalignedSystems
                        getDataGame().addUnalignedSystems(s);
                        getDataGame().setLog(log + "\n\nYou lost the fight! this planet is now unaligned system!\n");
                    } else {
                        getDataGame().setLog(log + "\n\nYou lost the fight! this planet will remain on unaligned system!\n");
                    }
                }
            } else {
                return; // Never should happens.
            }
        }
    }
}
