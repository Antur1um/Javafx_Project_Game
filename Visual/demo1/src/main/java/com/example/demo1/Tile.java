package com.example.demo1;

import eu.hansolo.tilesfx.skins.ImageTileSkin;
import javafx.fxml.FXML;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {

    private Piece piece;
    //public ImageView skin;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /*public void setImage(ImageView skin) {
        this.skin = skin;
    }*/



    public Tile(boolean light, int x, int y) {

        setWidth(CheckersApp.TILE_SIZE);
        setHeight(CheckersApp.TILE_SIZE);
        //setStyle( );
        //setStyle("./com/example/demo1/Style.css");

        //setImage(new ImageView(new Image("./com/example/demo1/tile.png")));




        relocate(x * CheckersApp.TILE_SIZE, y * CheckersApp.TILE_SIZE);

        setFill(light ? Color.valueOf("#999") : Color.valueOf("#000"));
        //setFill(Color.valueOf("#999"));




    }
}
