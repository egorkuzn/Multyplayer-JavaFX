package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.ToDo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class City {
    final int xLimit, yLimit;
    HashMap<Actor, Model> models = new HashMap<>();
    //Mask cityMask;
    letter_to_server;
    // что-то, с чем элементы могут взаимодействовать
    npc_models;

    public City(int xLimit, int yLimit){
        this.xLimit = xLimit;
        this.yLimit = yLimit;
        // сама концепция маски - фигня. cityMask = new Mask(xLimit, yLimit);
    }

    public void getToDo(ArrayList<ToDo> toDoList){
        for (ToDo toDo: toDoList)
            run(toDo);

        for(Map.Entry<Actor, Model> elem : models.entrySet()){
            // В письмо серверу добавляем по статусу: координата, действие
        }
    }

    private void run(ToDo toDo){
        if(!models.containsKey(toDo.who()))
            models.put(toDo.who(), new Model(toDo.who()));

        models.get(toDo.who()).run(toDo.what(), npc_models);
    }

    public Letter getLetter() {
        return letter_to_server;
    }
}
