package com.sniper.expanse.model.bodies;

import com.badlogic.gdx.physics.box2d.Body;


public interface Updatable {
    void update(float delta);
    void pause();
    void resume();

    void collisionContact(Body collidedBody);
}
