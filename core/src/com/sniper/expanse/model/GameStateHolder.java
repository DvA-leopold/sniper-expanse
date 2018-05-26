package com.sniper.expanse.model;

import java.util.Vector;


public class GameStateHolder {
    static {
        observers = new Vector<>();
    }

    public static void dispose() {
        currentState = GameStates.GAME_PREPARING;
        observers.clear();
    }

    public static void changeState(GameStates newState) {
        currentState = newState;
        for (Observer observer: observers) {
            observer.notify(newState);
        }
    }

    public static GameStates getState() {
        return currentState;
    }

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }


    private static GameStates currentState;
    private static Vector<Observer> observers;
}
