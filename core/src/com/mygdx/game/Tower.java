package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Tower {

    protected Vector2 position;
    protected Sprite sprite;
    protected Bullet activeBullet;
    protected Enemy currentTarget;

    protected float cost;
    protected float range;
    protected boolean hasTarget;

    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }

    //Old method / bad method
    /*public boolean isEnemyNear(Enemy enemy) {
        if (enemy.position.dst(position) < range) {
            currentTarget = enemy;
            return true;
        }else if (enemy.position.dst(position) > range && activeBullet != null) {
            activeBullet = null;
        }
        return false;
    }*/

    public void searchTarget(ArrayList<Enemy> enemies) {

        /*if (currentTarget != null) {
            //Target has gone out of tower range
            if (currentTarget.position.dst(position) > range){
                currentTarget = null;
                hasTarget = false;
                activeBullet = null;
                return;
            }
            //Target is on tower range
            return;
        }*/

        for (Enemy enemy : enemies) {
            if (enemy.position.dst(position) < range) {
                currentTarget = enemy;
                hasTarget = true;
                return;
            }
        }
    }

    public void Shoot() {
        if(activeBullet == null) {
            activeBullet = new CommonBullet(currentTarget, new Vector2(position.x + ((float) sprite.getTexture().getWidth() / 2),
                                                                       position.y + ((float) sprite.getTexture().getHeight() / 2)));
            activeBullet.move();
        }else{
            activeBullet.move();
            if (activeBullet.distance < 10) {
                activeBullet = null;
            }
        }
        if (currentTarget.position.dst(position) > range) {
            /*currentTarget = null;
            hasTarget = false;
            activeBullet = null;*/
            clear();
        }
    }

    public void clear(){
        currentTarget = null;
        hasTarget = false;
        activeBullet = null;
    }

    public void render(SpriteBatch batch){
        if (activeBullet != null) {
            activeBullet.render(batch);
        }
        sprite.setPosition(position.x, position.y);
        sprite.draw(batch);
    }

    public void update(){

    }
}
