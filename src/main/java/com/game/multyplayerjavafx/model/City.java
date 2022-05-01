package com.game.multyplayerjavafx.model;

import com.game.multyplayerjavafx.controller.Actor;
import com.game.multyplayerjavafx.controller.ToDo;

import java.util.ArrayList;
import java.util.HashMap;

public class City {
    HashMap<Actor, Model> models = new HashMap<>();
    Mask cityMask = new Mask();

    public void getToDo(ArrayList<ToDo> toDoList){
        for (ToDo toDo: toDoList)
            run(toDo);
    }

    private void run(ToDo toDo){
        if(!models.containsKey(toDo.who()))
            models.put(toDo.who(), new Model(toDo.who()));

        models.get(toDo.who()).run(toDo.what());
    }
}
