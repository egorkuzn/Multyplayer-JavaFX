package com.game.multy_player_javafx.mvc.view.scene_items;

import javafx.scene.layout.BorderPane;

import javax.swing.*;

public class Background extends Thread{
    BorderPane field;
    public Background(BorderPane field){
        this.field = field;
        start();
    }
    @Override
    public void run(){

    }
}
