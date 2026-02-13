module AdventureGuild {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    exports project;
    opens project to javafx.graphics, javafx.fxml;
    exports project.Map;
    opens project.Map to javafx.fxml, javafx.graphics;
    exports project.People;
    opens project.People to javafx.fxml, javafx.graphics;

}