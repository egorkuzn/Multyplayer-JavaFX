package com.game.multy_player_javafx.mvc.controller;

import com.game.multy_player_javafx.mvc.exceptions.BadCommand;

public class ToDo {
    Actor actor;
    Task task;
    public ToDo(String command) throws BadCommand {
        String actorName = "",
                taskName = "",
                taskParam = "";
        Boolean actorSex = false;
        extractInfo(command, actorName, actorSex, taskName, taskParam);

        actor = new Actor(actorName, actorSex);
        task = new Task(taskName, taskParam);
    }

    private void extractInfo(String command, String actorName, Boolean actorSex,
                             String taskName, String taskParam) throws BadCommand {
        String[] strArr = command.split(" ");

        if(strArr.length != 4)
            throw new BadCommand(strArr.length);
        actorName = strArr[0];

        if(strArr[1] == "girl" || strArr[1] == "GIRL")
            actorSex = true;

        taskName = strArr[2];
        taskParam = strArr[3];
    }

    public Actor who(){
        return actor;
    }

    public Task what(){
        return  task;
    }
}
