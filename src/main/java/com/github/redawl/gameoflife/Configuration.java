package com.github.redawl.gameoflife;

public class Configuration {
    // Cell Configs
    public static final int CELL_SIZE = 10;
    public static final String DEAD_CSS = "-fx-background-color: #FF2BEA; -fx-border-color: #92FF41";
    public static final String ALIVE_CSS = "-fx-background-color: #5200BD";

    // Timer Configs
    public static final double TIMER_INTERVAL = .05 * 1000;

    // Board Configs
    /**
     * Private default constructor
     */
    private Configuration () {

    }
}
