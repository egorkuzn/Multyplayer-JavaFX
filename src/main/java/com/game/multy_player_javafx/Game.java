package com.game.multy_player_javafx;

import com.game.multy_player_javafx.mvc.controller.Command;
import com.game.multy_player_javafx.mvc.model.City;
import com.game.multy_player_javafx.mvc.model.server.Server;
import com.game.multy_player_javafx.mvc.view.LayoutManager;

public class Game {
    String pathToServer = "";
    int userLimit = 0;
    int xLimit = 500;
    int yLimit = 500;
    Server server;
    Command command;
    City model;
    LayoutManager view;
    String fileName;

    public Game(int userLimit, String pathToServer){
        this.userLimit = userLimit;
        this.pathToServer = pathToServer;

        server = new Server(userLimit, pathToServer);
    }

// ещё было бы круто перегрузить конструкторы
// как-то нужно сделать задержки, чтобы они были адекватными

    public void clientRun(){
        Boolean RUN = true;

        while(RUN) {
            command.getCommands(fileName);
            Server.sendToDo(command.sendCommand());
            view.display(Server.getView(), fileName);
        }
    }

    public void serverRun(){
        Boolean RUN = true;
        model = new City(xLimit, yLimit);

        while (RUN){
            model.setToDo(Server.getToDo());
            Server.sendView(model.getLetter());
        }
    }
}
