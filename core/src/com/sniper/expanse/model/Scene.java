package com.sniper.expanse.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.sniper.expanse.model.bodies.CollisionListener;
import com.sniper.expanse.model.bodies.Sniper;
import com.sniper.expanse.model.bodies.Updatable;
import com.sniper.expanse.model.bodies.Wall;
import com.sniper.expanse.utils.controller.UController;

import java.util.ArrayList;


final public class Scene {
    public Scene() {
        debugRenderer = new Box2DDebugRenderer();
        sceneBodies = new ArrayList<>();

        world = worldInit();

        sceneBodies.add(new Sniper(world, "sniper", new Vector2(50, 50), new UController()));
//        sceneBodies.add(new Sniper(world, "sniper", new Vector2(), new Vector2(), new AIController()));

        sceneBodies.add(new Wall(world, "wall", new Vector2(0, 0), new Vector2()));
        sceneBodies.add(new Wall(world, "wall", new Vector2(Gdx.graphics.getHeight(), 0), new Vector2()));
        sceneBodies.add(new Wall(world, "wall", new Vector2(0, Gdx.graphics.getWidth()), new Vector2()));
        sceneBodies.add(new Wall(world, "wall", new Vector2(Gdx.graphics.getHeight(), Gdx.graphics.getWidth()), new Vector2()));
    }

    public void update(float delta) {
//        debugRenderer.render(world, ((SniperExpanse) Gdx.app.getApplicationListener()).getWidgetsHolder().getCamera().combined);

        world.step(1/60f, 6, 2);

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

    private World worldInit() {
        final World world = new World(new Vector2(0, 0), false);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                try {
                    ((CollisionListener) contact.getFixtureA().getBody().getUserData())
                            .collisionContact(contact.getFixtureB().getBody());

                    ((CollisionListener) contact.getFixtureB().getBody().getUserData())
                            .collisionContact(contact.getFixtureA().getBody());
                } catch (Exception err) {
                    Gdx.app.log(getClass().getCanonicalName(), "collision contact: " + err.getMessage());
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

        return world;
    }


    final private Box2DDebugRenderer debugRenderer;

    final private World world;
    final private ArrayList<Updatable> sceneBodies;

}
