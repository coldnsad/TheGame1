package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Iterator;

public class MainScreen implements Screen {

    final Game game;
    SpriteBatch batch;

    //Background properties
    Background bg;

    //Enemy properties
    ArrayList<Enemy> enemies;

    //UI properties
    Stage stage;
    Skin skin;
    UIPanel panel;

    Label nameForPoints;
    Label points;
    Label nameForEnemies;
    Label amountOfEnemies;

    //Tower properties
    Tower currentTower;
    ArrayList<Image> towersOnPanel;
    ArrayList<Tower> activeTowers;
    //The name of file must fit this pattern *_*.* !!!
    String[] pathTowers = new String[] {"./TD/Sprites/Towers/common_tower.png", "./TD/Sprites/Towers/nature_tower.png"};

    int enemiesLeft;
    int accumulatedPoints;

    public MainScreen(Game myGdxGame) {
        this.game = myGdxGame;
    }

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
    public void show() {
        batch = new SpriteBatch();
        bg = new GrassBackground();
        stage = new Stage(new ScreenViewport());
        activeTowers = new ArrayList<>();
        enemiesLeft = bg.amountOFEnemiesForGameOver;
        accumulatedPoints = 0;

        //Tower for debugging
        //activeTowers.add(new CommonTower(new Vector2(430, 550)));

        //Object that consists styles for actors
        skin = new Skin(Gdx.files.internal("./Style/uiskin.json"));

        //For debug (Dialog, label)
        //final Dialog dialog = new Dialog("", skin);
        nameForEnemies = new Label("Enemies left: ", skin);
        nameForEnemies.setPosition(1000, 650);

        amountOfEnemies = new Label(Integer.toString(enemiesLeft), skin);
        amountOfEnemies.setPosition(nameForEnemies.getX() + 100, nameForEnemies.getY());

        nameForPoints = new Label("Points: ", skin);
        nameForPoints.setPosition(nameForEnemies.getX(), nameForEnemies.getY() - 40);

        points = new Label(Integer.toString(accumulatedPoints), skin);
        points.setPosition(nameForPoints.getX() + 70, nameForPoints.getY());

        panel = new UIPanel(stage);
        panel.setTowersOnPanel(pathTowers);
        panel.setListenerForTableOnPanel(new TowerOnPanelListener());

        enemies = new ArrayList<>();
        enemies.add(new Ghost(bg));
        enemies.add(new Samurai(bg));

        stage.addActor(panel.getInnerTable());
        stage.addActor(panel.getOuterTable());

        stage.addActor(nameForEnemies);
        stage.addActor(amountOfEnemies);
        stage.addActor(nameForPoints);
        stage.addActor(points);

        Gdx.input.setInputProcessor(stage);
        //stage.setDebugAll(true);
    }

    @Override
    public void render(float delta) {
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

    public void update(){
        Iterator<Enemy> it = enemies.iterator();
        while (it.hasNext()) {
            Enemy nextEnemy = it.next();
            if (nextEnemy.position.x > bg.endOfBg + 20) {
                it.remove();
                enemiesLeft-=1;
                amountOfEnemies.setText(Integer.toString(enemiesLeft));
                if (enemiesLeft <=0){
                    game.setScreen(new GameOverScreen(this.game));
                }

                System.out.println("Enemy is deleted");
            }
            else {
                nextEnemy.update();
                bg.update(nextEnemy);
            }
        }

       /* if (enemies.size() == 0){
            game.setScreen(new GameOverScreen(game));
        }*/

        //Targeting
        if (activeTowers.size() != 0) {
            for (Tower tower: activeTowers) {
                if(tower.hasTarget) {
                    tower.Shoot();
                    if(tower.activeBullet != null && tower.activeBullet.position.dst(tower.currentTarget.position) < 50){
                        tower.currentTarget.hp-=1;
                    }

                    it = enemies.iterator();
                    while (it.hasNext()) {
                        Enemy nextEnemy = it.next();
                        if (nextEnemy.hp <= 0) {
                            it.remove();
                            accumulatedPoints+=nextEnemy.cost;
                            points.setText(Integer.toString(accumulatedPoints));
                            for (Tower tower2: activeTowers) {
                                if (tower2.hasTarget && tower2.currentTarget == tower.currentTarget){
                                    tower2.clear();
                                }
                            }
                            System.out.println("Enemy is destroyed");
                        }
                    }

                    System.out.println("SHOOT");
                }else{
                    tower.searchTarget(enemies);
                }
            }
        }// END Targeting

        if (currentTower != null) currentTower.update();

        //For debug
        /*if (activeTowers.size() != 0 && enemies.size() != 0) {
            label.setText(Float.toString(activeTowers.get(0).position.dst(enemies.get(0).position)));
        }*///END For debug

        stage.act(Gdx.graphics.getDeltaTime());
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
        batch.dispose();
    }
}
