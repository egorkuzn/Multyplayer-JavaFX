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
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class City {
    Logger log = Logger.getLogger(City.class.getName());
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
        clients = new Clients();
        log.info("\'City\' started");
    }

    private void setToDo(){
        ArrayList<ToDo> toDoList;

        toDoList = controller.sendCommands();

        if(!toDoList.isEmpty()) {
            log.info("---");
            clients.setClientsList(controller.getSockets());

            for (ToDo toDo : toDoList)
                setUniqueToDo(toDo);
        }
    }

    private void setUniqueToDo(ToDo toDo){
        if(!models.containsKey(toDo.who())) {
            log.info(String.valueOf(models.size()));
            models.put(toDo.who(), new ActiveModel(toDo.who()));
        }

        if(!models.get(toDo.who()).setToDo(toDo.what(), passive_models))
            models.remove(toDo.who());
    }

    private boolean refresh(){
        letter_from_server.clear();

        for(Map.Entry<Actor, ActiveModel> model : models.entrySet()) {
            if(!model.getValue().refresh(letter_from_server))
                return false;
        }

        return true;
    }

    private boolean sendLetter() {
        if(clients != null && !letter_from_server.isEmpty())
            return clients.send(letter_from_server);

        return true;
    }

    public void run(){
        while (RUN) {
            setToDo();
            RUN = refresh();
            RUN = sendLetter();

            try {
                TimeUnit.MILLISECONDS.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.info("That's all)))");
    }
}
