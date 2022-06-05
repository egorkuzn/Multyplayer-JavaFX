package com.game.multy_player_javafx.mvc.controller;

public class Task {
    String taskName = "";

    Task(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName(){
        return taskName;
    }
}
