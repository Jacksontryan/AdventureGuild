module AdventureGuild {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    exports project;
    opens project to javafx.graphics, javafx.fxml;
    exports project.NPC;
    opens project.NPC to javafx.fxml, javafx.graphics;
    exports project.Map;
    opens project.Map to javafx.fxml, javafx.graphics;

}