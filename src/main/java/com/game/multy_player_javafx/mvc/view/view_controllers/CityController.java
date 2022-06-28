package com.game.multy_player_javafx.mvc.view.view_controllers;

import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.scene_items.Sprites;
import com.game.multy_player_javafx.mvc.view.system.LocalDisplay;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

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
    Sprites sprites;
    @FXML
    Pane surface = new Pane();
    ImageView image;
    @FXML
    void initialize(){
        getKeyMap();

        image = setImage();
        surface.getChildren().add(image);

        image.setOnMouseEntered(mouseEvent -> {
            if (notFirstTime)
                if(sprites.isRunning())
                    image.getScene().setOnKeyTyped(this::usualBlock);
                else {
                    connectionLose();
                    notFirstTime = false;
                }
            else {
                userInit();
                notFirstTime = true;
            }
        });
    }

    ImageView setImage(){
        image = new ImageView(new Image(Location.STREET.path()));

        System.out.println(LocalDisplay.width());
        System.out.println(LocalDisplay.height());
        System.out.flush();

        Location.STREET.setBiasX((LocalDisplay.width() - Location.STREET.width()) / 2);
        Location.STREET.setBiasY((LocalDisplay.height() - Location.STREET.height())/ 2);

        image.setX(Location.STREET.getBiasX());
        image.setY(Location.STREET.getBiasY());

        return image;
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
        if(keyEvent.getCharacter().equals("q") || keyEvent.getCharacter().equals("Q")) {
            ClientController.sendCommandToServer("stop");
            Platform.exit();
            System.exit(0);
        }

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
        sprites = new Sprites(surface);
        sprites.start();
    }
}
