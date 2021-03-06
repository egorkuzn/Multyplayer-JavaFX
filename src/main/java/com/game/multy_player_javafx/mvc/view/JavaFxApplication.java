package com.game.multy_player_javafx.mvc.view;

import com.game.multy_player_javafx.mvc.view.view_controllers.HelloController;
import com.game.multy_player_javafx.mvc.view.view_controllers.JavaFxToolkit;
import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFxApplication extends Application{
    JavaFxToolkit javaFxTool = new JavaFxToolkit();
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        javaFxTool.load();
        stage = primaryStage;
        javaFxTool.setStage(primaryStage, "START_PANEL");
    }
}
