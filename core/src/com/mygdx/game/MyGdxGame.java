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
	UIPanel panel;

	Stage stage;
	Skin skin;

	Tower tower;
	ArrayList<Image> towersOnPanel;
	ArrayList<Tower> activeTowers;
	String[] pathTowers;

	//Inner class for listeners
	class TowerOnPanelListener extends ClickListener {

		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			//Create object of tower if key is in down state
			if (tower == null) {
				switch (event.getTarget().getName()){
					case("common"):
						tower = new CommonTower(new Vector2(event.getStageX(), event.getStageY()));
						break;
					case("nature"):
						tower = new NatureTower(new Vector2(event.getStageX(), event.getStageY()));
						break;
				}
			}
			//For debug
			//label.setText(Float.toString(x) + "--" + Float.toString(y));
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			//Create object of tower if key is in up state
			tower.setPosition(new Vector2(event.getStageX(), event.getStageY()));
			activeTowers.add(new CommonTower(tower));
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
		activeTowers = new ArrayList<>();

		//Object that consists styles for actors
		skin = new Skin(Gdx.files.internal("./Style/uiskin.json"));
		//The name of file must fit this pattern *_*.* !!!
		pathTowers = new String[] {"./TD/Sprites/Towers/common_tower.png",
								   "./TD/Sprites/Towers/nature_tower.png"};

		//For debug (Dialog, label)
		/*final Dialog dialog = new Dialog("", skin);
		final Label label = new Label("Coordinates", skin);
		label.setPosition(200, 200);*/

		panel = new UIPanel(stage);
		panel.setTowersOnPanel(pathTowers);
		panel.setListenerForTableOnPanel(new TowerOnPanelListener());

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
		for (Tower tower: activeTowers) {
			if(tower != null) tower.render(batch);
		}
		batch.end();
		stage.draw();
	}

	public void update() {
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i) != null && enemies.get(i).position.x > bg.endOfBg + 20) {
				enemies.set(i, null);
				System.out.println("Enemy is deleted");
			}
			else if (enemies.get(i) != null){
				enemies.get(i).update();
				bg.update(enemies.get(i));
			}
		}
		stage.act(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
