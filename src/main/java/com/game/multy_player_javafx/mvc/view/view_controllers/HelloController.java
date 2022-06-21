package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button button;
    @FXML
    private BorderPane field = new BorderPane();
    private JavaFxToolkit javaFxTool = new JavaFxToolkit();
    ClientController clientController = new ClientController();
    boolean firstTime = true;

    @FXML
    void initialize(){
        clientController.start();

        if(firstTime) {
            button.setOnAction(actionEvent -> {
                javaFxTool.setStage(JavaFxApplication.stage, "LOADING_STAGE");

                if (clientController.getStatus())
                    continueGame();
                else
                    showConnectionException();

                firstTime = false;
            });
        }

        field.setOnKeyTyped(keyEvent -> {
            if(keyEvent.getCharacter().equals("q") || keyEvent.getCharacter().equals("Q")) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    @FXML
    void continueGame(){
        LoadController.status = "Press to continue ...";
        LoadController.isClickable = true;
    }

    void showConnectionException(){
        LoadController.status = "Server is down. Click to go menu.";
        LoadController.isClickable = true;
    }
}
