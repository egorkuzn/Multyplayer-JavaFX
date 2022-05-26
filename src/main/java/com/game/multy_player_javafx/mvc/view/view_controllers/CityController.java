package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.Sprites;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class CityController {
    String propPath = "actions/keyList.properties";
    Sprites hero = new Sprites();
    public static Stage stage;
    ClientController clientController = new ClientController();
    static final HashMap<KeyCode, String> commandByKey = new HashMap<>();
    @FXML
    BorderPane field = new BorderPane();
    @FXML
    void initialize(){
        getKeyMap();

        field.setOnKeyPressed(keyEvent -> {
            clientController.setCommand(commandByKey.get(keyEvent.getCharacter()));
        });

    }

    void getKeyMap(){
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(propPath);
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
