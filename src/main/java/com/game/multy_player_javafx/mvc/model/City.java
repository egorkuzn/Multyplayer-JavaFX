package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class City {
    final int xLimit, yLimit;
    HashMap<Actor, ActiveModel> models = new HashMap<>();
    //Может быть не строка, а хэш, но это на будущее
    HashMap<String, ArrayList<Integer>> letter_to_server = new HashMap<>();
    HashMap<Integer, PassiveStatus> passive_models = new HashMap<>();

    public City(int xLimit, int yLimit){
        this.xLimit = xLimit;
        this.yLimit = yLimit;
    }

    public void setToDo(ArrayList<ToDo> toDoList){
        for (ToDo toDo: toDoList)
            setUniqueToDo(toDo);
    }

    private void setUniqueToDo(ToDo toDo){
        if(!models.containsKey(toDo.who()))
            models.put(toDo.who(), new ActiveModel(toDo.who()));

        models.get(toDo.who()).setToDo(toDo.what(), passive_models);
    }

    public void run(){
        letter_to_server.clear();
        //как-то надо параллелить
        for(Map.Entry<Actor, ActiveModel> model : models.entrySet())
            model.getValue().refresh(letter_to_server);
    }

    public HashMap<String, ArrayList<Integer>> getLetter() {
        return letter_to_server;
    }
}
