package com.example.demo;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import java.io.File;
import java.util.HashMap;

import static com.example.demo.HelloController.TILE_SIZE;


public class Unit extends ImageView {
    private double X; // Текущая позиция юнита
    private double Y; // Используются чтобы вернуть его назад в случае недопустимого движения

private String getSkin(String t){
    HashMap<String, String> skins = new HashMap<>();
    skins.put("SPEARMAN_RED", "src/main/resources/com/example/demo/units_skins/spearman_red.png" );
    skins.put("SPEARMAN_BLUE", "src/main/resources/com/example/demo/units_skins/spearman_blue.png" );
    skins.put("GUARDIAN_RED", "src/main/resources/com/example/demo/units_skins/guardian_red.png" );
    skins.put("GUARDIAN_BLUE", "src/main/resources/com/example/demo/units_skins/guardian_blue.png" );

    return skins.get(t);
}

   /* private enum Skins{ //Перечисление для файлов

        SPEARMAN_RED("src/main/resources/com/example/demo/units_skins/spearman_red.png"),
        SPEARMAN_BLUE("src/main/resources/com/example/demo/units_skins/spearman_blue.png"),
        GUARDIAN_RED("src/main/resources/com/example/demo/units_skins/guardian_red.png"),
        GUARDIAN_BLUE("src/main/resources/com/example/demo/units_skins/guardian_blue.png");

       private String files;
        Skins(String file_name){
            this.files = file_name;
        }

        public String getFiles(){
            return files;
        }
    }*/
    private double mouseX;
    private double mouseY;



    public double getXPos(){
        return  X;
    }

    public double getYPos(){
        return Y;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY(){
        return mouseY;
    }

    public void setMouseX(double newX){
        mouseX = newX;
    }

    public void setMouseY(double newY){
        mouseX = newY;
    }

    public void setUnitX(double newX){X = newX;}
    public void setUnitY(double newY){X = newY;}


    public void toBoard(double x, double y){
        relocate(x * TILE_SIZE,  y * TILE_SIZE - (TILE_SIZE / 1.5));
        relocate(x * TILE_SIZE,  y * TILE_SIZE - (TILE_SIZE / 1.5));
        setUnitX(x * TILE_SIZE);
        setUnitY(y * TILE_SIZE - (TILE_SIZE / 1.5));
    }


    public Unit(String type, double x, double y) {
        File file = new File(getSkin(type.toString()));
        Image image = new Image(file.toURI().toString());
        setImage(image);
        X = x * TILE_SIZE;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        setFitHeight(205);
        setFitWidth(128);
        relocate(X, Y);
        setPickOnBounds(true);
        DrugAbleMaker mover = new DrugAbleMaker();
        mover.makeDraggable(this);
    }



    public void changepos(double x, double y){
        relocate(x, y);

    }

    public void move(double x, double y){
        X = x * TILE_SIZE;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        relocate(X, Y);

    }

    public void abortMove() {
        relocate(X, Y);
    }

}



