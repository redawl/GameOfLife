package org.example;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import org.example.components.Cell;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameOfLifeController {
    @FXML
    private TilePane container;

    private List<List<Cell>> gameBoard;

    private BoardTimer boardTimer;

    private static final int xSize = 100;

    private static final int ySize = 100;

    private final AtomicBoolean timerActive = new AtomicBoolean(false);


    @FXML
    private void initialize() {
        // Create cells
        gameBoard = new ArrayList<>();
        for(int x = 0; x < xSize; x++){
            gameBoard.add(new ArrayList<>());
            for(int y = 0; y < ySize; y++){
                gameBoard.get(x).add(Cell.createInstance(false));
            }
        }

        // Add neighbors
        for(int x = 0; x < xSize; x++){
            for(int y = 0; y < ySize; y++){
                List<Cell> neighbors = new ArrayList<>();

                for(int i = x - 1; i <= x + 1; i++){
                    for(int j = y - 1; j <= y + 1; j++){
                        if(i >= 0 && j >= 0 && !(i == x && j == y) && i < xSize && j < ySize){
                            neighbors.add(gameBoard.get(i).get(j));
                        }
                    }
                }

                gameBoard.get(x).get(y).addNeighbors(neighbors);
            }
        }

        // Init state
        container.setPrefColumns(100);
        container.setPrefRows(100);
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

        startTimer();
    }

    private void startTimer(){
        timerActive.set(true);
        boardTimer.start();
    }

    private void stopTimer(){
        boardTimer.stop();
        timerActive.set(false);
    }

    public void toggleTimer(KeyEvent e){
        System.out.println("On key pressed fired");
        if(e.getCode() == KeyCode.SPACE){
            if(timerActive.get()){
                stopTimer();
            } else {
                startTimer();
            }
        }
    }
}
