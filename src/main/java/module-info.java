module GameOfLife {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.github.redawl.gameoflife to javafx.fxml;
    exports com.github.redawl.gameoflife;
}

