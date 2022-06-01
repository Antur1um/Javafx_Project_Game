package com.example.demo;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import com.example.demo.HelloApplication.*;
import javafx.scene.layout.Pane;

import java.io.File;

public class HelloController {

    public static final int TILE_SIZE = 128;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 5;

    public Tile[][] board = new Tile[WIDTH][HEIGHT];
    public Group tileGroup = new Group();

    @FXML
    AnchorPane main_screen = new AnchorPane();
    @FXML    //вот они объявлены
    ScrollPane game_screen = new ScrollPane();

    @FXML
    ImageView neutral = new ImageView();

    @FXML
    public void draw_field() {

        main_screen.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE)+ TILE_SIZE); //вот создаются
        main_screen.getChildren().addAll(tileGroup);
        main_screen.setStyle("-fx-background-color: #202020");
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile("TILE", x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile);
            }
        }
        game_screen.setContent(main_screen);
        game_screen.contentProperty();
    }





   @FXML
    public void draw_unit(String type, double x, double y) {
        Unit unit = new Unit(type, x, y);
        main_screen.getChildren().add(unit);
    }

    @FXML
    public void draw_tile(String type, int x, int y) {
        Tile tile = new Tile(type, x, y);
        main_screen.getChildren().add(tile);
    }


    @FXML
    public void start_game(){
        draw_field();

    }























}