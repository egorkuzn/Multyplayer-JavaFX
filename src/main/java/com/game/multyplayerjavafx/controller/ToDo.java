package com.game.multyplayerjavafx.controller;

public class ToDo {
    Actor actor;
    Task task;
    public ToDo(String actorName, String actorTask){
        actor = new Actor(actorName);
        task = new Task(actorTask);
    }

    public Actor who(){
        return actor;
    }

    public Task what(){
        return  task;
    }
}
