package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

class Samurai extends Enemy{

    Samurai(Background bg){
        position = new Vector2(bg.positionOfEnemies.x, bg.positionOfEnemies.y);
        texture = new Texture("./TD/Sprites/samurai118x100.png");
        //Speed can be only divider of 10 (1,2,5)
        speed = 1.0f;

        this.keyPoints = bg.keyPoints;
    }
}
