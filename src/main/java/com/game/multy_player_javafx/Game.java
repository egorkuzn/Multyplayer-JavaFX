package com.game.multy_player_javafx;

import com.game.multy_player_javafx.mvc.controller.ServerController;
import com.game.multy_player_javafx.mvc.model.City;
import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import javafx.application.Application;

public class Game {
    int userLimit;
    String pathToServer;
    public Game(int userLimit, String pathToServer){
        this.userLimit = userLimit;
        this.pathToServer = pathToServer;
    }

// ещё было бы круто перегрузить конструкторы
// как-то нужно сделать задержки, чтобы они были адекватными

    public void clientRun(){
        new Thread(){
            @Override
            public void run() {
                Application.launch(JavaFxApplication.class, new String[]{});
            }
        }.start();
    }

    public void serverRun(){
        ServerController controller = new ServerController();
        City city = new City(controller);
        city.run();
    }
}
