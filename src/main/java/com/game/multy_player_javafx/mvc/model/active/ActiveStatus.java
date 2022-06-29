package com.game.multy_player_javafx.mvc.model.active;

import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.PlaceInArea;

import java.util.HashMap;

public enum ActiveStatus {
    NO_STATUS,
    GIRL,
    BOY,
    CAR,
    DOG;

    // defaults energy and speed
    public int energy = 4;
    public int speed = 40;
    public HashMap<String, Action> actionList = new HashMap<>();

    public int maxWidth(){
        switch(this){
            case GIRL -> {return 108;}
            case BOY -> {return 108;}
            default -> {return 108;}
        }
    }

    public int minWidth(){
        switch (this){
            case GIRL -> {return 54;}
            case BOY -> {return 54;}
            default -> {return 108;}
        }
    }

    public int height(){
        switch(this){
            case GIRL -> {return 255;}
            case BOY -> {return 264;}
            default -> {return 255;}
        }
    }

    public int bias(PlaceInArea where) {
        switch (where){
            case TOP -> {return height() / 2;}
            case LEFT -> {return minWidth() / 2;}
            case RIGHT -> {return minWidth() / 2;}
            case BOTTOM -> {return height() / 2;}
            default -> {return 0;}
        }
    }
}
