
package model.states;

public interface IStates {
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer();
    IStates exploreAttack();
    IStates change();
    IStates buildMilitary();
    IStates discoverTechnology();
    IStates newTurn();
    IStates gameOver();    
}
