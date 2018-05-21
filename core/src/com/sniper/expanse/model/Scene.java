package com.sniper.expanse.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sniper.expanse.model.bodies.Sniper;
import com.sniper.expanse.model.bodies.Updatable;

import java.util.ArrayList;


final public class Scene {
//    static {
//        Box2DSprite.setUserDataAccessor(arg -> ((Updatable) arg).getSprite());
//    }

    public Scene() {
        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(0, 9.8f), false);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                try {
                    ((Updatable) contact.getFixtureA().getBody().getUserData())
                            .collisionContact(contact.getFixtureB().getBody());

                    ((Updatable) contact.getFixtureB().getBody().getUserData())
                            .collisionContact(contact.getFixtureA().getBody());
                } catch (Exception err) {
                    System.err.println("collision contact: " + err.getMessage());
                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        sceneBodies = new ArrayList<>();

        sceneBodies.add(new Sniper(world, "sniper"));
    }

    public void update(float delta) {
//        debugRenderer.render(world, ((SniperExpanse) Gdx.app.getApplicationListener()).getStage().getCamera().combined);

        for (Updatable body: sceneBodies) {
            body.update(delta);
        }
    }

    public void pause() {
        for (Updatable body: sceneBodies) {
            body.pause();
        }
    }

    public void resume() {
        for (Updatable body: sceneBodies) {
            body.resume();
        }
    }

    public void dispose() {
        debugRenderer.dispose();
    }


    final private Box2DDebugRenderer debugRenderer;

    final private World world;
    final private ArrayList<Updatable> sceneBodies;
}
