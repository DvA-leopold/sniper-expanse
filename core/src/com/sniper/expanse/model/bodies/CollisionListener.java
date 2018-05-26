package com.sniper.expanse.model.bodies;

import com.badlogic.gdx.physics.box2d.Body;


public interface CollisionListener {
    void collisionContact(Body collidedBody);

}
