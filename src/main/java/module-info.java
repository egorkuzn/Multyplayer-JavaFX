module com.game.multyplayerjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.game.multyplayerjavafx to javafx.fxml;
    exports com.game.multyplayerjavafx;
}