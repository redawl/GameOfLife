package com.github.redawl.gameoflife;

import javafx.animation.AnimationTimer;
import com.github.redawl.gameoflife.components.Cell;

import java.util.List;

public class BoardTimer extends AnimationTimer {
    private final List<List<Cell>> gameBoard;

    private long time;

    private long intervalInMillis = (long)(Configuration.TIMER_INTERVAL * 1000);

    public BoardTimer(List<List<Cell>> gameBoard){
        super();
        this.gameBoard = gameBoard;
        // Init state of board
        for (List<Cell> row : gameBoard) {
            for (Cell cell : row) {
                cell.update();
            }
        }
        this.time = System.currentTimeMillis();
    }

    @Override
    public void handle(long time){
        if(System.currentTimeMillis() - this.time > intervalInMillis) {
            // Compute
            for (List<Cell> row : gameBoard) {
                for (Cell cell : row) {
                    cell.computeState();
                }
            }

            // Update
            for (List<Cell> row : gameBoard) {
                for (Cell cell : row) {
                    cell.update();
                }
            }
            this.time = System.currentTimeMillis();
        }
    }
}
