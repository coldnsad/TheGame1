package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class CommonTower extends Tower {

    CommonTower(Vector2 startPosition) {
        sprite = new Sprite(new Texture("./TD/Sprites/Towers/common_tower.png"));
        position = startPosition;
        range = 200;
        hasTarget = false;
    }

    CommonTower(Tower t) {
        sprite = new Sprite(t.sprite.getTexture());
        cost = t.cost;
        position = t.position;
        range = 200;
        hasTarget = false;
    }
}
