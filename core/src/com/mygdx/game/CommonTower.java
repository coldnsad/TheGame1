package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class CommonTower extends Tower {

    CommonTower(Vector2 startPosition) {
        texture = new Texture("./TD/Sprites/Towers/common_tower.png");
        position = startPosition;
    }

    CommonTower(Tower t) {
        texture = t.texture;
        cost = t.cost;
        position = t.position;
    }
}
