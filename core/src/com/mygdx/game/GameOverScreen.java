package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreen implements Screen {

    private final Game game;
    SpriteBatch batch;
    Background bg;

    GameOverScreen(Game game){
       this.game = game;
   }
    @Override
    public void show() {
        batch = new SpriteBatch();
        bg = new GameOverBackground();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor( 1,1,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new MainScreen(this.game));
        }

        batch.begin();
        bg.render(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
