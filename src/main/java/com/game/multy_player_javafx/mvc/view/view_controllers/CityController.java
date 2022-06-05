package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.Background;
import com.game.multy_player_javafx.mvc.view.scene_items.Sprites;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CityController {
    String propPath = "/actions/keyList.properties";
    public static Stage stage;
    boolean notFirstTime = false;
    HashMap<String, String> commandByKey = new HashMap<>();
    JavaFxToolkit javaFxToolkit = new JavaFxToolkit();
    @FXML
    BorderPane field = new BorderPane();
    @FXML
    void initialize(){
        ClientController.sendCommandToServer("*CITY!!!");
        getKeyMap();
        userInit();
        Sprites sprites = new Sprites(field);
        ClientController.sendCommandToServer("*Yeahoo!");

        field.setOnKeyTyped(keyEvent -> {
            ClientController.sendCommandToServer(keyEvent.getCharacter());
            usualBlock(keyEvent);
        });

    }

    void getKeyMap(){
        ClientController.sendCommandToServer("*Key map taking");
        Properties properties = new Properties();
        InputStream inputStream = getClass().getResourceAsStream(propPath);

        try {
            properties.load(inputStream);
            ClientController.sendCommandToServer("*Properties loaded");
            for(Map.Entry<?, ?> elem : properties.entrySet())
                commandByKey.put((String) elem.getKey(), (String) elem.getValue());
            ClientController.sendCommandToServer("*Keys read successfully");
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

    void usualBlock(KeyEvent keyEvent){
        ClientController.sendCommandToServer("*In usual block now");
        if (commandByKey.containsKey(keyEvent.getCharacter())) {
            if (!ClientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                connectionLose();
            else {
                getKeyMap();

                if (commandByKey.containsKey(keyEvent.getCharacter()))
                    if (!ClientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                        connectionLose();
            }
        } else ClientController.sendCommandToServer("*Hmmmmm");
    }

    void userInit(){
        String inputedName = "Valentine"; // TODO: make special window for init data input
        String sex = "GIRL";
        synchronized (ClientController.clientSocket) {
            ClientController.sendCommandToServer("THREAD_" + inputedName + " " + sex);
            ClientController.sendCommandToServer("INIT");
        }
    }
}
