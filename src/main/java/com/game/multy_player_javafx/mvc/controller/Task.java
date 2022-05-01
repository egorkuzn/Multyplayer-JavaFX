package com.game.multy_player_javafx.mvc.controller;

public class Task {
    String param = "";
    String taskName = "";

    Task(String taskName, String param){
        this.taskName = getTaskName();
        this.param = param;
    }

    public String getTaskName(){
        return taskName;
    }

    public String getParam(){
        return param;
    }


}
