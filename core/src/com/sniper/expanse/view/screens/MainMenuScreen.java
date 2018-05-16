package com.sniper.expanse.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.sniper.expanse.SniperExpanse;
import com.sniper.expanse.utils.MusicManager;


final class MainMenuScreen implements Screen {
    MainMenuScreen() { }

    @Override
    public void show() {
        MusicManager.instance().registerMusic(this.getClass(), MusicManager.MusicTypes.ADDITION_MUSIC);
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.justTouched()) {
            ((SniperExpanse) Gdx.app.getApplicationListener()).setScreen(new GameScreen());
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        MusicManager.instance().pauseMusic();
    }

    @Override
    public void resume() {
        MusicManager.instance().resumeMusic();
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() { }
}