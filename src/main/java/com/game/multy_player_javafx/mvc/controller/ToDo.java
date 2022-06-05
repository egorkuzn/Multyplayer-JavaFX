package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

import java.util.logging.Logger;

public class ToDo {
    Logger log = Logger.getLogger("");
    Actor actor;
    Task task;
    String actorName;
    boolean actorSex;

    public ToDo(String actorInfo, String commandInfo) throws BadCommand {
        Boolean actorSex = false;

        extractInfo(actorInfo);

        actor = new Actor(actorName, actorSex);
        task = new Task(commandInfo);
    }

    private void extractInfo(String actorInfo) throws BadCommand {
        String[] strArr = actorInfo.split(" " );

        if(strArr.length != 2) {
            log.info(actorInfo);
            throw new BadCommand(strArr.length);
        }

        actorName = strArr[0];

        if(strArr[1].equals("girl") || strArr[1].equals("GIRL"))
            actorSex = true;
    }

    public Actor who(){
        return actor;
    }

    public Task what(){
        return  task;
    }
}
