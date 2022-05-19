package com.game.multy_player_javafx.mvc.view;

import com.game.multy_player_javafx.mvc.controller.ClientController;
import javafx.application.Application;

public class ViewManager {
    public ViewManager() {
        new Thread(){
            @Override
            public void run() {
                Application.launch(JavaFxApplication.class);
            }
        }.start();
    }
}
