package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Ghost extends Enemy{

    Ghost(ArrayList<Vector2> keyPoints) {
        texture = new Texture("./TD/Sprites/mob112x100.png");
        position = new Vector2(-20,320);
        speed = 0.5f;

        this.keyPoints = keyPoints;
    }
}
