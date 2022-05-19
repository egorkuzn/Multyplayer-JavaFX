package com.game.multy_player_javafx.mvc.view;

import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class ViewController extends Thread{
    Boolean RUN, QUIT;
    public ViewController(Boolean RUN, Boolean QUIT){
        this.RUN = RUN;
        this.QUIT = QUIT;
        start();
    }

    @Override
    public void run() {
        Button button = new Button();

        while (RUN && !QUIT){
            button.setOnKeyPressed(keyEvent -> );
        }
    }
}
