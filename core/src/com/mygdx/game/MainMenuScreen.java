package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MainMenuScreen implements Screen {

    private final Game game;
    SpriteBatch batch;
    Background bg;
    ShapeRenderer shapeRenderer;

    Texture welcomeSign;
    Texture startSign;

    public MainMenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        bg = new GrassBackground();
        shapeRenderer = new ShapeRenderer();

        welcomeSign = new Texture("./TD/Sprites/Sign/image.png");
        startSign = new Texture("./TD/Sprites/Sign/start.png");
    }

    @Override
    public void render(float v) {
        batch.begin();
        bg.render(batch);
        batch.end();
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(new Color(0, 0, 0, 0.5f));
        shapeRenderer.rect(0, 0, 1200, 800);
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);

        batch.begin();
        batch.draw(welcomeSign, 250, 400, 750,250);
        batch.draw(startSign, 300, 10, 600,200);
        batch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            game.setScreen(new MainScreen(this.game));
        }
    }

    @Override
    public void resize(int i, int i1) {

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
