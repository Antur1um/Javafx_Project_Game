package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.File;
import java.util.HashMap;

import static com.example.demo.HelloController.TILE_SIZE;


//
public class Tile extends ImageView{

    private String getSkin(String t){
        HashMap<String, String> skins = new HashMap<>();
        skins.put("TILE", "src/main/resources/com/example/demo/tiles_skins/tile.png" );
        //skins.put("TILE_GROWN", "src/main/resources/com/example/demo/units_skins/spearman_blue.png" );
        skins.put("TILE_RED", "src/main/resources/com/example/demo/tiles_skins/red_tile.png" );
        skins.put("TILE_BLUE", "src/main/resources/com/example/demo/tiles_skins/blue_tile.png" );

        return skins.get(t);
    }

        public Tile(String type, int x, int y) {
        File file = new File(getSkin(type.toString()));
            Image image = new Image(file.toURI().toString());
            setImage(image);
            setFitHeight(TILE_SIZE);
            setFitWidth(TILE_SIZE);
            relocate(x * TILE_SIZE, y * TILE_SIZE);
            isSmooth();
        }
}
