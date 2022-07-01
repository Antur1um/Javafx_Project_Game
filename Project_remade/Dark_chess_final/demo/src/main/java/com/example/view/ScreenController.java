package com.example.view;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;
import com.example.game.Player;
import com.example.network.Network;

public class ScreenController {

    public static final int TILE_SIZE = 128;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 6; //Размеры поля

    private Network network;


    public Player player = new Player();

    private boolean turn;

    private boolean color;

    public Tile[][] board = new Tile[WIDTH][HEIGHT];

    public ArrayList units = new ArrayList<UnitView>(); // список юнитов
    public Group tileGroup = new Group();

    @FXML
    AnchorPane main_screen = new AnchorPane();
    @FXML
    ScrollPane game_screen = new ScrollPane();

    @FXML
    Button Stranger = new Button(); //кнопка создания странника

    @FXML
    Button Spear = new Button(); // Кнопка создания копейщика

    @FXML
    Button Guard = new Button(); // Кнопка создания стража

    @FXML
    Button End = new Button(); // Кнопка "конец хода"

    @FXML
    TextArea Info = new TextArea();



    @FXML
    TextArea Profit = new TextArea();

    @FXML
    TextArea Treasure = new TextArea();

    @FXML
    ImageView Side = new ImageView();


    @FXML
    public void draw_field() {
        //String name;
        network = new Network();
        //name = network.waitForString();

        //System.out.println(name);
        /*side = network.waitForInt();
        if(side == 1){
            File s = new File("src/main/resources/com/example/view/red_team.png");
            Image image = new Image(s.toURI().toString());
            Side.setImage(image);
        }
        else if(side == 2){
            File s = new File("src/main/resources/com/example/view/blue_team.png");
            Image image = new Image(s.toURI().toString());
            Side.setImage(image);
        }*/
        setTurn(true);
        setColor(true);
        main_screen.setPrefSize(WIDTH * TILE_SIZE, (HEIGHT * TILE_SIZE)+ TILE_SIZE);
        main_screen.getChildren().addAll(tileGroup);
        main_screen.setStyle("-fx-background-color: #202020");
        for (int y = 1; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile("TILE", x, y);
                board[x][y] = tile;
                //System.out.println("X: " + x + "Y: " + y);
                tileGroup.getChildren().add(tile);

            }
            Info.setEditable(false);
            Treasure.setEditable(false);
            Profit.setEditable(false);
            Info.setText("Ходит красный");
            UpdateCounters();

        }

        Spear.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(getColor())
                draw_unit("SPEARMAN_RED", 0, 1);
                else
                    draw_unit("SPEARMAN_BLUE", 0, 1);
            }
        });


        Guard.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(getColor())
                    draw_unit("GUARDIAN_RED", 0, 1);
                else
                    draw_unit("GUARDIAN_BLUE", 0, 1);
            }
        });

        Stranger.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(getColor())
                    draw_unit("STRANGER_RED", 0, 1);
                else
                    draw_unit("STRANGER_BLUE", 0, 1);
            }
        });

        End.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {  // кнопка передачи хода, отправка должна происходить
            // именно здесь
            @Override
            public void handle(MouseEvent mouseEvent) {
                Info.setText("Вы передали ход");
                Spear.setDisable(true);
                Stranger.setDisable(true);
                Guard.setDisable(true);
                End.setDisable(true);
                update_field();

               /* if(turn){
                    setTurn(false);
                    Info.setText("Ходит синий");
                    File s = new File("src/main/resources/com/example/view/blue_team.png");
                    Image image = new Image(s.toURI().toString());
                    Side.setImage(image);
                }
                else{
                    setTurn(true);
                    Info.setText("Ходит красный");
                    File s = new File("src/main/resources/com/example/view/red_team.png");
                    Image image = new Image(s.toURI().toString());
                    Side.setImage(image);
                }*/

                player.setMyTreasure(player.getMyTreasure() + player.getMyProfit());
                UpdateCounters();
                sendField();
            }
        });
        game_screen.setContent(main_screen);
        game_screen.contentProperty();
    }
   @FXML // нарисовать юнита можно на любой клетке
    public void draw_unit(String type, int x, int y) {
        UnitView unit = new UnitView(type, x, y);
        //System.out.print(player.getMyTreasure() + "\n" + unit.getCost() + "\n" + unit.getPower() + "\n" + unit.getFood());
        if(player.getMyTreasure() >= unit.getCost()){
            main_screen.getChildren().add(unit);
            board[x][y].setSide(unit.getSide());
            unit.setBoardX(x);
            unit.setBoardY(y);
            units.add(unit);
            player.setMyProfit(player.getMyProfit() - unit.getFood());
            player.setMyTreasure(player.getMyTreasure() - unit.getCost());
            UpdateCounters();
        }
        else {
            Info.setText("Вы не можете нанять этого юнита!");
        }
    }

    /*public void move_unit(UnitView unit, int x, int y) {
        unit.move(x, y);
        change_tile_side(unit.getSide(), x, y);
    }*/

    public void sendField(){ // тправляем все координаты
        for (int y = 1; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                //System.out.println(board[x][y].getColor());
                network.send(board[x][y].getColor());

            }
        }
    }


    @FXML
    public void update_field(){ // обновление поля, проверка, под всеми юнитами меняется тайл
        for(int i= 0; i < units.size(); i = i+1){
            for(int j=i+1; j < units.size(); j++ ){
                UnitView unit = (UnitView) units.get(i);
                UnitView unit2 = (UnitView) units.get(j);
                if (unit.getSide() == unit2.getSide()){
                    if(unit.getBoardX() == unit2.getBoardX() && unit.getBoardY() == unit2.getBoardY()){
                        unit.move(unit.getBoardX() + 1, unit.getBoardY() + 1);
                    }
                }
                if(unit.getYPos()==unit2.getYPos() && unit.getXPos() == unit2.getXPos() && unit.getSide() != unit2.getSide() && (unit.getPower() > unit2.getPower() || unit.getPower() < unit2.getPower())){
                    unit2.setVisible(false); // Уничтожение юнитов
                    units.remove(unit2);
                }
            }
        }
        for(int i= 0; i < units.size(); i = i+1){
            UnitView unit = (UnitView) units.get(i);
            change_tile_side(unit.getSide(), unit.getBoardX(), unit.getBoardY());

            //Изменение тайлов
        }

        System.out.println(player.getCaptured_area());


      }

    @FXML
    public void change_tile_side(boolean side, int x, int y){
        board[x][y].setSide(side);
        player.addCaptured_area(1);
        player.setMyProfit(player.getMyProfit() + player.getCaptured_area());

    } //Изменение тайла

    public void setTurn(boolean t){
        turn = t;
    }
    public boolean getTurn(){
        return turn;
    }


    public void setColor(boolean t){
        color = t;
    }
    public boolean getColor(){
        return color;
    }

    @FXML
    public void UpdateCounters(){
        Treasure.setText(String.valueOf(player.getMyTreasure()));
        Profit.setText(String.valueOf(player.getMyProfit()));
    }

public ScreenController(){}



    @FXML
    public void start_game(){
        draw_field();
    }


    public boolean checkPos(double x, double y) {
        for(int i= 0; i < units.size(); i = i+1){
            UnitView unit = (UnitView) units.get(i);
            if(unit.getBoardX() == x && unit.getBoardY() == y && unit.getSide()){
                return false;
            }

        }

        return true;
    }


    }























