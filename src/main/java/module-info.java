module taskmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens taskmanager to javafx.fxml;
    exports taskmanager;
}
