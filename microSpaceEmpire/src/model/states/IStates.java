
package model.states;

import model.data.Cards.SystemCard.SystemType;

public interface IStates {
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer();
    IStates exploreAttack(SystemType s);
    IStates change();
    IStates buildMilitary();
    IStates discoverTechnology();
    IStates newTurn();
    IStates gameOver();    
}
