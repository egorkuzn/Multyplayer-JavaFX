package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.Background;
import com.game.multy_player_javafx.mvc.view.scene_items.Sprites;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CityController {
    String propPath = "actions/keyList.properties";
    public static Stage stage;
    ClientController clientController = new ClientController();
    HashMap<String, String> commandByKey = new HashMap<>();
    JavaFxToolkit javaFxToolkit = new JavaFxToolkit();
    @FXML
    BorderPane field = new BorderPane();
    @FXML
    void initialize(){
        getKeyMap();

        Sprites sprites = new Sprites(field);

        field.setOnKeyPressed(keyEvent -> {
            if(commandByKey.containsKey(keyEvent.getCharacter()))
                if(!clientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                    connectionLose();
            else {
                getKeyMap();

                if(commandByKey.containsKey(keyEvent.getCharacter()))
                    if(!clientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                        connectionLose();
            }
        });

    }

    void getKeyMap(){
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(propPath);

        try {
            properties.load(inputStream);

            for(Map.Entry<?, ?> elem : properties.entrySet())
                commandByKey.put((String) elem.getKey(), (String) elem.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void connectionLose(){
        LoadController.stage = stage;
        LoadController.isClickable = true;
        LoadController.status = "Connection with server is lost.";
        javaFxToolkit.setStage(stage, "LOADING_STAGE");
    }
}
