package com.example.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ScreenController {

    public static final int TILE_SIZE = 128;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 6;

    public Tile[][] board = new Tile[WIDTH][HEIGHT];
    public Group tileGroup = new Group();

    @FXML
    AnchorPane main_screen = new AnchorPane();
    @FXML
    ScrollPane game_screen = new ScrollPane();

    @FXML
    Button Stranger = new Button();

    @FXML
    Button Spear = new Button();

    @FXML
    Button Guard = new Button();

    @FXML
    Button End = new Button();

    @FXML
    TextArea Info = new TextArea();

    @FXML
    public void draw_field() {
        main_screen.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE)+ TILE_SIZE); //вот создаются
        main_screen.getChildren().addAll(tileGroup);
        main_screen.setStyle("-fx-background-color: #202020");
        for (int y = 1; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile("TILE", x, y);
                board[x][y] = tile;
                //System.out.println("X: " + x + "Y: " + y);
                tileGroup.getChildren().add(tile);
                Info.setText("Game started");
            }
        }

        Spear.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draw_unit("SPEARMAN_RED", 0, 1);
                Info.setText("Spearman created");

            }
        });


        Guard.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draw_unit("GUARDIAN_BLUE", 0, 1);
                Info.setText("Guard created");

            }
        });

        Stranger.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                draw_unit("STRANGER_RED", 0, 1);
                Info.setText("Stranger created");
            }
        });

        End.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Info.setText("Coming soon");
            }
        });;






        game_screen.setContent(main_screen);
        game_screen.contentProperty();


    }


   @FXML
    public void draw_unit(String type, int x, int y) {
        UnitView unit = new UnitView(type, x, y);
        main_screen.getChildren().add(unit);
        board[x][y].setSide(unit.getSide());
    }

    public void move_unit(UnitView unit, int x, int y) {
        unit.move(x, y);
        change_tile_side(unit.getSide(), x, y);
    }

    @FXML
    public void change_tile_side(boolean side, int x, int y){
        board[x][y].setSide(side);
    }



    @FXML
    public void start_game(){
        draw_field();
    }


   /* @FXML
    public void update_units(){
      ArrayList a =  new ArrayList(List.of(main_screen.getChildren().toArray()));
      for(int i = 0; i < a.size(); i++){
          a.
      }*/



    }























