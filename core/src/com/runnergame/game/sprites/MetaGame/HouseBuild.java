package com.runnergame.game.sprites.MetaGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.runnergame.game.GameRunner;

/**
 * Created by 333da on 11.11.2016.
 */
public class HouseBuild extends Building {

    public HouseBuild(int _t) {
        super(_t);
        name = "House";
        level = 10;
        max_level = 3;
        //GameRunner.dm.addData2("House_lvl", 0);
        level_now = GameRunner.dm.load2("House_lvl");
        price = 100 * level_now + 100;
        if(level_now == 0) {
            sprite = new Sprite(new Texture(Gdx.files.internal("meta/house_ic.png")));
        } else if(level_now == 1) {
            sprite = new Sprite(new Texture(Gdx.files.internal("meta/house1.png")));
        } else if(level_now == 2) {
            sprite = new Sprite(new Texture(Gdx.files.internal("meta/house2.png")));
        } else if(level_now == 3) {
            sprite = new Sprite(new Texture(Gdx.files.internal("meta/house3.png")));
        }

        sprite.setPosition(-80, -50);
        sprite.setCenter(-80, -50);
        bounds = new Rectangle(sprite.getBoundingRectangle());
    }

    @Override
    public void update(float delta, float _x) {

    }
}