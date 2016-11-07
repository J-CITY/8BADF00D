package com.runnergame.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by 333da on 07.11.2016.
 */
public class BlockBeam extends Block {
    public BlockBeam(float x, float y, int type) {
        super(x, type);
        TYPE = 6;
        color = 0;
        texOn = new Texture("beamOn.png");
        texOff = new Texture("beamOff.png");
        spriteOn = new Sprite(texOn);
        spriteOff = new Sprite(texOff);
        bounds = new Rectangle(spriteOn.getBoundingRectangle());
        pos = new Vector2(x, y);
        spriteOn.setCenter(pos.x, pos.y);
        spriteOff.setCenter(pos.x, pos.y);

        bounds.setCenter(pos.x, pos.y);
    }

    @Override
    public void reposition(float x, float y) {
        pos.set(x, y);
        spriteOn.setCenter(pos.x, pos.y);
        spriteOff.setCenter(pos.x, pos.y);
        bounds.setCenter(pos.x, pos.y);
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }


    @Override
    public void update(float delta, float _x) {
        pos.add(-650 * delta, 0.0f);
        spriteOn.setCenter(pos.x, pos.y);
        spriteOff.setCenter(pos.x, pos.y);
        bounds.setPosition(pos.x, pos.y);
        bounds.setCenter(pos.x, pos.y);
        if(pos.x < _x - 160) {
            pos.y -= 10;
            spriteOn.setCenter(pos.x, pos.y);
            spriteOff.setCenter(pos.x, pos.y);
            bounds.setCenter(pos.x, pos.y);
        }
    }

    @Override
    public boolean collide(Rectangle player) {
        return bounds.overlaps(player);
    }

    @Override
    public void dispose() {
        texOn.dispose();
        texOff.dispose();
    }
}