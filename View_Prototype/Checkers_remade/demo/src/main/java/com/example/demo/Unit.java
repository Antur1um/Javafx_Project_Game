package com.example.demo;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.io.File;

import static com.example.demo.HelloController.TILE_SIZE;


public class Unit extends ImageView {
    private ImageView type;
    private double X;
    private double Y;
    private double mouseX;
    private double mouseY;

    public ImageView getType(){
        return type;
    }

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
        return Y;
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
        type.relocate(x * TILE_SIZE,  y * TILE_SIZE - (TILE_SIZE / 1.5));
        setUnitX(x * TILE_SIZE);
        setUnitY(y * TILE_SIZE - (TILE_SIZE / 1.5));

    }


    public Unit(File file, double x, double y) {
        //File file  = new File("src/main/java/com/example/demo/tile.png");
        Image image = new Image(file.toURI().toString());
        ImageView unit = new ImageView(image);
        type = unit;
        X = x * TILE_SIZE ;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        type.setFitHeight(202);
        type.setFitWidth(128);
        type.relocate(X, Y);


        type.setOnMouseDragged(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                relocate(event.getSceneX(), event.getSceneY());
                type.relocate(event.getSceneX(), event.getSceneY());
                //System.out.println(type.getX() + " " + type.getY());
                //System.out.println(getXPos() + " " + getYPos());


                //System.out.println((int)event.getSceneX() + " " + (int)event.getSceneY() );

            }
        });

       type.setOnMouseReleased(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event){
                relocate((int)event.getSceneX(), (int)event.getSceneY());
                System.out.println(getXPos() + " " + getYPos());
            }

        });


        /*type.setOnMousePressed(e -> {
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
        });

        type.setOnMouseDragged(e -> {
            relocate(e.getSceneX() - mouseX + X, e.getSceneY() - mouseY + Y);
            //type.relocate(e.getSceneX() - mouseX + X, e.getSceneY() - mouseY + Y);
        });*/

    }


    public void changepos(double x, double y){
        relocate(x, y);
        type.relocate(x, y);
    }

    public void move(double x, double y){
        X = x * TILE_SIZE;
        Y = y * TILE_SIZE - (TILE_SIZE / 1.5);
        relocate(X, Y);
        type.relocate(X, Y);
    }

    public void abortMove() {
        relocate(X, Y);
    }













}



