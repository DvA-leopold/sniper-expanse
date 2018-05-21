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
import net.dermetfan.gdx.graphics.g2d.Box2DSprite;


final public class Sniper implements Updatable {
    public Sniper(final World world, final String textureKey) {
        BodyEditorLoader physicBodyLoader = new BodyEditorLoader("box2d_bodies/sniper");

        BodyDef bd = new BodyDef();
        bd.position.set(100, 100);
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        physicsBody = world.createBody(bd);
        physicBodyLoader.attachFixture(physicsBody, textureKey, fd, 60);
        originBodyPos = physicBodyLoader.getOrigin(textureKey, 60).cpy();

        batch = ((SniperExpanse) Gdx.app.getApplicationListener()).getMainBatch();

        box2DSprite = new Box2DSprite((Texture) ResourceManager.instance().get("box2d_bodies/sniper.png"));
        System.out.println(box2DSprite.getX() + " " + box2DSprite.getY() + " " + box2DSprite.getWidth() + " " + box2DSprite.getHeight());
        physicsBody.setUserData(this);
    }

    @Override
    public void update(float delta) {
        Vector2 bodyPos = physicsBody.getPosition().sub(originBodyPos);
        box2DSprite.setPosition(bodyPos.x, bodyPos.y);
//        box2DSprite.setOrigin(originBodyPos.x, originBodyPos.y);
//        box2DSprite.setRotation(physicsBody.getAngle() * MathUtils.radiansToDegrees);

        box2DSprite.draw(batch, physicsBody);
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


    final private Batch batch;
    final private Box2DSprite box2DSprite;

    final private Body physicsBody;
    final private Vector2 originBodyPos;
}
