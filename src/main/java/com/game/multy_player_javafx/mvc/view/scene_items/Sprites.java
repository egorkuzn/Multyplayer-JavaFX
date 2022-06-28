package com.game.multy_player_javafx.mvc.view.scene_items;

import com.game.multy_player_javafx.mvc.model.passive.area.Point;
import com.game.multy_player_javafx.mvc.view.network_controllers.ClientController;
import com.game.multy_player_javafx.mvc.view.network_controllers.LetterReceiver;
import javafx.application.Platform;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Sprites extends Thread {
    LetterReceiver letterReceiver = new LetterReceiver();
    ImageMap imageMap = new ImageMap();
    AtomicBoolean status = new AtomicBoolean(true);
    HeroChoose choosePanel = new HeroChoose(imageMap);
    NameChoose textLine = new NameChoose(imageMap);

    public Sprites(Pane field){
        field.getChildren().add(imageMap);
    }

    @Override
    public void run() {
        nameChoose();

        while (!textLine.getChooseStatus()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        heroChoose(textLine.name());

        while (!choosePanel.getChooseStatus()) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int countOfLoops = 0;

        while (!ClientController.getStatus()) {
            try {
                TimeUnit.SECONDS.sleep(1);
                countOfLoops++;

                if(countOfLoops == 10) {
                    status.set(false);
                    break;
                }
            } catch (InterruptedException e) {
                status.set(false);
                throw new RuntimeException(e);
            }
        }

        if(status.get())
            usualLetterReceiving();

        System.out.println("finished");
        System.out.flush();
    }

    void usualLetterReceiving(){
        letterReceiver.start();

        while (letterReceiver.itWorks()) {
            if (letterReceiver.newLetter()) {
                System.out.println("LETTER GOT");
                System.out.flush();
                setMap(letterReceiver.getMap());
            }
        }

        status.set(false);
        System.out.println("finished");
        System.out.flush();
    }

    void setMap(HashMap<String, ArrayList<Point>> map){
        for (Map.Entry<String, ArrayList<Point>> group : map.entrySet())
            for (Point coordinate : group.getValue()) {

                imageMap.add(group.getKey(), coordinate);
                System.out.println(coordinate.X + ";" + coordinate.Y);
                System.out.flush();
            }

        imageMap.setAll();
    }

    void heroChoose(String name){
        choosePanel.setName(name);
        Platform.runLater(choosePanel);
    }

    void nameChoose(){
        Platform.runLater(textLine);
    }

    public boolean isRunning() {
        return status.get();
    }
}
