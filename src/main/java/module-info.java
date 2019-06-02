module LaborReport {
    requires poi;
    requires poi.ooxml;
    requires commons.csv;
    requires javafx.controls;
    requires javafx.fxml;

    opens ru.indraft.controller to javafx.fxml;
    exports ru.indraft;
}