package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Bullet {

    protected Vector2 position;
    protected Sprite sprite;
    protected Enemy target;
    protected Float speed;
    protected Float distance; // between spawn point of ammo and enemy position

    public void move(){
        float targetPositionX = target.position.x + ((float)target.texture.getWidth() / 2);
        float targetPositionY = target.position.y + ((float)target.texture.getHeight() / 2);
        distance = position.dst(new Vector2(targetPositionX, targetPositionY));

        position.x+=speed * Gdx.graphics.getDeltaTime() * (targetPositionX - position.x) / distance;
        position.y+=speed * Gdx.graphics.getDeltaTime() * (targetPositionY - position.y) / distance;
        sprite.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch){
        //batch.draw(sprite.getTexture(), sprite.getX(), sprite.getY());
        sprite.draw(batch);
    }

    public void update(){

    }
}
