package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class Ghost extends Enemy{

    Ghost(ArrayList<Vector2> keyPoints, Background bg) {
        super(bg);
        texture = new Texture("./TD/Sprites/mob112x100.png");
        //Speed can be only divider of 10 (1,2,5,10)
        speed = 2.0f;

        this.keyPoints = keyPoints;
    }
}
