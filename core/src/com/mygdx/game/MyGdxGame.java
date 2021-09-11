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
		enemy = new Ghost(bg.getKeyPoints(), bg);
	}

	@Override
	public void render () {
		update();
		batch.begin();
		bg.render(batch);
		if(enemy != null) enemy.render(batch);
		batch.end();
	}

	public void update() {
		if (enemy != null && enemy.position.x > bg.endOfBg + 20) {
			enemy = null;
		}
		else if (enemy != null){
			enemy.update();
		}
		bg.update(enemy);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
