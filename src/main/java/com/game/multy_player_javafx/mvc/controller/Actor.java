package com.game.multy_player_javafx.mvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Logger;

public class Actor {
    static HashMap<String, Integer> hashHolder = new HashMap<>();
    Logger log = Logger.getLogger("");
    HashSet<Task> Actions = new HashSet<>();
    Boolean sex;
    String name = "";
    int number;

    Actor(String name, Boolean sex){
        this.name = name;
        this.sex = sex;

        if(!hashHolder.containsKey(name + sex))
            hashHolder.put(name + sex, hashHolder.size());
    }

    @Override
    public boolean equals(Object obj){
        if(obj != null && obj.getClass() == Actor.class) {
            Actor newActor = (Actor) obj;

            return this.name.equals(newActor.getName()) && (this.getSex() == newActor.getSex());
        } else
            return false;
    }

    @Override
    public int hashCode(){
        return hashHolder.get(name + sex);
    }

    public String getName(){
        return name;
    }

    public boolean getSex(){
        return sex;
    }
}
