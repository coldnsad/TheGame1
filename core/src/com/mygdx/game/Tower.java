package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tower {

    protected Texture texture;
    protected Vector2 position;

    Tower(Vector2 startPosition) {
        texture = new Texture("common_tower.png");
        position = startPosition;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(Vector2 newPosition){
        position = newPosition;
    }
}
