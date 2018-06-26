package com.sniper.expanse;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.sniper.expanse.model.resource.manager.ResourceManager;
import com.sniper.expanse.utils.DebugStatistic;
import com.sniper.expanse.utils.MusicManager;
import com.sniper.expanse.view.screens.LoadingScreen;

import static com.sniper.expanse.utils.Constants.PTM_RATIO;


public class SniperExpanse extends Game {
	@Override
	public void create () {
		batch = new SpriteBatch();

		final float width= Gdx.graphics.getWidth() / PTM_RATIO;
		final float height = Gdx.graphics.getHeight() / PTM_RATIO;
		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false, width, height);

		widgetsHolder = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()), batch);
		debug = new DebugStatistic(true, true);

		Gdx.input.setInputProcessor(new InputMultiplexer(widgetsHolder));
		setScreen(new LoadingScreen());
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor( 0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		super.render();
		batch.end();

		widgetsHolder.act();
		widgetsHolder.draw();

		debug.act();
	}

	@Override
	public void dispose() {
		super.dispose();
		ResourceManager.instance().dispose();
		MusicManager.instance().dispose();
		widgetsHolder.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void setScreen(Screen screen) {
		super.setScreen(screen);
		MusicManager.instance().switchSample(screen.getClass());
	}

	public Batch getMainBatch() {
		return batch;
	}

	public Stage getWidgetsHolder() {
		return widgetsHolder;
	}


	private Batch batch;
	private Stage widgetsHolder;
	private OrthographicCamera camera;
	private DebugStatistic debug;
}
