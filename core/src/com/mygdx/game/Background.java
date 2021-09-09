package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class Background {

    protected Texture texture;
    protected Vector2 position;
    protected ArrayList<Vector2> keyPoints;

    Background(){
        position = new Vector2(0,0);
    }

    public ArrayList<Vector2> getKeyPoints() {
        return keyPoints;
    }

    public void render(SpriteBatch batch){
        batch.draw(texture, position.x, position.y);
    }

    public void update(){

    }
}
