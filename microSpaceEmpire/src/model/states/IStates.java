package model.states;

import model.data.Cards.SystemCard.SystemType;
import model.data.DataGame;

public interface IStates {
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
    IStates saveGame();
}
