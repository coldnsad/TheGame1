package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameOverBackground extends Background{

    public GameOverBackground() {
        super();
        texture = new Texture("./TD/Backgrounds/game_over_bg.jpg");
        position = new Vector2(0,0);
    }
}
