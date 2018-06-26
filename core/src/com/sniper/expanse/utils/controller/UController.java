package com.sniper.expanse.utils.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.sniper.expanse.SniperExpanse;
import com.sniper.expanse.model.resource.manager.ResourceManager;


final public class UController implements Controller {
    public UController() {
        final Stage widgetHolder = ((SniperExpanse) Gdx.app.getApplicationListener()).getWidgetsHolder();
        final Skin skin = (Skin) ResourceManager.instance().get("main_skin");
        touchpad = new Touchpad(1, skin, "touchpad");
        widgetHolder.addActor(touchpad);
        fireButton = new Button(skin, "fire_button");
        widgetHolder.addActor(fireButton);
    }

    @Override
    public void moveAction(MoveAction action) {
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                float posX = ((Touchpad) actor).getKnobPercentX();
                float posY = ((Touchpad) actor).getKnobPercentY();
                action.run(posX, posY);
            }
        });
    }

    @Override
    public void fireAction(FireAction action) {
        fireButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                action.run();
            }
        });
    }


    final private Touchpad touchpad;
    final private Button fireButton;
}
