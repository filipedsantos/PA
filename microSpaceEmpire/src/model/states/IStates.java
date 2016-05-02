
package model.states;

import model.data.Cards.SystemCard.SystemCard;
import model.data.Cards.SystemCard.SystemType;

public interface IStates {
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer(int opt);
    IStates exploreAttack(SystemType s);
    IStates change(int o);
    IStates buildMilitary();
    IStates discoverTechnology();
    IStates newTurn();
    IStates gameOver();    
}
