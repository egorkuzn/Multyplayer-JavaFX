package com.game.multy_player_javafx.mvc.model.active.actions.car.move;

import com.game.multy_player_javafx.mvc.model.active.ActiveStatus;
import com.game.multy_player_javafx.mvc.model.passive.area.Area;
import com.game.multy_player_javafx.mvc.model.passive.items.PassiveStatus;
import com.game.multy_player_javafx.mvc.model.active.actions.Action;
import com.game.multy_player_javafx.mvc.model.passive.area.geometry.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class MoveDown implements Action {
    int energy;
    boolean first_time;

    public MoveDown(){
        energy = 0;
        first_time = true;
    }
    @Override
    public boolean make(String name, Point coordinate, ActiveStatus[] status, HashMap<Point, PassiveStatus> passive_models, HashMap<String, ArrayList<Point>> letter_to_server, Area place) {
        if(first_time){
            energy = status[0].energy;
            first_time = false;
        }

        if(energy > 0){
            energy--;
            coordinate.Y += status[0].speed;
        }

        return true;
    }

    @Override
    public String getViewParam() {
        return "_down:" + energy;
    }

    @Override
    public Action clone() {
        return new MoveDown();
    }

    @Override
    public boolean equals(Action other) {
        return other != null && other.getClass() == MoveDown.class;
    }

    @Override
    public boolean isFinished() {
        return energy == 0;
    }
}
