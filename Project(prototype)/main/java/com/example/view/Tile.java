package com.example.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.util.HashMap;

import static com.example.view.ScreenController.TILE_SIZE;


//
public class Tile extends ImageView{


    private int X;
    private int Y;

    private boolean Side; // 1 - красные 0 - синие

    private boolean hasUnit;
    private String getSkin(String t){
        HashMap<String, String> skins = new HashMap<>();
        skins.put("TILE", "src/main/resources/com/example/view/tiles_skins/tile.png" );
        //skins.put("TILE_GROWN", "src/main/resources/com/example/view/units_skins/spearman_blue.png" );
        skins.put("TILE_RED", "src/main/resources/com/example/view/tiles_skins/red_tile.png" );
        skins.put("TILE_BLUE", "src/main/resources/com/example/view/tiles_skins/blue_tile.png" );
        return skins.get(t);
    }

        public Tile(String type, int x, int y) {
        //File file = new File(getSkin(type.toString()));
            //Image image = new Image(file.toURI().toString());
            putImage(type);
            setFitHeight(TILE_SIZE);
            setFitWidth(TILE_SIZE);
            relocate(x * TILE_SIZE, y * TILE_SIZE);
            X = x * TILE_SIZE;
            Y = y * TILE_SIZE;
            //System.out.println("X " + X + " Y " + Y + "\n");
            System.out.println(this.getLayoutX() + "  " + this.getLayoutY());
            isSmooth();
        }

        public void putImage(String type){
            File file = new File(getSkin(type.toString()));
            Image image = new Image(file.toURI().toString());
            setImage(image);
        }

        public void setSide(boolean side){
        Side = side;
        hasUnit = true;
        if(side){
            putImage("TILE_RED");
        }
        else{
            putImage("TILE_BLUE");
        }
        }

        public boolean getHasUnit(){
        return hasUnit;
        }





    public int getXPos() {
        return X;
    }


    public int getYPos() {
        return Y;
    }
}
