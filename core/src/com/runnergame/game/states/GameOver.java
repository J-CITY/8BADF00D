package com.runnergame.game.states;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.runnergame.game.GameRunner;
import com.runnergame.game.sprites.Button;

public class GameOver extends State {
    private Button playBtn, onSoundBtn, offSoundBtn, exitBtn;
    private String TITLE = "<< GAME OVER >>";
    private final GlyphLayout layout = new GlyphLayout(GameRunner.font, TITLE);

    private float pbtnY0 = 0, pbtnY = 400;
    private float ebtnY0 = 0, ebtnY = -400;
    private float sbtnY0 = -250, sbtnY = -400;

    public GameOver(GameStateManager gameStateMenager) {
        super(gameStateMenager);
        camera.setToOrtho(false, GameRunner.WIDTH, GameRunner.HEIGHT);
        playBtn = new Button("playAgain.png", camera.position.x-200, camera.position.y+pbtnY, 1, 1);
        onSoundBtn = new Button("SoundOn.png", camera.position.x-530, camera.position.y+sbtnY, 1, 1);
        offSoundBtn = new Button("SoundOff.png", camera.position.x-530, camera.position.y+sbtnY, 1, 1);
        exitBtn = new Button("EndGame.png", camera.position.x+200, camera.position.y+ebtnY, 1, 1);
    }

    @Override
    protected void hendleInput() {
        if(Gdx.input.justTouched()) {
            Vector3 vec = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if(playBtn.collide(vec.x, vec.y)) {
                gameStateMenager.set(new PlayState(gameStateMenager));
            } else if(onSoundBtn.collide(vec.x, vec.y)) {
                GameRunner.isPlay = !GameRunner.isPlay;
            } else if(exitBtn.collide(vec.x, vec.y)) {
                //gameStateMenager.set(new MetaGameState(gameStateMenager));
                gameStateMenager.set(new MoonCityState(gameStateMenager));
            }
        }
    }

    @Override
    public void update(float delta) {
        hendleInput();
        if(pbtnY0 < pbtnY) {
            pbtnY -= speed*delta;
        } else {
            pbtnY = pbtnY0;
        }
        if(ebtnY0 > ebtnY) {
            ebtnY += speed*delta;
        } else {
            ebtnY = ebtnY0;
        }
        if(sbtnY0 > sbtnY) {
            sbtnY += speed*delta;
        } else {
            sbtnY = sbtnY0;
        }
        playBtn.setPos(playBtn.getPos().x, camera.position.y + pbtnY);
        exitBtn.setPos(exitBtn.getPos().x, camera.position.y + ebtnY);
        offSoundBtn.setPos(offSoundBtn.getPos().x, camera.position.y + sbtnY);
        onSoundBtn.setPos(onSoundBtn.getPos().x, camera.position.y + sbtnY);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        GameRunner.font.draw(sb, TITLE, (GameRunner.WIDTH - layout.width) / 2, GameRunner.HEIGHT - 100);
        playBtn.getSprite().draw(sb);
        //GameRunner.font.draw(sb, "PLAY AGAIN.", playBtn.getPos().x-30, playBtn.getPos().y + 70);
        exitBtn.getSprite().draw(sb);
        //GameRunner.font.draw(sb, "END GAME.", exitBtn.getPos().x-30, exitBtn.getPos().y + 70);
        if(GameRunner.isPlay) {
            onSoundBtn.getSprite().draw(sb);
        } else {
            offSoundBtn.getSprite().draw(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        playBtn.dispose();
        onSoundBtn.dispose();
        offSoundBtn.dispose();
    }
}