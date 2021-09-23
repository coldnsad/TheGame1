package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Tower {

    protected Texture texture;
    protected Vector2 position;
    protected Vector2 cost;

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(Vector2 newPosition){
        position = newPosition;
    }
}
