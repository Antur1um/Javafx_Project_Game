package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


import java.io.File;

import static com.example.demo.HelloController.TILE_SIZE;



public class Tile extends ImageView{


    //ImageView pick = new ImageView("com/example/demo/tile.png");
    private ImageView status;


        public Tile(File file, int x, int y) {
            //File file  = new File("D:/Хранилище/Институт/Checkers_remade/demo/src/main/java/com/example/demo/tile.png");
            Image image = new Image(file.toURI().toString());
            ImageView tile = new ImageView(image);
            status = tile;
            status.setFitHeight(TILE_SIZE);
            status.setFitWidth(TILE_SIZE);
            status.relocate(x * TILE_SIZE, y * TILE_SIZE);

            //this.setStyle("-fx-background-image: File: com/example/demo/tile.png ");
            //this.getChildren().addAll(pick);
            //this.getChildren().add(pic);
            //setStyle("-fx-background-image: url(../../../../resources/com/example/demo/tile.png)");
        }

        public ImageView getStatus(){
            return status;
        }



}
