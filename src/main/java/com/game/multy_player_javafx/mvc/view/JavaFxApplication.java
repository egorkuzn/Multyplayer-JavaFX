package com.game.multy_player_javafx.mvc.view;

import com.game.multy_player_javafx.mvc.controller.ClientController;
import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class JavaFxApplication extends Application{
    Point limitPoint = new Point(600,600);
    ServerReceiver server;
    ViewController controller;
    Boolean RUN = true, QUIT = false;
    Button button = new Button();
    static Pane root = new Pane();
    ViewMode mode = ViewMode.GRAPHICS;

    @Override
    public void init() throws Exception {
        this.server = new ServerReceiver();
        controller = new ViewController();
    }

    void stagePrepare(Stage stage){
        stage.setTitle("City");

    }

    public void displayGameMap(Stage stage){
        while (RUN && !QUIT){
            HashMap<String, Point> layout_map = server.getLayoutMap();
            for(Map.Entry<String, Point> elem: layout_map.entrySet())
                uniqueDisplay(elem);

            stage.setScene(new Scene(root, 200, 200, Color.GREEN));
            stage.show();
        }
    }

    void uniqueDisplay(Map.Entry<String, Point> elem){
        root.getChildren().add(new GraphicalTemplate(elem));
    }


    @Override
    public void start(Stage stage) throws Exception {
        stagePrepare(stage);
        displayStartMenu(stage);
        displayGameMap(stage);
    }
}
