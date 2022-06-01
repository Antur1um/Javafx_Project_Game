package com.example.demo;

import javafx.scene.Cursor;
import javafx.scene.Node;

public class DrugAbleMaker {
    private double mouseAnchorX;
    private double mouseAnchorY;

    public void makeDraggable(Node node){
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getX();
            mouseAnchorY = mouseEvent.getY();
            node.setCursor(Cursor.HAND);
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setLayoutX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseAnchorY);
            node.setCursor(Cursor.CLOSED_HAND);
        });

        node.setOnMouseReleased(mouseEvent -> {
            double newX, newY;
            newX = mouseEvent.getSceneX() - mouseAnchorX;
            newY = mouseEvent.getSceneX() - mouseAnchorY;

            if (newX > HelloController.WIDTH * HelloController.TILE_SIZE){
                newX = HelloController.WIDTH * HelloController.TILE_SIZE;
            } else if (newX < 0 ) {
                newX = 0;
            }

            if (newY > HelloController.HEIGHT * HelloController.TILE_SIZE + HelloController.TILE_SIZE){
                newX = HelloController.HEIGHT * HelloController.TILE_SIZE;
            } else if (newY < 0 ) {
                newY = 0;
            }

            node.setLayoutX(Math.ceil(newX / HelloController.TILE_SIZE) * HelloController.TILE_SIZE);
            node.setLayoutX(Math.ceil(newY/ HelloController.TILE_SIZE) * HelloController.TILE_SIZE - HelloController.TILE_SIZE / 1.5 );

        });


    }
}
