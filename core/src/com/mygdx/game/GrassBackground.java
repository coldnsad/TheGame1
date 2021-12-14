package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GrassBackground extends Background{

    public GrassBackground() {
        super();
        texture = new Texture("./TD/Backgrounds/grassBg.png");
        position = new Vector2(0,0);
        positionOfEnemies = new Vector2(-20,320);
        endOfBg = texture.getWidth();
        amountOFEnemiesForGameOver = 2;

        keyPoints = new ArrayList<>();
        keyPoints.add(new Vector2(130, 320));  //1
        keyPoints.add(new Vector2(130, 550));  //2
        keyPoints.add(new Vector2(380, 550));  //3
        keyPoints.add(new Vector2(380, 230));  //4
        keyPoints.add(new Vector2(700, 230));  //5
        keyPoints.add(new Vector2(700, 390));  //6
    }

    public void update(Enemy enemy){

        if (enemy != null) {

            if (enemy.countOfPassedPoints != 7) {
                for (Vector2 vector:keyPoints) {
                    if (enemy.position.x == vector.x && enemy.position.y == vector.y){
                        enemy.countOfPassedPoints++;
                    }
                }
            }

            switch (enemy.countOfPassedPoints){
                case (0):
                    if (enemy.position.x != keyPoints.get(0).x) {
                        enemy.position.x+=enemy.speed;
                    }
                    break;
                case(1):
                    if (enemy.position.y != keyPoints.get(1).y) {
                        enemy.position.y+=enemy.speed;
                    }
                    break;
                case(2):
                    if (enemy.position.x != keyPoints.get(2).x) {
                        enemy.position.x+=enemy.speed;
                    }
                    break;
                case(3):
                    if (enemy.position.y != keyPoints.get(3).y) {
                        enemy.position.y-=enemy.speed;
                    }
                    break;
                case(4):
                    if (enemy.position.x != keyPoints.get(4).x) {
                        enemy.position.x+=enemy.speed;
                    }
                    break;
                case(5):
                    if (enemy.position.y != keyPoints.get(5).y) {
                        enemy.position.y+=enemy.speed;
                    }
                    break;
                case(6):
                    enemy.position.x+=enemy.speed;
                    break;
            }
        }
    }
}
