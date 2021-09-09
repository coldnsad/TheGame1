package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GrassBackground extends Background{

    GrassBackground() {
        super();
        texture = new Texture("./TD/Backgrounds/grassBg.png");
        position = new Vector2(0,0);

        keyPoints = new ArrayList<>();
        keyPoints.add(new Vector2(130, 320));
    }

    public void update(){

    }
}
