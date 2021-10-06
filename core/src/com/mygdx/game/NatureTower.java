package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class NatureTower extends Tower{

    NatureTower(Vector2 startPosition) {
        sprite = new Sprite(new Texture("./TD/Sprites/Towers/nature_tower.png"));
        position = startPosition;
        range = 100;
    }

    NatureTower(Tower t) {
        sprite = new Sprite(t.sprite.getTexture());
        cost = t.cost;
        position = t.position;
        range = 200;
    }
}
