package com.github.redawl.gameoflife;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

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
        Optional<? extends Node> game;
        game = page.getChildren().stream()
                .filter(node -> node instanceof TilePane)
                .findFirst();

        if(game.isPresent() && game.get() instanceof TilePane) {
            GameOfLifeController controller = GameOfLifeController.of((TilePane) game.get());
            loader.setController(controller);
            Scene scene = new Scene(page);
            scene.addEventFilter(KeyEvent.KEY_PRESSED, controller.getTimerController());

            primaryStage.setTitle("Game of Life");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } else {
            throw new RuntimeException("Could not retrieve TilePane! " + game);
        }
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
