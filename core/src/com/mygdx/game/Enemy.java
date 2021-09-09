package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

abstract public class Enemy {

    protected Texture texture;
    protected Vector2 position;
    protected float speed;
    protected ArrayList<Vector2> keyPoints;

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(){
        if (position.x != keyPoints.get(0).x) {
            position.x+=speed;
        }
        else
        {
            position.y+=speed;
        }
    }
}
