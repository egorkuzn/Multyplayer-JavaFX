package com.game.multy_player_javafx.mvc.view;

import com.game.multy_player_javafx.mvc.controller.ClientController;
import com.game.multy_player_javafx.mvc.model.passive.Point;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LayoutManager extends Application{
    Point limitPoint = new Point(600,600);
    ServerReceiver server;
    ClientController controller;
    static Pane root = new Pane();
    ViewMode mode = ViewMode.GRAPHICS;
    LayoutManager(ClientController controller){
        this.controller = controller;
        this.server = new ServerReceiver();
    }

    public void display(){
        HashMap<String, Point> layout_map = server.getLayoutMap();
        for(Map.Entry<String, Point> elem: layout_map.entrySet())
            uniqueDisplay(elem);
    }

    void uniqueDisplay(Map.Entry<String, Point> elem){
        root.getChildren().add(new GraphicalTemplate(elem));
    }


    @Override
    public void start(Stage stage) throws Exception {

    }
}
