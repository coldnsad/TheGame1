package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Background bg;
	ArrayList<Enemy> enemies;
	Stage stage;

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new GrassBackground();

		stage = new Stage(new ScreenViewport());

		Image tower1 = new Image(new Texture(Gdx.files.internal("common_tower.png")));
		Image tower2 = new Image(new Texture(Gdx.files.internal("common_tower.png")));
		Image panelTexture = new Image(new Texture(Gdx.files.internal("bottom_panel.jpg")));

		tower1.setColor(1,0,0,1f);
		tower2.setColor(1,1,1,1f);

		Table innerTable = new Table();
		innerTable.setWidth(stage.getWidth());
		innerTable.setHeight(150);
		innerTable.align(Align.center|Align.bottom);
		innerTable.setPosition(0,0);
		innerTable.setColor(1,1,1,0.8f);

		Table outerTable = new Table();
		outerTable.setWidth(stage.getWidth());
		outerTable.setHeight(250);
		outerTable.align(Align.center|Align.bottom);
		outerTable.setPosition(0,0);
		outerTable.setColor(1,1,1,1f);


		innerTable.add(panelTexture);
		outerTable.add(tower1);
		outerTable.add(tower2);
		//table.add(btn2);

		stage.addActor(innerTable);
		stage.addActor(outerTable);
		//stage.setDebugAll(true);

		Gdx.input.setInputProcessor(stage);

		enemies = new ArrayList<>();
		enemies.add(new Ghost(bg));
		enemies.add(new Samurai(bg));
	}

	@Override
	public void render () {
		update();
		batch.begin();
		bg.render(batch);
		for (Enemy enemy: enemies) {
			if(enemy != null) enemy.render(batch);
		}
		batch.end();

		stage.draw();
	}

	public void update() {
		for (Enemy enemy: enemies) {
			if (enemy != null && enemy.position.x > bg.endOfBg + 20) {
				enemy = null;
			}
			else if (enemy != null){
				enemy.update();
				bg.update(enemy);
			}
		}
		stage.act(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
