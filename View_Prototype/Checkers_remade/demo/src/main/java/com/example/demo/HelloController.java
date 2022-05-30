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

    public static final int TILE_SIZE = 200;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 5;

    public Tile[][] board = new Tile[WIDTH][HEIGHT];
    public Group tileGroup = new Group();

    @FXML
    AnchorPane main_screen = new AnchorPane();
    @FXML
    ScrollPane game_screen = new ScrollPane();

    @FXML
    ImageView neutral = new ImageView();


    @FXML
    public void draw_field() {

        main_screen.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        main_screen.getChildren().addAll(tileGroup);
        main_screen.setStyle("-fx-background-color: #030309");
        for (int y = 1; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                File file = new File("src/main/java/com/example/demo/tile.png");
                Tile tile = new Tile(file, x, y);
                board[x][y] = tile;
                tileGroup.getChildren().add(tile.getStatus());
            }
        }

        game_screen.setContent(main_screen);
        game_screen.contentProperty();
    }

    @FXML
    public void draw_unit(double x, double y) {
        File file = new File("src/main/java/com/example/demo/spearman_red.png");
        Unit unit = new Unit(file, x, y);
        main_screen.getChildren().add(unit.getType());
    }

}