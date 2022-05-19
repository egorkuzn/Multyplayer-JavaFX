package com.game.multy_player_javafx.mvc.model.active;

import com.game.multy_player_javafx.mvc.controller.Actor;
import com.game.multy_player_javafx.mvc.controller.Task;
import com.game.multy_player_javafx.mvc.model.passive.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.Point;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class ActiveModel {
    private String modelName;
    //первое - будет держать актуальный статус
    //второе - для возврата к прошлому актуальному
    //Пример: сейчас CAR, а до этого ты кем был? Собака? Девушка? Парень?
    private ActiveStatus[] status = new ActiveStatus[2];
    private Action action;
    private Point coordinate;
    private HashMap<Point, PassiveStatus> passive_models;
    private HashMap<String, ArrayList<Point>> letter_to_server;

    public ActiveModel(Actor actor){
        // а вообще нужно сделать нормальный рандомайзер координаты
        coordinate = new Point(0,0);
        modelName = actor.showName();
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
        InputStream inputStream = null;

        try{
            Properties prop = new Properties();
            final String propFileName = "actions." + status[0].name().toLowerCase();

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if(inputStream != null)
                prop.load(inputStream);
            else
                return false;

            inputStream.close();
            Action obj = (Action) Class.forName(taskName).getDeclaredConstructor().newInstance();
            status[0].actionList.put(taskName, obj);

            return true;
        } catch (IOException | ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setToDo(Task task, HashMap<Point, PassiveStatus> passive_models){
        this.passive_models = passive_models;

        if(status[0].actionList.containsKey(task.getTaskName()))
            action = status[0].actionList.get(task.getTaskName()).clone();
        else if(foundAtProperties(task.getTaskName()))
            action = status[0].actionList.get(task.getTaskName()).clone();
    }

    public void refresh(HashMap<String, ArrayList<Point>> letter_to_server, Boolean RUN){
        if(action != null)
            RUN = action.make(modelName, coordinate, status, passive_models, letter_to_server);

        String word = status[0].name() + action.getViewParam();
        ArrayList<Point> coordinate_list = new ArrayList<>();

        if(letter_to_server.containsKey(word))
            coordinate_list = new ArrayList<>(letter_to_server.get(word));

        coordinate_list.add(coordinate);
        letter_to_server.put(word , coordinate_list);
    }
}
