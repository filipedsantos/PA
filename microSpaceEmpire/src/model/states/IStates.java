package model.states;

import java.io.Serializable;
import model.data.Cards.SystemCard.SystemType;

public interface IStates extends Serializable{
    static final long serialVersionUID = 1l;
    
    IStates start();
    IStates end();
    IStates pass();
    IStates conquer(int opt);
    IStates exploreAttack(SystemType s);
    IStates change(int o);
    IStates collect();
    IStates buildMilitary();
    IStates discoverTechnology(String TecName);
    IStates newTurn();
    IStates gameOver();
}
