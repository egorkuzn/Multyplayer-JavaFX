package com.game.multy_player_javafx;

import com.game.multy_player_javafx.mvc.controller.ClientController;
import com.game.multy_player_javafx.mvc.controller.ServerController;
import com.game.multy_player_javafx.mvc.model.City;
import com.game.multy_player_javafx.mvc.model.networking.Server;
import com.game.multy_player_javafx.mvc.view.JavaFxApplication;
import com.game.multy_player_javafx.mvc.view.ViewManager;

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
        ViewManager view = new ViewManager();
    }

    public void serverRun(){
        ServerController controller = new ServerController();
        City city = new City(controller);
        city.run();
    }
}
