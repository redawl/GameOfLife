package com.github.redawl.gameoflife;

import com.github.redawl.gameoflife.components.Cell;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameOfLifeController {
    private final TilePane container;

    private BoardTimer boardTimer;
    private static final int ySize = 100;
    private static final int xSize = 150;
    private final AtomicBoolean timerActive = new AtomicBoolean(false);

    private GameOfLifeController(TilePane container){
        this.container = container;
        initialize();
    }

    public static GameOfLifeController of(TilePane container){
        return new GameOfLifeController(container);
    }

    public EventHandler<KeyEvent> getTimerController(){
        return this::handleGlobalKeyPresses;
    }

    @FXML
    private void initialize() {
        // Create cells
        List<List<Cell>> gameBoard = new ArrayList<>();
        for(int y = 0; y < ySize; y++){
            gameBoard.add(new ArrayList<>());
            for(int x = 0; x < xSize; x++){
                gameBoard.get(y).add(Cell.createInstance(false));
            }
        }

        // Add neighbors
        for(int x = 0; x < ySize; x++){
            for(int y = 0; y < xSize; y++){
                List<Cell> neighbors = new ArrayList<>();

                for(int i = x - 1; i <= x + 1; i++){
                    for(int j = y - 1; j <= y + 1; j++){
                        if(i >= 0 && j >= 0 && !(i == x && j == y) && i < ySize && j < xSize){
                            neighbors.add(gameBoard.get(i).get(j));
                        }
                    }
                }

                gameBoard.get(x).get(y).addNeighbors(neighbors);
            }
        }

        // Init state
        container.setPrefColumns(xSize);
        container.setPrefRows(ySize);
        container.setOnKeyPressed(e -> {
            System.out.println("On key pressed fired");
            if(e.getCode() == KeyCode.SPACE){
                if(timerActive.get()){
                    stopTimer();
                } else {
                    startTimer();
                }
            }
        });
        container.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent event) -> {
            System.out.println("Key pressed");
            if (event.getCode() == KeyCode.F1) {
                System.out.println("F1 pressed");
            }
            event.consume();
        });
        for(List<Cell>row: gameBoard){
            container.getChildren().addAll(row);
        }

        // Init board timer
        this.boardTimer = new BoardTimer(gameBoard);
    }

    private void startTimer(){
        timerActive.set(true);
        boardTimer.start();
    }

    private void stopTimer(){
        boardTimer.stop();
        timerActive.set(false);
    }

    public void handleGlobalKeyPresses(KeyEvent e){
        System.out.println("On key pressed fired");
        if(e.getCode() == KeyCode.SPACE){
            if(timerActive.get()){
                stopTimer();
            } else {
                startTimer();
            }
        } else if (e.getCode() == KeyCode.ESCAPE){
            System.exit(0);
        }
    }
}
