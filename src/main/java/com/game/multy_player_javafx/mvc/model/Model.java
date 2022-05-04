package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.Task;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.util.HashMap;

public class Model {
    private String modelName;
    private ModelStatus status;
    private Action action;
    private Integer coordinate;

    public Model(Actor actor){
        modelName = actor.showName();
        setDefaultStatus(actor.getSex());
    }

    public void setDefaultStatus(Boolean sex){
        if(sex)
            status = ModelStatus.GIRL;
        else
            status = ModelStatus.BOY;
    }

    public Integer getCoordinate(){
        return coordinate;
    }

    public ModelStatus getStatus() {
        return status;
    }

    public void run(Task task){
        if(status.actionList.containsKey(task.getTaskName()))
            status.actionList.get(task.getTaskName()).make(task.getParam(), coordinate, status);
    }
}
