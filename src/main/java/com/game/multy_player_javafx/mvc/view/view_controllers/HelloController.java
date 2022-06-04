package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HelloController {
    public static Stage stage;
    @FXML
    private Button button;
    @FXML
    private BorderPane field = new BorderPane();
    private JavaFxToolkit javaFxTool = new JavaFxToolkit();
    ClientController clientController = new ClientController();

    @FXML
    void initialize(){
        button.setOnAction(actionEvent -> {
            LoadController.stage = stage;
            javaFxTool.setStage(stage,"LOADING_STAGE");
            clientController.run();

            if(clientController.status)
                continueGame();
            else
                showConnectionException();
        });

        field.setOnKeyTyped(keyEvent -> {
            if(keyEvent.getCharacter().equals("q")) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    @FXML
    void continueGame(){
        CityController.stage = stage;
        javaFxTool.setStage(stage, "CITY");
    }

    void showConnectionException(){
        LoadController.status = "Server is down. Click to go menu.";
        LoadController.isClickable = true;
    }
}
