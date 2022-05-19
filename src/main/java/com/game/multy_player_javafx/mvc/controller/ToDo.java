package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

public class ToDo {
    Actor actor;
    Task task;

    public ToDo(String actorInfo, String commandInfo) throws BadCommand {
        String actorName = "",
                taskName = "",
                taskParam = "";
        Boolean actorSex = false;
        extractInfo(actorInfo, commandInfo, actorName, actorSex, taskName, taskParam);

        actor = new Actor(actorName, actorSex);
        task = new Task(taskName, taskParam);
    }

    private void extractInfo(String actorInfo, String commandInfo, String actorName, Boolean actorSex,
                             String taskName, String taskParam) throws BadCommand {
        String[] strArr = actorInfo.split(" ");

        if(strArr.length != 2)
            throw new BadCommand(strArr.length);

        actorName = strArr[0];

        if(strArr[1].equals("girl") || strArr[1].equals("GIRL"))
            actorSex = true;

        strArr = commandInfo.split(" ");

        if(strArr.length != 2)
            throw new BadCommand(strArr.length);

        taskName = strArr[0];
        taskParam = strArr[1];
    }

    public Actor who(){
        return actor;
    }

    public Task what(){
        return  task;
    }
}
