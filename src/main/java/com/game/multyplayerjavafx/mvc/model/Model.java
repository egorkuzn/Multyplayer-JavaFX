package com.game.multyplayerjavafx.mvc.model;

import com.game.multyplayerjavafx.mvc.controller.Actor;
import com.game.multyplayerjavafx.mvc.controller.Task;
import com.game.multyplayerjavafx.mvc.model.actions.Action;

public class Model {
    private String modelName;
    private ModelStatus status;
    private Action action;
    private int coordinate;

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



    public void run(Task task){
        if(status.actionList.containsKey(task.getTaskName()))
            status.actionList.get(task.getTaskName()).make(task.getParam(), coordinate);
    }
}
