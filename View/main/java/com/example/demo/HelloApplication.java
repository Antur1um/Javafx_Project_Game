package com.example.demo;

import com.example.demo.Tile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        HelloController battle_field = new HelloController();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Parent loader = fxmlLoader.load();
        HelloController controller = fxmlLoader.getController();
        controller.draw_field();
        Scene scene = new Scene(loader, 1280, 720);

        stage.setTitle("Game_Window");

        stage.setScene(scene);
        stage.show();
    }





    public static void main(String[] args) {
        launch();
    }
}