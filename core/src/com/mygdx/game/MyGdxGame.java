package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	Enemy enemy;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new GrassBackground();
		enemy = new Ghost(bg.getKeyPoints());
	}

	@Override
	public void render () {
		update();
		batch.begin();
		bg.render(batch);
		enemy.render(batch);
		batch.end();
	}

	public void update() {
		bg.update();
		enemy.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
