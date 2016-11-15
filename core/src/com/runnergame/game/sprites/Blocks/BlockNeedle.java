package com.runnergame.game.sprites.Blocks;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.runnergame.game.Colors;
import com.runnergame.game.sprites.*;

public class BlockNeedle extends com.runnergame.game.sprites.Blocks.Block {
    public BlockNeedle(float x, float y, int type) {
        super(x, type);
        TYPE = 1;
        color = 0;
        animOn = new Animation(new TextureRegion(new Texture("blocks/bOn.png")), 1, 1);
        animOff = new Animation(new TextureRegion(new Texture("blocks/bOff.png")), 1, 1);
        sprite = animOn.getSprite();
        sprite.setCenter(x, y);
        bounds = new Rectangle(sprite.getBoundingRectangle());
        pos = new Vector2(x, y);
    }

    @Override
    public Vector2 getPos() {
        return pos;
    }


    @Override
    public void update(float delta, float _x) {
        animOn.update(delta);
        animOff.update(delta);
        pos.add(-speed * delta, 0.0f);
        bounds.setPosition(pos.x, pos.y);
        bounds.setCenter(pos.x, pos.y);
        if(pos.x < _x - 160) {
            pos.y -= 10;
            bounds.setCenter(pos.x, pos.y);
        }
    }

    @Override
    public boolean collide(Player player) {
        if(bounds.overlaps(player.getBounds()) && (color == Colors.GRAY || color == player.color)) {
            player.setLife(false);
            player.jump(300);
        }
        return false;
    }

    @Override
    public void dispose() {
    }
}
