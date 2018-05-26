package com.sniper.expanse.model.bodies;


public interface Updatable {
    void update(float delta);
    void pause();
    void resume();
}
