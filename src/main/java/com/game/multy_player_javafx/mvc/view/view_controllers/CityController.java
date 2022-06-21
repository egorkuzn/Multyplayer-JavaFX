package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.Sprites;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CityController {
    String propPath = "/keyList.properties";
    boolean notFirstTime = false;
    HashMap<String, String> commandByKey = new HashMap<>();
    JavaFxToolkit javaFxToolkit = new JavaFxToolkit();
    @FXML
    BorderPane surface = new BorderPane();
    @FXML
    ImageView image = new ImageView();
    @FXML
    void initialize(){
        getKeyMap();

        image.setOnMouseEntered(mouseEvent -> image.getScene().setOnKeyTyped(keyEvent -> {
            if(notFirstTime)
                usualBlock(keyEvent);
            else
                userInit();

            notFirstTime = true;
        }));
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
        LoadController.isClickable = true;
        LoadController.status = "Connection with server is lost.";
        ClientController clientController = new ClientController();
        clientController.start();
        javaFxToolkit.setStage(JavaFxApplication.stage, "LOADING_STAGE");
    }

    void usualBlock(KeyEvent keyEvent){
        if (commandByKey.containsKey(keyEvent.getCharacter())) {
            if (!ClientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                connectionLose();
        } else {
            getKeyMap();

            if (commandByKey.containsKey(keyEvent.getCharacter()))
                if (!ClientController.sendCommandToServer(commandByKey.get(keyEvent.getCharacter())))
                    connectionLose();
        }
    }

    void userInit(){
        Sprites sprites = new Sprites(surface);
        sprites.start();

        String inputedName = "Valentine"; // TODO: make special window for init data input
        String sex = "BOY";

        synchronized (ClientController.clientSocket) {
            ClientController.sendCommandToServer("THREAD_" + inputedName + " " + sex);
            ClientController.sendCommandToServer("INIT");
        }
    }
}
