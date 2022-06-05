package com.game.multy_player_javafx.mvc.controller;

import java.util.HashSet;
import java.util.logging.Logger;

public class Actor {
    Logger log = Logger.getLogger("");
    HashSet<Task> Actions = new HashSet<>();
    Boolean sex;
    String name = "";

    Actor(String name, Boolean sex){
        this.name = name;
        this.sex = sex;
    }

    public String showName(){
        return name;
    }

    public boolean getSex(){
        return sex;
    }
}
