package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.ServerController;
import com.game.multy_player_javafx.mvc.controller.ToDo;
import com.game.multy_player_javafx.mvc.model.active.ActiveModel;
import com.game.multy_player_javafx.mvc.model.networking.Clients;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class City {
    Boolean RUN;
    final Point spaceSize;
    HashMap<Actor, ActiveModel> models = new HashMap<>();
    //Может быть не строка, а хэш, но это на будущее
    HashMap<String, ArrayList<Point>> letter_from_server = new HashMap<>();
    HashMap<Point, PassiveStatus> passive_models = new HashMap<>();
    ServerController controller;
    Clients clients;

    public City(ServerController controller){
        this.controller = controller;
        RUN = controller.RUN;
        spaceSize = controller.initSizeLimit();
        clients = controller.initClients();
    }

    private void setToDo(){
        ArrayList<ToDo> toDoList = controller.sendCommands();
        Clients.setClientsList(controller.getSockets());

        for (ToDo toDo: toDoList)
            setUniqueToDo(toDo);
    }

    private void setUniqueToDo(ToDo toDo){
        if(!models.containsKey(toDo.who()))
            models.put(toDo.who(), new ActiveModel(toDo.who()));

        models.get(toDo.who()).setToDo(toDo.what(), passive_models);
    }

    private void refresh(Boolean RUN){
        letter_from_server.clear();
        for(Map.Entry<Actor, ActiveModel> model : models.entrySet())
            model.getValue().refresh(letter_from_server, RUN);
    }

    private void sendLetter(Boolean RUN) {
           clients.send(letter_from_server, RUN);
    }

    public void run(){
        while (RUN) {
            setToDo();
            refresh(RUN);
            sendLetter(RUN);
        }
    }
}
