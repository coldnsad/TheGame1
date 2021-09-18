package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import java.util.ArrayList;

public class MyGdxGame extends ApplicationAdapter {

	SpriteBatch batch;
	Background bg;
	ArrayList<Enemy> enemies;
	ArrayList<Image> towers;
	UIPanel panel;

	Stage stage;
	Skin skin;

	Tower tower;
	ArrayList<Tower> activeTowers;

	//Inner class for listeners
	class TowerListener extends ClickListener {
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			//Create object of tower if key is in down state
			if (tower == null) {
				tower = new Tower(new Vector2(event.getStageX(), event.getStageY()));
			}
			//For debug
			//label.setText(Float.toString(x) + "--" + Float.toString(y));
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			//Create object of tower if key is in up state
			tower = null;
			super.touchUp(event, x, y, pointer, button);
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer) {
			tower.update(new Vector2(event.getStageX(),event.getStageY()));
			//For debug
			//label.setText(Float.toString(x) + "--" + Float.toString(y));
			super.touchDragged(event, x, y, pointer);
		}
	}//END Inner class for listener

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new GrassBackground();
		stage = new Stage(new ScreenViewport());

		//Object that consists styles for actors
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		//For debug (Dialog, label)
		/*final Dialog dialog = new Dialog("", skin);
		final Label label = new Label("Coordinates", skin);
		label.setPosition(200, 200);*/

		Image tower1 = new Image(new Texture(Gdx.files.internal("common_tower.png")));
		Image tower2 = new Image(new Texture(Gdx.files.internal("common_tower.png")));
		tower1.setColor(1,0,0,1f);
		tower2.setColor(1,1,1,1f);

		tower1.addListener(new TowerListener());
		tower2.addListener(new TowerListener());

		towers = new ArrayList<>();
		towers.add(tower1);
		towers.add(tower2);

		panel = new UIPanel(stage, towers);

		enemies = new ArrayList<>();
		enemies.add(new Ghost(bg));
		enemies.add(new Samurai(bg));

		stage.addActor(panel.getInnerTable());
		stage.addActor(panel.getOuterTable());
		//stage.addActor(label);

		Gdx.input.setInputProcessor(stage);
		//stage.setDebugAll(true);
	}

	@Override
	public void render () {
		update();
		batch.begin();
		bg.render(batch);
		for (Enemy enemy: enemies) {
			if(enemy != null) enemy.render(batch);
		}
		if (tower != null) {
			tower.render(batch);
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
