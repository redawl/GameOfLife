package org.example.components;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.Region;

public class Cell extends Region{

    private static final String deadCss = "-fx-background-color: white";

    private static final String aliveCss = "-fx-background-color: black";

    private static final int size = 5;
    private List<Cell> neighbors;
    private boolean alive;
    private boolean newAlive;

    private Cell() {
        super();
        setSize();
        this.setOnMouseEntered(e -> {
            this.setAlive();
        });
    }

    private void setSize(){
        this.setMinHeight(Cell.size);
        this.setMaxHeight(Cell.size);
        this.setMinWidth(Cell.size);
        this.setMaxWidth(Cell.size);
    }

    public static Cell createInstance(boolean startingState){
        Cell cell = new Cell();
        cell.neighbors = new ArrayList<>();
        cell.alive = startingState;

        return cell;
    }

    public void addNeighbors(List<Cell> neighbors){
        this.neighbors.addAll(neighbors);
    }

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
