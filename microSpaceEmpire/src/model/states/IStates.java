
package model.states;

import model.data.Cards.SystemCard.SystemCard;

public interface IStates {
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer();
    IStates exploreAttack(SystemCard s);
    IStates change();
    IStates buildMilitary();
    IStates discoverTechnology();
    IStates newTurn();
    IStates gameOver();    
}
