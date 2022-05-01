package com.game.multyplayerjavafx.model;

import com.game.multyplayerjavafx.controller.Actor;
import com.game.multyplayerjavafx.controller.Task;
import com.game.multyplayerjavafx.model.actions.Action;

public class Model {
    String modelName;
    ModelStatus status;
    Action action;
    Model(Actor actor){
        modelName = actor.modelName;
        setDefaultStatus(actor.sex());
    }

    void setDefaultStatus(Boolean sex){
        if(sex)
            status = ModelStatus.GIRL;
        else
            status = ModelStatus.BOY;
    }
    void run(Task task){
        if(status.actionList.containsKey(task.getTaskName()))
            status.actionList.get(task.getTaskName()).make(task.param());
    }
}
