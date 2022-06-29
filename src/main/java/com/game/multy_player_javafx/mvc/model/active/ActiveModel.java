package com.game.multy_player_javafx.mvc.model.active;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.Task;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.logging.Logger;

public class ActiveModel {
    Logger log = Logger.getLogger("");
    private String modelName;
    //первое - будет держать актуальный статус
    //второе - для возврата к прошлому актуальному
    //Пример: сейчас CAR, а до этого ты кем был? Собака? Девушка? Парень?
    private ActiveStatus[] status = new ActiveStatus[2];
    private Action action;
    private Area modelArea;
    private Point coordinate = new Point();
    private HashMap<Point, PassiveStatus> passive_models;
    private HashMap<String, ArrayList<Point>> letter_to_server;

    public ActiveModel(Actor actor){
        modelName = actor.getName();
        modelArea = actor.getArea();
        setDefaultStatus(actor.getSex());
    }

    public void setDefaultStatus(Boolean sex){
        if(sex)
            status[0] = ActiveStatus.GIRL;
        else
            status[0] = ActiveStatus.BOY;
    }

    public Point getCoordinate(){
        return coordinate;
    }

    public ActiveStatus getStatus() {
        return status[0];
    }

    private boolean foundAtProperties(String taskName){
        log.info("Searching at props");

        try{
            Properties prop = new Properties();
            final String propFileName = status[0].name().toLowerCase() + ".properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                return false;

            log.info(taskName);
            Action obj = (Action) Class.forName(prop.getProperty(taskName)).getConstructor().newInstance();
            inputStream.close();

            status[0].actionList.put(taskName, obj);

            log.info("FOUND!");
            return true;
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean setToDo(Task task, HashMap<Point, PassiveStatus> passive_models){
        if(task.getTaskName().equals("DELETE"))
            return false;

        log.info("todo setting");
        this.passive_models = passive_models;
        Action new_action = null;

        if(status[0].actionList.containsKey(task.getTaskName()))
            new_action = status[0].actionList.get(task.getTaskName()).clone();
        else if(foundAtProperties(task.getTaskName()))
            new_action = status[0].actionList.get(task.getTaskName()).clone();
        else log.info("TODO not found in props");

        if(action == null || !action.equals(new_action) || action.isFinished())
            action = new_action;

        return true;
    }

    public boolean refresh(HashMap<String, ArrayList<Point>> letter_to_server){
        if(action != null) {
            log.info("Refreshed");
            boolean RUN = action.make(modelName, coordinate, status, passive_models, letter_to_server, modelArea);

            String word = status[0].name() + action.getViewParam();

            if(!letter_to_server.containsKey(word))
                letter_to_server.put(word, new ArrayList<>());

            letter_to_server.get(word).add(coordinate);
            letter_to_server.put("TEXT_" + modelName, new ArrayList<>());
            letter_to_server.get("TEXT_" + modelName).add(coordinate);

            return RUN;
        }

        return true;
    }
}
