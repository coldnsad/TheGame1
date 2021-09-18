package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;

public class UIPanel {

    private Table innerTable;
    private Table outerTable;
    private Image panelTexture;

    UIPanel(Stage stage, ArrayList<Image> towers) {
        panelTexture = new Image(new Texture(Gdx.files.internal("bottom_panel.jpg")));

        innerTable = new Table();
        innerTable.setWidth(stage.getWidth());
        innerTable.setHeight(150);
        innerTable.align(Align.center|Align.bottom);
        innerTable.setPosition(0,0);
        innerTable.setColor(1,1,1,0.3f);

        outerTable = new Table();
        outerTable.setWidth(stage.getWidth());
        outerTable.setHeight(250);
        outerTable.align(Align.center|Align.bottom);
        outerTable.setPosition(0,0);
        outerTable.setColor(1,1,1,1f);

        innerTable.add(panelTexture);

        for (Image tower: towers) {
            outerTable.add(tower);
        }
    }

    public Table getInnerTable(){
        return innerTable;
    }

    public Table getOuterTable(){
        return outerTable;
    }
}
