package com.game.multy_player_javafx.mvc.view.view_controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class LoadController {
    public static Stage stage;
    public static String status = "Connecting to the server...";
    public  static  boolean isClickable = false;
    JavaFxToolkit javaFxTool = new JavaFxToolkit();
    @FXML
    private Label statusMessage = new Label();


    @FXML
    void initialize(){
        statusMessage.setOnMouseEntered(mouseEvent -> {
            statusMessage.setText(status.toUpperCase());
        });

        statusMessage.setOnMouseExited(mouseEvent -> {
            statusMessage.setText(status);
        });

        statusMessage.setOnMouseClicked(mouseEvent -> {
            if(isClickable)
                javaFxTool.setStage(stage, "START_PANEL");
        });
    }
}
