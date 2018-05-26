package com.sniper.expanse.model.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sniper.expanse.SniperExpanse;
import com.sniper.expanse.utils.GestureController;
import com.sniper.expanse.model.resource.manager.ResourceManager;
import com.sniper.expanse.model.resource.manager.loaders.BodyEditorLoader;
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import static com.sniper.expanse.utils.Constants.PTM_RATIO;


final public class Sniper implements Updatable, CollisionListener {
    public Sniper(final World world, final String textureKey) {
        BodyEditorLoader physicBodyLoader = new BodyEditorLoader("box2d_bodies/sniper");

        BodyDef bd = new BodyDef();
        bd.position.set(0, 0);
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        sniperPhysicBody = world.createBody(bd);
        physicBodyLoader.attachFixture(sniperPhysicBody, textureKey, fd, 70 / PTM_RATIO);

        batch = ((SniperExpanse) Gdx.app.getApplicationListener()).getMainBatch();

        box2DSprite = new Box2DSprite((Texture) ResourceManager.instance().get("box2d_bodies/sniper.png"));
        sniperPhysicBody.setUserData(this);

        new GestureController(this);
    }

    @Override
    public void update(float delta) {
        box2DSprite.setPosition(sniperPhysicBody.getPosition().x, sniperPhysicBody.getPosition().y);
//        box2DSprite.setRotation(sniperPhysicBody.getAngle() * MathUtils.radiansToDegrees);

        box2DSprite.draw(batch, sniperPhysicBody);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void collisionContact(Body collidedBody) {

    }

    public void applyForce(float forceX, float forceY) {
        sniperPhysicBody.applyForceToCenter(forceX, -forceY, true);
//        sniperPhysicBody.applyLinearImpulse(forceX, forceY, sniperPhysicBody.getPosition().x, sniperPhysicBody.getPosition().y, true);
    }



    final private Batch batch;
    final private Box2DSprite box2DSprite;
    final private Body sniperPhysicBody;
}
