package com.game.multyplayerjavafx.mvc.controller;

import java.util.HashSet;

public class Actor {
    String name;
    HashSet<Task> Actions = new HashSet<>();
    Boolean sex;

    Actor(String name, Boolean sex){
        this.name = name;
        this.sex = sex;
    }

    public String showName(){
        return name;
    }

    public void getName(String name){
        this.name = name;
    }

    public boolean getSex(){
        return sex;
    }
}
