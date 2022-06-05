package com.game.multy_player_javafx.mvc.view.view_controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class JavaFxToolkit {
    static HashMap<String, Scene> cashData = new HashMap<>();
    public void load(){
        if(cashData.isEmpty()) {
            try {
                loadAllFXML();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static final String sceneConfigPath = "/scene/scene_list.properties";
    public void setStage(Stage primaryStage, String sceneName){
        primaryStage.setTitle("City");
        primaryStage.setScene(cashData.get(sceneName));
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setFullScreen(true);
        primaryStage.show();
    }

    void loadAllFXML() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/scene/hello-view.fxml"));
        cashData.put("START_PANEL", new Scene(root));

        root = FXMLLoader.load(getClass().getResource("/scene/loading.fxml"));
        cashData.put("LOADING_STAGE", new Scene(root));

        root = FXMLLoader.load(getClass().getResource("/scene/city.fxml"));
        cashData.put("CITY", new Scene(root));
    }
}
