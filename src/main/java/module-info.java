module com.game.multyplayerjavafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.game.multy_player_javafx to javafx.fxml;
    exports com.game.multy_player_javafx;
}