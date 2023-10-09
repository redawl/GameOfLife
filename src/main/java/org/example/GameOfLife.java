package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Hello world!
 *
 */
public class GameOfLife extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = GameOfLife.class.getResource("/GameOfLifeController.fxml");
        FXMLLoader loader = new FXMLLoader(url);
        TilePane page = loader.load();
        Scene scene = new Scene(page);

        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main( String[] args )
    {
        launch(args);
    }
}
