package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.Socket;

public class LoadController {
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
            if(isClickable) {
                if(status.equals("Press to continue ..."))
                    javaFxTool.setStage(JavaFxApplication.stage, "CITY");
                else
                    javaFxTool.setStage(JavaFxApplication.stage, "START_PANEL");

                isClickable = false;
            }
        });
    }
}
