module de.fbeckmann.seamcarvingapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens de.fbeckmann.seamcarvingapp to javafx.fxml;
    exports de.fbeckmann.seamcarvingapp;
}