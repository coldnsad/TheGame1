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
import java.util.Iterator;
import java.util.List;

public class MyGdxGame extends ApplicationAdapter {


	SpriteBatch batch;

	//Background properties
	Background bg;

	//Enemy properties
	ArrayList<Enemy> enemies;

	//UI properties
	Stage stage;
	Skin skin;
	Label label;
	UIPanel panel;

	//Tower properties
	Tower currentTower;
	ArrayList<Image> towersOnPanel;
	ArrayList<Tower> activeTowers;
	//The name of file must fit this pattern *_*.* !!!
	String[] pathTowers = new String[] {"./TD/Sprites/Towers/common_tower.png",
										"./TD/Sprites/Towers/nature_tower.png"};

	//Inner class for listeners
	class TowerOnPanelListener extends ClickListener {

		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			//Create object of tower if key is in down state
			if (currentTower == null) {
				switch (event.getTarget().getName()){
					case("common"):
						currentTower = new CommonTower(new Vector2(event.getStageX(), event.getStageY()));
						break;
					case("nature"):
						currentTower = new NatureTower(new Vector2(event.getStageX(), event.getStageY()));
						break;
				}
			}
			return super.touchDown(event, x, y, pointer, button);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			super.touchUp(event, x, y, pointer, button);
			//Create object of tower if key is in up state
			currentTower.setPosition(new Vector2(event.getStageX(), event.getStageY()));
			activeTowers.add(new CommonTower(currentTower));
			currentTower = null;
		}

		@Override
		public void touchDragged(InputEvent event, float x, float y, int pointer) {
			super.touchDragged(event, x, y, pointer);
			currentTower.setPosition(new Vector2(event.getStageX(),event.getStageY()));
			//For debug
			//label.setText(Float.toString(x) + "--" + Float.toString(y));
		}
	}//END Inner class for listener

	@Override
	public void create () {
		batch = new SpriteBatch();
		bg = new GrassBackground();
		stage = new Stage(new ScreenViewport());
		activeTowers = new ArrayList<>();

		//Tower for debugging
		//activeTowers.add(new CommonTower(new Vector2(430, 550)));

		//Object that consists styles for actors
		skin = new Skin(Gdx.files.internal("./Style/uiskin.json"));

		//For debug (Dialog, label)
		//final Dialog dialog = new Dialog("", skin);
		label = new Label("Coordinates", skin);
		label.setPosition(200, 200);

		panel = new UIPanel(stage);
		panel.setTowersOnPanel(pathTowers);
		panel.setListenerForTableOnPanel(new TowerOnPanelListener());

		enemies = new ArrayList<>();
		enemies.add(new Ghost(bg));
		enemies.add(new Samurai(bg));

		stage.addActor(panel.getInnerTable());
		stage.addActor(panel.getOuterTable());
		stage.addActor(label);

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
		if (currentTower != null) {
			currentTower.render(batch);
		}
		for (Tower tower: activeTowers) {
			if(tower != null) tower.render(batch);
		}
		batch.end();
		stage.draw();
	}

	public void update() {
		Iterator<Enemy> it = enemies.iterator();
		while (it.hasNext()) {
			Enemy nextEnemy = it.next();
			if (nextEnemy.position.x > bg.endOfBg + 20) {
				it.remove();
				System.out.println("Enemy is deleted");
			}
			else {
				nextEnemy.update();
				bg.update(nextEnemy);
			}
		}

		//Targeting
		if (activeTowers.size() != 0) {
			for (Tower tower: activeTowers) {
				if(tower.hasTarget) {
					tower.Shoot();
					System.out.println("SHOOT");
				}else{
					tower.searchTarget(enemies);
				}
			}
		}// END Targeting

		if (currentTower != null) currentTower.update();

		//For debug
		if (activeTowers.size() != 0 && enemies.size() != 0) {

			label.setText(Float.toString(activeTowers.get(0).position.dst(enemies.get(0).position)));
		}//END For debug

		stage.act(Gdx.graphics.getDeltaTime());
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
