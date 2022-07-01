package com.example.view;

import javafx.scene.Cursor;
import com.example.view.HelloApplication;
import com.example.view.ScreenController;

public class Unit_Draggable_maker {
    private double mouseAnchorX;
    private double mouseAnchorY;



    public void makeDraggable(UnitView node){
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
            double newX, newY, baseX, baseY;
            baseX = node.getLayoutX() ;
            baseY =  node.getLayoutY() + node.getFitHeight(); // знаем координаты основания юнита
           //System.out.println(baseX + " " +  baseY);

            newX = Math.round(baseX / 128)  ;
            newY = Math.round(baseY / 128)  - 1;
            //System.out.println(newX + " " +  newY);


            node.move(newX, newY);
            node.setBoardX((int)newX);
            node.setBoardY((int)newY);






            //node.setLayoutX(Math.ceil(newX / HelloController.TILE_SIZE) * HelloController.TILE_SIZE);
            //node.setLayoutX(Math.ceil(newY/ HelloController.TILE_SIZE) * HelloController.TILE_SIZE - HelloController.TILE_SIZE / 1.5 );
        });


    }
}
