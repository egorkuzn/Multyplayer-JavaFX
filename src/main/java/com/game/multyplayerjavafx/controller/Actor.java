package com.game.multyplayerjavafx.controller;

import java.util.HashSet;

public class Actor {
    String name;
    HashSet<Task> Actions = new HashSet<>();


    Actor(String name){
        this.name = name;
    }

    String showName(){
        return name;
    }
}
