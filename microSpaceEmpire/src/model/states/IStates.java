
package model.states;

import model.data.Cards.SystemCard.SystemCard;

public interface IStates {
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer(SystemCard s, int militaryForce);
    IStates exploreAttack(SystemCard s, int militaryForce);
    IStates change(int o);
    IStates buildMilitary();
    IStates discoverTechnology();
    IStates newTurn();
    IStates gameOver();    
}
