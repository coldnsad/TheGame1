package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower {

    protected Vector2 position;
    protected Sprite sprite;
    protected float cost;
    protected float range;
    protected Enemy currentTarget;
    protected Bullet activeBullet;

    public void setPosition(Vector2 newPosition) {
        position = newPosition;
    }

    public boolean isEnemyNear(Enemy enemy) {
        if (enemy.position.dst(position) < range) {
            currentTarget = enemy;
            return true;
        }else if (enemy.position.dst(position) > range && activeBullet != null) {
            activeBullet = null;
        }
        return false;
    }

    public void Shoot(){
        if(activeBullet == null) {
            activeBullet = new CommonBullet(currentTarget, new Vector2(position.x, position.y));
            activeBullet.move();
        }else{
            activeBullet.move();
            if (activeBullet.distance < 10) {
                activeBullet = null;
            }
        }
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
