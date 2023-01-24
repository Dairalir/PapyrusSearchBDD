package com.example.papyrus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    static Stage MainStage = new Stage();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.setScene(scene);
        MainStage.show();
    }

    public static void changeFxml(String nom) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(nom+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.setScene(scene);
    }
}