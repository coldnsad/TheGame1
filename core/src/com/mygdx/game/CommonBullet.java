package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

class CommonBullet extends Bullet {

    CommonBullet(Enemy currentTarget, Vector2 startPosition) {
        sprite = new Sprite(new Texture("./TD/Sprites/Towers/ammo_22.png"));
        target = currentTarget;
        position = startPosition;
        sprite.setPosition(position.x, position.y);
        speed = 100f;
    }
}
