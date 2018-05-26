package com.sniper.expanse.view.screens;

import com.badlogic.gdx.Screen;
import com.sniper.expanse.model.GameStateHolder;
import com.sniper.expanse.model.Scene;


final class GameScreen implements Screen {
    @Override
    public void show() {
        scene = new Scene();


    }

    @Override
    public void render(float delta) {
        scene.update(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        scene.pause();
    }

    @Override
    public void resume() {
        scene.resume();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        scene.dispose();
        GameStateHolder.dispose();
    }


    private Scene scene;
}
