package com.github.redawl.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

/**
 * Main class for the Game of Life
 */
public class GameOfLife extends Application
{
    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = GameOfLife.class.getResource("/GameOfLifeController.fxml");
        Objects.requireNonNull(url, "Failed to load fxml file.");

        FXMLLoader loader = new FXMLLoader(url);

        AnchorPane page = loader.load();
        TilePane game;
        try {
            game = (TilePane) page.getChildren().stream()
                    .filter(node -> node instanceof TilePane)
                    .findFirst()
                    .orElseThrow(Exception::new);
        } catch (Exception ex){
            throw new RuntimeException("Wrong node was retrieved. ");
        }

        GameOfLifeController controller = GameOfLifeController.of(game);
        loader.setController(controller);
        Scene scene = new Scene(page);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, controller.getTimerController());

        primaryStage.setTitle("Game of Life");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Main entrypoint of the application
     * @param args commandline arguments
     */
    public static void main( String[] args )
    {
        launch(args);
    }
}
