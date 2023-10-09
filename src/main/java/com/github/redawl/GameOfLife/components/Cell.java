package com.github.redawl.GameOfLife.components;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Region;

/**
 * Game of Life cell are represented by this class
 */
public class Cell extends Region{

    private static final String deadCss = "-fx-background-color: white";

    private static final String aliveCss = "-fx-background-color: black";

    private static final int size = 10;
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
        this.setOnMouseEntered(e -> this.setAlive());
    }

    /**
     * Sets the size of the cell, by setting min and max height and width to the same value.
     */
    private void setSize(){
        this.setMinHeight(Cell.size);
        this.setMaxHeight(Cell.size);
        this.setMinWidth(Cell.size);
        this.setMaxWidth(Cell.size);
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
        int aliveNeighbors = neighbors.stream().mapToInt(n -> n.alive ? 1 : 0).sum();
        if(alive && (aliveNeighbors >= 4 || aliveNeighbors <= 1)){
                newAlive = false;
        } else if(!alive && (aliveNeighbors == 3)) {
            newAlive = true;
        }
    }

    public void update(){
        alive = newAlive;
        if(alive){
            this.setStyle(aliveCss);
        } else {
            this.setStyle(deadCss);
        }
    }

    public boolean isAlive(){
        return alive;
    }

    private void setAlive(){
        alive = !alive;
        this.setStyle(alive ? aliveCss : deadCss);
    }
}
