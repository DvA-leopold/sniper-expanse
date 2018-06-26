package com.sniper.expanse.model.bodies;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.sniper.expanse.SniperExpanse;
import com.sniper.expanse.model.resource.manager.ResourceManager;
import com.sniper.expanse.model.resource.manager.loaders.BodyEditorLoader;
import com.sniper.expanse.utils.controller.Controller;
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;

import static com.sniper.expanse.utils.Constants.IMG_SCALE;
import static com.sniper.expanse.utils.Constants.PIC_MIME_TYPE;
import static com.sniper.expanse.utils.Constants.PTM_RATIO;


final public class Sniper implements Updatable, CollisionListener {
    public Sniper(World world, String textureKey, Vector2 pos, Controller controller) {
        body = initBody(textureKey, world, pos);
        gun = new Gun(world, "gun", pos);
        batch = ((SniperExpanse) Gdx.app.getApplicationListener()).getMainBatch();

        sprite = new Box2DSprite((Texture) ResourceManager.instance().get("box2d_bodies/" + textureKey + PIC_MIME_TYPE));

        controller.moveAction((float x, float y) -> body.applyForceToCenter(x, y, true));
        controller.fireAction(this::fire);
    }

    @Override
    public void update(float delta) {
        sprite.setPosition(body.getPosition().x, body.getPosition().y);
        sprite.draw(batch, body);
        gun.update(delta);
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

    private Body initBody(String textureKey, World world, Vector2 pos) {
        BodyDef bd = new BodyDef();
        bd.position.set(pos);
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        final Body sniperPhysicBody = world.createBody(bd);
        BodyEditorLoader physicBodyLoader = new BodyEditorLoader("box2d_bodies/" + textureKey);
        physicBodyLoader.attachFixture(sniperPhysicBody, textureKey, fd, IMG_SCALE / PTM_RATIO);
        sniperPhysicBody.setUserData(this);
        return sniperPhysicBody;
    }

    private void fire() {
        gun.fire();
    }


    final private Gun gun;

    final private Batch batch;
    final private Box2DSprite sprite;
    final private Body body;
}
