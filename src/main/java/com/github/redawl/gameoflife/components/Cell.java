package com.github.redawl.gameoflife.components;

import java.util.ArrayList;
import java.util.List;

import com.github.redawl.gameoflife.Configuration;
import javafx.scene.layout.Region;

/**
 * Game of Life cell are represented by this class
 */
public class Cell extends Region{
    private List<Cell> neighbors;
    private boolean alive;
    private boolean newAlive;

    /**
     * 1. Initialize parent class<br>
     * 2. Set the size of the cell<br>
     * 3. Add event listener for changing the cell's state manually<br>
     */
    private Cell() {
        super();
        setSize();
        this.setOnMouseEntered(e -> {
            if (e.isShiftDown()) {
                this.setAlive(true);
            } else if (e.isControlDown()){
                this.setAlive(false);
            }
        });
    }

    /**
     * Sets the size of the cell, by setting min and max height and width to the same value.
     */
    private void setSize(){
        this.setMinHeight(Configuration.CELL_SIZE);
        this.setMaxHeight(Configuration.CELL_SIZE);
        this.setMinWidth(Configuration.CELL_SIZE);
        this.setMaxWidth(Configuration.CELL_SIZE);
    }

    /**
     * Create a new cell, given the initial state
     * @param startingState whether the cell should start out as a dead cell or an alive cell
     * @return the newly created cell
     */
    public static Cell createInstance(boolean startingState){
        Cell cell = new Cell();
        cell.neighbors = new ArrayList<>();
        cell.alive = startingState;

        return cell;
    }

    /**
     * Add a list of neighbors for the cell to check
     * @param neighbors List of neighbors to add
     */
    public void addNeighbors(List<Cell> neighbors){
        this.neighbors.addAll(neighbors);
    }

    /**
     * Computes the state of the cell.<br>
     * IMPORTANT: Does not update the state. In order to update the state, call {@code Cell.update()}
     */
    public void computeState(){
        int aliveNeighbors = neighbors.stream()
                .mapToInt(n -> n.alive ? 1 : 0)
                .sum();
        if(alive && (aliveNeighbors >= 4 || aliveNeighbors <= 1)){
                newAlive = false;
        } else if(!alive && (aliveNeighbors == 3)) {
            newAlive = true;
        }
    }

    public void update(){
        alive = newAlive;
        if(alive){
            this.setStyle(Configuration.ALIVE_CSS);
        } else {
            this.setStyle(Configuration.DEAD_CSS);
        }
    }

    private void setAlive(boolean alive){
        this.alive = alive;
        this.setStyle(alive ? Configuration.ALIVE_CSS : Configuration.DEAD_CSS);
    }
}
