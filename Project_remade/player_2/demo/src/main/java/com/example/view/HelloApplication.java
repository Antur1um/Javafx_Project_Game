package com.example.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.example.view.ScreenController;
import java.io.File;
import java.io.IOException;



public class HelloApplication extends Application {

    private  ScreenController controller;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent loader = fxmlLoader.load();
         controller = fxmlLoader.getController();
        controller.draw_field();

        /*controller.draw_unit("GUARDIAN_BLUE",5,1);
        controller.draw_unit("GUARDIAN_RED",0,2);

        controller.draw_unit("SPEARMAN_BLUE",5,2);
        controller.draw_unit("SPEARMAN_RED", 0,3);

        controller.draw_unit("STRANGER_BLUE",5,4);
        controller.draw_unit("STRANGER_RED", 0,4);*/



        /*controller.change_tile("TILE_RED", 1, 3);
        controller.change_tile("TILE_RED", 2, 3);
        controller.change_tile("TILE_RED", 3, 3);

        controller.change_tile("TILE_BLUE", 4, 2);
        controller.change_tile("TILE_BLUE", 5, 2);
        controller.change_tile("TILE_BLUE", 6, 2);*/


        Scene scene = new Scene(loader, 1366, 768);
        stage.setTitle("Dark_Chess");
        File file = new File("src/main/resources/com/example/view/icon.png");
        stage.getIcons().add(new Image(file.toURI().toString()));
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch();
    }
}