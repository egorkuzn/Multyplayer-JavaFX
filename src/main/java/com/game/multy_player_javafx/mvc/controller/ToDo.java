package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;

import java.util.logging.Logger;

public class ToDo {
    Logger log = Logger.getLogger("");
    Actor actor;
    Task task;
    String actorName;
    boolean actorSex;
    Area actorArea;

    public ToDo(String actorInfo, String commandInfo) throws BadCommand {
        actorSex = false;

        extractInfo(actorInfo);

        actor = new Actor(actorName, actorSex, actorArea);
        task = new Task(commandInfo);
    }

    private void extractInfo(String actorInfo) throws BadCommand {
        String[] strArr = actorInfo.split(" " );

        if(strArr.length != 3) {
            log.info(actorInfo);
            throw new BadCommand(strArr.length);
        }

        actorName = strArr[0];

        if(strArr[1].equals("girl") || strArr[1].equals("GIRL"))
            actorSex = true;

        switch (strArr[2]){
            case "STREET" -> actorArea = Area.STREET;
            default -> actorArea = Area.STREET;
        }
    }

    public Actor who(){
        return actor;
    }

    public Task what(){
        return  task;
    }
}
