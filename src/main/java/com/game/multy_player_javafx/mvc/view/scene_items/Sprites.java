package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.network_controllers.LetterReceiver;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sprites extends Thread{
    LetterReceiver letterReceiver = new LetterReceiver();
    ImageMap imageMap = new ImageMap();
    public Sprites(BorderPane field){
        field.getChildren().addAll(imageMap);
        start();
        synchronized (ClientController.clientSocket)
        {
            ClientController.sendCommandToServer("*Sprites thread started");
        }
    }

    @Override
    public void run() {
        while (letterReceiver.itWorks())
            if(letterReceiver.newLetter())
                setMap(letterReceiver.getMap());
    }

    void setMap(HashMap<String, ArrayList<Point>> map){
        for(Map.Entry<String, ArrayList<Point>> group : map.entrySet())
            for(Point coordinate : group.getValue())
                imageMap.add(group.getKey(), coordinate);

        imageMap.setAll();
    }
}
