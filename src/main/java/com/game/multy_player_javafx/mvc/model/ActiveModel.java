package com.game.multy_player_javafx.mvc.model;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.Task;
import com.game.multy_player_javafx.mvc.model.actions.Action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

public class ActiveModel {
    private String modelName;
    private ActiveStatus status;
    private Action action;
    private Integer coordinate;
    private HashMap<Integer, PassiveStatus> passive_models;
    private HashMap<String, ArrayList<Integer>> letter_to_server;

    public ActiveModel(Actor actor){
        modelName = actor.showName();
        setDefaultStatus(actor.getSex());
    }

    public void setDefaultStatus(Boolean sex){
        if(sex)
            status = ActiveStatus.GIRL;
        else
            status = ActiveStatus.BOY;
    }

    public Integer getCoordinate(){
        return coordinate;
    }

    public ActiveStatus getStatus() {
        return status;
    }

    private boolean foundAtProperties(String taskName){
        InputStream inputStream = null;

        try{
            Properties prop = new Properties();
            final String propFileName = "actions." + status.name().toLowerCase();

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                return false;

            Action obj = (Action) Class.forName(taskName).getDeclaredConstructor().newInstance();
            status.actionList.put(taskName, obj);

            return true;
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setToDo(Task task, HashMap<Integer, PassiveStatus> passive_models){
        this.passive_models = passive_models;
        this.letter_to_server = letter_to_server;

        if(status.actionList.containsKey(task.getTaskName()))
            action = status.actionList.get(task.getTaskName()).clone();
        else if(foundAtProperties(task.getTaskName()))
            action = status.actionList.get(task.getTaskName()).clone();
    }

    public void refresh(HashMap<String, ArrayList<Integer>> letter_to_server){
        letter_to_server.get(status).remove(coordinate);

        if(action != null)
            action.make(modelName, coordinate, status, passive_models, letter_to_server);

        letter_to_server.get(status).add(coordinate);
    }
}
