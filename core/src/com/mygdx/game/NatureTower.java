package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class NatureTower extends Tower{

    NatureTower(Vector2 startPosition) {
        texture = new Texture("./TD/Sprites/Towers/nature_tower.png");
        position = startPosition;
    }

    NatureTower(Tower t) {
        texture = t.texture;
        cost = t.cost;
        position = t.position;
    }
}
