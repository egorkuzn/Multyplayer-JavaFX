package com.game.multy_player_javafx;

import com.game.multy_player_javafx.mvc.controller.Command;
import com.game.multy_player_javafx.mvc.model.City;
import com.game.multy_player_javafx.mvc.model.server.Server;
import com.game.multy_player_javafx.mvc.view.Layout;

public class Game {
    String pathToServer = "";
    int userLimit = 0;
    Server server;
    Command command;
    City model;
    Layout view;
    String fileName;

    public Game(int userLimit, String pathToServer){
        this.userLimit = userLimit;
        this.pathToServer = pathToServer;

        server = new Server(userLimit, pathToServer);
    }

// ещё было бы круто перегрузить конструкторы

    public void clientRun(){
        while(RUN) {
            command.getCommands(fileName);
            Server.sendToDo(command.sendCommand());
            view.display(Server.getView());
        }
    }

    public void serverRun(){
        while (RUN){
            model.getToDo(Server.getToDo());
            Server.setView(model.getCityMask());
        }
    }
}
