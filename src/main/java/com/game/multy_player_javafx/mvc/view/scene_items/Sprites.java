package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.Point;
import com.game.multy_player_javafx.mvc.view.network_controllers.LetterReceiver;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Sprites extends Thread {
    LetterReceiver letterReceiver = new LetterReceiver();
    ImageMap imageMap = new ImageMap();
    BorderPane borderPane;
    public Sprites(BorderPane field){
        borderPane = field;
        field.getChildren().add(imageMap);
    }

    @Override
    public void run() {
        heroChoose();
        letterReceiver.start();

        while (letterReceiver.itWorks()) {
            if (letterReceiver.newLetter()) {
                System.out.println("LETTER GOT");
                System.out.flush();
                setMap(letterReceiver.getMap());
            }
        }

        System.out.println("finished");
        System.out.flush();
    }

    void setMap(HashMap<String, ArrayList<Point>> map){
        for (Map.Entry<String, ArrayList<Point>> group : map.entrySet())
            for (Point coordinate : group.getValue()) {
                System.out.println("adding");
                System.out.flush();

                imageMap.add(group.getKey(), coordinate);

                System.out.println("added");
                System.out.flush();
            }

        imageMap.setAll();
    }

    void heroChoose(){
        Platform.runLater(new HeroChoose(imageMap));
    }
}
