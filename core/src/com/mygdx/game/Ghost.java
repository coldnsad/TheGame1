package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

class Ghost extends Enemy{

    Ghost(Background bg) {
        position = new Vector2(bg.positionOfEnemies.x, bg.positionOfEnemies.y);
        texture = new Texture("./TD/Sprites/Enemies/mob112x100.png");
        hp = 10;
        //Speed can be only divider of 10 (1,2,5,10)
        speed = 0.5f;

        this.keyPoints = bg.keyPoints;
    }
}
