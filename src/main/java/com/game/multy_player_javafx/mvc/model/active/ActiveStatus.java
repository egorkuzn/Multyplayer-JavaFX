package com.game.multy_player_javafx.mvc.model.active;

import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.area.PlaceInArea;
import com.game.multy_player_javafx.mvc.model.passive.area.Point;

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
            case GIRL -> {return 216;}
            case BOY -> {return 216;}
            default -> {return 216;}
        }
    }

    public int minWidth(){
        switch (this){
            case GIRL -> {return 108;}
            case BOY -> {return 108;}
            default -> {return 108;}
        }
    }

    public int height(){
        switch(this){
            case GIRL -> {return 510;}
            case BOY -> {return 528;}
            default -> {return 255;}
        }
    }

    public int bias(PlaceInArea where) {
        switch (where){
            case TOP -> {return height() / 4;}
            case LEFT -> {return minWidth() / 2;}
            case RIGHT -> {return minWidth() / 2;}
            case BOTTOM -> {return height();}
            default -> {return 0;}
        }
    }

    public String dynamicHeight(Point coordinate, Area place) {
        switch (place) {
            case STREET -> {
                double k = (coordinate.Y - 150) / place.height() * 1.3;
                int newHeight = (int) (height() * k);
                int newWidth = (int) (maxWidth() * k);

                return newHeight + ":" + newWidth;
            }

            default -> {return "0:0";}
        }
    }
}
