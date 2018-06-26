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

import static com.sniper.expanse.utils.Constants.PTM_RATIO;


final public class Wall implements Updatable, CollisionListener {
    public Wall(final World world, String textureKey, Vector2 pos, Vector2 size) {
        BodyEditorLoader physicBodyLoader = new BodyEditorLoader("box2d_bodies/wall");

        BodyDef bd = new BodyDef();
        bd.position.set(pos);
        bd.type = BodyDef.BodyType.DynamicBody;

        FixtureDef fd = new FixtureDef();
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.3f;

        wallBody = world.createBody(bd);
        physicBodyLoader.attachFixture(wallBody, textureKey, fd, 70 / PTM_RATIO);

        batch = ((SniperExpanse) Gdx.app.getApplicationListener()).getMainBatch();

        wall = new Box2DSprite((Texture) ResourceManager.instance().get("box2d_bodies/wall.png"));
        wallBody.setUserData(this);
    }

    @Override
    public void update(float delta) {
        wall.draw(batch, wallBody);
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
    final private Body wallBody;
    final private Box2DSprite wall;
}
